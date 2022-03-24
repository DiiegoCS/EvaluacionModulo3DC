package com.diegocampos.evaluacionmodulo3dc.Interactor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroEquipo.RegistroEquipoInteractor;
import com.diegocampos.evaluacionmodulo3dc.Presenter.RegistroEquipoPresenterImpl;

public class RegistroEquipoInteractorImpl implements RegistroEquipoInteractor {
    @Override
    public void guardarRegistro(String codigoIngreso,String nombre, String marca, String modelo,
                                String fechaIngreso, String equipoEnCaja, String cargadorEnCaja , String manualEnCaja,
                                String garantiaEnCaja, String cargaSo, String monitor, String audio, String touchpad,
                                String observaciones,
                                RegistroEquipoPresenterImpl presenter, Context contexto) {
        if(codigoIngreso.equals("")){
            presenter.setErrorCodigo();
        }
        else if(nombre.equals("")){
            presenter.setErrorNombre();
        }
        else {
            //registrar en SQLite
            ConexionBD conexion = new ConexionBD(contexto, "administracion", null, 1);
            SQLiteDatabase bd = conexion.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("codigoIngreso", codigoIngreso);
            registro.put("nombre", nombre);
            registro.put("marca", marca);
            registro.put("modelo", modelo);
            registro.put("fechaIngreso", fechaIngreso);
            registro.put("equipoEnCaja", equipoEnCaja);
            registro.put("cargadorEnCaja", cargadorEnCaja);
            registro.put("manualEnCaja", manualEnCaja);
            registro.put("garantiaEnCaja", garantiaEnCaja);
            registro.put("cargaSo", cargaSo);
            registro.put("monitor", monitor);
            registro.put("audio", audio);
            registro.put("touchpad", touchpad);
            registro.put("observaciones", observaciones);


            long x = bd.insert("registroEquipos", null, registro);

            if(x > 0){
                presenter.exito();
            }
            else{
                presenter.error();
            }

            bd.close();

            presenter.exito();
        }
    }
}
