package com.age.mac.baselibrary.utils.pickerview;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2019/8/5.
 */

public class PickerEntity implements Serializable{
    private String name;
    private String confirmName;
    private String cancleName;
    private List<String> data;
    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfirmName() {
        return confirmName;
    }

    public void setConfirmName(String confirmName) {
        this.confirmName = confirmName;
    }

    public String getCancleName() {
        return cancleName;
    }

    public void setCancleName(String cancleName) {
        this.cancleName = cancleName;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
