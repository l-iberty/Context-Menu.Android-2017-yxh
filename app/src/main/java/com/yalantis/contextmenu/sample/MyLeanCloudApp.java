package com.yalantis.contextmenu.sample;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by liberty on 2017/7/20.
 */

public class MyLeanCloudApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"NSi3zw31GKknPBYg2m96zwEt-gzGzoHsz","JbuDhMWEAwHWgOgzXYalJnLb");

        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
        AVOSCloud.setDebugLogEnabled(true);
    }
}
