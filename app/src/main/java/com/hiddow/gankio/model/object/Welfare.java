package com.hiddow.gankio.model.object;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public class Welfare implements Serializable {
    @SerializedName("_id")
    public String id;
    public Date createdAt;
    public String desc;
    public Date publishedAt;
    public String source;
    public String type;
    public String url;
    public boolean used;
    public String who;

    @Override
    public String toString() {
        return super.toString();
    }

    public String getShortTime() {

        String shortString = null;

        long now = Calendar.getInstance().getTimeInMillis();
        long deltime = (now - createdAt.getTime()) / 1000;
        if (deltime > 365 * 24 * 60 * 60) {
            shortString = (int) (deltime / (365 * 24 * 60 * 60)) + "年前";
        } else if (deltime > 24 * 60 * 60) {
            shortString = (int) (deltime / (24 * 60 * 60)) + "天前";
        } else if (deltime > 60 * 60) {
            shortString = (int) (deltime / (60 * 60)) + "小时前";
        } else if (deltime > 60) {
            shortString = (int) (deltime / (60)) + "分前";
        } else if (deltime > 1) {
            shortString = deltime + "秒前";
        } else {
            shortString = "1秒前";
        }
        return shortString;
    }

}
