package com.jack.android.template.core.http.service;

import android.app.admin.DeviceAdminInfo;

import com.jack.android.template.core.http.DataApi;
import com.jack.android.template.core.http.HttpManager;
import com.jack.android.template.core.http.ResponseCallback;
import com.jack.android.template.core.http.RxUtils;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.RxFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import dagger.internal.DaggerCollections;
import okhttp3.Response;
import rx.functions.Action1;

/**
 * Created by hou on 2017/6/14.14:11
 * introduce :
 */

public class UserService implements IUserService {

    @Inject
    DataApi dataApi;

    public UserService() {
        DaggerDataApiComponent.builder().httpManager(HttpManager.getInstance()).build().inject(this);
    }

    @Override
    public void getUserInfo(RxAppCompatActivity a, ResponseCallback<String> callback) {
        dataApi.getUserInfo().compose(RxUtils.<String>applySchedulers())
                .compose(a.<String>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }
}
