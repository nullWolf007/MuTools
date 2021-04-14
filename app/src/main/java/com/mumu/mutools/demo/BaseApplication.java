package com.mumu.mutools.demo;

import android.app.Application;

import com.mumu.kernel.MuTool;

/**
 * Created by nullWolf on 2019/10/31.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MuTool.init(this);
    }
}
