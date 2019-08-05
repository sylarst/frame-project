package com.sub.login_module.select;

import com.age.mac.baselibrary.base.quickadapter.entity.MultiItemEntity;

/**
 * Created by mac on 2019/8/5.
 */

public class SpinnearBean implements MultiItemEntity {

    private String paraName;
    private int mType=0;

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    @Override
    public int getItemType() {
        return mType;
    }

    public void setType(int type){
        mType=type;
    }
}
