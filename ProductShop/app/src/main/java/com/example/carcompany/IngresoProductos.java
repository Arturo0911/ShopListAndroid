package com.example.carcompany;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carcompany.process.AdminDatabase;
import com.example.carcompany.process.Credential;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLDataException;

public class IngresoProductos extends AppCompatActivity {

    private Spinner PaymentMethod;
    private TextInputEditText productCode;
    private TextInputEditText cantidadProduct;
    private TextInputEditText cedulaCliente;
    private TextInputEditText nombresCliente;
    private TextInputEditText apellidosCliente;
    private TextInputEditText latitud;
    private TextInputEditText longitud;

    LocationManager locationManager;
    LocationListener locationListener;

    Credential credential = null;

    public void Regresar(View view){
        Intent intent = new Intent(IngresoProductos.this, MainActivity.class);
        startActivity(intent);
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(IngresoProductos.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
            }
        }

    }


    public void guardarProducto(View view){


        String codigo = productCode.getText().toString();
        String cantidad = cantidadProduct.getText().toString();
        String cedula = cedulaCliente.getText().toString();
        String nombres = nombresCliente.getText().toString();
        String apellidos = apellidosCliente.getText().toString();
        String latitudproducto =latitud.getText().toString();
        String longitudProduct = longitud.getText().toString();
        String pago = PaymentMethod.getSelectedItem().toString();

        if (chequearCampos(codigo, cantidad, cedula, nombres, apellidos, latitudproducto, longitudProduct)&& credential.verificarCedula(cedula)){

            try {

                AdminDatabase admin = new AdminDatabase(IngresoProductos.this, "AllProducts", null, 1);
                SQLiteDatabase data = admin.getReadableDatabase();

                ContentValues registro = new ContentValues();

                registro.put("codigo", codigo);
                registro.put("cantidad", cantidad);
                registro.put("cedula", cedula);
                registro.put("nombres", nombres);
                registro.put("apellidos", apellidos);
                registro.put("latitud", latitudproducto);
                registro.put("longitud", longitudProduct);
                registro.put("pago", pago);

                data.insert("producto", null, registro);
                data.close();

                limpiarCamps(productCode, cantidadProduct, cedulaCliente, nombresCliente, apellidosCliente, latitud, longitud);
                Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();



            }catch (Exception e ){
                e.printStackTrace();
                Toast.makeText(this, "Error por: "+e.toString(), Toast.LENGTH_SHORT).show();

            }


        }else{
            Toast.makeText(this, "Los campos no pueden estar vacios!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void verificarProducto(View view){

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getLocation(View view){

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                latitud.setText( String.valueOf(location.getLatitude()));
                longitud.setText( String.valueOf(location.getLongitude()));

            }


            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }
        };

        if (Build.VERSION.SDK_INT < 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }else{
            if (ContextCompat.checkSelfPermission(IngresoProductos.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(IngresoProductos.this, new String []{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_productos);
        productCode = (TextInputEditText) findViewById(R.id.productCode);
        cantidadProduct = (TextInputEditText) findViewById(R.id.cantidadProduct);
        cedulaCliente = (TextInputEditText) findViewById(R.id.cedulaCliente);
        nombresCliente = (TextInputEditText) findViewById(R.id.nombresCliente);
        apellidosCliente = (TextInputEditText) findViewById(R.id.apellidosCliente);
        latitud = (TextInputEditText) findViewById(R.id.latitud);
        longitud = (TextInputEditText) findViewById(R.id.longitud);
        PaymentMethod = (Spinner) findViewById(R.id.PaymentMethod);

        credential = new Credential();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.metodospago, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        PaymentMethod.setAdapter(adapter);

    }


    private boolean chequearCampos(String codigo, String cantidad, String cedula, String nombres, String apellidos, String latitudProducto,
                                   String longitudProducto){

        return !codigo.equals("")&&!cantidad.equals("")&&!cedula.equals("")&&!nombres.equals("")&&
                !apellidos.equals("")&&!latitudProducto.equals("")&&!longitudProducto.equals("");

    }

    private void limpiarCamps(TextInputEditText productCode, TextInputEditText cantidadProduct, TextInputEditText cedulaCliente,
                              TextInputEditText nombresCliente, TextInputEditText apellidosCliente, TextInputEditText latitud, TextInputEditText longitud ){

        productCode.setText("");
        cantidadProduct.setText("");
        cedulaCliente.setText("");
        nombresCliente.setText("");
        apellidosCliente.setText("");
        latitud.setText("");
        longitud.setText("");

    }





}