package com.jack.android.template.core.http.service;

import com.jack.android.template.core.http.HttpManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hou on 2017/6/14.14:32
 * introduce :
 */

@Singleton
@Component(modules = {HttpManager.class})
public interface DataApiComponent {
    void inject(UserService userService);

    void inject(MessageService messageService);
}
