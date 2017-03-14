package com.concepts.android.ridecellmaptest;

import android.app.Application;

import com.concepts.android.ridecellmaptest.di.AppComponent;
import com.concepts.android.ridecellmaptest.di.AppModule;
import com.concepts.android.ridecellmaptest.di.DaggerAppComponent;

/**
 * Created by Siris on 3/13/2017.
 */

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
