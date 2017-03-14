package com.concepts.android.ridecellmaptest.di;

import com.concepts.android.ridecellmaptest.ui.input.MainActivity;

import dagger.Component;

/**
 * Created by Siris on 3/13/2017.
 */

@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
