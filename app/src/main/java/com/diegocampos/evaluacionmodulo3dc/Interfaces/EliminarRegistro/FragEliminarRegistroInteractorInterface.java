package com.diegocampos.evaluacionmodulo3dc.Interfaces.EliminarRegistro;

import android.content.Context;

public interface FragEliminarRegistroInteractorInterface {

    void BuscarReg(FragEliminarRegistroPresenterInterface presentador, Context contexto, String codigo);
    void eliminarRegistro(FragEliminarRegistroPresenterInterface presentador, Context contexto, String codigo);
}
