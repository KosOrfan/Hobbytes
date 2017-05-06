package com.example.ntinos.hobbytes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterPoint extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_point);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        TextView name = (TextView) findViewById(R.id.textView);
        EditText nickName = (EditText) findViewById(R.id.editText);
        EditText info = (EditText) findViewById(R.id.editText1);

    }


}
