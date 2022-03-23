package com.diegocampos.evaluacionmodulo3dc.Presenter;

import android.content.Context;

import com.diegocampos.evaluacionmodulo3dc.Interactor.FragEliminarRegistroInteractorImpl;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.EliminarRegistro.FragEliminarRegistroInteractorInterface;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.EliminarRegistro.FragEliminarRegistroPresenterInterface;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.EliminarRegistro.FragEliminarRegistroViewInterface;

import java.util.ArrayList;

public class FragEliminarRegistroPresenterImpl implements FragEliminarRegistroPresenterInterface {

    private FragEliminarRegistroViewInterface vista;
    private FragEliminarRegistroInteractorInterface interactor;

    public FragEliminarRegistroPresenterImpl(FragEliminarRegistroViewInterface vista) {
        this.vista = vista;
        this.interactor= new FragEliminarRegistroInteractorImpl();
    }

    @Override
    public void BuscarReg(Context contexto, String codigo) {
        interactor.BuscarReg(this, contexto, codigo);
    }

    @Override
    public void exitoBuscarR(ArrayList<String> datos) {
        vista.exitoBuscarR(datos);
    }

    @Override
    public void errorBuscarR() {
        vista.errorBuscarR();
    }

    @Override
    public void eliminarRegistro(Context contexto, String codigo) {
        interactor.eliminarRegistro(this, contexto, codigo);
    }

    @Override
    public void exitoEliminar() {
        vista.exitoEliminar();
    }

    @Override
    public void errorEliminar() {
        vista.errorEliminar();
    }
}
