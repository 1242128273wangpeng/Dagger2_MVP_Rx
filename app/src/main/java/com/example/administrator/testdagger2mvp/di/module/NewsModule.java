package com.example.administrator.testdagger2mvp.di.module;

import com.example.administrator.testdagger2mvp.mvp.contract.ListContract;
import com.example.administrator.testdagger2mvp.api.ServiceManager;
import com.example.administrator.testdagger2mvp.di.component.ActivityScope;
import com.example.administrator.testdagger2mvp.mvp.model.NewsModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */
@Module
public class NewsModule {
    private ListContract.View mView;

    public NewsModule(ListContract.View mView) {
        this.mView = mView;
    }

    @ActivityScope
    @Provides
    ListContract.View provideUserView() {
        return this.mView;
    }

    @ActivityScope
    @Provides
    ListContract.Model provideUserModel(ServiceManager serviceManager) {
        return new NewsModel(serviceManager);
    }
}
