package com.concepts.android.ridecellmaptest.ui.map;

import com.concepts.android.ridecellmaptest.data.entities.Leg;
import com.concepts.android.ridecellmaptest.data.entities.Result;
import com.concepts.android.ridecellmaptest.data.entities.Route;
import com.concepts.android.ridecellmaptest.data.entities.Step;
import com.concepts.android.ridecellmaptest.di.DaggerMapComponent;
import com.concepts.android.ridecellmaptest.di.MapModule;
import com.concepts.android.ridecellmaptest.data.remote.DirectionUtility;
import com.concepts.android.ridecellmaptest.util.helper.MovementThread;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import java.util.List;
import android.os.Handler;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Siris on 3/13/2017.
 */

public class MapPresenter implements MapContract.Presenter {

    private static final String KEY = "AIzaSyA5fap3jzLCV0XL4iZGLkU9I5AB0YAb1H8";

    @Inject
    DirectionUtility directionUtility;

    private MapContract.View view;
    private Subscription subscription;
    private MovementThread movementThread;

    public MapPresenter() {
        DaggerMapComponent.builder()
                .mapModule(new MapModule())
                .build()
                .inject(this);
    }

    @Override
    public void addView(MapContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void getDirections(LatLng originCoord, LatLng destinationCoord, double speed) {
        String origin = String.valueOf(originCoord.latitude) + "," + String.valueOf(originCoord.longitude);
        String destination = String.valueOf(destinationCoord.latitude) + "," + String.valueOf(destinationCoord.longitude);

        Observer<Result> directionObserver = new Observer<Result>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError(e.getMessage());
            }

            @Override
            public void onNext(Result result) {
                // TODO: 3/13/2017 Loop through route steps if routes > 0
                List<Route> routes = result.getRoutes();
                    if(routes.size() > 0){
                        List<Leg> legs = routes.get(0).getLegs();
                        if(legs.size() > 0) {
                            List<Step> steps = legs.get(0).getSteps();
                            for (Step step : steps) {
                                List<LatLng> latLngs = PolyUtil.decode(step.getPolyline().getPoints());
                                for(LatLng latLng : latLngs)
                                    // TODO: 3/13/2017 Add line for each step in route
                                    view.addStep(latLng);
                            }
                        }
                        // TODO: 3/13/2017 Draw final route composed of each step
                        view.drawRoute();
                    }
            }
        };

        // TODO: 3/13/2017 Subscribe to observable async call
        subscription = directionUtility.getService().getDirections(origin, destination, KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(directionObserver);
    }

    @Override
    public void updateCoordinates(List<LatLng> latLngList, Handler handler, String speed) {
        // TODO: 3/13/2017 Start thread to update position on map
        movementThread = new MovementThread(latLngList, handler, speed);
        movementThread.start();
    }
}
