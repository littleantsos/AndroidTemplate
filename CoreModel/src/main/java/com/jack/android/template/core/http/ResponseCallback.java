package com.jack.android.template.core.http;

/**
 * Created by hou on 2017/4/13.10:32
 * introduce :
 */

public interface ResponseCallback<T> {
    void onSuccess(T t);
    void onFail(Throwable throwable);
}
