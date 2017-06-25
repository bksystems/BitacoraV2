package com.apps.movilidadcb.bitacorav2.Entitys;

/**
 * Created by jurizo on 14/11/16.
 */

public class SincronizacionEntity {


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

    public String getOficinaVisitada() {
        return OficinaVisitada;
    }

    public void setOficinaVisitada(String oficinaVisitada) {
        OficinaVisitada = oficinaVisitada;
    }

    public String getFechaVisita() {
        return FechaVisita;
    }

    public void setFechaVisita(String fechaVisita) {
        FechaVisita = fechaVisita;
    }

    public int getNominaColaboradorPL() {
        return NominaColaboradorPL;
    }

    public void setNominaColaboradorPL(int nominaColaboradorPL) {
        NominaColaboradorPL = nominaColaboradorPL;
    }

    public String getEtiquetaDM() {
        return EtiquetaDM;
    }

    public void setEtiquetaDM(String etiquetaDM) {
        EtiquetaDM = etiquetaDM;
    }

    public int getNominaColaboradorVisita() {
        return NominaColaboradorVisita;
    }

    public void setNominaColaboradorVisita(int nominaColaboradorVisita) {
        NominaColaboradorVisita = nominaColaboradorVisita;
    }

    public String getTiempoUltSincronizacion() {
        return TiempoUltSincronizacion;
    }

    public void setTiempoUltSincronizacion(String tiempoUltSincronizacion) {
        TiempoUltSincronizacion = tiempoUltSincronizacion;
    }

    public String getCausaNoSincronizacion() {
        return CausaNoSincronizacion;
    }

    public void setCausaNoSincronizacion(String causaNoSincronizacion) {
        CausaNoSincronizacion = causaNoSincronizacion;
    }

    public SincronizacionEntity(int id, int idVisita, String oficinaVisitada, String fechaVisita, int nominaColaboradorPL, String etiquetaDM, int nominaColaboradorVisita, String tiempoUltSincronizacion, String causaNoSincronizacion) {
        Id = id;
        IdVisita = idVisita;
        OficinaVisitada = oficinaVisitada;
        FechaVisita = fechaVisita;
        NominaColaboradorPL = nominaColaboradorPL;
        EtiquetaDM = etiquetaDM;
        NominaColaboradorVisita = nominaColaboradorVisita;
        TiempoUltSincronizacion = tiempoUltSincronizacion;
        CausaNoSincronizacion = causaNoSincronizacion;
    }

    private int Id;
    private int IdVisita;
    private String OficinaVisitada;
    private String FechaVisita;
    private int NominaColaboradorPL;
    private String EtiquetaDM;
    private int NominaColaboradorVisita;
    private String TiempoUltSincronizacion;
    private String CausaNoSincronizacion;


}
