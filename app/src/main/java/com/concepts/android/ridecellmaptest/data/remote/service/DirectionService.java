package com.concepts.android.ridecellmaptest.data.remote.service;

import com.concepts.android.ridecellmaptest.data.entities.Result;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Siris on 3/13/2017.
 */

public interface DirectionService {
    @GET("maps/api/directions/json")
    Observable<Result> getDirections (@Query("origin") String origin, @Query("destination") String destination, @Query("key") String key);
}
