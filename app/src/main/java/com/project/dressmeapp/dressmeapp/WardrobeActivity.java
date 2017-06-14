package com.project.dressmeapp.dressmeapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class WardrobeActivity extends AppCompatActivity {

    public static final int IMAGE_GALLERY_REQUEST = 20;
    private ImageView Head, Torso, Leg, Feet;
    private int Tipo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe);
        Head =(ImageView)findViewById(R.id.Head);
        Torso =(ImageView)findViewById(R.id.Torso);
        Leg =(ImageView)findViewById(R.id.Leg);
        Feet =(ImageView)findViewById(R.id.Feet);
    }

    public void option1(View v){
        Tipo = 1;
        onImageGalleryClicked(v);
    }

    public void option2(View v){
        Tipo = 2;
        onImageGalleryClicked(v);
    }

    public void option3(View v){
        Tipo = 3;
        onImageGalleryClicked(v);
    }

    public void option4(View v){
        Tipo = 4;
        onImageGalleryClicked(v);
    }

    public void onImageGalleryClicked(View v){
        //Se invoca la galeria utilizando un intento implicito
        Intent photoPickerIntent = new Intent (Intent.ACTION_PICK);
        File myDir = null;
        //directorio
        if (Tipo == 1){
            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
            myDir = new File(root + "/DressMeApp/Head");
        }
        else if (Tipo == 2){
            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
            myDir = new File(root + "/DressMeApp/Torso");

        }
        else if (Tipo == 3){
            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
            myDir = new File(root + "/DressMeApp/Legs");
        }
        else{
            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
            myDir = new File(root + "/DressMeApp/Feet");
        }
        //File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        //String pictureDirectoryPath = pictureDirectory.getPath();
        String pictureDirectoryPath = myDir.getPath();
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
                    if (Tipo == 1){
                        Head.setImageBitmap(scaleDownBitmapImage(image, 200, 200));
                    }
                    else if (Tipo == 2){
                        Torso.setImageBitmap(scaleDownBitmapImage(image, 200, 200));
                    }
                    else if (Tipo == 3){
                        Leg.setImageBitmap(scaleDownBitmapImage(image, 200, 200));
                    }
                    else{
                        Feet.setImageBitmap(scaleDownBitmapImage(image, 200, 200));
                    }

                    //profilePic.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    // show a message to the user indictating that the image is unavailable.
                    Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    private Bitmap scaleDownBitmapImage(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
        return resizedBitmap;
    }
}
