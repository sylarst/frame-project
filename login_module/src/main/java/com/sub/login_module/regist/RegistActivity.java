package com.sub.login_module.regist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.age.mac.baselibrary.base.BaseMvpActivity;
import com.age.mac.baselibrary.bean.BaseObjectBean;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.hg.uselibrary.ArouterConfig;
import com.sub.login_module.R;

/**
 * Created by mac on 2019/7/31.
 */

@Route(path = ArouterConfig.Login_registActivity)
public class RegistActivity extends BaseMvpActivity<RegistPresenter> implements RegistContract.View ,View.OnClickListener{

    private EditText mName;
    private EditText mPassword;
    private EditText mSendCode;
    private Button mButton;
    private Button mGetCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=new RegistPresenter();
        mPresenter.attachView(this);
        mName=findViewById(R.id.regist_name);
        mPassword=findViewById(R.id.regist_password);
        mSendCode=findViewById(R.id.regist_sendcode);
        mButton=findViewById(R.id.regist_button);
        mGetCode=findViewById(R.id.regist_getcode);
        mButton.setOnClickListener(this);
        mGetCode.setOnClickListener(this);
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
    public void onRegistSuccess(BaseObjectBean<RegistBean> bean) {

    }

    @Override
    public void onSendCodeSuccess(BaseObjectBean bean) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.regist_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.regist_getcode) {
            mPresenter.sendCode(mName.getText().toString().trim(),"1");
        } else if (i == R.id.regist_button) {
            mPresenter.regist(mName.getText().toString(),mPassword.getText().toString(),mSendCode.getText().toString());
        }
    }
}
