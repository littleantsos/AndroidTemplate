package com.jack.android.template.core.http;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by hou on 2017/6/14.14:02
 * introduce :
 */

public interface DataApi {

    @GET
    Observable<String> getUserInfo();
}
