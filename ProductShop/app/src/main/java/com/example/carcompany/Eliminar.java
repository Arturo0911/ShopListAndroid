package com.example.carcompany;

import androidx.appcompat.app.AppCompatActivity;

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

public class Eliminar extends AppCompatActivity {

    private TextInputEditText codigoEliminar;
    private TextInputEditText cantidadEliminar;
    private TextInputEditText cedulaEliminar;
    private TextInputEditText nombresEliminar;
    private TextInputEditText apellidosEliminar;
    private TextInputEditText latitudEliminar;
    private TextInputEditText longitudEliminar;
    private Spinner PaymentMethodEliminar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        codigoEliminar = (TextInputEditText) findViewById(R.id.codigoEliminar);
        cantidadEliminar = (TextInputEditText) findViewById(R.id.cantidadEliminar);
        cedulaEliminar = (TextInputEditText) findViewById(R.id.cedulaEliminar);
        nombresEliminar = (TextInputEditText) findViewById(R.id.nombresEliminar);
        apellidosEliminar = (TextInputEditText) findViewById(R.id.apellidosEliminar);
        latitudEliminar = (TextInputEditText) findViewById(R.id.latitudEliminar);
        longitudEliminar = (TextInputEditText) findViewById(R.id.longitudEliminar);
        PaymentMethodEliminar = (Spinner) findViewById(R.id.PaymentMethodEliminar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.metodospago, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        PaymentMethodEliminar.setAdapter(adapter);


    }

    public void eliminarButton(View view){

        String codigo = codigoEliminar.getText().toString();

        AdminDatabase admin = new AdminDatabase(Eliminar.this, "AllProducts", null, 1);
        SQLiteDatabase data = admin.getReadableDatabase();

        int cantidad = data.delete("producto", "codigo ='"+codigo+"'", null);

        if (cantidad == 1){
            Toast.makeText(this, "Datos borrados satisfactoriamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Eliminar.this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "No se ha podido borrar el registro porque el producto no existe", Toast.LENGTH_SHORT).show();
        }




    }

    public void consultaEliminarButton(View view){


        if (!codigoEliminar.getText().toString().equals("")){
            try {

                AdminDatabase admin = new AdminDatabase(Eliminar.this, "AllProducts", null, 1);
                SQLiteDatabase data = admin.getReadableDatabase();

                Cursor cursor = data.rawQuery("SELECT * FROM producto WHERE codigo = '"+codigoEliminar.getText().toString()+"'", null);

                /*
                * dataBaseProducto.execSQL("CREATE TABLE producto (id INTEGER PRIMARY KEY AUTOINCREMENT, codigo TEXT, cantidad TEXT," +
                " cedula TEXT, nombres TEXT, apellidos TEXT, latitud TEXT, longitud TEXT, pago TEXT )");
                *
                * */

                if (cursor.moveToFirst()){

                    cantidadEliminar.setText(cursor.getString(2));
                    cedulaEliminar.setText(cursor.getString(3));
                    nombresEliminar.setText(cursor.getString(4));
                    apellidosEliminar.setText(cursor.getString(5));
                    latitudEliminar.setText(cursor.getString(6));
                    longitudEliminar.setText(cursor.getString(7));


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

    public void regresarEliminarButton (View view){

        Intent intent = new Intent(Eliminar.this, MainActivity.class);
        startActivity(intent);
    }



}