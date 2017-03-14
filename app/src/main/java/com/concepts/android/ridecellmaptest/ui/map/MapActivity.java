package com.concepts.android.ridecellmaptest.ui.map;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.concepts.android.ridecellmaptest.R;
import com.concepts.android.ridecellmaptest.data.Coord;
import com.concepts.android.ridecellmaptest.di.DaggerMapComponent;
import com.concepts.android.ridecellmaptest.di.MapModule;
import com.concepts.android.ridecellmaptest.ui.input.MainActivity;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import javax.inject.Inject;
import javax.xml.transform.stream.StreamResult;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback,MapContract.View {

    private GoogleMap mMap;

    private Coord origin;
    private Coord destination;
    private Double speed;

    private PolylineOptions polylineOptions;
    private CircleOptions circleOptions;

    private Circle circle;
    private Handler handler;

    @Inject MapPresenter mapPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getInfoFromIntent(getIntent());

        setupDaggerComponent();

        circleOptions = new CircleOptions();
        polylineOptions = new PolylineOptions();

        mapPresenter.addView(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LatLng position = msg.getData().getParcelable("LATLNG");
                updateLocation(position);
            }
        };
    }

    private void setupDaggerComponent() {
        DaggerMapComponent.builder()
                .mapModule(new MapModule())
                .build()
                .inject(this);
    }

    private void getInfoFromIntent(Intent intent) {
        origin = intent.getParcelableExtra(MainActivity.KEY_ORIGIN);
        destination = intent.getParcelableExtra(MainActivity.KEY_DESTINATION);
        speed = intent.getDoubleExtra(MainActivity.KEY_SPEED, 1.0);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng originLatLng = new LatLng(origin.getLatitude(), origin.getLongitude());
        LatLng destinationLatLng = new LatLng(destination.getLatitude(), destination.getLongitude());

        mMap.addMarker(new MarkerOptions().position(originLatLng).title("Origin"));
        mMap.addMarker(new MarkerOptions().position(destinationLatLng).title("Destination"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(originLatLng, 10));
        mapPresenter.getDirections(originLatLng, destinationLatLng, speed);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addStep(LatLng latlng) {
        polylineOptions.add(latlng);
    }

    @Override
    public void drawRoute() {
        polylineOptions.color(Color.BLUE);
        polylineOptions.width(15);
        mMap.addPolyline(polylineOptions);
        mapPresenter.updateCoordinates(polylineOptions.getPoints(), handler, String.valueOf(speed));
    }

    public void updateLocation(LatLng latLng) {
        if(circle != null){
            circle.remove();
        }
        circleOptions.center(latLng);
        circleOptions.fillColor(Color.GREEN);
        circleOptions.radius(10);
        circleOptions.strokeColor(Color.GREEN);
        circle = mMap.addCircle(circleOptions);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapPresenter.removeView();
    }
}
