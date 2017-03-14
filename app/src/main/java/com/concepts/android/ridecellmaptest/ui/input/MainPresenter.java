package com.concepts.android.ridecellmaptest.ui.input;

import com.concepts.android.ridecellmaptest.data.entities.Coord;

/**
 * Created by Siris on 3/13/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    @Override
    public void addView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void validateInput(Coord origin, Coord destination, double speed) {
        // TODO: 3/13/2017 Only set coordinate with valid data
        if (!origin.validate() || !destination.validate()){
            view.invalidCoordinates();
            return;
        }

        view.validCoordinates(origin, destination, speed);
    }
}
