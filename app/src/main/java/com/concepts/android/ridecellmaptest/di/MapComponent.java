package com.concepts.android.ridecellmaptest.di;

import com.concepts.android.ridecellmaptest.ui.map.MapActivity;
import com.concepts.android.ridecellmaptest.ui.map.MapPresenter;

import dagger.Component;

/**
 * Created by Siris on 3/13/2017.
 */

@Component(modules = MapModule.class)
public interface MapComponent {
    void inject(MapPresenter mapPresenter);
    void inject(MapActivity mapActivity);
}
