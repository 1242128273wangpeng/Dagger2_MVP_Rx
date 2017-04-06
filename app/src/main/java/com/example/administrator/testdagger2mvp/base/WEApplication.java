package com.example.administrator.testdagger2mvp.base;

import android.content.Context;
import android.widget.Toast;

import com.example.administrator.testdagger2mvp.BuildConfig;
import com.example.administrator.testdagger2mvp.api.Constants;
import com.example.administrator.testdagger2mvp.di.component.AppComponent;
import com.example.administrator.testdagger2mvp.di.component.DaggerAppComponent;
import com.example.administrator.testdagger2mvp.di.module.ServiceModule;
import com.example.administrator.testdagger2mvp.http.GlobeHttpHandler;
import com.example.administrator.testdagger2mvp.listener.ResponseErrorListener;
import com.example.administrator.testdagger2mvp.utils.UiUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */

public class WEApplication extends BaseApplication {
    private AppComponent mAppComponent;
    private RefWatcher mRefWatcher;//leakCanary观察器

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(getAppModule())//baseApplication提供
                .clientModule(getClientModule())//baseApplication提供
                .imageModule(getImageModule())//baseApplication提供
                .serviceModule(new ServiceModule())//需自行创建
                .build();
        if (BuildConfig.LOG_DEBUG) {//Timber日志打印
            Timber.plant(new Timber.DebugTree());
        }
        installLeakCanary();//leakCanary内存泄露检查
    }


    /**
     * 安装leakCanary检测内存泄露
     */
    protected void installLeakCanary() {
        this.mRefWatcher = BuildConfig.USE_CANARY ? LeakCanary.install(this) : RefWatcher.DISABLED;
    }

    /**
     * 获得leakCanary观察器
     *
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
        WEApplication application = (WEApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }


    @Override
    public String getBaseUrl() {
        return Constants.BASEURL;
    }

    /**
     * 将AppComponent返回出去,供其它地方使用, AppComponent接口中声明的方法返回的实例, 在getAppComponent()拿到对象后都可以直接使用
     *
     * @return
     */
    public AppComponent getAppComponent() {
        return mAppComponent;
    }


    /**
     * 这里可以提供一个全局处理http响应结果的处理类,
     * 这里可以比客户端提前一步拿到服务器返回的结果,可以做一些操作,比如token超时,重新获取
     * 默认不实现,如果有需求可以重写此方法
     *
     * @return
     */
    @Override
    public GlobeHttpHandler getHttpHandler() {
//        new GlobeHttpHandler() {
//            @Override
//            public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
//                //这里可以先客户端一步拿到每一次http请求的结果,可以解析成json,做一些操作,如检测到token过期后
//                //重新请求token,并重新执行请求
//                try {
//                    JSONArray array = new JSONArray(httpResult);
//                    JSONObject object = (JSONObject) array.get(0);
//                    String login = object.getString("login");
//                    String avatar_url = object.getString("avatar_url");
//                    Timber.tag(TAG).w("result ------>" + login + "    ||   avatar_url------>" + avatar_url);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                //这里如果发现token过期,可以先请求最新的token,然后在拿新的token放入request里去重新请求
//                //注意在这个回调之前已经调用过proceed,所以这里必须自己去建立网络请求,如使用okhttp使用新的request去请求
//                // create a new request and modify it accordingly using the new token
////                    Request newRequest = chain.request().newBuilder().header("token", newToken)
////                            .build();
//
////                    // retry the request
////
////                    response.body().close();
//                //如果使用okhttp将新的请求,请求成功后,将返回的response  return出去即可
//
//                //如果不需要返回新的结果,则直接把response参数返回出去
//                return response;
//            }
//
//            @Override
//            public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
//                //如果需要再请求服务器之前做一些操作,则重新返回一个做过操作的的requeat如增加header,不做操作则返回request
//                //return chain.request().newBuilder().header("token", tokenId)
////                .build();
//                return request;
//            }
//        }
        return null;
    }

    @Override
    protected ResponseErrorListener getResponseErrorListener() {
        return new ResponseErrorListener() {
            @Override
            public void handleResponseError(Context context, Exception e) {
                Timber.tag(TAG).w("------------>" + e.getMessage());
                Toast.makeText(context, "handleResponseError", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
