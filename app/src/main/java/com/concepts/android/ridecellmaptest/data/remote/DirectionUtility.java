package com.concepts.android.ridecellmaptest.data.remote;

import com.concepts.android.ridecellmaptest.data.remote.service.DirectionService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Siris on 3/13/2017.
 */

public class DirectionUtility {

    private static final String BASE_URL = "https://maps.googleapis.com";

    private DirectionService service;

    public DirectionUtility() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        service = retrofit.create(DirectionService.class);
    }

    public DirectionService getService() {
        return service;
    }
}
