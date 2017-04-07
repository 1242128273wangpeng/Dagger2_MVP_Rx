package com.example.administrator.testdagger2mvp.bean;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 2014wang on 2016/10/6   @desc
 */
public class NewsBean implements Serializable {
    @SerializedName("title")
    private String title;
    @SerializedName("time")
    private String time;
    @SerializedName("src")
    private String src;
    @SerializedName("category")
    private String category;
    @SerializedName("pic")
    private String pic;
    @SerializedName("content")
    private String content;
    @SerializedName("url")
    private String url;
    @SerializedName("weburl")
    private String weburl;

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getSrc() {
        return src;
    }

    public String getCategory() {
        return category;
    }

    public String getPic() {
        return pic;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String getWeburl() {
        return weburl;
    }

    public boolean allNotNull() {
        if (!TextUtils.isEmpty(title)
                && !TextUtils.isEmpty(time)
                && !TextUtils.isEmpty(time)
                && !TextUtils.isEmpty(src)
                && !TextUtils.isEmpty(category)
                && !TextUtils.isEmpty(pic)
                && !TextUtils.isEmpty(content)
                && !TextUtils.isEmpty(url)
                && !TextUtils.isEmpty(weburl)) {
            return true;
        }
        return false;
    }
}
