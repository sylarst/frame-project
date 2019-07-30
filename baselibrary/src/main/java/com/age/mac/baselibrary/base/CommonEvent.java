package com.age.mac.baselibrary.base;

/**
 * Created by mac on 2019/7/11.
 */

/**
 * EventBus发送的事件
 */
public class CommonEvent {
    private int mWhat;
    private String mMessage;
    public CommonEvent(int what) {
        this(what, null);
    }

    public CommonEvent(int what, String message) {
        mWhat = what;
        mMessage = message;
    }

    public int getmWhat() {
        return mWhat;
    }

    public void setmWhat(int mWhat) {
        this.mWhat = mWhat;
    }

    public Object getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}