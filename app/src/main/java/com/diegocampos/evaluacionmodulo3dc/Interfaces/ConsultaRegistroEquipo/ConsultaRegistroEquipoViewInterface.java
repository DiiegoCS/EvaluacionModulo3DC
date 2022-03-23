package com.diegocampos.evaluacionmodulo3dc.Interfaces.ConsultaRegistroEquipo;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public interface ConsultaRegistroEquipoViewInterface {

    void exitoMostrar(ArrayList<String> listaRegistro , List<Bitmap> archivos);
    void errorMostrar();
}
