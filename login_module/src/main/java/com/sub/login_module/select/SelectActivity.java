package com.sub.login_module.select;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.age.mac.baselibrary.utils.pickerview.PickerEntity;
import com.age.mac.baselibrary.utils.pickerview.PickerViewControler;
import com.age.mac.baselibrary.utils.pickerview.PickerViewListener;
import com.sub.login_module.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by mac on 2019/8/5.
 */

public class SelectActivity extends Activity {
    private TextView hobbyTv;//选择爱好

    private PickerViewControler mControler;
    private PickerViewControler mTimerPicker;

    private TextView startdateTv;//开始日期


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selsect_layout);

        initViews();
        initEvents();
    }

    private void initViews() {
        hobbyTv = findViewById(R.id.hobbyTv);
        startdateTv = findViewById(R.id.startdateTv);

        RelativeLayout rly=findViewById(R.id.activity_rootview);
        PickerEntity entity=new PickerEntity();
        entity.setCancleName("取消");
        entity.setConfirmName("确认");
        entity.setName("仓库编号");
        entity.setType(PickerViewControler.PICKER_VIEW);
        List<String> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add("仓库"+i+"号");
        }
        entity.setData(list);

        mControler=new PickerViewControler(this, entity, rly, new PickerViewListener() {
            @Override
            public void getPickerResult(String result, int type) {
                hobbyTv.setText("=="+result);
            }
        });

        PickerEntity timerEntity=new PickerEntity();
        timerEntity.setName("时间");
        timerEntity.setConfirmName("确定");
        timerEntity.setCancleName("取消");
        timerEntity.setType(PickerViewControler.TIMER_VIEW);

        mTimerPicker=new PickerViewControler(this, timerEntity, rly, new PickerViewListener() {
            @Override
            public void getPickerResult(String result, int type) {
                startdateTv.setText("=="+result);
            }
        });
    }

    private void initEvents() {
        //选择爱好的下拉菜单点击事件
        hobbyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mControler.show();
            }
        });

        //开始日期的下拉菜单点击事件
        startdateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimerPicker.show();
            }
        });
    }

}
