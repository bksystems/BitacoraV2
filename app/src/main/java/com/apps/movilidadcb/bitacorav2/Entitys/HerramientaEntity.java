package com.apps.movilidadcb.bitacorav2.Entitys;

/**
 * Created by jcarl on 06/11/2016.
 */

public class HerramientaEntity {
    public int Id;
    public String Tipo;
    public String Descripcion;
    public boolean isSelected;

    public HerramientaEntity(int id, String tipo, String descripcion) {
        Id = id;
        Tipo = tipo;
        Descripcion = descripcion;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
