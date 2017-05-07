package com.example.ntinos.hobbytes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

public class RegisterPoint extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    public static boolean registerPointOpened = false;
    private static int RESULT_LOAD_IMAGE = 1;
    private final String FILEPATH = "FilePath";
    GoogleApiClient mGoogleApiClient;
    String path;
    Location mLastLocation;
    LatLng mLocation;
    Double mLat;
    Double mLong ;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_point);

        prefs = this.getSharedPreferences("com.example.ntinos", Context.MODE_PRIVATE);

        final ImageButton imageBtn = (ImageButton) findViewById(R.id.imageButton);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText infoText = (EditText) findViewById(R.id.infoText);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        //Connecting to googleplay services

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


        //Image button event
        imageBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        //Submit button event
        Button SubmitButton = (Button) findViewById(R.id.btnSubmit);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterPoint.this, MapsActivity.class);

                //Get marker Info


                registerPointOpened = true;

                //Build an intent and tranfer the data.
                intent.putExtra("Name", nameText.getText().toString());
                intent.putExtra("Info", infoText.getText().toString());
                intent.putExtra("location", mLocation);
                intent.putExtra("mlong", mLong);
                intent.putExtra("mlang", mLat);

                //Code to transfer image as Extra
                //TODO: Ορισμος τροπου μεταφορας της εικονας
                //Bitmap bitmap = BitmapFactory.decodeResource(imageView.getResources(),0);
                //ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);
                //byte[] b = baos.toByteArray();

                //String imagePath = "";
                //try {
                //    FileOutputStream fileOutStream = openFileOutput(imagePath, MODE_PRIVATE);
                //    fileOutStream.write(b);
                //    fileOutStream.close();
                //} catch (IOException ioe) {
                //    ioe.printStackTrace();
                //}
                //intent.putExtra("Image", imagePath);

                //TODO: Get current location and transfer LATLANG as extra.


                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == 1) {
            final Bundle extras = data.getExtras();

            if (extras != null) {
                CheckedUtils.run(() -> imageView.setImageDrawable(new BitmapDrawable(getContentResolver().openInputStream(data.getData()))));
            }
        }
    }

    //GOOGLE LOCATION API FUNC

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Call get location method
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mLat = mLastLocation.getLatitude();
            mLong = mLastLocation.getLongitude();

        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

}
