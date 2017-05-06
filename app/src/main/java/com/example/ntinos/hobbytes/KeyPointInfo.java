package com.example.ntinos.hobbytes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import static com.example.ntinos.hobbytes.MapsActivity.EXTRA_NAME;

public class KeyPointInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_point_info);

        String KpName = getIntent().getStringExtra(EXTRA_NAME);
        String KpSnip = getIntent().getExtras().getString("Snippet");

        TextView KeyPointName = (TextView)findViewById(R.id.KpInformation);
        KeyPointName.setText(KpSnip);
        TextView KeyPointSnip = (TextView)findViewById(R.id.KpName);
        KeyPointSnip.setText(KpName);

        ImageView KeyPointPic = (ImageView) findViewById(R.id.imageView2);
        KeyPointPic.setImageResource(R.drawable.sea);


    }
}
