package com.concepts.android.ridecellmaptest.di;

import com.concepts.android.ridecellmaptest.ui.input.MainContract;
import com.concepts.android.ridecellmaptest.ui.input.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Siris on 3/13/2017.
 */

@Module
public class AppModule {
    @Provides
    public MainPresenter provideMainPresenter(){
        return new MainPresenter();
    }
}
