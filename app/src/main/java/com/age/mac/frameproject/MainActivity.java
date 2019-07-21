package com.age.mac.frameproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.age.mac.baselibrary.base.ArouterConfig;
import com.age.mac.baselibrary.base.BaseActivity;
import com.age.mac.baselibrary.base.BaseMvpActivity;
import com.age.mac.baselibrary.base.CommonEvent;
import com.age.mac.baselibrary.bean.BaseObjectBean;
import com.age.mac.baselibrary.utils.LLog;
import com.age.mac.frameproject.bean.LoginBean;
import com.age.mac.frameproject.contract.MainContract;
import com.age.mac.frameproject.presenter.MainPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = ArouterConfig.Main_Activity)
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.av_main_name)
    EditText mNameTv;
    @BindView(R.id.av_main_password)
    EditText mPasswordTv;

    @OnClick(R.id.login_confirm)
    public void confirm(){
        mPresenter.login(mNameTv.getText().toString(),mPasswordTv.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ARouter.getInstance().build(ArouterConfig.Main_Test).navigation();
//        ARouter.getInstance().build(ArouterConfig.Apply_Activity).navigation();
        mPresenter = new MainPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void onPostEvent(CommonEvent event) {
        super.onPostEvent(event);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onSuccess(BaseObjectBean<LoginBean> bean) {
        LLog.e(bean.getErrorMsg());
        ARouter.getInstance().build(ArouterConfig.Apply_Activity).navigation();
    }
}
