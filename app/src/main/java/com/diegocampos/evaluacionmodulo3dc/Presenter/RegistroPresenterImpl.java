package com.diegocampos.evaluacionmodulo3dc.Presenter;

import android.content.Context;

import com.diegocampos.evaluacionmodulo3dc.Interactor.RegistroInteractorImpl;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroUser.RegistroInteractor;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroUser.RegistroPresenter;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroUser.RegistroView;

public class RegistroPresenterImpl implements RegistroPresenter {

    RegistroView vista;
    RegistroInteractor interactor;

    public RegistroPresenterImpl(RegistroView vista) {
        this.vista = vista;
        interactor = new RegistroInteractorImpl();
    }

    @Override
    public void registrar(String nombre, String user, String pass, Context contexto) {
        interactor.registrar(nombre, user, pass, this, contexto);
    }

    @Override
    public void error() {
        vista.error();
    }

    @Override
    public void exito() {
        vista.exito();
    }

    @Override
    public void setErrorNombre() {
        vista.setErrorNombre();
    }

    @Override
    public void setErrorUser() {
        vista.setErrorUser();
    }

    @Override
    public void setErrorPassword() {
        vista.setErrorPassword();
    }
}