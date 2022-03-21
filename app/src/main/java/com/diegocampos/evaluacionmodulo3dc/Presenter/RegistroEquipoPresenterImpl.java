package com.diegocampos.evaluacionmodulo3dc.Presenter;

import android.content.Context;

import com.diegocampos.evaluacionmodulo3dc.Interactor.RegistroEquipoInteractorImpl;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroEquipo.RegistroEquipoInteractor;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroEquipo.RegistroEquipoPresenter;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroEquipo.RegistroEquipoView;

public class RegistroEquipoPresenterImpl implements RegistroEquipoPresenter {
    RegistroEquipoView vista;
    RegistroEquipoInteractor interactor;

    public RegistroEquipoPresenterImpl(RegistroEquipoView vista) {
        this.vista = vista;
        this.interactor = new RegistroEquipoInteractorImpl();
    }

    @Override
    public void guardarRegistro( String codigoIngreso,String nombre, String marca, String modelo,
                                 String fechaIngreso, String equipoEnCaja, String cargadorEnCaja , String manualEnCaja,
                                 String garantiaEnCaja, String cargaSo, String monitor, String audio, String touchpad,
                                 String observaciones, Context contexto) {
        interactor.guardarRegistro(codigoIngreso, nombre, marca, modelo, fechaIngreso, equipoEnCaja, cargadorEnCaja , manualEnCaja,
                garantiaEnCaja, cargaSo, monitor, audio, touchpad, observaciones, this, contexto);
    }


    @Override
    public void exito() {
        vista.exitoRegistro();
    }

    @Override
    public void error() {
        vista.errorRegistro();
    }

    @Override
    public void setErrorCodigo() {
        vista.setErrorCodigo();
    }

    @Override
    public void setErrorNombre() {
        vista.setErrorNombre();
    }
}
