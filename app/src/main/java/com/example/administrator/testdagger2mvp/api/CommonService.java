package com.example.administrator.testdagger2mvp.api;

import com.example.administrator.testdagger2mvp.bean.NewsData;
import com.example.administrator.testdagger2mvp.bean.RootsData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jess on 8/5/16 12:05
 * contact with jess.yan.effort@gmail.com
 */
public interface CommonService {

    @GET("news/words")
    Observable<RootsData> getNews(@Query("key") String apiKey);

    @GET("news/query")
    Observable<NewsData> getDetails(@Query("key") String apiKey, @Query("q") String keyword);
}
