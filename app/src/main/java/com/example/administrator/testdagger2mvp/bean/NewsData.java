package com.example.administrator.testdagger2mvp.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 2014wang on 2016/10/29   @desc
 */
public class NewsData extends BaseData {
    @SerializedName("result")
    private Result result;

    public Result getResult() {
        return result;
    }

    public class Result {
        @SerializedName("channel")
        private String channel;
        @SerializedName("num")
        private String num;
        @SerializedName("list")
        private List<NewsBean> newsBeanList;

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getChannel() {
            return this.channel;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getNum() {
            return this.num;
        }

        public void setList(List<NewsBean> list) {
            this.newsBeanList = list;
        }

        public List<NewsBean> getNewsBeanList() {
            return this.newsBeanList;
        }

    }
}
