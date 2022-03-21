package com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroEquipo;

import android.content.Context;

public interface RegistroEquipoPresenter {
    void guardarRegistro(String codigoIngreso, String nombre, String marca, String modelo,
                         String fechaIngreso, String equipoEnCaja, String cargadorEnCaja , String manualEnCaja,
                         String garantiaEnCaja, String cargaSo, String monitor, String audio, String touchpad,
                         String observaciones, Context contexto);
    void exito();
    void error();
    void setErrorCodigo();
    void setErrorNombre();
}
