package com.example.administrator.testdagger2mvp.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.testdagger2mvp.R;
import com.example.administrator.testdagger2mvp.base.BaseHolder;
import com.example.administrator.testdagger2mvp.base.DefaultAdapter;

import java.util.List;

import butterknife.BindView;

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
        return new NewsItemHolder(v);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_news;
    }

}
