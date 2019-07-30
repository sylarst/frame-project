package com.sub.login_module.login;

import com.age.mac.baselibrary.bean.BaseObjectBean;
import com.age.mac.baselibrary.network.RetrofitClient;
import com.sub.login_module.network.LoginAPIService;

import org.json.JSONObject;

import io.reactivex.Flowable;
import retrofit2.http.Body;

public class LoginModel implements LoginContract.Model{
    @Override
    public Flowable<BaseObjectBean<LoginBean>> login(@Body JSONObject params) {
        return RetrofitClient.getInstance().getRetrofit().create(LoginAPIService.class).login(params);
    }
}
