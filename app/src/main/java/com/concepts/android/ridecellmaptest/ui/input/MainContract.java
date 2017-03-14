package com.concepts.android.ridecellmaptest.ui.input;

import com.concepts.android.ridecellmaptest.BasePresenter;
import com.concepts.android.ridecellmaptest.BaseView;
import com.concepts.android.ridecellmaptest.data.Coord;

/**
 * Created by Siris on 3/13/2017.
 */

public interface MainContract {
    interface View extends BaseView{
        void showMarkers();
        void validCoordinates(Coord origin, Coord destination, double speed);
        void invalidCoordinates();
    }

    interface Presenter extends BasePresenter<View>{
        void validateInput(Coord origin, Coord destination, double speed);

    }
}
