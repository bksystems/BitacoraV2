package com.apps.movilidadcb.bitacorav2.Entitys;

/**
 * Created by jcarl on 06/11/2016.
 */

public class ConocimientoEntity {
    public int Id;
    public String Conocimiento;
    public String Descripcion;
    public boolean isSelected;

    public ConocimientoEntity(int id, String conocimiento, String descripcion, boolean isSelected) {
        Id = id;
        Conocimiento = conocimiento;
        Descripcion = descripcion;
        this.isSelected = isSelected;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getConocimiento() {
        return Conocimiento;
    }

    public void setConocimiento(String conocimiento) {
        Conocimiento = conocimiento;
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
