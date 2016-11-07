package com.hiddow.gankio.model.object;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
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
}
