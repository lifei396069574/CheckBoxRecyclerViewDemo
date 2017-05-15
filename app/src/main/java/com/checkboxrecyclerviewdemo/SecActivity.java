package com.checkboxrecyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.checkboxrecyclerviewdemo.adapter.RecyAdapter2;
import com.checkboxrecyclerviewdemo.bean.JavaBean;
import com.checkboxrecyclerviewdemo.utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class SecActivity extends AppCompatActivity {

    private RecyclerView rv_sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);


        initView();

        //注册 EventBus
        EventBus.getDefault().register(this);


    }

    private void initView() {

        rv_sec = (RecyclerView) findViewById(R.id.RecyclerView_sec);
        rv_sec.setLayoutManager(new LinearLayoutManager(this));

    }

   // 接收 并 对消息处理
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMoonEvent(MessageEvent messageEvent){
//
//        Log.i("zzz","messageEvent.getMessage().size() :"+messageEvent.getMessage().size());
//
//        RecyAdapter2 recyAdapter2 = new RecyAdapter2(messageEvent.getMessage(), this);
//        rv_sec.setAdapter(recyAdapter2);
//
//    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(MessageEvent messageEvent){

        List<JavaBean.DataBean> list = messageEvent.getMessage();

        RecyAdapter2 recyAdapter2 = new RecyAdapter2(list, this);
        rv_sec.setAdapter(recyAdapter2);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

}
