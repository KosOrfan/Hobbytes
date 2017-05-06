package com.example.ntinos.hobbytes;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback ,GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    private Marker mDef;
    private Marker mRandom;
    private static final LatLng DEF = new LatLng(40,22);
    private static final LatLng RND = new LatLng(41,21);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mDef = mMap.addMarker(new MarkerOptions().position(DEF).title("Def2").snippet("STuff 2"));
        mRandom = mMap.addMarker(new MarkerOptions().position(RND).title("Def3").snippet("STuff 1"));


        // Add a marker in Sydney and move the camera
        LatLng defMark = new LatLng(41, 23);
        mMap.addMarker(new MarkerOptions().position(defMark).title("Default Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(defMark));
        mMap.setOnMarkerClickListener(this);

    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        Intent intent = new Intent(this,KeyPoint.class);
        startActivity(intent);

        return false;
    }
}


/* I want to be able to click on "InfoWindow" to open a new activity so I can add more information.
*  Mymap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
               Intent intent = new Intent(MapActivity.this,OtherActivity.class);
               startActivity(intent);


            }
        });
* */