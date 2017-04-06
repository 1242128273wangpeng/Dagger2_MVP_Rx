package com.example.administrator.testdagger2mvp.base;

import android.content.Intent;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */

public interface BaseView {
    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息
     */
    void showMessage(String message);

    /**
     * 跳转activity
     */
    void launchActivity(Intent intent);
    /**
     * 杀死自己
     */
    void killMyself();
}
