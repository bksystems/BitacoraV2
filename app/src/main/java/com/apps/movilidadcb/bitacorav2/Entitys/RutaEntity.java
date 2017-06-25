package com.apps.movilidadcb.bitacorav2.Entitys;

import com.apps.movilidadcb.bitacorav2.DataBase.DatabaseHelper;

/**
 * Created by jcarl on 05/11/2016.
 */

public class RutaEntity {
    public String CC_ERP;
    public String Oficina;
    public String Nombre;
    public String Nomina;
    public String Id_Ruta;
    public String CC;

    public RutaEntity(String CC_ERP, String oficina, String nombre, String nomina, String id_Ruta, String CC) {
        this.CC_ERP = CC_ERP;
        Oficina = oficina;
        Nombre = nombre;
        Nomina = nomina;
        Id_Ruta = id_Ruta;
        this.CC = CC;
    }

    public String getCC_ERP() {
        return CC_ERP;
    }

    public void setCC_ERP(String CC_ERP) {
        this.CC_ERP = CC_ERP;
    }

    public String getOficina() {
        return Oficina;
    }

    public void setOficina(String oficina) {
        Oficina = oficina;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNomina() {
        return Nomina;
    }

    public void setNomina(String nomina) {
        Nomina = nomina;
    }

    public String getId_Ruta() {
        return Id_Ruta;
    }

    public void setId_Ruta(String id_Ruta) {
        Id_Ruta = id_Ruta;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }
}
