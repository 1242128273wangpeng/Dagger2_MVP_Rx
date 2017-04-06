package com.example.administrator.testdagger2mvp.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 2014wang on 2016/10/6   @desc
 */
public class RootsData extends BaseData {
    @SerializedName("result")
    private List<String> resultString;

    public List<String> getResultString() {
        return resultString;
    }
}
