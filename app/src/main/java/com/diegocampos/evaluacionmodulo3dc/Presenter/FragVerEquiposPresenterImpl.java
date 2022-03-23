package com.diegocampos.evaluacionmodulo3dc.Presenter;

import android.content.Context;

import com.diegocampos.evaluacionmodulo3dc.Interactor.FragVerEquiposInteractorImpl;
import com.diegocampos.evaluacionmodulo3dc.Interactor.manejoListview.RegistroEquipoDatos;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.VerEquipos.FragVerEquiposInteractorInterface;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.VerEquipos.FragVerEquiposPresenterInterface;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.VerEquipos.FragVerEquiposViewInterface;

import java.util.ArrayList;

public class FragVerEquiposPresenterImpl implements FragVerEquiposPresenterInterface {

    FragVerEquiposViewInterface vista;
    FragVerEquiposInteractorInterface interactor;

    public FragVerEquiposPresenterImpl(FragVerEquiposViewInterface vista) {
        this.vista = vista;
        this.interactor = new FragVerEquiposInteractorImpl();
    }

    @Override
    public void llenarLista(Context contexto) {
        interactor.llenarLista(this, contexto);
    }

    @Override
    public void exito(ArrayList<RegistroEquipoDatos> listaRegistros) {
        vista.exitoVer(listaRegistros);}

    @Override
    public void error() {vista.errorVer();}
}
