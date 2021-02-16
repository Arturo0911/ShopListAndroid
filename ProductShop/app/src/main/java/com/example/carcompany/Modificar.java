package com.example.carcompany;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carcompany.process.AdminDatabase;
import com.google.android.material.textfield.TextInputEditText;

public class Modificar extends AppCompatActivity {

    private TextInputEditText codigoModificar;
    private TextInputEditText cantidadProductModificar;
    private TextInputEditText cedulaClienteModificar;
    private TextInputEditText nombresClienteModificar;
    private TextInputEditText apellidosClienteModificar;
    private TextInputEditText latitudModificar;
    private TextInputEditText longitudModificar;
    private Spinner PaymentMethodModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        codigoModificar = (TextInputEditText) findViewById(R.id.codigoModificar);
        cantidadProductModificar = (TextInputEditText) findViewById(R.id.cantidadProductModificar);
        cedulaClienteModificar = (TextInputEditText) findViewById(R.id.cedulaClienteModificar);
        nombresClienteModificar = (TextInputEditText) findViewById(R.id.nombresClienteModificar);
        apellidosClienteModificar = (TextInputEditText) findViewById(R.id.apellidosClienteModificar);
        latitudModificar = (TextInputEditText) findViewById(R.id.latitudModificar);
        longitudModificar = (TextInputEditText) findViewById(R.id.longitudModificar);
        PaymentMethodModificar = (Spinner) findViewById(R.id.PaymentMethodModificar);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.metodospago, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        PaymentMethodModificar.setAdapter(adapter);



    }



    public void modificarButton(View view){

        String codigo = codigoModificar.getText().toString();
        String cantidad = cantidadProductModificar.getText().toString();
        String cedula = cedulaClienteModificar.getText().toString();
        String nombres = nombresClienteModificar.getText().toString();
        String apellidos = apellidosClienteModificar.getText().toString();
        String latitud = latitudModificar.getText().toString();
        String longitud = longitudModificar.getText().toString();
        String pago = PaymentMethodModificar.getSelectedItem().toString();


        try {

            AdminDatabase admin = new AdminDatabase(Modificar.this,"AllProducts", null, 1);
            SQLiteDatabase data = admin.getReadableDatabase();

            ContentValues register = new ContentValues();

            /*
                * dataBaseProducto.execSQL("CREATE TABLE producto (id INTEGER PRIMARY KEY AUTOINCREMENT, codigo TEXT, cantidad TEXT," +
                " cedula TEXT, nombres TEXT, apellidos TEXT, latitud TEXT, longitud TEXT, pago TEXT )");
                *
                * */

            register.put("codigo",codigo);
            register.put("cantidad",cantidad);
            register.put("cedula",cedula);
            register.put("nombres",nombres);
            register.put("apellidos",apellidos);
            register.put("latitud",latitud);
            register.put("longitud",longitud);
            register.put("pago",pago);

            int cambio = data.update("producto",register, "codigo='"+codigo+"'",null);
            data.close();

            if (cambio == 1 ){
                Toast.makeText(this, "Los datos fueron cambiados exitosamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Los campos no se pueden modificar", Toast.LENGTH_SHORT).show();
            }




        }catch (Exception e ){
            e.printStackTrace();
        }


    }


    public void consultarModificarButton (View view){
        if (!codigoModificar.getText().toString().equals("")){
            try {

                AdminDatabase admin = new AdminDatabase(Modificar.this, "AllProducts", null, 1);
                SQLiteDatabase data = admin.getReadableDatabase();

                Cursor cursor = data.rawQuery("SELECT * FROM producto WHERE codigo = '"+codigoModificar.getText().toString()+"'", null);

                /*
                * dataBaseProducto.execSQL("CREATE TABLE producto (id INTEGER PRIMARY KEY AUTOINCREMENT, codigo TEXT, cantidad TEXT," +
                " cedula TEXT, nombres TEXT, apellidos TEXT, latitud TEXT, longitud TEXT, pago TEXT )");
                *
                * */

                if (cursor.moveToFirst()){

                    cantidadProductModificar.setText(cursor.getString(2));
                    cedulaClienteModificar.setText(cursor.getString(3));
                    nombresClienteModificar.setText(cursor.getString(4));
                    apellidosClienteModificar.setText(cursor.getString(5));
                    latitudModificar.setText(cursor.getString(6));
                    longitudModificar.setText(cursor.getString(7));


                }else{
                    Toast.makeText(this, "No hay datos que coincidan con el codigo ingresado", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e ){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "El campo de consulta no puede estar vacio!", Toast.LENGTH_SHORT).show();
        }
    }


    public void regresarmodificarButton (View view){

        Intent intent = new Intent(Modificar.this, MainActivity.class);
        startActivity(intent);
    }


}