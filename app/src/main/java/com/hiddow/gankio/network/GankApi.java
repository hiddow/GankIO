package com.hiddow.gankio.network;

import com.hiddow.gankio.model.WelfareData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public interface GankApi {
    @GET("data/福利/{pageSize}/{pageNum}")
    Observable<WelfareData> getPicData(@Path("pageSize") int pageSize, @Path("pageNum") int pageNum);
}
