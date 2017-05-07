package com.example.ntinos.hobbytes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;

public class RegisterPoint extends AppCompatActivity {


    public static boolean registerPointOpened = false;
    private static int RESULT_LOAD_IMAGE = 1;
    private final String FILEPATH = "FilePath";

    String path;

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
                LatLng mLocation = new LatLng(41, 23);
                Double mLang = 33.1;
                Double mLong = 20.1;

                registerPointOpened = true;

                //Build an intent and tranfer the data.
                intent.putExtra("Name", nameText.getText().toString());
                intent.putExtra("Info", infoText.getText().toString());
                intent.putExtra("location", mLocation);
                intent.putExtra("mlong", mLong);
                intent.putExtra("mlang", mLang);

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
}
