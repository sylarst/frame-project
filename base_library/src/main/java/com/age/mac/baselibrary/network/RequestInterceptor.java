package com.age.mac.baselibrary.network;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.EOFException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


public class RequestInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }


    public static JSONObject getHeaderInfo() throws JSONException {
        // 获取当前手机的品牌和型号：
        String brand = Build.BRAND;
        brand = brand.replaceAll(" ", "_");

        String model = Build.MODEL;
        model = model.replaceAll(" ", "_");

//        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        JSONObject headerInfo = new JSONObject();

        headerInfo.put("plat", "android");
        headerInfo.put("appv", "1.2.0");
        headerInfo.put("apiv", "1");
        headerInfo.put("appid", "com.shenghetiyu.selltool");
        headerInfo.put("mac", "02:00:00:00:00:00");
        headerInfo.put("w", "1080");
        headerInfo.put("h", "1920");
        headerInfo.put("os", Build.VERSION.RELEASE);
        headerInfo.put("mid", model);
        headerInfo.put("brand", brand);
        headerInfo.put("appCodeName", "11");
        headerInfo.put("net", "3G");
        headerInfo.put("channel", "c10021");
        headerInfo.put("version", "1.2.0");
        headerInfo.put("androidid", "d2b40272287c4080");
        headerInfo.put("lon", "0");
        headerInfo.put("lat", "0");

        return headerInfo;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.header("Content-Type", "application/json; charset=utf-8");
        builder.header("Accept-Encoding", "gzip, deflate");
        builder.header("Connection", "keep-alive");
//        String token = CommonApp.getToken();
//        if (!TextUtils.isEmpty(token))
            builder.header("token", "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxZTFkNGYyNjUtZTcyZC00ZDM2LWEzYjAtNGIwZmViY2Y4Mzg3IiwidXNlcklkIjoiNDcwNDkxIn0.4n_wpK1mxUJRnNlA2Hi-gVPmelJtdfe-jQxn4hjpZdnDfR0rxaWB_TsZs_ykx-TgH2olRPwPQ6rFyRiOVdpCOqQSfRlwRpxAELKho76oxnukjG0x3cicun3-vjunaePkAOLsnx9QyzAX0PLbSgRIcypJxbbwg6Khw7EMQqFiZ9E");
        builder.header("Accept", "*/*");

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        String requestStartMessage = "--> " + request.method() + ' ' + request.url();
//        Timber.d(requestStartMessage);


        JSONObject headerInfo = null;
        try {
            headerInfo = getHeaderInfo();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject params = new JSONObject();
        try {
            params.put("device", headerInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (hasRequestBody) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            if (isPlaintext(buffer)) {
                String strBody = buffer.readString(UTF8);
//                Timber.d("strBody " + strBody);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(strBody);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    params.put("body",jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

//        Timber.d(params.toJSONString());
//        if (requestBody.contentType() != null) {
//            Timber.d("Content-Type: " + requestBody.contentType());
//        }
//        if (requestBody.contentLength() != -1) {
//            Timber.d("Content-Length: " + requestBody.contentLength());
//        }

        request = builder.post(RequestBody.create(requestBody.contentType(),params.toString())).build();

//        request = builder.post(requestBody).build();

        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            throw e;
        }
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
//        Timber.d("<-- " + response.code() + ' ' + response.message() + ' '
//                + response.request().url() + " (" + bodySize + " body" + ')');

//        Headers headers = response.headers();
//        for (int i = 0, count = headers.size(); i < count; i++) {
//            Timber.d(headers.name(i) + ": " + headers.value(i));
//        }

//        BufferedSource source = responseBody.source();
//        source.request(Long.MAX_VALUE); // Buffer the entire body.
//        Buffer buffer = source.buffer();
//
//        Charset charset = UTF8;
//        MediaType contentType = responseBody.contentType();
//        if (contentType != null) {
//            try {
//                charset = contentType.charset(UTF8);
//            } catch (UnsupportedCharsetException e) {
//                Timber.d("");
//                Timber.d("Couldn't decode the response body; charset is likely malformed.");
//                Timber.d("<-- END HTTP");
//
//                return response;
//            }
//        }
//        if (!isPlaintext(buffer)) {
//            Timber.d("");
//            Timber.d("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");
//            return response;
//        }
//
//        if (contentLength != 0) {
//            Timber.d("");
//            Timber.d(buffer.clone().readString(charset));
//        }
//
//        Timber.d("<-- END HTTP (" + buffer.size() + "-byte body)");

        return response;
    }


}
