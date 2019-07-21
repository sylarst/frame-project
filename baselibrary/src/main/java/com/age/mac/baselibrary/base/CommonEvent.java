package com.age.mac.baselibrary.base;

/**
 * Created by mac on 2019/7/11.
 */

/**
 * EventBus发送的事件
 */
public class CommonEvent {
    private int mWhat;
    private String mMessage = "";
    public CommonEvent(int what) {
        this(what, null);
    }

    public CommonEvent(int what, String message) {
        mWhat = what;
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }


    public int getWhat() {
        return mWhat;
    }

    public void setWhat(int what) {
        mWhat = what;
    }
}