package com.demoapp.module;

import android.content.Context;

import com.demoapp.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shreeji on 10/3/2016.
 */
@Module
public class AppModule {

    MyApplication mApplication;

    public AppModule(MyApplication mApplication){
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    MyApplication providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return mApplication;
    }


}
