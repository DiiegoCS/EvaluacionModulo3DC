package com.diegocampos.evaluacionmodulo3dc.Interactor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Environment;

import com.diegocampos.evaluacionmodulo3dc.Interfaces.EliminarRegistro.FragEliminarRegistroInteractorInterface;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.EliminarRegistro.FragEliminarRegistroPresenterInterface;

import java.io.File;
import java.util.ArrayList;

public class FragEliminarRegistroInteractorImpl implements FragEliminarRegistroInteractorInterface {

    ArrayList<String> datos = new ArrayList<>();
    String codIn= "";
    String nombreCl= "";
    String MarcaE= "";
    String ModeloE= "";
    String FechaIn= "";

    @Override
    public void BuscarReg(FragEliminarRegistroPresenterInterface presentador, Context contexto, String codigo) {

        ConexionBD conexion = new ConexionBD(contexto, "administracion", null, 1);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        Cursor fila = bd.rawQuery("SELECT codigoIngreso,nombre, marca, modelo, fechaIngreso FROM registroEquipos WHERE codigoIngreso='"+codigo+"'",null);

        if(fila.moveToFirst()) {
            codIn = fila.getString(0);
            nombreCl= fila.getString(1);
            MarcaE = fila.getString(2);
            ModeloE = fila.getString(3);
            FechaIn = fila.getString(4);

            datos.add(codIn);
            datos.add(nombreCl);
            datos.add(MarcaE);
            datos.add(ModeloE);
            datos.add(FechaIn);

            presentador.exitoBuscarR(datos);
        }
        else {
            presentador.errorBuscarR();
        }
    }

    @Override
    public void eliminarRegistro(FragEliminarRegistroPresenterInterface presentador, Context contexto, String codigo) {
        ConexionBD conexion = new ConexionBD(contexto, "administracion", null, 1);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        int a=bd.delete("registroEquipos", "codigoIngreso='"+codigo+"'", null);

        File ruta = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+"/MyApp/");
        }
        else{
            ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        }

        if (a>0){
            presentador.exitoEliminar();
        }
        else{
            presentador.errorEliminar();
        }
    }
}
