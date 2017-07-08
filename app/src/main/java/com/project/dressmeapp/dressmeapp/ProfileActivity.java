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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {
    public static final int IMAGE_GALLERY_REQUEST = 20;
    private TextView tv1, tv2;
    private ImageView profilePic;
    private ImageView Head1, Head2, Head3, Torso1, Torso2, Torso3, Leg1, Leg2, Leg3, Feet1, Feet2, Feet3;
    private int nArchivo = 0;
    String url = "http://demo5993971.mockable.io/userdata";
    String headUrl = "http://demo5993971.mockable.io/myHeadPhotos";
    String torsoUrl = "http://demo5993971.mockable.io/myTorsoPhotos";
    String legsUrl = "http://demo5993971.mockable.io/LegsWardrove";
    String feetUrl = "http://demo5993971.mockable.io/myFeetPhotos";
    // This string will hold the results
    String data = "";
    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;
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
        Head2 =(ImageView)findViewById(R.id.head_two);
        Head3 =(ImageView)findViewById(R.id.head_three);
        Torso1 =(ImageView)findViewById(R.id.torso_one);
        Torso2 =(ImageView)findViewById(R.id.torso_two);
        Torso3 =(ImageView)findViewById(R.id.torso_three);
        Leg1 =(ImageView)findViewById(R.id.leg_one);
        Leg2 =(ImageView)findViewById(R.id.leg_two);
        Leg3 =(ImageView)findViewById(R.id.leg_three);
        Feet1 =(ImageView)findViewById(R.id.feet_one);
        Feet2 =(ImageView)findViewById(R.id.feet_two);
        Feet3 =(ImageView)findViewById(R.id.feet_three);
        profilePic =(ImageView)findViewById(R.id.profilepicture);
        //tv1.setText(correo);
        // Creates the Volley request queue
        requestQueue = Volley.newRequestQueue(this);
        // Creating the JsonObjectRequest class called obreq, passing required parameters:
        //GET is used to fetch data from the server, JsonURL is the URL to be fetched from.
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, url, null,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONObject obj = response.getJSONObject("colorObject");
                            // Retrieves the string labeled "colorName" and "description" from
                            //the response JSON Object
                            //and converts them into javascript objects
                            String name = response.getString("name");
                            String email = response.getString("email");
                            String description = response.getString("description");
                            String profilePicUrl = response.getString("picture");

                            // Adds strings from object to the "data" string
                            data += "Nombre: " + name +
                                    "\nEmail: " + email;
                            Picasso.with(getApplicationContext()).load(profilePicUrl).resize(100,100).into(profilePic);

                            tv2.setText(description);
                            // Adds the data string to the TextView "results"
                            tv1.setText(data);
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON object request "obreq" to the request queue
        requestQueue.add(obreq);


        //tv2.setText(pass);
        loadHeadphotos();
        loadTorsophotos();
        loadLegphotos();
        loadFeetphotos();

    }


    public void loadHeadphotos(){
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, headUrl, null,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONObject obj = response.getJSONObject("colorObject");
                            // Retrieves the string labeled "colorName" and "description" from
                            //the response JSON Object
                            //and converts them into javascript objects
                            String imageUrl = response.getString("photo");
                            Picasso.with(getApplicationContext()).load(imageUrl).resize(100,100).into(Head1);
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON object request "obreq" to the request queue
        requestQueue.add(obreq);

        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/DressMeApp/Head";
        File dir = new File(root+File.separator);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                // Do something with child

                if (child != null){

                    //Uri uri = Uri.fromFile(child);
                    //Picasso.with(this).load(uri).into(Head1);
                    //Head1.setImageURI(uri);
                    nArchivo++;
                    if (nArchivo ==1){
                        Bitmap bitmap = BitmapFactory.decodeFile(child.getPath());
                        Head2.setImageBitmap(scaleDownBitmapImage(bitmap, 100, 100));
                    }
                    if (nArchivo ==2){
                        Bitmap bitmap = BitmapFactory.decodeFile(child.getPath());
                        Head3.setImageBitmap(scaleDownBitmapImage(bitmap, 100, 100));
                        break;
                    }
                    /*
                    if (nArchivo ==3){
                        Bitmap bitmap = BitmapFactory.decodeFile(child.getPath());
                        Head3.setImageBitmap(scaleDownBitmapImage(bitmap, 100, 100));
                        break;
                    }*/

                }

            }
        }
        nArchivo = 0;
    }


    public void loadTorsophotos(){
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, torsoUrl, null,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONObject obj = response.getJSONObject("colorObject");
                            // Retrieves the string labeled "colorName" and "description" from
                            //the response JSON Object
                            //and converts them into javascript objects
                            String imageUrl = response.getString("photo");
                            Picasso.with(getApplicationContext()).load(imageUrl).resize(100,100).into(Torso1);
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON object request "obreq" to the request queue
        requestQueue.add(obreq);
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/DressMeApp/Torso";
        File dir = new File(root+File.separator);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                // Do something with child

                if (child != null){

                    //Uri uri = Uri.fromFile(child);
                    //Picasso.with(this).load(uri).into(Head1);
                    //Head1.setImageURI(uri);
                    nArchivo++;
                    if (nArchivo ==1){
                        Bitmap bitmap = BitmapFactory.decodeFile(child.getPath());
                        Torso2.setImageBitmap(scaleDownBitmapImage(bitmap, 100, 100));
                    }
                    if (nArchivo ==2){
                        Bitmap bitmap = BitmapFactory.decodeFile(child.getPath());
                        Torso3.setImageBitmap(scaleDownBitmapImage(bitmap, 100, 100));
                        break;
                    }
                    /*
                    if (nArchivo ==3){
                        Bitmap bitmap = BitmapFactory.decodeFile(child.getPath());
                        Torso3.setImageBitmap(scaleDownBitmapImage(bitmap, 100, 100));
                        break;
                    }*/

                }

            }
        }
        nArchivo = 0;
    }

    public void loadLegphotos(){
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, legsUrl, null,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONObject obj = response.getJSONObject("colorObject");
                            // Retrieves the string labeled "colorName" and "description" from
                            //the response JSON Object
                            //and converts them into javascript objects
                            String imageUrl = response.getString("photo");
                            Picasso.with(getApplicationContext()).load(imageUrl).resize(100,100).into(Leg1);
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON object request "obreq" to the request queue
        requestQueue.add(obreq);
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/DressMeApp/Legs";
        File dir = new File(root+File.separator);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                // Do something with child

                if (child != null){

                    //Uri uri = Uri.fromFile(child);
                    //Picasso.with(this).load(uri).into(Head1);
                    //Head1.setImageURI(uri);
                    nArchivo++;
                    if (nArchivo ==1){
                        Bitmap bitmap = BitmapFactory.decodeFile(child.getPath());
                        Leg2.setImageBitmap(scaleDownBitmapImage(bitmap, 100, 100));
                    }
                    if (nArchivo ==2){
                        Bitmap bitmap = BitmapFactory.decodeFile(child.getPath());
                        Leg3.setImageBitmap(scaleDownBitmapImage(bitmap, 100, 100));
                        break;
                    }
                    /*
                    if (nArchivo ==3){
                        Bitmap bitmap = BitmapFactory.decodeFile(child.getPath());
                        Leg3.setImageBitmap(scaleDownBitmapImage(bitmap, 100, 100));
                        break;
                    }*/

                }

            }
        }
        nArchivo = 0;
    }


    public void loadFeetphotos(){
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, feetUrl, null,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONObject obj = response.getJSONObject("colorObject");
                            // Retrieves the string labeled "colorName" and "description" from
                            //the response JSON Object
                            //and converts them into javascript objects
                            String imageUrl = response.getString("photo");
                            Picasso.with(getApplicationContext()).load(imageUrl).resize(100,100).into(Feet1);
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON object request "obreq" to the request queue
        requestQueue.add(obreq);
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/DressMeApp/Feet";
        File dir = new File(root+File.separator);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                // Do something with child

                if (child != null){

                    //Uri uri = Uri.fromFile(child);
                    //Picasso.with(this).load(uri).into(Head1);
                    //Head1.setImageURI(uri);
                    nArchivo++;
                    if (nArchivo ==1){
                        Bitmap bitmap = BitmapFactory.decodeFile(child.getPath());
                        Feet2.setImageBitmap(scaleDownBitmapImage(bitmap, 100, 100));
                    }
                    if (nArchivo ==2){
                        Bitmap bitmap = BitmapFactory.decodeFile(child.getPath());
                        Feet3.setImageBitmap(scaleDownBitmapImage(bitmap, 100, 100));
                        break;
                    }
                    /*
                    if (nArchivo ==3){
                        Bitmap bitmap = BitmapFactory.decodeFile(child.getPath());
                        Feet3.setImageBitmap(scaleDownBitmapImage(bitmap, 100, 100));
                        break;
                    }*/

                }

            }
        }
        nArchivo = 0;
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
                    profilePic.setImageBitmap(scaleDownBitmapImage(image, 200, 200));
                    //profilePic.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    // show a message to the user indictating that the image is unavailable.
                    Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    public void Closet (View v) {
        Intent i=new Intent(this,WardrobeActivity.class);
        startActivity(i);
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

    private Bitmap scaleDownBitmapImage(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
        return resizedBitmap;
    }


    public void finalizar(View v) {
        finish();
    }




}
