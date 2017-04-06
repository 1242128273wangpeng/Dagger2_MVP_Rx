package com.example.administrator.testdagger2mvp.bean;

import java.util.List;

/**
 * Created by 2014wang on 2016/10/6   @desc
 */
public class NewsResult {
    private String reason;

    private List<String> resultString ;

    private int error_code;

    public void setReason(String reason){
        this.reason = reason;
    }
    public String getReason(){
        return this.reason;
    }
    public void setResultString(List<String> result){
        this.resultString = result;
    }
    public List<String> getResultString(){
        return this.resultString;
    }
    public void setError_code(int error_code){
        this.error_code = error_code;
    }
    public int getError_code(){
        return this.error_code;
    }
}
