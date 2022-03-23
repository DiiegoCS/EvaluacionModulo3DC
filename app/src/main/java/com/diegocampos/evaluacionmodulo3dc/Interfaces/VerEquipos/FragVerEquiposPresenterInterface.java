package com.diegocampos.evaluacionmodulo3dc.Interfaces.VerEquipos;

import android.content.Context;

import com.diegocampos.evaluacionmodulo3dc.Interactor.manejoListview.RegistroEquipoDatos;

import java.util.ArrayList;

public interface FragVerEquiposPresenterInterface {

    void llenarLista(Context contexto);
    void exito(ArrayList<RegistroEquipoDatos> listaRegistros);
    void error();

}
