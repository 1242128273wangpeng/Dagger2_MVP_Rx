package com.example.administrator.testdagger2mvp.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */

public class BasePresenter<M, V extends BaseView> implements Presenter {
    protected final String TAG = this.getClass().getSimpleName();
    protected CompositeSubscription mCompositeSubscription;
    // BasePresenter中需要Module和View
    protected M mModel;
    protected V mRootView;


    public BasePresenter(M model, V rootView) {
        this.mModel = model;
        this.mRootView = rootView;
        onStart();
    }

    public BasePresenter(V rootView) {
        this.mRootView = rootView;
        onStart();
    }

    public BasePresenter() {
        onStart();
    }


    protected void handleError(Throwable throwable) {

    }


    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);//将所有subscription放入,集中处理
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();//保证activity结束时取消所有正在执行的订阅
        }
    }

    // 接口presenter中的方法
    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        unSubscribe();//解除订阅
        this.mModel = null;
        this.mRootView = null;
    }

    @Override
    public void unSubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();//保证activity结束时取消所有正在执行的订阅
        }
    }
}
