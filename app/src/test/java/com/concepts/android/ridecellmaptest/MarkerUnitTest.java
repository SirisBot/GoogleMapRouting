package com.concepts.android.ridecellmaptest;

import com.concepts.android.ridecellmaptest.data.entities.Coord;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Siris on 3/14/2017.
 */

public class MarkerUnitTest {

    String latitude, longitude;

    @Test
    public void validCoordinate() throws Exception {
        latitude = "33.751650";
        longitude = "-84.401388";

        Coord coord = new Coord(latitude, longitude);

        assertEquals(true, coord.validate());
    }

    @Test
    public void invalidCoordinate() throws Exception {
        latitude = "";
        longitude = "";

        Coord coord = new Coord(latitude, longitude);

        assertEquals(false, coord.validate());
    }
}
