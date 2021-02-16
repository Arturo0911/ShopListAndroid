package com.example.carcompany;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

public class ExternalStorage extends AppCompatActivity {

    private TextInputEditText userField;
    private TextInputEditText passField;

    // this is the way to get access to server to save date externally
    private static final String urlContext = "http://" + "10.0.2.2"+":"+4000+"/signup";;

    public void saveButton(View view){

        userField = (TextInputEditText) findViewById(R.id.userField);
        passField = (TextInputEditText) findViewById(R.id.passField);

        try {
            onHttpRequestMethodPOST(userField.getText().toString(), passField.getText().toString(), urlContext);
            clearFields(userField, passField);
            Intent intent = new Intent(ExternalStorage.this, InternalStorage.class);
            startActivity(intent);

        }catch (Exception e){
            Toast.makeText(this, "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
    }

    public void onHttpRequestMethodPOST(String username, String password, String url){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Username", username);
            jsonObject.put("Password", password);
        }catch (Exception e){
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(ExternalStorage.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(ExternalStorage.this, "Data was send", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });



        requestQueue.add(jsonObjectRequest);

    }

    private void clearFields(TextInputEditText  username, TextInputEditText password){

        username.setText("");
        password.setText("");
    }



}