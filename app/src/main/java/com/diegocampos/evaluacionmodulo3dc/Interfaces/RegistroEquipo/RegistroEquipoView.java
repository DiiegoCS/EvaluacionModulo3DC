package com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroEquipo;

public interface RegistroEquipoView {
    void exitoRegistro();
    void errorRegistro();
    void setErrorCodigo();
    void setErrorNombre();
    void guardarRegistro();
    void solicitarGuardarFotos();
    void exitoGuardarFotos();

    void errorGuardarFotos();
}
