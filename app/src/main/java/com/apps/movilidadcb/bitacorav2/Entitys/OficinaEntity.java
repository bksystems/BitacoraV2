package com.apps.movilidadcb.bitacorav2.Entitys;

/**
 * Created by jcarl on 05/11/2016.
 */

public class OficinaEntity {
    public int Id;
    public int IdOS;
    public int CC;
    public String CC_ERP;
    public String Nombre;
    public String Region;
    public String Subdireccion;
    public String Direccion;

    public OficinaEntity(int id, int idOS, int CC, String CC_ERP, String nombre, String region, String subdireccion, String direccion) {
        Id = id;
        IdOS = idOS;
        this.CC = CC;
        this.CC_ERP = CC_ERP;
        Nombre = nombre;
        Region = region;
        Subdireccion = subdireccion;
        Direccion = direccion;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdOS() {
        return IdOS;
    }

    public void setIdOS(int idOS) {
        IdOS = idOS;
    }

    public int getCC() {
        return CC;
    }

    public void setCC(int CC) {
        this.CC = CC;
    }

    public String getCC_ERP() {
        return CC_ERP;
    }

    public void setCC_ERP(String CC_ERP) {
        this.CC_ERP = CC_ERP;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getSubdireccion() {
        return Subdireccion;
    }

    public void setSubdireccion(String subdireccion) {
        Subdireccion = subdireccion;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }
}
