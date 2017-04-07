package com.example.administrator.testdagger2mvp.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.testdagger2mvp.R;
import com.example.administrator.testdagger2mvp.base.BaseHolder;
import com.example.administrator.testdagger2mvp.base.WEApplication;
import com.example.administrator.testdagger2mvp.bean.NewsBean;
import com.example.administrator.testdagger2mvp.bean.NewsData;
import com.example.administrator.testdagger2mvp.imageloader.ImageLoader;
import com.example.administrator.testdagger2mvp.imageloader.glide.GlideImageConfig;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/7 0007.
 */

public class NewsItemHolder extends BaseHolder<NewsBean> {
    @Nullable
    @BindView(R.id.ivNews)
    ImageView ivNews;
    @Nullable
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @Nullable
    @BindView(R.id.pdate)
    TextView pdate;
    @Nullable
    @BindView(R.id.srctv)
    TextView srctv;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    private final WEApplication mApplication;

    public NewsItemHolder(View itemView) {
        super(itemView);
        mApplication = (WEApplication) itemView.getContext().getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();
    }

    @Override
    public void setData(NewsBean data) {
        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url(data.getPic())
                .imagerView(ivNews)
                .build());
        tvTitle.setText(data.getTitle());
        pdate.setText(data.getTime());
        srctv.setText(data.getSrc());
    }
}
