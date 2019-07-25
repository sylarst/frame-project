package com.age.mac.applymodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.age.mac.baselibrary.base.ArouterConfig;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by mac on 2019/7/10.
 */
@Route(path = ArouterConfig.Apply_Activity)
public class ApplyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_layout);
    }
}
