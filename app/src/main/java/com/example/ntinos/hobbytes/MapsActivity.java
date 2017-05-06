package com.example.ntinos.hobbytes;

import android.content.Intent;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback ,GoogleMap.OnMarkerClickListener{

    public static final String EXTRA_NAME = null;
    private GoogleMap mMap;
    private Marker mDef;
    private Marker mRandom;
    private static final LatLng DEF = new LatLng(40,22);
    private static final LatLng RND = new LatLng(41,21);


    private FloatingActionButton myFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        myFAB = (FloatingActionButton) this.findViewById(R.id.floatingActionButton2);
        myFAB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(MapsActivity.this, KeyPoint.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Test Marker init
        mDef = mMap.addMarker(new MarkerOptions().position(DEF).title("Plateia").snippet("PLATEIAPLATEIAPLATEIAAAA"));
        mRandom = mMap.addMarker(new MarkerOptions().position(RND).title("Def3").snippet("STuff 1"));


        // Add a marker in Sydney and move the camera
        LatLng defMark = new LatLng(41, 23);
        mMap.addMarker(new MarkerOptions().position(defMark).title("Default Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(defMark));
        mMap.setOnMarkerClickListener(this);

    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        //on marker Click event.
        Intent intent = new Intent(this,KeyPointInfo.class); //Activity1.this
        //Get marker Info
        String mName= marker.getTitle();
        String mSnip = marker.getSnippet();
        //Build an intent and tranfer the data.
        intent.putExtra(EXTRA_NAME,mName) ;
        intent.putExtra("Snippet",mSnip);

        //Start the KeypointINfo Activity
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