package com.example.carcompany;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.carcompany.process.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;

public class ListCarProperties extends Activity {

    private ListView carList;
    private CustomizedListCar customizedListCar;
    Intent intentLastTemplate;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_cars);
        intentLastTemplate = new Intent(ListCarProperties.this, LastTemplate.class);

        carList = (ListView) findViewById(R.id.listCarProperties);

        ArrayList<ArrayList<String>> carResources = Vehicle.vehicleList;


        /*ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();

         ArrayList<String> vector_1 = new ArrayList<String>(Arrays.asList("GYE-2526", "Chevrolet",
                 "Grand Vitara", "2002", "Black", "Arturo", "Negreiros", "091823721", "Car washing", Vehicle.imageName));

        ArrayList<String> vector_2 = new ArrayList<String>(Arrays.asList("UIO-1515", "Ford",
                "Runner", "2018", "Red", "Francesco", "Samanez", "1234567898", "Car maintenance", Vehicle.imageName));

        ArrayList<String> vector_3 = new ArrayList<String>(Arrays.asList("TOJ-0010", "Mazda",
                "Mazda", "2000", "Silver", "Juan", "Samanez", "1234567898", "Car paint", Vehicle.imageName));

        Vehicle.vehicleList.add(vector_1);
        Vehicle.vehicleList.add(vector_2);
        Vehicle.vehicleList.add(vector_3);*/

        try {
            customizedListCar = new CustomizedListCar(this,carResources);

            carList.setAdapter(customizedListCar);
            carList.setOnItemClickListener(listClick);
        }catch (Exception e){
            e.printStackTrace();
            Log.e("error", "onCreate: ", e);
        }

    }

    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Toast.makeText(ListCarProperties.this, "position: "+String.valueOf(position), Toast.LENGTH_SHORT).show();
            intentLastTemplate.putExtra("position", String.valueOf(position));
            startActivity(intentLastTemplate);
        }
    };



}
