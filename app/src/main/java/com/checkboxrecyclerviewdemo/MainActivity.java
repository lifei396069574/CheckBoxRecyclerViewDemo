package com.checkboxrecyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.checkboxrecyclerviewdemo.adapter.MyRecyAdapter;
import com.checkboxrecyclerviewdemo.bean.JavaBean;
import com.checkboxrecyclerviewdemo.utils.MessageEvent;
import com.checkboxrecyclerviewdemo.utils.MyUrl;
import com.checkboxrecyclerviewdemo.utils.OkHttpManager;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView recyclerView;
    private MyRecyAdapter mMyRecyAdapter;
    private Button button_go;
    private List<JavaBean.DataBean> mData;
    private List<JavaBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();
    }

    private void initData() {

        OkHttpManager.getAsync(MyUrl.url, new OkHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                JavaBean javaBean = gson.fromJson(result, JavaBean.class);
                mData = javaBean.getData();
                setData();
            }
        });


    }

    private void setData() {
        mMyRecyAdapter = new MyRecyAdapter(mData, this);
        recyclerView.setAdapter(mMyRecyAdapter);

        mMyRecyAdapter.setRecyclerViewOnItemClickListener(new MyRecyAdapter.RecyclerViewOnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                //点击事件
                //设置选中的项
                mMyRecyAdapter.setSelectItem(position);
            }

            @Override
            public boolean onItemLongClickListener(View view, int position) {
                //长按事件
                mMyRecyAdapter.setShowBox();
                //设置选中的项
//                mMyRecyAdapter.setSelectItem(position);
                mMyRecyAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void initView() {

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        button_go = (Button) findViewById(R.id.button_go);
        button_go.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.xx, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //全选
            case R.id.all:
                Map<Integer, Boolean> map = mMyRecyAdapter.getMap();
                for (int i = 0; i < map.size(); i++) {
                    map.put(i, true);
                    mMyRecyAdapter.notifyDataSetChanged();
                }
                break;
            //全不选
            case R.id.no_all:
                Map<Integer, Boolean> m = mMyRecyAdapter.getMap();
                for (int i = 0; i < m.size(); i++) {
                    m.put(i, false);
                    mMyRecyAdapter.notifyDataSetChanged();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_go:
                chuan();

                break;
        }
    }

    public void chuan(){

        mList = new ArrayList<JavaBean.DataBean>();

        Map<Integer, Boolean> map = mMyRecyAdapter.getMap();
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i)){
                mList.add(mData.get(i));
            }
        }
        Log.i("zzz","mList : " + mList.size());
        EventBus.getDefault().postSticky(new MessageEvent(mList));
        startActivity(new Intent(this,SecActivity.class));

    }
}
