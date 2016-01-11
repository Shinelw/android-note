package com.shinelw.securitypay;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.baidu.mapapi.SDKInitializer;

/**
 * Created by shinelw on 1/4/16.
 */
public class MyApplication extends Application {

    private static final String APP_ID = "CQ6ggAH3BSxrvatgKwa2d9oW-gzGzoHsz";
    private static final String APP_KEY = "pePFPeE10hn5BpAn8CRaxlkL";

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, APP_ID, APP_KEY);
        SDKInitializer.initialize(this);
    }


}
