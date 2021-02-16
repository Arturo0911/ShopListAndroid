package com.example.carcompany;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import com.example.carcompany.process.Vehicle;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class LastTemplate extends AppCompatActivity{

    private TextView whichService;
    private TextView fullMake;
    private TextView fullModel;
    private TextView fullName;
    private TextView fullCredentials;
    private TextView serviceDescription;
    private TextView fullOrder;

    private MaterialButton deleteOrder;
    private MaterialButton confirmOrder;

    private TextInputLayout email;

    private ImageView finalImage;

    public void onDeleteOrder(View view){
        fullOrder = (TextView) findViewById(R.id.fullOrder);
        int index = Integer.parseInt(fullOrder.getText().toString());
        generateDialogOnDelete(index);
        //Toast.makeText(this, "order number: "+fullOrder.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void sendOnclickButton(View view){

        String emailAddress = email.getEditText().getText().toString();
        String url = "http://" + "10.0.2.2"+":"+4000+"/android";
        generateDialogOnConFirm();

        if (!emailAddress.equals("")){

            try {
                RequestMethodPOST(fullName.getText().toString(), emailAddress,whichService.getText().toString(), url );
                Toast.makeText(this, "email sended successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LastTemplate.this, MainActivity.class);
                startActivity(intent);
            }catch (Exception e){
                Toast.makeText(this, "Error by: "+e.toString(), Toast.LENGTH_SHORT).show();
            }finally {
                email.getEditText().setText("");
            }
        }else{
            Toast.makeText(this, "the field email cannot be empty", Toast.LENGTH_SHORT).show();
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_template);

        whichService = (TextView)findViewById(R.id.whichService);
        fullMake = (TextView)findViewById(R.id.fullMake);
        fullModel = (TextView)findViewById(R.id.fullModel);
        fullName = (TextView)findViewById(R.id.fullName);
        fullCredentials = (TextView)findViewById(R.id.fullCredentials);
        serviceDescription = (TextView)findViewById(R.id.serviceDescription);
        fullOrder = (TextView) findViewById(R.id.fullOrder);
        finalImage = (ImageView) findViewById(R.id.finalImage);

        email = (TextInputLayout) findViewById(R.id.email);


        Intent lastIntent = getIntent();
        int index = Integer.parseInt(lastIntent.getStringExtra("position"));

        ArrayList<String> valuesAdd = new ArrayList<String>();

        valuesAdd = Vehicle.vehicleList.get(index);


        fullOrder.setText(String.valueOf(index));
        whichService.setText(valuesAdd.get(8));
        fullMake.setText("Make: "+valuesAdd.get(1));
        fullModel.setText("Model: "+valuesAdd.get(2));
        fullName.setText(valuesAdd.get(5)+" "+valuesAdd.get(6));
        fullCredentials.setText(valuesAdd.get(7));
        serviceDescription.setText("The plate car is "+valuesAdd.get(0)+".With this service you will have the best experience with us");

        if(whichService.getText().toString().equals("Car washing")){
            finalImage.setImageResource(R.drawable.wash);
        }else if (whichService.getText().toString().equals("Car maintenance")){
            finalImage.setImageResource(R.drawable.maintenance_2);
        }else{
            finalImage.setImageResource(R.drawable.paint_2);
        }



    }



    private void generateDialogOnConFirm(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LastTemplate.this);
        builder.setTitle("Alert");
        builder.setMessage("confirm the purchase?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void generateDialogOnDelete(int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(LastTemplate.this);
        builder.setTitle("Alert");
        builder.setMessage("Are you sure to delete this order?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "deleting...", Toast.LENGTH_LONG).show();

                        if(Vehicle.vehicleList.size()<=1){
                            Vehicle.vehicleList.remove(index);
                            Intent intent = new Intent(LastTemplate.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Vehicle.vehicleList.remove(index);
                            Intent intent = new Intent(LastTemplate.this, ListCarProperties.class);
                            startActivity(intent);
                        }

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "ok! No problem", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }




    public void RequestMethodPOST(String fullname , String email, String service, String url){

        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("Fullname", fullname);
            jsonObject.put("Email", email);
            jsonObject.put("service",service);


        }catch (Exception e ){
            Toast.makeText(LastTemplate.this, "Error by: "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        RequestQueue requestQueue = Volley.newRequestQueue(LastTemplate.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(LastTemplate.this, "response: "+response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LastTemplate.this, "this is my error: "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }


}