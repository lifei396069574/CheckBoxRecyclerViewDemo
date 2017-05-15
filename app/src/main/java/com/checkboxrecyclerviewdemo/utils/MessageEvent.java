package com.checkboxrecyclerviewdemo.utils;

import com.checkboxrecyclerviewdemo.bean.JavaBean;

import java.util.List;

/**
 * 作者：李飞 on 2017/5/13 08:40
 * 类的用途：
 */

public class MessageEvent {
    private List<JavaBean.DataBean> message;

    public MessageEvent(List<JavaBean.DataBean> message) {

        this.message = message;
    }

    public List<JavaBean.DataBean>  getMessage()
    {
        return message;
    }

    public void setMessage(List<JavaBean.DataBean>  message) {

        this.message = message;
    }
}
