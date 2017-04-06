package com.example.administrator.testdagger2mvp.bean;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 2014wang on 2016/10/6   @desc
 */
public class NewsBean implements Serializable {
    @SerializedName("title")
    private String title; // 标题
    @SerializedName("content")
    private String content; // 内容
    @SerializedName("src")
    private String src; // 来源
    @SerializedName("img")
    private String imgUrl;//图片路径
    @SerializedName("url")
    private String url; // 文章路径
    @SerializedName("pdate")
    private String pdate; // 发布时长
    @SerializedName("pdate_src")
    private String pdate_src; // 具体发布时间

    public NewsBean() {
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getSrc() {
        return src;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getPdate() {
        return pdate;
    }

    public String getPdate_src() {
        return pdate_src;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", src='" + src + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", url='" + url + '\'' +
                ", pdate='" + pdate + '\'' +
                ", pdate_src='" + pdate_src + '\'' +
                '}';
    }

    public boolean allNotNull() {
        if (!TextUtils.isEmpty(this.getContent())
                && !TextUtils.isEmpty(this.getImgUrl())
                && !TextUtils.isEmpty(this.getPdate())
                && !TextUtils.isEmpty(this.getPdate_src())
                && !TextUtils.isEmpty(this.getSrc())
                && !TextUtils.isEmpty(this.getTitle())
                && !TextUtils.isEmpty(this.getUrl())) {
            return true;
        }else{
            return false;
        }
    }
}
