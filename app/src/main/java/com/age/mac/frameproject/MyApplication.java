package com.age.mac.frameproject;

import com.age.mac.baselibrary.base.BaseApplication;

/**
 * Created by mac on 2019/7/10.
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
//        ARouter.openLog();   // 打印日志
//        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
