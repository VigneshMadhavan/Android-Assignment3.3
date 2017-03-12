package com.vigneshtraining.assignment33;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private ImageView imgView;

    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button) findViewById(R.id.toogleBtn);
        imgView=(ImageView) findViewById(R.id.imageView);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("OnClicked","SSS");
        switch (v.getId()){
            case R.id.toogleBtn:
                /*if(isVisible){
                    imgView.setVisibility(View.INVISIBLE);
                    btn.setText(R.string.show);
                }else{
                    imgView.setVisibility(View.VISIBLE);
                    btn.setText(R.string.hide);
                }
                isVisible=!isVisible;*/
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                List activities = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_ALL);

                Boolean isIntentSafe = activities.size() > 0;

                if(isIntentSafe) {
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
                }else{
                    Toast.makeText(MainActivity.this, "No Image", Toast.LENGTH_LONG);
                }
                break;
            default:
                Toast.makeText(MainActivity.this, "Deffault", Toast.LENGTH_LONG);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                //selectedImagePath = getPath(selectedImageUri);
                imgView.setImageURI(selectedImageUri);
            }
        }else{
            Toast.makeText(MainActivity.this, "No Image Selected", Toast.LENGTH_LONG);
        }
    }
}
