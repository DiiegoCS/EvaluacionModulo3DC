package com.diegocampos.evaluacionmodulo3dc.Interfaces.LoginUSer;

public interface Loginview {

    void mostrarProgreso();
    void esconderProgreso();
    void setErrorUser();
    void setErrorPassword();
    void exito(String nombre);
    void noExiste();
}
