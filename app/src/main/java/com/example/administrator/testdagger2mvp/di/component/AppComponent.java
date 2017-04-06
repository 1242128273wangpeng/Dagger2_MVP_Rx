package com.example.administrator.testdagger2mvp.di.component;

import android.app.Application;

import com.example.administrator.testdagger2mvp.api.ServiceManager;
import com.example.administrator.testdagger2mvp.di.module.AppModule;
import com.example.administrator.testdagger2mvp.di.module.ClientModule;
import com.example.administrator.testdagger2mvp.di.module.ImageModule;
import com.example.administrator.testdagger2mvp.di.module.ServiceModule;
import com.example.administrator.testdagger2mvp.imageloader.ImageLoader;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */
@Singleton
@Component(modules = {AppModule.class, ClientModule.class, ImageModule.class, ServiceModule.class})
public interface AppComponent {
    Application Application();

    //服务管理器,retrofitApi
    ServiceManager serviceManager();

    OkHttpClient okHttpClient();

    //图片管理器,用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    ImageLoader imageLoader();

    //Gson
    Gson gson();

}
