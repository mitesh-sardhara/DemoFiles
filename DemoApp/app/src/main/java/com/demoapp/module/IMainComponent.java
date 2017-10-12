package com.demoapp.module;


import com.demoapp.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Shreeji on 10/4/2016.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface IMainComponent {

    void inject(MainActivity homeActivity);

}
