package com.example.carcompany.process;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminDatabase extends SQLiteOpenHelper {
    public AdminDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase dataBaseProducto) {

        /*
        * productCode = (TextInputEditText) findViewById(R.id.productCode);
        cantidadProduct = (TextInputEditText) findViewById(R.id.cantidadProduct);
        cedulaCliente = (TextInputEditText) findViewById(R.id.cedulaCliente);
        nombresCliente = (TextInputEditText) findViewById(R.id.nombresCliente);
        apellidosCliente = (TextInputEditText) findViewById(R.id.apellidosCliente);
        latitud = (TextInputEditText) findViewById(R.id.latitud);
        longitud = (TextInputEditText) findViewById(R.id.longitud);
        PaymentMethod = (Spinner) findViewById(R.id.PaymentMethod);
        *
        *
        * */

        dataBaseProducto.execSQL("CREATE TABLE producto (id INTEGER PRIMARY KEY AUTOINCREMENT, codigo TEXT, cantidad TEXT," +
                " cedula TEXT, nombres TEXT, apellidos TEXT, latitud TEXT, longitud TEXT, pago TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
