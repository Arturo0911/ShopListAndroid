package com.example.carcompany.process;

import java.util.ArrayList;
import java.util.Arrays;

public class Vehicle {


    /**
     * We declare a Constant to access in all project
     * for get secure of any data will be inserted successfully then
     *use  initialized variables as
     *@param constant serviceType
     * @param constant imageName to be acess in the list view activity
    */

    public static ArrayList<ArrayList<String>> vehicleList = new ArrayList<ArrayList<String>>();


    // this method gonna store the type of service such (car washing, car maintenance, car painting)
    // setting default car washing
    public static String serviceType = "Car washing";

    // wash     maintenance_2       paint_2
    public static String imageName = "wash";

    /**
     * @param plate plate car
     * @param color color car
     * @param make make car
     * @param model model car
     * @param year year, whatever you want!!
     * @param ownerName propietary name
     * @param ownerCredential credentials
     * @param ownerLastname lastname propietary
     * */
    public void addToArray(String plate, String make,String model, String year,
                           String color, String ownerName, String ownerLastname,String ownerCredential, String service, String imgName){
        ArrayList<String> vector = new ArrayList<String>(Arrays.asList(plate, make, model, year, color, ownerName, ownerLastname, ownerCredential, service, imgName));

        vehicleList.add(vector);
    }

}
