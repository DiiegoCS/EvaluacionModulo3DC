package com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroUser;

import android.content.Context;

public interface RegistroView {
    void exito();
    void error();
    void setErrorNombre();
    void setErrorUser();
    void setErrorPassword();
}
