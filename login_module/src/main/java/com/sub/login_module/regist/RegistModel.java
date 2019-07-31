package com.sub.login_module.regist;

import com.age.mac.baselibrary.bean.BaseObjectBean;
import com.age.mac.baselibrary.network.RetrofitClient;
import com.sub.login_module.network.LoginAPIService;

import org.json.JSONObject;

import io.reactivex.Flowable;

/**
 * Created by mac on 2019/7/31.
 */

public class RegistModel implements RegistContract.Model{
    @Override
    public Flowable<BaseObjectBean<RegistBean>> regist(JSONObject params) {
        return RetrofitClient.getInstance().getRetrofit().create(LoginAPIService.class).regist(params);
    }

    @Override
    public Flowable<BaseObjectBean> sendCode(JSONObject params) {
        return RetrofitClient.getInstance().getRetrofit().create(LoginAPIService.class).sendCode(params);
    }
}
