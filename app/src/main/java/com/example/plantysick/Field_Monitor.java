package com.example.plantysick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Field_Monitor extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap gmap;
    FrameLayout map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_monitor);
        map = findViewById(R.id.map);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
    this.gmap = googleMap;
        LatLng mapBangladesh = new LatLng(23.6850,90.3563);
        this.gmap.addMarker(new MarkerOptions().position(mapBangladesh).title("Marker in Bangladessh"));
        this.gmap.moveCamera(CameraUpdateFactory.newLatLng(mapBangladesh));
    }
}