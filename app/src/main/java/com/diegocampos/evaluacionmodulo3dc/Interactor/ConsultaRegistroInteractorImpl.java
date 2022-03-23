package com.diegocampos.evaluacionmodulo3dc.Interactor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;

import com.diegocampos.evaluacionmodulo3dc.Interfaces.ConsultaRegistroEquipo.ConsultaRegistroEquipoInteractorInterface;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.ConsultaRegistroEquipo.ConsultaRegistroEquipoPresenterInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRegistroInteractorImpl implements ConsultaRegistroEquipoInteractorInterface {


    @Override
    public void mostrarRegistro(ConsultaRegistroEquipoPresenterInterface presentador, Context contexto, String codigo) {
        ConexionBD conexion = new ConexionBD(contexto, "administracion", null, 1);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        String codigoIngreso = codigo;

        Cursor fila = bd.rawQuery("SELECT * FROM registroEquipos WHERE codigoIngreso='"+codigoIngreso+"'",null);

        String codigoIng="";
        String nomCl="";
        String marcaEq="";
        String modeloEq="";
        String fechaIngr="";
        String equipoCaja="";
        String cargadorCaja="";
        String manualCaja="";
        String garantiaEq="";
        String cargaSisO="";
        String monitorEq="";
        String audioEq="";
        String touchpadE="";
        String obsExtras="";

        ArrayList<String> listaRegistro = new ArrayList<>();

        if(fila.moveToFirst()) {

            codigoIng = fila.getString(0);
            listaRegistro.add(codigoIng);
            nomCl= fila.getString(1);
            listaRegistro.add(nomCl);
            marcaEq= fila.getString(2);
            listaRegistro.add(marcaEq);
            modeloEq= fila.getString(3);
            listaRegistro.add(modeloEq);
            fechaIngr= fila.getString(4);
            listaRegistro.add(fechaIngr);
            equipoCaja= fila.getString(5);
            listaRegistro.add(equipoCaja);
            cargadorCaja= fila.getString(6);
            listaRegistro.add(cargadorCaja);
            manualCaja= fila.getString(7);
            listaRegistro.add(manualCaja);
            garantiaEq=fila.getString(8);
            listaRegistro.add(garantiaEq);
            cargaSisO=fila.getString(9);
            listaRegistro.add(cargaSisO);
            monitorEq=fila.getString(10);
            listaRegistro.add(monitorEq);
            audioEq = fila.getString(11);
            listaRegistro.add(audioEq);
            touchpadE=fila.getString(12);
            listaRegistro.add(touchpadE);
            obsExtras = fila.getString(13);
            listaRegistro.add(obsExtras);

            File ruta = null;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+"/MyApp/");
            }
            else{
                ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            }

            List<Bitmap> archivos = new ArrayList<>();

            File fotos[] = ruta.listFiles();

            if(fotos != null) {
                for (int i = 0; i < fotos.length; i++) {
                    if (fotos[i].getAbsolutePath().contains(codigo)) {
                        archivos.add(BitmapFactory.decodeFile(fotos[i].getAbsolutePath()));

                    }
                }
            }
            bd.close();
            presentador.exitoMostrar(listaRegistro, archivos);
        }
        else{
            bd.close();
            presentador.errorMostrar();
        }

    }

}
