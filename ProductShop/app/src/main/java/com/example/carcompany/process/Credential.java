package com.example.carcompany.process;

import android.content.Intent;

public class Credential {
    /**
     *
     * @param credential the credential to be tested
     *  if is correct retrurn true, otherwise return false
     */
    public boolean checkCredential(String credential){

        String[] vectorCed = credential.split("");

        try {
            int sizeVector = vectorCed.length;
            int digitVerify = Integer.parseInt(vectorCed[sizeVector - 1]);
            int thirdDigit  = Integer.parseInt(vectorCed[3]);
            int sum = 0;


            if (sizeVector == 11 && thirdDigit < 6){
                for (int i = 0; i < vectorCed.length-1; i++){

                    if (i % 2 == 0){
                        if(Integer.parseInt((vectorCed[i])) * 2 >= 10 ){
                            sum += (Integer.parseInt((vectorCed[i])) * 2) - 9 ;
                        }else{
                            sum += Integer.parseInt((vectorCed[i])) * 2;
                        }
                    }else{
                        sum += Integer.parseInt((vectorCed[i]));
                    }

                }

                String[] finalArray = String.valueOf(sum).split("");
                int lastDigit = Integer.parseInt(finalArray[2]);

                return 10 - lastDigit  == digitVerify;

            }else{
                return false;
            }


        }catch (Exception e){
            return false;
        }




    }
}
