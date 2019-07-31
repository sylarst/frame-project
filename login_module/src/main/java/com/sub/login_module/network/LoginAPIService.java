package com.sub.login_module.network;

import com.age.mac.baselibrary.bean.BaseObjectBean;
import com.sub.login_module.login.LoginBean;
import com.sub.login_module.regist.RegistBean;

import org.json.JSONObject;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPIService {
    /**
     * 登陆
     * @return
     */
    @POST("member/login/loginByPass")
    Flowable<BaseObjectBean<LoginBean>> login(@Body JSONObject params);

    /**
     * 注册
     * @return
     */
    @POST("member/user/register")
    Flowable<BaseObjectBean<RegistBean>> regist(@Body JSONObject params);

    /**
     * 获取验证吗
     * @return
     */
    @POST("member/sms/sendSmsCode")
    Flowable<BaseObjectBean> sendCode(@Body JSONObject params);
}
