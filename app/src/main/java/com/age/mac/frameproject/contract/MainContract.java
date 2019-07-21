package com.age.mac.frameproject.contract;

import com.age.mac.baselibrary.base.BaseView;
import com.age.mac.baselibrary.bean.BaseObjectBean;
import com.age.mac.frameproject.bean.LoginBean;

import org.json.JSONObject;

import io.reactivex.Flowable;
import retrofit2.http.Body;

/**
 * Created by mac on 2019/7/16.
 */

public interface MainContract {
    interface Model {
        Flowable<BaseObjectBean<LoginBean>> login(@Body JSONObject params);
    }

    interface View extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean<LoginBean> bean);
    }

    interface Presenter {
        /**
         * 登陆
         *
         * @param username
         * @param password
         */
        void login(String username, String password);
    }
}