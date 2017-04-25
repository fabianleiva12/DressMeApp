package com.project.dressmeapp.dressmeapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

import static android.R.attr.data;

public class PhotoActivity extends AppCompatActivity {

    private ImageView imagen1;
    private EditText et1;
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       // Uri uri = Uri.fromFile();
        Bitmap bitmap1 = BitmapFactory.decodeFile(data.getDataString());
        imagen1.setImageBitmap(bitmap1);

    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imagen1=(ImageView)findViewById(R.id.imageView);
        et1=(EditText)findViewById(R.id.editText);
    }

    public void tomarFoto(View v) {
        Intent intento1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File foto = new File(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.getPath(), et1.getText().toString());
        intento1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));
        startActivityForResult(intento1, 66);
    }

    public void recuperarFoto(View v) {
        Bitmap bitmap1 = BitmapFactory.decodeFile(getExternalFilesDir(null)+"/"+et1.getText().toString());
        imagen1.setImageBitmap(bitmap1);
    }

    public void ver(View v) {
        Intent intento1=new Intent(this,CameraActivity.class);
        startActivity(intento1);
    }

}
