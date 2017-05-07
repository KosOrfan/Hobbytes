package com.example.ntinos.hobbytes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    public static final String EXTRA_NAME = null;
    private static final LatLng DEF = new LatLng(40, 22);
    private static final LatLng RND = new LatLng(41, 21);
    private GoogleMap mMap;
    private Marker mDef;
    private Marker mRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton myFAB;

        myFAB = (FloatingActionButton) this.findViewById(R.id.floatingActionButton2);
        myFAB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MapsActivity.this, RegisterPoint.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (RegisterPoint.registerPointOpened) {

            //Get intent from Register
            String KpName = getIntent().getStringExtra("Name");
            String KpSnip = getIntent().getExtras().getString("Info");
            Double mLong = getIntent().getExtras().getDouble("mlong");
            Double mLang = getIntent().getExtras().getDouble("mlang");
            Marker userMarker;
            LatLng userLoc = new LatLng(mLang, mLong);
            userMarker = mMap.addMarker(new MarkerOptions().position(userLoc).title(KpName).snippet(KpSnip));
        }
        //Test Marker init
        mDef = mMap.addMarker(new MarkerOptions().position(DEF).title("KEYPOINT").snippet("KEYPOINT general information"));
        mRandom = mMap.addMarker(new MarkerOptions().position(RND).title("KEYPOINT2").snippet("KEYPOINT General Information"));

        mMap.setOnMarkerClickListener(this);

    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        //on marker Click event.
        Intent intent = new Intent(this, KeyPointInfo.class); //Activity1.this
        //Get marker Info
        String mName = marker.getTitle();
        String mSnip = marker.getSnippet();
        //Build an intent and tranfer the data.
        intent.putExtra(EXTRA_NAME, mName);
        intent.putExtra("Snippet", mSnip);

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