package com.age.mac.baselibrary.utils.pickerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.age.mac.baselibrary.R;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mac on 2019/8/5.
 */

public class PickerViewControler {

    public static final int PICKER_VIEW=0;
    public static final int TIMER_VIEW=1;

    private Context mContext;
    PickerViewListener listener;
    private int mType;
    private PickerEntity mEntity;
    private RelativeLayout mRootView;
    private OptionsPickerView mPickerView;//选择器
    private TimePickerView mStartDatePickerView;//选择时间

    public PickerViewControler(Context context,PickerEntity entity,ViewGroup rootView,PickerViewListener listener){
        mContext=context;
        this.mEntity=entity;
        mType=entity.getType();
        this.listener=listener;
        mRootView= (RelativeLayout) rootView;
        switch (mType){
            case PICKER_VIEW:
                initData();
                break;
            case TIMER_VIEW:
                initStartTimePicker();
                break;
        }

    }

    private void initData(){
        mPickerView=new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                listener.getPickerResult(options1+"",mType);
            }
        })       .setDecorView(mRootView)//必须是RelativeLayout，不设置setDecorView的话，底部虚拟导航栏会显示在弹出的选择器区域
                .setTitleText(mEntity.getName())//标题文字
                .setTitleSize(20)//标题文字大小
                .setTitleColor(mContext.getResources().getColor(R.color.pickerview_title_text_color))//标题文字颜色
                .setCancelText(mEntity.getCancleName())//取消按钮文字
                .setCancelColor(mContext.getResources().getColor(R.color.pickerview_cancel_text_color))//取消按钮文字颜色
                .setSubmitText(mEntity.getConfirmName())//确认按钮文字
                .setSubmitColor(mContext.getResources().getColor(R.color.pickerview_submit_text_color))//确定按钮文字颜色
                .setContentTextSize(20)//滚轮文字大小
                .setTextColorCenter(mContext.getResources().getColor(R.color.pickerview_center_text_color))//设置选中文本的颜色值
                .setLineSpacingMultiplier(1.8f)//行间距
                .setDividerColor(mContext.getResources().getColor(R.color.pickerview_divider_color))//设置分割线的颜色
                .setSelectOptions(0)//设置选择的值
                .build();
        mPickerView.setPicker(mEntity.getData());
    }

    /**初始化开始日期选择器控件*/
    private void initStartTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        //设置最小日期和最大日期
        Calendar startDate = Calendar.getInstance();
        Date date=new Date(0);
        startDate.setTime(date);//设置为2006年4月28日
        Calendar endDate = Calendar.getInstance();//最大日期是今天

        //时间选择器
        mStartDatePickerView = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // HH ~ 24小时制
                listener.getPickerResult(sdf1.format(date),mType);
            }
        })
                .setDecorView(mRootView)//必须是RelativeLayout，不设置setDecorView的话，底部虚拟导航栏会显示在弹出的选择器区域
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)//是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setTitleText("开始日期")//标题文字
                .setTitleSize(20)//标题文字大小
                .setTitleColor(mContext.getResources().getColor(R.color.pickerview_title_text_color))//标题文字颜色
                .setCancelText("取消")//取消按钮文字
                .setCancelColor(mContext.getResources().getColor(R.color.pickerview_cancel_text_color))//取消按钮文字颜色
                .setSubmitText("确定")//确认按钮文字
                .setSubmitColor(mContext.getResources().getColor(R.color.pickerview_submit_text_color))//确定按钮文字颜色
                .setContentTextSize(20)//滚轮文字大小
                .setTextColorCenter(mContext.getResources().getColor(R.color.pickerview_center_text_color))//设置选中文本的颜色值
                .setLineSpacingMultiplier(1.8f)//行间距
                .setDividerColor(mContext.getResources().getColor(R.color.pickerview_divider_color))//设置分割线的颜色
                .setRangDate(startDate, endDate)//设置最小和最大日期
                .setDate(selectedDate)//设置选中的日期
                .build();
    }

    public void show(){
        switch (mType){
            case PICKER_VIEW:
                mPickerView.show();
                break;
            case TIMER_VIEW:
                mStartDatePickerView.show();
                break;
        }

    }
}
