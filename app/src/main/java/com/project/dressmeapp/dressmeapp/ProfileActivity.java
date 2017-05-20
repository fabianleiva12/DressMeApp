package com.project.dressmeapp.dressmeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView tv1, tv2;
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
        tv1.setText(correo);
        tv2.setText(pass);

    }

    public void VerFotos (View v) {
        Intent i=new Intent(this,PhotoActivity.class);
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


    public void finalizar(View v) {
        finish();
    }




}
