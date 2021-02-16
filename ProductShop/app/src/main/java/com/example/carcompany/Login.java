package com.example.carcompany;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.carcompany.connections.ConnectionServer;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    ActionBar actionBar;


    private TextInputEditText userField;
    private TextInputEditText passField;

    public void initLoginButton(View view){

        userField = (TextInputEditText) findViewById(R.id.userField);
        passField = (TextInputEditText) findViewById(R.id.passField);
        ConnectionServer connection = new ConnectionServer();


        String username = userField.getText().toString();
        String password = passField.getText().toString();

        try {
            Toast.makeText(this, "the user "+userField.getText()+" and the password "+passField.getText(), Toast.LENGTH_SHORT).show();
            Log.i("info", userField.getText().toString().getClass().getName());
            connection.sendToServer(username,password);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error by: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        actionBar  = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#273036")));
        actionBar.setTitle("CAR SERVICES");
    }
}