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

public class Consultar extends AppCompatActivity {

    private TextInputEditText codigoConsulta;
    private TextInputEditText cantidadProduct;
    private TextInputEditText cedulaCliente;
    private TextInputEditText nombresCliente;
    private TextInputEditText apellidosCliente;
    private TextInputEditText latitud;
    private TextInputEditText longitud;
    private Spinner PaymentMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);


        codigoConsulta = (TextInputEditText) findViewById(R.id.codigoConsulta);
        cantidadProduct = (TextInputEditText) findViewById(R.id.cantidadProductConsulta);
        cedulaCliente = (TextInputEditText) findViewById(R.id.cedulaClienteConsulta);
        nombresCliente = (TextInputEditText) findViewById(R.id.nombresClienteConsulta);
        apellidosCliente = (TextInputEditText) findViewById(R.id.apellidosClienteConsulta);
        latitud = (TextInputEditText) findViewById(R.id.latitudConsulta);
        longitud = (TextInputEditText) findViewById(R.id.longitudConsulta);
        PaymentMethod = (Spinner) findViewById(R.id.PaymentMethodConsulta);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.metodospago, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        PaymentMethod.setAdapter(adapter);



    }

    private boolean chequearCampos(String codigo, String cantidad, String cedula, String nombres, String apellidos, String latitudProducto,
                                   String longitudProducto){

        return !codigo.equals("")&&!cantidad.equals("")&&!cedula.equals("")&&!nombres.equals("")&&
                !apellidos.equals("")&&!latitudProducto.equals("")&&!longitudProducto.equals("");

    }

    public void consultarProducto(View view){

        if (!codigoConsulta.getText().toString().equals("")){
            try {

                AdminDatabase admin = new AdminDatabase(Consultar.this, "AllProducts", null, 1);
                SQLiteDatabase data = admin.getReadableDatabase();

                Cursor cursor = data.rawQuery("SELECT * FROM producto WHERE codigo = '"+codigoConsulta.getText().toString()+"'", null);

                /*
                * dataBaseProducto.execSQL("CREATE TABLE producto (id INTEGER PRIMARY KEY AUTOINCREMENT, codigo TEXT, cantidad TEXT," +
                " cedula TEXT, nombres TEXT, apellidos TEXT, latitud TEXT, longitud TEXT, pago TEXT )");
                *
                * */

                if (cursor.moveToFirst()){
                    Toast.makeText(this, ""+cursor.getString(2), Toast.LENGTH_SHORT).show();
                    cantidadProduct.setText(cursor.getString(2));
                    cedulaCliente.setText(cursor.getString(3));
                    nombresCliente.setText(cursor.getString(4));
                    apellidosCliente.setText(cursor.getString(5));
                    latitud.setText(cursor.getString(6));
                    longitud.setText(cursor.getString(7));




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



    public void regresar(View view){
        Intent intentReturn = new Intent(Consultar.this, MainActivity.class);
        startActivity(intentReturn);
    }


}