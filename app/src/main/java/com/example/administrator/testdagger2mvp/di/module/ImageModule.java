package com.example.administrator.testdagger2mvp.di.module;

import com.example.administrator.testdagger2mvp.imageloader.BaseImageLoaderStrategy;
import com.example.administrator.testdagger2mvp.imageloader.ImageLoader;
import com.example.administrator.testdagger2mvp.imageloader.glide.GlideImageLoaderStrategy;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */
@Module
public class ImageModule  {
    @Singleton
    @Provides
    public BaseImageLoaderStrategy provideImageLoaderStrategy() {
        return new GlideImageLoaderStrategy();
    }

    @Singleton
    @Provides
    public ImageLoader provideImageLoader(BaseImageLoaderStrategy strategy) {
        return new ImageLoader(strategy);
    }
}
