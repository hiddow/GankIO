package com.hiddow.gankio.model.object;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public class Welfare extends BaseResult {
    @SerializedName("_id")
    public String id;
    public Date createdAt;
    public String source;
    public String type;
    public boolean used;

    @Override
    public String toString() {
        return super.toString();
    }


}
