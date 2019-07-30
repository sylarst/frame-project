package com.age.mac.frameproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.age.mac.baselibrary.base.BaseActivity;
import com.age.mac.frameproject.R;
import com.age.mac.frameproject.service.EventHandlingService;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.hg.uselibrary.ArouterConfig;
import com.example.hg.uselibrary.EventPostService;

import butterknife.OnClick;

@Route(path = ArouterConfig.List_Activity)
public class ListActivity extends BaseActivity {

    @OnClick(R.id.id0)
    void click0(){
        ARouter.getInstance().build(ArouterConfig.Apply_Activity).withString("data","test").navigation();
    }

    @OnClick(R.id.id1)
    void click1(){
        ARouter.getInstance().build(ArouterConfig.Main_Activity).navigation();
    }

    @OnClick(R.id.id2)
    void click2(){
        ARouter.getInstance().build(ArouterConfig.Login_loginActivity).navigation();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=new Intent(this, EventPostService.class);
        startService(intent);

        Intent intent2=new Intent(this, EventHandlingService.class);
        startService(intent2);
    }

    @Override
    public int getLayoutId() {
        return R.layout.list_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent=new Intent(this, EventPostService.class);
        stopService(intent);

        Intent intent2=new Intent(this, EventHandlingService.class);
        stopService(intent2);
    }
}
