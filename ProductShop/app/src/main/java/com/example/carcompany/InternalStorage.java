package com.example.carcompany;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class InternalStorage extends AppCompatActivity {

    EditText editTextMultiline;
    EditText nameEditText;
    private static final String fileName = "memoria.txt";

    public void onSaveButton(View view){

        nameEditText = (EditText) findViewById(R.id.nameEditText);

        try {
            /*OutputStreamWriter file = new OutputStreamWriter(openFileOutput(fileName, Activity.MODE_PRIVATE));
            file.write(editTextMultiline.getText().toString());
            file.flush();
            file.close();
            Toast.makeText(this, "data was saved", Toast.LENGTH_SHORT).show();*/

            File cardSD = Environment.getExternalStorageDirectory();
            Toast.makeText(this, ""+cardSD.getPath().toString(), Toast.LENGTH_SHORT).show();
            File filePath =  new File(cardSD.getPath(), nameEditText.getText().toString());
            OutputStreamWriter createFile = new OutputStreamWriter(openFileOutput(nameEditText.getText().toString(), Activity.MODE_PRIVATE));

            createFile.write(editTextMultiline.getText().toString());
            createFile.flush();
            createFile.close();
            Toast.makeText(this, "data was saved", Toast.LENGTH_SHORT).show();
            nameEditText.setText("");
            editTextMultiline.setText("");
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "data cannot be saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void onQueryButton(View view ){

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        try {
            File cardSD = Environment.getExternalStorageDirectory();
            File filePath =  new File(cardSD.getPath(), nameEditText.getText().toString());
            InputStreamReader file = new InputStreamReader(openFileInput(nameEditText.getText().toString()));
            BufferedReader readFile = new BufferedReader(file);
            String line = readFile.readLine();
            String fileFull = "";
            while (line != null){
                fileFull = fileFull + line + "\n";
                line = readFile.readLine();
            }
            readFile.close();
            file.close();
            editTextMultiline.setText(fileFull);

        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Error in read file", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage2);

        editTextMultiline = (EditText) findViewById(R.id.editTextMultiline);


    }

    /*private boolean fileExist(String files[], String fileName_){


        for(int x = 0; x < files.length; x++ )
            if(fileName_.equals(files[x]))
                return true ;

        return false;
    }*/


}