package com.diegocampos.evaluacionmodulo3dc.Interfaces.ConsultaRegistroEquipo;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public interface ConsultaRegistroEquipoPresenterInterface {

    void mostrarRegistro(Context contexto, String codigo);
    void exitoMostrar(ArrayList<String> listaRegistro ,List<Bitmap> archivos);
    void errorMostrar();
}
