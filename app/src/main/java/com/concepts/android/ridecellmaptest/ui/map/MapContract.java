package com.concepts.android.ridecellmaptest.ui.map;

import com.concepts.android.ridecellmaptest.BasePresenter;
import com.concepts.android.ridecellmaptest.BaseView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import android.os.Handler;

/**
 * Created by Siris on 3/13/2017.
 */

public interface MapContract {
    // TODO: 3/13/2017 Setup directions API logic
    interface View extends BaseView {
        void addStep(LatLng latlng);
        void drawRoute();
    }

    interface Presenter extends BasePresenter<View> {
        void getDirections(LatLng origin, LatLng destination, double speed);
        void updateCoordinates(List<LatLng> latLngList, Handler handler, String speed);
    }
}
