package com.example.carcompany.process;

import android.content.Intent;

import java.util.ArrayList;

public class Credential {


    public boolean verificarCedula(String credential){

        ArrayList<String> cedula = new ArrayList<>();

        for (int i = 0; i < credential.split("").length; i++){
            cedula.add(credential.split("")[i]);
        }

        try {

            int tercerDigito = Integer.parseInt( cedula.get(2));
            int ultimoDigito = Integer.parseInt( cedula.get(9));
            int suma = 0;
            if (cedula.size() == 10 && tercerDigito < 6){

                for (int i = 0; i < cedula.size() - 1; i++){

                    if (i%2==0){

                        if ( Integer.parseInt(cedula.get(i))*2 >=10 ){
                            suma += Integer.parseInt(cedula.get(i))*2 - 9;
                        }else{
                            suma += Integer.parseInt(cedula.get(i))*2;
                        }

                    }else{
                        suma += Integer.parseInt(cedula.get(i));
                    }

                }

                int digitoVerificador = Integer.parseInt( String.valueOf(suma).split("")[1]);

                return 10 - digitoVerificador == ultimoDigito ;



            }else{

                return false;
            }


        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
