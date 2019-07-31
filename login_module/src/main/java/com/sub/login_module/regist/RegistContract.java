package com.sub.login_module.regist;

import com.age.mac.baselibrary.base.BaseView;
import com.age.mac.baselibrary.bean.BaseObjectBean;
import com.sub.login_module.login.LoginBean;

import org.json.JSONObject;

import io.reactivex.Flowable;
import retrofit2.http.Body;

/**
 * Created by mac on 2019/7/31.
 */

public interface RegistContract {
    interface Model{
        Flowable<BaseObjectBean<RegistBean>> regist(@Body JSONObject params);

        Flowable<BaseObjectBean> sendCode(@Body JSONObject params);
    }

    interface View extends BaseView{
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onRegistSuccess(BaseObjectBean<RegistBean> bean);

        void onSendCodeSuccess(BaseObjectBean bean);
    }

    interface Presenter {
        /**
         * 注册
         *
         */
        void regist(String username, String password,String sendCode);

        /**
         * 获取验证吗
         *
         */
        void sendCode(String mobile,String smsType);
    }
}
