package com.sub.login_module.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.age.mac.baselibrary.base.BaseMvpActivity;
import com.age.mac.baselibrary.bean.BaseObjectBean;
import com.age.mac.baselibrary.utils.LLog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.hg.uselibrary.ArouterConfig;
import com.sub.login_module.R;

@Route(path = ArouterConfig.Login_loginActivity)
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {

    EditText mNameTv;
    EditText mPasswordTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);
        mNameTv=findViewById(R.id.av_main_name);
        mPasswordTv=findViewById(R.id.av_main_password);
        findViewById(R.id.login_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.login(mNameTv.getText().toString(),mPasswordTv.getText().toString());
            }
        });
        findViewById(R.id.login_regist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ArouterConfig.Login_registActivity).navigation();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.login_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onSuccess(BaseObjectBean<LoginBean> bean) {
        LLog.e(bean.getErrorMsg());
//        ARouter.getInstance().build(ArouterConfig.Apply_Activity).navigation();
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
}
