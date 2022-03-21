package com.diegocampos.evaluacionmodulo3dc.Interactor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionBD extends SQLiteOpenHelper {

    public ConexionBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table usuarios(user text primary key, nombre text, pass text)");

        bd.execSQL("create table registroEquipos (codigoIngreso text primary key, nombre text, marca text" +
                ", modelo text, fechaIngreso text, equipoEnCaja text, cargadorEnCaja text, manualEnCaja text," +
                "garantiaEnCaja text, cargaSo text, monitor text, audio text, touchpad text, " +
                "observaciones text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
