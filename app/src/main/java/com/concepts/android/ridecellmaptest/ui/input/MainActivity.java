package com.concepts.android.ridecellmaptest.ui.input;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.concepts.android.ridecellmaptest.App;
import com.concepts.android.ridecellmaptest.R;
import com.concepts.android.ridecellmaptest.data.entities.Coord;
import com.concepts.android.ridecellmaptest.ui.map.MapActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    public static final String KEY_ORIGIN = "KEY_ORIGIN";
    public static final String KEY_DESTINATION = "KEY_DESTINATION";
    public static final String KEY_SPEED = "KEY_SPEED";

    @BindView(R.id.main_origin)
    EditText editTextOrigin;
    @BindView(R.id.main_destination)
    EditText editTextDestination;
    @BindView(R.id.main_speed)
    EditText editTextSpeed;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupDaggerComponent();

        ButterKnife.bind(this);

        mainPresenter.addView(this);
    }

    private void setupDaggerComponent() {
        ((App) getApplication()).getAppComponent().inject(this);
    }

    @OnClick(R.id.main_btn)
    public void btnClicked() {
        // TODO: 3/13/2017 Validate strings instead of coordinates
        // TODO: 3/13/2017 Use the Google Places
        Coord origin = new Coord(editTextOrigin.getText().toString().split(",")[0],
                editTextOrigin.getText().toString().split(",")[1]);
        Coord destination = new Coord(editTextDestination.getText().toString().split(",")[0],
                editTextDestination.getText().toString().split(",")[1]);

        String s = editTextSpeed.getText().toString();

        mainPresenter.validateInput(origin, destination, Double.valueOf(s));
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMarkers() {

    }

    @Override
    public void validCoordinates(Coord origin, Coord destination, double speed) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(KEY_ORIGIN, origin);
        intent.putExtra(KEY_DESTINATION, destination);
        intent.putExtra(KEY_SPEED, speed);
        startActivity(intent);
    }

    @Override
    public void invalidCoordinates() {
        Toast.makeText(this, "Invalid coordinates", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mainPresenter.removeView();
    }
}
