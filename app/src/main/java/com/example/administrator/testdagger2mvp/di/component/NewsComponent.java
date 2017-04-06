package com.example.administrator.testdagger2mvp.di.component;

import com.example.administrator.testdagger2mvp.ListActivity;
import com.example.administrator.testdagger2mvp.di.module.NewsModule;

import dagger.Component;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */
@ActivityScope
@Component(modules = NewsModule.class, dependencies = AppComponent.class)
public interface NewsComponent {
    void inject(ListActivity activity);
}
