package com.example.carcompany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.carcompany.process.Credential;
import com.example.carcompany.process.Vehicle;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout carPlate;
    private TextInputLayout carColor;
    private TextInputLayout carMake;
    private TextInputLayout carModel;
    private TextInputLayout carYear;
    private TextInputLayout ownerName;
    private TextInputLayout ownerCredentials;
    private TextInputLayout ownerLastname;

    MaterialButton carPaint;
    MaterialButton carWashing;
    MaterialButton carMaintenance;


    // initializing such constant
    private static final Vehicle vehicle = new Vehicle();
    ActionBar actionBar;

    public void sendOnclickButton(View view){

        Credential credential = new Credential();

        carPlate = (TextInputLayout) findViewById(R.id.carPlate);
        carColor = (TextInputLayout) findViewById(R.id.carColor);
        carMake = (TextInputLayout) findViewById(R.id.carMake);
        carModel = (TextInputLayout) findViewById(R.id.carModel);
        carYear = (TextInputLayout) findViewById(R.id.carYear);
        ownerName = (TextInputLayout) findViewById(R.id.ownerName);
        ownerLastname = (TextInputLayout) findViewById(R.id.ownerLastname);
        ownerCredentials = (TextInputLayout) findViewById(R.id.ownerCredentials);

        try {

            if(checkIsEmpty(carPlate, carMake,carModel,carYear,carColor,ownerName,ownerLastname,ownerCredentials)){
                String plate = carPlate.getEditText().getText().toString();
                String make = carMake.getEditText().getText().toString();
                String model = carModel.getEditText().getText().toString();
                String year = carYear.getEditText().getText().toString();
                String color = carColor.getEditText().getText().toString();
                String owName  = ownerName.getEditText().getText().toString();
                String owLname = ownerLastname.getEditText().getText().toString();
                String owCredential = ownerCredentials.getEditText().getText().toString();

                vehicle.addToArray(plate, make,model,year,color, owName, owLname, owCredential, Vehicle.serviceType, Vehicle.imageName);
                clearFields(carPlate, carMake, carModel,carYear, carColor, ownerName, ownerLastname, ownerCredentials);
                Toast.makeText(MainActivity.this, "The fields was be saved successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "The fields cannot be empty", Toast.LENGTH_SHORT).show();
            }


        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error by: " +e.toString(), Toast.LENGTH_LONG).show();
        }


    }

    public void viewOnclickButton(View view){

        Intent intent = new Intent(MainActivity.this,ListCarProperties.class);
        try {
            if(Vehicle.vehicleList.size() > 0){
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, "cannot view the data because is already empty", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error by: "+ e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar  = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#273036")));
        actionBar.setTitle("CAR SERVICES");

        carPaint = (MaterialButton)findViewById(R.id.carPaint);
        carWashing =(MaterialButton)findViewById(R.id.carWashing);
        carMaintenance = (MaterialButton)findViewById(R.id.carMaintenance);


        carPaint.setOnClickListener(this);
        carWashing.setOnClickListener(this);
        carMaintenance.setOnClickListener(this);


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        ImageView carService = (ImageView) findViewById(R.id.carService);

        switch (v.getId()){
            case R.id.carWashing:
            carService.setImageResource(R.drawable.wash);
            Vehicle.serviceType = "Car washing";
            break;

            case R.id.carMaintenance:
                carService.setImageResource(R.drawable.maintenance_2);
                Vehicle.serviceType = "Car maintenance";
                break;

            case R.id.carPaint:
                carService.setImageResource(R.drawable.paint_2);
                Vehicle.serviceType = "Car paint";
                break;
        }
    }



    public void clearFields(TextInputLayout carPlate, TextInputLayout carMake, TextInputLayout carModel,
                            TextInputLayout carYear, TextInputLayout carColor, TextInputLayout ownerName,
                            TextInputLayout ownerLastname, TextInputLayout ownerCredentials){

        carPlate.getEditText().setText("");
        carMake.getEditText().setText("");
        carModel.getEditText().setText("");
        carColor.getEditText().setText("");
        carYear.getEditText().setText("");
        ownerName.getEditText().setText("");
        ownerLastname.getEditText().setText("");
        ownerCredentials.getEditText().setText("");


    }

    public boolean checkIsEmpty(TextInputLayout carPlate, TextInputLayout carMake, TextInputLayout carModel,
                                TextInputLayout carYear, TextInputLayout carColor, TextInputLayout ownerName,
                                TextInputLayout ownerLastname, TextInputLayout ownerCredentials){

        if (!carPlate.getEditText().getText().toString().equals("") && !carMake.getEditText().getText().toString().equals("") &&
                !carModel.getEditText().getText().toString().equals("") && !carYear.getEditText().getText().toString().equals("") &&
                !carColor.getEditText().getText().toString().equals("") && !ownerName.getEditText().getText().toString().equals("") &&
                !ownerLastname.getEditText().getText().toString().equals("") && !ownerCredentials.getEditText().getText().toString().equals("")){
            return true;
        }else{
            return false;
        }

    }


}