package com.diegocampos.evaluacionmodulo3dc.Presenter;

import android.content.Context;

import com.diegocampos.evaluacionmodulo3dc.Interactor.LoginInteractorImpl;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.LoginUSer.LoginInteractor;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.LoginUSer.LoginPresenter;
import com.diegocampos.evaluacionmodulo3dc.View.actividades.Login;

public class LoginPresenterImpl implements LoginPresenter {

    Login vista;
    LoginInteractor interactor;

    public LoginPresenterImpl(Login vista) {
        this.vista = vista;
        interactor = new LoginInteractorImpl();
    }


    @Override
    public void validarUsuario(String user, String pass, Context contexto) {
        if(vista != null){
            vista.mostrarProgreso();
        }
        interactor.validarUsuario(user, pass, this, contexto);
    }

    @Override
    public void setErrorUser() {
        if(vista != null){
            vista.esconderProgreso();
            vista.setErrorUser();
        }
    }

    @Override
    public void setErrorPassword() {
        if(vista != null){
            vista.esconderProgreso();
            vista.setErrorPassword();
        }
    }

    @Override
    public void exito(String nombre) {
        if(vista != null){
            vista.esconderProgreso();
            vista.exito(nombre);
        }
    }

    @Override
    public void noExiste() {
        vista.noExiste();
        vista.esconderProgreso();
    }
}
