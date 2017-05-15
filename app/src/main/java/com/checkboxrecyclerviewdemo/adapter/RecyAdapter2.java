package com.checkboxrecyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.checkboxrecyclerviewdemo.R;
import com.checkboxrecyclerviewdemo.bean.JavaBean;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * 作者：李飞 on 2017/5/13 08:48
 * 类的用途：
 */

public class RecyAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_ONE = 0;// 条目一
    private static final int ITEM_SEC = 1;//  条目二


    //数据源
    private List<JavaBean.DataBean> list;
    private Context context;

    public RecyAdapter2(List<JavaBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;

    }
    //视图管理
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;
        private View root;

        public ViewHolder(View root) {
            super(root);
            this.root = root;
            title = (TextView) root.findViewById(R.id.tv_sec);
            image  = (ImageView) root.findViewById(R.id.image_sec);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;
        private View root;

        public ViewHolder2(View root) {
            super(root);
            this.root = root;
            title = (TextView) root.findViewById(R.id.tv_sec2);
            image  = (ImageView) root.findViewById(R.id.image_sec2);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    //绑定视图管理者


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case ITEM_ONE:
                View root1  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_sec, parent, false);
                ViewHolder vh1 = new ViewHolder(root1);
                return vh1;

            case ITEM_SEC:
                View root2  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_sec2, parent, false);
                ViewHolder2 vh2 = new ViewHolder2(root2);
                return vh2;
        }
          return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.list_anim);
        switch (itemViewType){
            case ITEM_ONE:
                ViewHolder holder1 = (ViewHolder) holder;
                holder1.title.setText(list.get(position).getGoods_name());
                if (position%2==0){
                    Glide.with(context).load(list.get(position).getGoods_img()).error(R.mipmap.ic_launcher).into(holder1.image);
                }else {
                    Picasso.with(context).load(list.get(position).getGoods_img()).error(R.mipmap.ic_launcher).into(holder1.image);
                }

                //设置checkBox显示的动画
                holder1.title.startAnimation(animation);
                //设置Tag
                holder1.root.setTag(position);
                break;
            case ITEM_SEC:
                ViewHolder2 holder2 = (ViewHolder2) holder;
                holder2.title.setText(list.get(position).getGoods_name());
                if (position%2==0){
                    Glide.with(context).load(list.get(position).getGoods_img()).error(R.mipmap.ic_launcher).into(holder2.image);
                }else {
                    Picasso.with(context).load(list.get(position).getGoods_img()).error(R.mipmap.ic_launcher).into(holder2.image);
                }
                //设置checkBox显示的动画
                holder2.title.startAnimation(animation);
                //设置Tag
                holder2.root.setTag(position);
                break;
        }

    }


    //多条目展示需要使用此方法
    @Override
    public int getItemViewType(int position) {
        if (position % 2==0) {
            return ITEM_ONE;
        } else{
            return ITEM_SEC;
        }
    }

}
