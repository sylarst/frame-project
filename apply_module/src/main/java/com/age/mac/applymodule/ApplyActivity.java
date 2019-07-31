package com.age.mac.applymodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.age.mac.baselibrary.base.CommonEvent;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.hg.uselibrary.ArouterConfig;
import com.example.hg.uselibrary.ModuleRequestConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by mac on 2019/7/10.
 */
@Route(path = ArouterConfig.Apply_Activity)
public class ApplyActivity extends AppCompatActivity {

    //--arouter传递过来的数据
    @Autowired(name = "data")
    public String data;

    private TextView apply0;
    private Button apply1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.apply_layout);
        apply0=findViewById(R.id.apply0);
        apply0.setText(data);
        apply1=findViewById(R.id.apply1);
        EventBus.getDefault().register(this);
        apply1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //----发出event请求，相关module的控制器处理数据，封装并返回
                CommonEvent evt=new CommonEvent(ModuleRequestConfig.APPLY_REQUEST);
                EventBus.getDefault().post(evt);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPostEvent(CommonEvent event) {
        switch (event.getmWhat()){
            case ModuleRequestConfig.APPLY_REQSPONSE: //相关module处理完了数据，返回过来
                Toast.makeText(getApplicationContext(),"111",Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
