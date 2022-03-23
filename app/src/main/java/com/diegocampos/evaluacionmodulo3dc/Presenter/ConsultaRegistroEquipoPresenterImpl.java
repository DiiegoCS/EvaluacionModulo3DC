package com.diegocampos.evaluacionmodulo3dc.Presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.diegocampos.evaluacionmodulo3dc.Interactor.ConsultaRegistroInteractorImpl;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.ConsultaRegistroEquipo.ConsultaRegistroEquipoInteractorInterface;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.ConsultaRegistroEquipo.ConsultaRegistroEquipoPresenterInterface;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.ConsultaRegistroEquipo.ConsultaRegistroEquipoViewInterface;

import java.util.ArrayList;
import java.util.List;

public class ConsultaRegistroEquipoPresenterImpl implements ConsultaRegistroEquipoPresenterInterface {

    private ConsultaRegistroEquipoViewInterface vista;
    private ConsultaRegistroEquipoInteractorInterface interactor;

    public ConsultaRegistroEquipoPresenterImpl(ConsultaRegistroEquipoViewInterface vista) {
        this.vista = vista;
        this.interactor= new ConsultaRegistroInteractorImpl();
    }

    @Override
    public void mostrarRegistro(Context contexto, String codigo) {
        interactor.mostrarRegistro(this, contexto, codigo);

    }

    @Override
    public void exitoMostrar(ArrayList<String> listaRegistro, List<Bitmap> archivos) {
        vista.exitoMostrar(listaRegistro, archivos);
    }

    @Override
    public void errorMostrar() {
        vista.errorMostrar();
    }
}
