package com.example.ntinos.hobbytes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.Map;

public class RegisterPoint extends AppCompatActivity {


    public static boolean registerPointOpened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_point);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        TextView name = (TextView) findViewById(R.id.textView);
        EditText nickName = (EditText) findViewById(R.id.editText);


        Button SubmitButton = (Button) findViewById(R.id.ButtonClick);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (RegisterPoint.this, MapsActivity.class); //Activity1.this
                //Get marker Info
                String mName= "NEWMARKER";
                String mSnip ="NEWINFO";
                LatLng mLocation = new LatLng(41,23);
                Double mLang = 33.1;
                Double mLong = 20.1;

                registerPointOpened = true;

                //Build an intent and tranfer the data.
                intent.putExtra("Name",mName) ;
                intent.putExtra("Info",mSnip);
                intent.putExtra("location",mLocation);
                intent.putExtra("mlong",mLong);
                intent.putExtra("mlang",mLang);
                startActivity(intent);
            }
        });


    }


}
