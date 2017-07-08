package com.project.dressmeapp.dressmeapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText et1, et2;
    private ImageView iconPic;
    //RequestQueue queue;
   // String url = "http://demo5993971.mockable.io/login";
    String icon = "https://previews.123rf.com/images/sarahdesign/sarahdesign1410/sarahdesign141002842/32954550-Cloth-icon-Stock-Vector.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        iconPic = (ImageView)findViewById(R.id.icon);
        Picasso.with(getApplicationContext()).load(icon).resize(150,150).into(iconPic);
    }



    public void Ingresar (View v) {
        Intent i=new Intent(this,ProfileActivity.class);
        i.putExtra("correo", et1.getText().toString());
        i.putExtra("password", et2.getText().toString());
        startActivity(i);
    }



}
