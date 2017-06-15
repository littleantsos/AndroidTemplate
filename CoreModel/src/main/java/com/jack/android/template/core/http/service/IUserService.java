package com.jack.android.template.core.http.service;

import com.jack.android.template.core.http.ResponseCallback;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by hou on 2017/6/14.14:10
 * introduce :
 */

public interface IUserService {

    void getUserInfo(RxAppCompatActivity a,ResponseCallback<String> callback);
}
