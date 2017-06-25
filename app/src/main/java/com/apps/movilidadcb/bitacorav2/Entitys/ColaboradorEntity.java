package com.apps.movilidadcb.bitacorav2.Entitys;

/**
 * Created by jcarl on 05/11/2016.
 */

public class ColaboradorEntity {
    public int Id;
    public String CC_ERP;
    public String Nomina;
    public String Colaborador;
    public String Puesto;

    public ColaboradorEntity(int id, String CC_ERP, String nomina, String colaborador, String puesto) {
        Id = id;
        this.CC_ERP = CC_ERP;
        Nomina = nomina;
        Colaborador = colaborador;
        Puesto = puesto;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCC_ERP() {
        return CC_ERP;
    }

    public void setCC_ERP(String CC_ERP) {
        this.CC_ERP = CC_ERP;
    }

    public String getNomina() {
        return Nomina;
    }

    public void setNomina(String nomina) {
        Nomina = nomina;
    }

    public String getColaborador() {
        return Colaborador;
    }

    public void setColaborador(String colaborador) {
        Colaborador = colaborador;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }
}
