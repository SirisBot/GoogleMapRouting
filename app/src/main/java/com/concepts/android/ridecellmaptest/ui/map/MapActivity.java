package com.concepts.android.ridecellmaptest.ui.map;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.concepts.android.ridecellmaptest.R;
import com.concepts.android.ridecellmaptest.data.Coord;
import com.concepts.android.ridecellmaptest.ui.input.MainActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Coord origin;
    private Coord destination;
    private Double speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getInfoFromIntent(getIntent());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getInfoFromIntent(Intent intent) {
        origin = intent.getParcelableExtra(MainActivity.KEY_ORIGIN);
        destination = intent.getParcelableExtra(MainActivity.KEY_DESTINATION);
        speed = intent.getDoubleExtra(MainActivity.KEY_SPEED, 1.0);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng originLatLng = new LatLng(origin.getLatitude(), origin.getLatitude());
        LatLng destinationLatLng = new LatLng(destination.getLatitude(), destination.getLongitude());

        mMap.addMarker(new MarkerOptions().position(originLatLng).title("Origin"));
        mMap.addMarker(new MarkerOptions().position(destinationLatLng).title("Destination"));
    }
}
