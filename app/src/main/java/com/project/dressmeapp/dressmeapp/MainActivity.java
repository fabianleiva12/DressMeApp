package com.project.dressmeapp.dressmeapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);

    }

    public void Ingresar (View v) {
        Intent i=new Intent(this,ProfileActivity.class);
        i.putExtra("correo", et1.getText().toString());
        i.putExtra("password", et2.getText().toString());
        startActivity(i);

    }



}
