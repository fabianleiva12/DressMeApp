package com.project.dressmeapp.dressmeapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {
    public static final int IMAGE_GALLERY_REQUEST = 20;
    private TextView tv1, tv2;
    private ImageView profilePic;
    private ImageView Head1;
    //private TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Bundle bundle = getIntent().getExtras();
        String correo=bundle.getString("correo");
        String pass=bundle.getString("password");
        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        Head1 =(ImageView)findViewById(R.id.head_one);
        profilePic =(ImageView)findViewById(R.id.profilepicture);
        tv1.setText(correo);
        //tv2.setText(pass);




    }


    public void onImageGalleryClicked(View v){
        //Se invoca la galeria utilizando un intento implicito
        Intent photoPickerIntent = new Intent (Intent.ACTION_PICK);
        //directorio
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        //Representar como URI
        Uri data = Uri.parse(pictureDirectoryPath);
        //Todos los tipos de imagenes sirven
        photoPickerIntent.setDataAndType(data, "image/*");
        //Invocaremos esta actividad y recibiremos algo de esta
        startActivityForResult(photoPickerIntent,IMAGE_GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // if we are here, everything processed successfully.
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                // if we are here, we are hearing back from the image gallery.

                // the address of the image on the SD Card.
                Uri imageUri = data.getData();

                // declare a stream to read the image data from the SD Card.
                InputStream inputStream;

                // we are getting an input stream, based on the URI of the image.
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);

                    // get a bitmap from the stream.
                    Bitmap image = BitmapFactory.decodeStream(inputStream);


                    // show the image to the user
                    profilePic.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    // show a message to the user indictating that the image is unavailable.
                    Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    public void TakeHeadPhoto (View v) {
        Intent i=new Intent(this,HeadPhotoActivity.class);
        startActivity(i);
    }

    public void TakeTorsoPhoto (View v) {
        Intent i=new Intent(this,TorsoPhotoActivity.class);
        startActivity(i);
    }

    public void TakeLegsPhoto (View v) {
        Intent i=new Intent(this,LegsPhotoActivity.class);
        startActivity(i);
    }

    public void TakeFeetPhoto (View v) {
        Intent i=new Intent(this,FeetPhotoActivity.class);
        startActivity(i);
    }


    public void finalizar(View v) {
        finish();
    }




}
