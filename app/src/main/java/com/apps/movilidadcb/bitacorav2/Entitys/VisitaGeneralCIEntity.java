package com.apps.movilidadcb.bitacorav2.Entitys;

/**
 * Created by jcarl on 13/11/2016.
 */

public class VisitaGeneralCIEntity {
    private int Id;
    private int IdVisita;
    private int CC_ERP;
    private String Oficina;
    private String RutaId;
    private int NominaColaborador;
    private String NombreColaborador;
    private String UtilizaFormatos;
    private int NominaColaboradorPL;
    private String NombreColaboradorPL;
    private String FechaVisita;

    public VisitaGeneralCIEntity(int id, int idVisita, int CC_ERP, String oficina, String rutaId, int nominaColaborador, String nombreColaborador, String utilizaFormatos, int nominaColaboradorPL, String nombreColaboradorPL, String fechaVisita) {
        Id = id;
        IdVisita = idVisita;
        this.CC_ERP = CC_ERP;
        Oficina = oficina;
        RutaId = rutaId;
        NominaColaborador = nominaColaborador;
        NombreColaborador = nombreColaborador;
        UtilizaFormatos = utilizaFormatos;
        NominaColaboradorPL = nominaColaboradorPL;
        NombreColaboradorPL = nombreColaboradorPL;
        FechaVisita = fechaVisita;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdVisita() {
        return IdVisita;
    }

    public void setIdVisita(int idVisita) {
        IdVisita = idVisita;
    }

    public int getCC_ERP() {
        return CC_ERP;
    }

    public void setCC_ERP(int CC_ERP) {
        this.CC_ERP = CC_ERP;
    }

    public String getOficina() {
        return Oficina;
    }

    public void setOficina(String oficina) {
        Oficina = oficina;
    }

    public String getRutaId() {
        return RutaId;
    }

    public void setRutaId(String rutaId) {
        RutaId = rutaId;
    }

    public int getNominaColaborador() {
        return NominaColaborador;
    }

    public void setNominaColaborador(int nominaColaborador) {
        NominaColaborador = nominaColaborador;
    }

    public String getNombreColaborador() {
        return NombreColaborador;
    }

    public void setNombreColaborador(String nombreColaborador) {
        NombreColaborador = nombreColaborador;
    }

    public String getUtilizaFormatos() {
        return UtilizaFormatos;
    }

    public void setUtilizaFormatos(String utilizaFormatos) {
        UtilizaFormatos = utilizaFormatos;
    }

    public int getNominaColaboradorPL() {
        return NominaColaboradorPL;
    }

    public void setNominaColaboradorPL(int nominaColaboradorPL) {
        NominaColaboradorPL = nominaColaboradorPL;
    }

    public String getNombreColaboradorPL() {
        return NombreColaboradorPL;
    }

    public void setNombreColaboradorPL(String nombreColaboradorPL) {
        NombreColaboradorPL = nombreColaboradorPL;
    }

    public String getFechaVisita() {
        return FechaVisita;
    }

    public void setFechaVisita(String fechaVisita) {
        FechaVisita = fechaVisita;
    }
}
