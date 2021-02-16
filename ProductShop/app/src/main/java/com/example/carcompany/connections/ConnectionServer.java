package com.example.carcompany.connections;

import android.app.Activity;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.carcompany.Login;

import org.json.JSONObject;

public class ConnectionServer extends Activity {

    // to send directly to server created with Flask python
    private static final String url = "http://" + "10.0.2.2"+":"+4000+"/android";
    RequestQueue requestQueue = null;


    /**
     * @param username
     * @param password
     *
     *
     *
     */
    public void sendToServer(String username, String password){

        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("Username", username);
            jsonObject.put("Password", password);
        }catch (Exception e){
            e.printStackTrace();
        }

        requestQueue = Volley.newRequestQueue(ConnectionServer.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Toast.makeText(ConnectionServer.this, "this is my response: "+response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }
}
