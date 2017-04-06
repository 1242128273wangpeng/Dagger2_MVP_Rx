package com.example.administrator.testdagger2mvp.mvp.model;

import android.text.TextUtils;

import com.example.administrator.testdagger2mvp.bean.NewsData;
import com.example.administrator.testdagger2mvp.mvp.contract.ListContract;
import com.example.administrator.testdagger2mvp.api.CommonService;
import com.example.administrator.testdagger2mvp.api.Constants;
import com.example.administrator.testdagger2mvp.api.ServiceManager;
import com.example.administrator.testdagger2mvp.base.BaseModel;
import com.example.administrator.testdagger2mvp.bean.NewsBean;
import com.example.administrator.testdagger2mvp.bean.RootsData;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */

public class NewsModel extends BaseModel<ServiceManager> implements ListContract.Model {
    public static final int USERS_PER_PAGE = 10;
    private CommonService mCommonService;

    public NewsModel(ServiceManager serviceManager) {
        super(serviceManager);
        this.mCommonService = mServiceManager.getCommonService();
    }


    @Override
    public Observable<List<NewsBean>> getNews(String ApiKey) {
        return mCommonService.getNews(ApiKey).map(new Func1<RootsData, List<NewsBean>>() {
            @Override
            public List<NewsBean> call(RootsData rootsData) {
                final List<NewsBean> dataLists = new ArrayList<>();
                List<String> resultString = rootsData.getResultString();
                for (String s : resultString) {
                    mCommonService.getDetails(Constants.APIKEY, s)
                            .observeOn(Schedulers.immediate())
                            .subscribeOn(Schedulers.immediate())
                            .unsubscribeOn(Schedulers.immediate())
                            .filter(new Func1<NewsData, Boolean>() {
                                @Override
                                public Boolean call(NewsData newsData) {
                                    for (NewsBean newBean : newsData.getListbeans()) {
                                        System.out.println("Thread ID:"+Thread.currentThread().getId());
                                        return newBean.allNotNull();
                                    }
                                    return false;
                                }
                            })
                            .subscribe(new Action1<NewsData>() {
                                @Override
                                public void call(NewsData newsData) {
                                    NewsBean bean = newsData.getListbeans().get(0);
                                    dataLists.add(bean);
                                }
                            });
                }
                return dataLists;
            }
        });


//                map(new Func1<RootsData, List<String>>() {
//                    @Override
//                    public List<String> call(RootsData rootsData) {
//                        return rootsData.getResultString() ;
//                    }
//                }).map(new Func1<List<String>, List<NewsBean>>() {
//            @Override
//            public List<NewsBean> call(List<String> strings) {
//                final List<NewsBean> newsLists = new ArrayList<>();
//                for (int i = 0; i < strings.size(); i++) {
//                    mCommonService.getDetails(Constants.APIKEY,strings.get(i)).map(new Func1<NewsData,  List<NewsBean>>() {
//                        @Override
//                        public List<NewsBean> call(NewsData newsData) {
//                            // 过滤出每个字段都有的Bean
//                            return newsData.getListbeans();
//                        }
//                    });
//                }
//                return  newsLists;
//            }
//        });
    }
}
