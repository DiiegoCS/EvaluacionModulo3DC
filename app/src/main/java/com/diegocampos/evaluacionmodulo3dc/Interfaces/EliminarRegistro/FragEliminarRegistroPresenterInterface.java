package com.diegocampos.evaluacionmodulo3dc.Interfaces.EliminarRegistro;

import android.content.Context;

import java.util.ArrayList;

public interface FragEliminarRegistroPresenterInterface {

    void BuscarReg(Context contexto, String codigo);
    void exitoBuscarR(ArrayList<String> datos);
    void errorBuscarR();
    void eliminarRegistro(Context contexto, String codigo);
    void exitoEliminar();
    void errorEliminar();
}
