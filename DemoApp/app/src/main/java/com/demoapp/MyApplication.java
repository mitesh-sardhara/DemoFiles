package com.demoapp;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.demoapp.module.AppModule;
import com.demoapp.module.DaggerIMainComponent;
import com.demoapp.module.IMainComponent;

/**
 * Created by Shreeji on 10/3/2016.
 */

@SuppressWarnings("deprecation")
public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        createComponent();
    }

    private IMainComponent mainComponent;

    public IMainComponent getMainComponent() {
        return mainComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(MyApplication.this);
    }

    protected void createComponent(){
        mainComponent = DaggerIMainComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }
}
