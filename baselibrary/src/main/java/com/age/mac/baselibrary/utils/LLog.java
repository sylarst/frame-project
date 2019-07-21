package com.age.mac.baselibrary.utils;

import android.util.Log;

import com.age.mac.baselibrary.BuildConfig;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mac on 2019/7/11.
 */

public class LLog {

    public static final boolean PRINT_LOG = BuildConfig.DEBUG;

    public static void i(String msg) {
        try {
            if (!PRINT_LOG) return;
            String[] info = getStackInfo();
            Log.i(info[0], msg + info[1]);
        } catch (Exception e) {
        }
    }

    public static void d(String msg) {
        try {
            if (!PRINT_LOG) return;
            String[] info = getStackInfo();
            Log.d(info[0], msg + info[1]);
        } catch (Exception e) {
        }
    }

    public static void v(String msg) {
        try {
            if (!PRINT_LOG) return;
            String[] info = getStackInfo();
            Log.v(info[0], msg + info[1]);
        } catch (Exception e) {
        }
    }

    public static void w(String msg) {
        try {
            if (!PRINT_LOG) return;
            String[] info = getStackInfo();
            Log.w(info[0], msg + info[1]);
        } catch (Exception e) {
        }
    }

    public static void e(String msg) {
        try {
            if (!PRINT_LOG) return;
            String[] info = getStackInfo();
            Log.e(info[0], msg + info[1]);
        } catch (Exception e) {
        }
    }
    public static void e(String msg,Throwable throwable){
        try {
            if (!PRINT_LOG) return;
            String[] info = getStackInfo();
            Log.e(info[0], msg + info[1], throwable);
        } catch (Exception e) {
        }
    }

    public static void json(Object msg) {
        try {
            if (!PRINT_LOG) return;
            String str = null;
            if (msg instanceof JSONObject) {
                str = ((JSONObject)msg).toString(4);
            } else if (msg instanceof JSONArray) {
                str = ((JSONArray)msg).toString(4);
            } else {
                str = msg == null ? "" : msg.toString();
                if (str.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(str);
                    str = jsonObject.toString(4);
                } else if (str.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(str);
                    str = jsonArray.toString(4);
                }
            }

            String[] info = getStackInfo();

            int maxLength = 3000;

            while (str != null && str.length() > 0) {
                if (str.length() <= maxLength) {
                    Log.i(info[0], str + info[1]);
                    str = null;
                } else {
                    Log.i(info[0], str.substring(0, maxLength));
                    str = str.substring(maxLength);
                }
            }
        } catch (Exception e) {
        }
    }

    private static String[] getStackInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null || stackTrace.length < 5) return new String[]{"LLog", ""};
        int index = 4;
        String className = stackTrace[index].getFileName();
//		String methodName = stackTrace[index].getMethodName();
        int lineNumber = stackTrace[index].getLineNumber();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(")
                .append(className)
                .append(":")
                .append(lineNumber)
                .append(")");

        return new String[]{"LLog/"+className, stringBuilder.toString()};
    }
}
