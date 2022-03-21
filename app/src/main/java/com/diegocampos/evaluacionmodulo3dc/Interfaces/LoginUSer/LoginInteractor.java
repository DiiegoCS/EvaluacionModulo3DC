package com.diegocampos.evaluacionmodulo3dc.Interfaces.LoginUSer;

import android.content.Context;

public interface LoginInteractor {
    void validarUsuario(String user, String pass, LoginPresenter presentador, Context contexto);
}
