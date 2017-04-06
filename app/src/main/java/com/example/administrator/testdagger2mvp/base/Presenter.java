package com.example.administrator.testdagger2mvp.base;

import rx.Subscription;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */

public interface Presenter {

    void onStart();

    void onDestroy();

    void unSubscribe(Subscription subscription);
}
