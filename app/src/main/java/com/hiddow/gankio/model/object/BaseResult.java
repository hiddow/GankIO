package com.hiddow.gankio.model.object;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yangxiaoguang on 2016/11/11.
 */

public class BaseResult implements Serializable {
    public String desc;
    public Date publishedAt;
    public String url;
    public String who;

    public String getShortTime() {

        String shortString = null;
        long current = new Date().getTime();
        long deltime = (current - publishedAt.getTime()) / 1000l;

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
