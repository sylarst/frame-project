package com.sub.login_module.login;

import com.age.mac.baselibrary.base.BaseView;
import com.age.mac.baselibrary.bean.BaseObjectBean;

import org.json.JSONObject;

import io.reactivex.Flowable;
import retrofit2.http.Body;

public interface LoginContract {
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
