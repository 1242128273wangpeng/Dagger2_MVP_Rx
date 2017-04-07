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
import com.example.administrator.testdagger2mvp.utils.BeanUtils;

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
    public Observable<List<NewsBean>> getModelNews(String ApiKey) {
        return mCommonService.getNews(ApiKey)
                .flatMap(new Func1<RootsData, Observable<String>>() {
                    @Override
                    public Observable<String> call(RootsData rootsData) {
                        return Observable.from(rootsData.getResultString());
                    }
                })
                .flatMap(new Func1<String, Observable<NewsData>>() {
                    @Override
                    public Observable<NewsData> call(String s) {
                        return mCommonService.getDetails(Constants.APIKEY, s, 0, USERS_PER_PAGE);
                    }
                })
                .flatMap(new Func1<NewsData, Observable<NewsBean>>() {
                    @Override
                    public Observable<NewsBean> call(NewsData newsData) {
                        return Observable.from(newsData.getResult().getNewsBeanList());
                    }
                }).filter(new Func1<NewsBean, Boolean>() {
                    @Override
                    public Boolean call(NewsBean newsBean) {
                        return BeanUtils.allNotNull(newsBean);
                    }
                })
                .toList();


        /**
         * mCommonService
         .getNews(ApiKey)
         .flatMap(new Func1<RootsData, Observable<String>>() {
        @Override public Observable<String> call(RootsData rootsData) {
        return Observable.from(rootsData.getResultString());
        }
        })
         .take(2)
         .compose(new Observable.Transformer<String, List<NewsBean>>() {
        @Override public Observable<List<NewsBean>> call(Observable<String> stringObservable) {
        return stringObservable
        .flatMap(new Func1<String, Observable<NewsData>>() {
        @Override public Observable<NewsData> call(String s) {
        return mCommonService.getDetails(Constants.APIKEY, s);
        }
        })
        .flatMap(new Func1<NewsData, Observable<NewsBean>>() {
        @Override public Observable<NewsBean> call(NewsData newsData) {
        return Observable.from(newsData.getListbeans());
        }
        })
        .filter(new Func1<NewsBean, Boolean>() {
        @Override public Boolean call(NewsBean newsData) {
        return newsData.allNotNull();
        }
        })
        .toList();
        }
        });
         */


//                .flatMap(new Func1<String, Observable<NewsData>>() {
//                    @Override
//                    public Observable<NewsData> call(String s) {
//                        return mCommonService.getDetails(Constants.APIKEY, s);
//                    }
//                })
//                .flatMap(new Func1<NewsData, Observable<NewsBean>>() {
//                    @Override
//                    public Observable<NewsBean> call(NewsData newsData) {
//                        return Observable.from(newsData.getListbeans());
//                    }
//                })
//                .filter(new Func1<NewsBean, Boolean>() {
//                    @Override
//                    public Boolean call(NewsBean newsData) {
//                        return newsData.allNotNull();
//                    }
//                })


//        return mCommonService.getNews(ApiKey).map(new Func1<RootsData, List<NewsBean>>() {
//            @Override
//            public List<NewsBean> call(RootsData rootsData) {
//                final List<NewsBean> dataLists = new ArrayList<>();
//                List<String> resultString = rootsData.getResultString();
//                for (String s : resultString) {
//                    mCommonService.getDetails(Constants.APIKEY, s)
//                            .observeOn(Schedulers.immediate())
//                            .subscribeOn(Schedulers.immediate())
//                            .unsubscribeOn(Schedulers.immediate())
//                            .filter(new Func1<NewsData, Boolean>() {
//                                @Override
//                                public Boolean call(NewsData newsData) {
//                                    for (NewsBean newBean : newsData.getListbeans()) {
//                                        System.out.println("Thread ID:"+Thread.currentThread().getId());
//                                        return newBean.allNotNull();
//                                    }
//                                    return false;
//                                }
//                            })
//                            .subscribe(new Action1<NewsData>() {
//                                @Override
//                                public void call(NewsData newsData) {
//                                    NewsBean bean = newsData.getListbeans().get(0);
//                                    dataLists.add(bean);
//                                }
//                            });
//                }
//                return dataLists;
//            }
//        });


    }
}
