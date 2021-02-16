package com.example.carcompany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.carcompany.process.Credential;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;

    public void sendOnclickButton(View view){

        Intent intent = new Intent( MainActivity.this, IngresoProductos.class);
        startActivity(intent);
    }

    public void consultar(View view){
        Intent intent = new Intent(MainActivity.this, Consultar.class);
        startActivity(intent);
    }

    public void borrar(View view){

        Intent borrarIntent = new Intent(MainActivity.this, Eliminar.class);
        startActivity(borrarIntent);

    }

    public void modificar(View view){

        Intent intent = new Intent(MainActivity.this, Modificar.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar  = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#273036")));
        actionBar.setTitle("CAR SERVICES");

    }



}