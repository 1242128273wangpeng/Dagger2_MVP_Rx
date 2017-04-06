package com.example.administrator.testdagger2mvp.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */

public abstract class BaseActivity <P extends BasePresenter> extends Activity{
    protected final String TAG = this.getClass().getSimpleName();
    public static final String ACTION_RECEIVER_ACTIVITY = "com.yimeng.activity";
    // 基类的广播
    private BroadcastReceiver mBroadcastReceiver;
    // 基类应用
    protected BaseApplication mApplication;
    @Inject
    protected P mPresenter;
    public static final String IS_NOT_ADD_ACTIVITY_LIST = "is_add_activity_list";//是否加入到activity的list，管理
    private Unbinder mUnbinder;

    @Override
    protected void onResume() {
        super.onResume();
        registReceiver();//注册广播
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregistReceriver();
    }

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (BaseApplication) getApplication();
        //如果intent包含了此字段,并且为true说明不加入到list
        // 默认为false,如果不需要管理(比如不需要在退出所有activity(killAll)时，退出此activity就在intent加此字段为true)
        boolean isNotAdd = getIntent().getBooleanExtra(IS_NOT_ADD_ACTIVITY_LIST, false);
        synchronized (BaseActivity.class) {
            if (!isNotAdd)
                mApplication.getActivityList().add(this);
        }
        setContentView(initView());
        //绑定到butterknife
        mUnbinder = ButterKnife.bind(this);
        ComponentInject();//依赖注入
        initData();
    }

    /**
     * 依赖注入的入口
     */
    protected abstract void ComponentInject();

    // 全屏显示
    public void FullScreenCall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (BaseActivity.class) {
            mApplication.getActivityList().remove(this);
        }
        if (mPresenter != null) mPresenter.onDestroy();//释放资源
        if (mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 注册广播
     */
    public void registReceiver() {
        try {
            mBroadcastReceiver = new ActivityReceriver();
            IntentFilter filter = new IntentFilter(ACTION_RECEIVER_ACTIVITY);
            registerReceiver(mBroadcastReceiver, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 解除注册广播
     */
    public void unregistReceriver() {
        if (mBroadcastReceiver == null) return;
        try {
            unregisterReceiver(mBroadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected abstract View initView();

    protected abstract void initData();

    /**
     * 用于处理当前activity需要,基类Activity的广播
     */
    class ActivityReceriver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                switch (intent.getStringExtra("type")) {
                    case "startActivity"://启动activity
                        Intent content = intent.getExtras().getParcelable("content");
                        startActivity(content);
                        break;
                    case "showSnackbar"://显示snackbar
                        Toast.makeText(context, "显示Snackbar", Toast.LENGTH_SHORT).show();
                        break;
                    case "killAll":
                        LinkedList<BaseActivity> copy;
                        synchronized (BaseActivity.class) {
                            copy = new LinkedList<BaseActivity>(mApplication.getActivityList());
                        }
                        for (BaseActivity baseActivity : copy) {
                            baseActivity.finish();
                        }
                        break;
                }
            }
        }
    }


}
