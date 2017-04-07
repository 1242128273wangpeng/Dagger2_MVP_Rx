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
    // 获取新闻频道

    /**
     * 请求示例：
     * http://api.jisuapi.com/news/channel?appkey=yourappkey
     * {
     * "status": "0",
     * "msg": "ok",
     * "result": [
     * "头条",
     * "新闻",
     * "财经",
     * "体育",
     * "娱乐",
     * "军事",
     * "教育",
     * "科技",
     * "NBA",
     * "股票",
     * "星座",
     * "女性",
     * "健康",
     * "育儿"
     * ]
     * }
     *
     * @param apiKey
     * @return
     */
    @GET("news/channel")
    Observable<RootsData> getNews(@Query("appkey") String apiKey);

    /**
     * 请求示例：
     * http://api.jisuapi.com/news/get?channel=头条&start=0&num=10&appkey=yourappkey
     *
     * @return
     */
    @GET("news/get")
    Observable<NewsData> getDetails(@Query("appkey") String apiKey, @Query("channel") String channel, @Query("start") int start, @Query("num") int num);
}
