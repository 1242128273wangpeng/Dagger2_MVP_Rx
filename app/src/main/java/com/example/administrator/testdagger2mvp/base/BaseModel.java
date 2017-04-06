package com.example.administrator.testdagger2mvp.base;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */

public class BaseModel <S>{
    protected S mServiceManager;//服务管理类,用于网络请求

    public BaseModel(S serviceManager) {
        this.mServiceManager = serviceManager;
    }

    public void onDestory() {
        mServiceManager = null;
    }
}
