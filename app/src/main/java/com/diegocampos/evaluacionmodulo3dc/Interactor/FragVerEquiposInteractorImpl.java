package com.diegocampos.evaluacionmodulo3dc.Interactor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.diegocampos.evaluacionmodulo3dc.Interactor.manejoListview.RegistroEquipoDatos;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.VerEquipos.FragVerEquiposInteractorInterface;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.VerEquipos.FragVerEquiposPresenterInterface;

import java.util.ArrayList;

public class FragVerEquiposInteractorImpl implements FragVerEquiposInteractorInterface {


    ArrayList<RegistroEquipoDatos> listaRegistros = new ArrayList<>();

   public void llenarLista(FragVerEquiposPresenterInterface presentador, Context contexto) {

       String codigo = "";
       String nombre = "";
       String fecha = "";

       ConexionBD conexion = new ConexionBD(contexto, "administracion", null, 1);
       SQLiteDatabase bd = conexion.getWritableDatabase();

       Cursor fila = bd.rawQuery("SELECT codigoIngreso,nombre,fechaIngreso FROM registroEquipos ORDER BY fechaIngreso DESC", null);

       if (fila.moveToFirst()) {
           do {
               codigo = fila.getString(0);
               nombre = fila.getString(1);
               fecha = fila.getString(2);

               listaRegistros.add(
                       new RegistroEquipoDatos(codigo, nombre,  fecha));

           } while (fila.moveToNext());

           presentador.exito(listaRegistros);

       } else {
          presentador.error();
       }
       fila.close();
       bd.close();
   }
}
