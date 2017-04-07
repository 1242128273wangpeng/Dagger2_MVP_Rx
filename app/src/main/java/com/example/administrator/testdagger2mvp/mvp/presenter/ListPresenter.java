package com.example.administrator.testdagger2mvp.mvp.presenter;

import com.example.administrator.testdagger2mvp.mvp.contract.ListContract;
import com.example.administrator.testdagger2mvp.adapter.NewsAdapter;
import com.example.administrator.testdagger2mvp.api.Constants;
import com.example.administrator.testdagger2mvp.base.BasePresenter;
import com.example.administrator.testdagger2mvp.base.DefaultAdapter;
import com.example.administrator.testdagger2mvp.bean.NewsBean;
import com.example.administrator.testdagger2mvp.di.component.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */
@ActivityScope
public class ListPresenter extends BasePresenter<ListContract.Model, ListContract.View> {
    private List<NewsBean> mNewsBean = new ArrayList<>();
    private DefaultAdapter mAdapter;

    @Inject
    public ListPresenter(ListContract.View view, ListContract.Model model) {
        super(model, view);
        mAdapter = new NewsAdapter(mNewsBean);
        mRootView.setAdapter(mAdapter);
    }

    public void requestUsers(final boolean pullToRefresh) {
        mModel.getModelNews(Constants.APIKEY).subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                if (pullToRefresh)
                    mRootView.showLoading();//显示上拉刷新的进度条
                else
                    mRootView.startLoadMore();
            }
        }).doAfterTerminate(new Action0() {
            @Override
            public void call() {
                if (pullToRefresh)
                    mRootView.hideLoading();//隐藏上拉刷新的进度条
                else
                    mRootView.endLoadMore();
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<NewsBean>>() {
            @Override
            public void call(List<NewsBean> lists) {
                if (pullToRefresh) {
                    mNewsBean.clear();
                }
                mNewsBean.addAll(lists);
                mAdapter.notifyDataSetChanged();//通知更新数据
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });
    }
}
