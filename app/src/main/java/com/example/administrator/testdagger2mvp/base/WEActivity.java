package com.example.administrator.testdagger2mvp.base;

import android.view.View;

import com.example.administrator.testdagger2mvp.di.component.AppComponent;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public abstract class WEActivity<P extends BasePresenter>  extends BaseActivity<P> {
    protected WEApplication mWeApplication;
    @Override
    protected void ComponentInject() {
        mWeApplication = (WEApplication) getApplication();
        setupActivityComponent(mWeApplication.getAppComponent());
    }
    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupActivityComponent(AppComponent appComponent);
}
