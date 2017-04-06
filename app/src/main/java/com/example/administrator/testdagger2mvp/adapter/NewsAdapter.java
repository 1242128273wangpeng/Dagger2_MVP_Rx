package com.example.administrator.testdagger2mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.testdagger2mvp.base.BaseHolder;
import com.example.administrator.testdagger2mvp.base.DefaultAdapter;

import java.util.List;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */

public class NewsAdapter extends DefaultAdapter {

    public NewsAdapter(List infos) {
        super(infos);
    }

    @Override
    public BaseHolder getHolder(View v) {
        return null;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
