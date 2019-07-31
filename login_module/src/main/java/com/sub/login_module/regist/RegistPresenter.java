package com.sub.login_module.regist;

import com.age.mac.baselibrary.base.BasePresenter;
import com.age.mac.baselibrary.bean.BaseObjectBean;
import com.age.mac.baselibrary.network.RxScheduler;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.functions.Consumer;

/**
 * Created by mac on 2019/7/31.
 */

public class RegistPresenter extends BasePresenter<RegistContract.View> implements RegistContract.Presenter{
    private RegistContract.Model model;

    public RegistPresenter() {
        model = new RegistModel();
    }

    @Override
    public void regist(String username, String password,String code) {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }

        JSONObject params = new JSONObject();
        try {
            params.put("loginSource", "1");
            params.put("mobile", username);
            params.put("passWord", password);
            params.put("smsCode", code);
            params.put("pushKey", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mView.showLoading();
        model.regist(params)
                .compose(RxScheduler.<BaseObjectBean<RegistBean>>Flo_io_main())
//                .as(mView.<BaseObjectBean<LoginBean>>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean<RegistBean>>() {
                    @Override
                    public void accept(BaseObjectBean<RegistBean> bean) throws Exception {
                        mView.onRegistSuccess(bean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void sendCode(String mobile, String smsType) {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        JSONObject params = new JSONObject();
        try {
            params.put("smsType", smsType);
            params.put("mobile", mobile);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mView.showLoading();
        model.sendCode(params)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
//                .as(mView.<BaseObjectBean<LoginBean>>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSendCodeSuccess(bean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
                        mView.hideLoading();
                    }
                });
    }

}
