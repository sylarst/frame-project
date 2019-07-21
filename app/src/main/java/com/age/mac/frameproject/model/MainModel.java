package com.age.mac.frameproject.model;

import com.age.mac.baselibrary.bean.BaseObjectBean;
import com.age.mac.baselibrary.network.RetrofitClient;
import com.age.mac.frameproject.bean.LoginBean;
import com.age.mac.frameproject.contract.MainContract;

import org.json.JSONObject;

import io.reactivex.Flowable;
import network.APIService;
import retrofit2.http.Body;

/**
 * Created by mac on 2019/7/16.
 */

public class MainModel implements MainContract.Model {

    @Override
    public Flowable<BaseObjectBean<LoginBean>> login(@Body JSONObject params) {
        return RetrofitClient.getInstance().getRetrofit().create(APIService.class).login(params);
    }
}
