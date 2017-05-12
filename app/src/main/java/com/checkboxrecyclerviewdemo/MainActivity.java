package com.checkboxrecyclerviewdemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.checkboxrecyclerviewdemo.adapter.MyRecyAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ArrayList<String> mList;
    private MyRecyAdapter mMyRecyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();
    }

    private void initData() {
        mList = new ArrayList<>();
        File directory = Environment.getExternalStorageDirectory();
        File[] files = directory.listFiles();
        for (File file : files) {
            mList.add(file.getName());
        }

        setData();
    }

    private void setData() {
        mMyRecyAdapter = new MyRecyAdapter(mList, this);
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
}
