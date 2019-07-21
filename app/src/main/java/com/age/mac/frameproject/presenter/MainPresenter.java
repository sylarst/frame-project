package com.age.mac.frameproject.presenter;

import com.age.mac.baselibrary.base.BasePresenter;
import com.age.mac.baselibrary.bean.BaseObjectBean;
import com.age.mac.baselibrary.network.RxScheduler;
import com.age.mac.frameproject.bean.LoginBean;
import com.age.mac.frameproject.contract.MainContract;
import com.age.mac.frameproject.model.MainModel;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.functions.Consumer;

/**
 * Created by mac on 2019/7/16.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private MainContract.Model model;

    public MainPresenter() {
        model = new MainModel();
    }

    @Override
    public void login(String username, String password) {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }

        JSONObject params = new JSONObject();
        try {
            params.put("pushKey", "key_push_client_id");
            params.put("loginSource", "1");
            params.put("mobile", username);
            params.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mView.showLoading();
        model.login(params)
                .compose(RxScheduler.<BaseObjectBean<LoginBean>>Flo_io_main())
                .as(mView.<BaseObjectBean<LoginBean>>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean<LoginBean>>() {
                    @Override
                    public void accept(BaseObjectBean<LoginBean> bean) throws Exception {
                        mView.onSuccess(bean);
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
