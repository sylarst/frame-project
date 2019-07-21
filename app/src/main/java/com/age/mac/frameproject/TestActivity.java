package com.age.mac.frameproject;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.age.mac.baselibrary.base.ArouterConfig;
import com.age.mac.baselibrary.base.BaseActivity;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by mac on 2019/7/10.
 */

@Route(path = ArouterConfig.Main_Test)
public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }
}
