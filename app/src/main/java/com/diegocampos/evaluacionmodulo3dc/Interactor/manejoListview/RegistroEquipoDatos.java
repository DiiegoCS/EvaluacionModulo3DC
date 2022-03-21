package com.diegocampos.evaluacionmodulo3dc.Interactor.manejoListview;

public class RegistroEquipoDatos {

    private String codigo;
    private String nombre;
    private String fecha;

    public RegistroEquipoDatos(String codigo, String nombre, String fecha) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fecha = fecha;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


}
