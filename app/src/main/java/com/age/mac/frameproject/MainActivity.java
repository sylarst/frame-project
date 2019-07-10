package com.age.mac.frameproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.age.mac.baselibrary.utils.ArouterConfig;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;


@Route(path = ArouterConfig.Main_Activity)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ARouter.getInstance().build(ArouterConfig.Main_Test).navigation();
        ARouter.getInstance().build(ArouterConfig.Apply_Activity).navigation();
    }
}
