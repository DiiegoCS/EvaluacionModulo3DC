package com.diegocampos.evaluacionmodulo3dc.Interfaces.VerEquipos;

import com.diegocampos.evaluacionmodulo3dc.Interactor.manejoListview.RegistroEquipoDatos;

import java.util.ArrayList;

public interface FragVerEquiposViewInterface {

    void exitoVer(ArrayList<RegistroEquipoDatos> listaRegistros);
    void errorVer();

}
