package com.example.administrator.testdagger2mvp.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 2014wang on 2016/10/29   @desc
 */
public class NewsData extends BaseData {
    @SerializedName("result")
    List<NewsBean> listbeans;

    public List<NewsBean> getListbeans() {
        return listbeans;
    }
}
