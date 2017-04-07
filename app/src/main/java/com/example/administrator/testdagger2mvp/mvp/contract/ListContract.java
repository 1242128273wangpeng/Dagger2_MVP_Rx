package com.example.administrator.testdagger2mvp.mvp.contract;

import android.widget.BaseAdapter;

import com.example.administrator.testdagger2mvp.base.BaseView;
import com.example.administrator.testdagger2mvp.base.DefaultAdapter;
import com.example.administrator.testdagger2mvp.bean.NewsBean;

import java.util.List;

import rx.Observable;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */

public interface ListContract {
    interface View extends BaseView{
        void setAdapter(DefaultAdapter adapter);

        void startLoadMore();

        void endLoadMore();
    }
    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model {
        Observable <List<NewsBean>> getModelNews(String ApiKey);
    }
}
