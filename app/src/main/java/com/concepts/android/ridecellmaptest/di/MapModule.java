package com.concepts.android.ridecellmaptest.di;

import com.concepts.android.ridecellmaptest.ui.map.MapPresenter;
import com.concepts.android.ridecellmaptest.data.remote.DirectionUtility;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Siris on 3/13/2017.
 */

@Module
public class MapModule {

    @Provides
    MapPresenter providesMapPresenter() {
        return new MapPresenter();
    }

    @Provides
    DirectionUtility provideDirectionUtility() {
        return new DirectionUtility();
    }
}
