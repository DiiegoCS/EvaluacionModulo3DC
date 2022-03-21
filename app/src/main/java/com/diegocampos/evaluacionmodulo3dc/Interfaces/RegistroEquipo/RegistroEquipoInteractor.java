package com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroEquipo;

import android.content.Context;

import com.diegocampos.evaluacionmodulo3dc.Presenter.RegistroEquipoPresenterImpl;

public interface RegistroEquipoInteractor {
    void guardarRegistro(String codigoIngreso,String nombre, String marca, String modelo,
                   String fechaIngreso, String equipoEnCaja, String cargadorEnCaja , String manualEnCaja,
                         String garantiaEnCaja, String cargaSo, String monitor, String audio, String touchpad,
                         String observaciones,
                   RegistroEquipoPresenterImpl presenter, Context contexto);


}
