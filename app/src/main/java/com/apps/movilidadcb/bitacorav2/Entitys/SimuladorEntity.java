package com.apps.movilidadcb.bitacorav2.Entitys;

/**
 * Created by jurizo on 15/11/16.
 */

public class SimuladorEntity {

    private String OficinaVisitada;
    private String FechaVisita;
    private int NominaColaboradorPL;
    private String NombreOficina;
    private int NominaColaborador;
    private boolean ColaboradorUtilizaSimulador;
    private String Motivo;
    private String Incidencia;
    private String Comentarios;
    private int Id;
    private int IdVisita;

    public SimuladorEntity(int id, int idVisita, String oficinaVisitada, String fechaVisita, int nominaColaboradorPL, String nombreOficina, int nominaColaborador, boolean colaboradorUtilizaSimulador, String motivo, String incidencia, String comentarios) {
        OficinaVisitada = oficinaVisitada;
        FechaVisita = fechaVisita;
        NominaColaboradorPL = nominaColaboradorPL;
        NombreOficina = nombreOficina;
        NominaColaborador = nominaColaborador;
        ColaboradorUtilizaSimulador = colaboradorUtilizaSimulador;
        Motivo = motivo;
        Incidencia = incidencia;
        Comentarios = comentarios;
        Id = id;
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

    public String getNombreOficina() {
        return NombreOficina;
    }

    public void setNombreOficina(String nombreOficina) {
        NombreOficina = nombreOficina;
    }

    public int getNominaColaborador() {
        return NominaColaborador;
    }

    public void setNominaColaborador(int nominaColaborador) {
        NominaColaborador = nominaColaborador;
    }

    public boolean getColaboradorUtilizaSimulador() {
        return ColaboradorUtilizaSimulador;
    }

    public void setColaboradorUtilizaSimulador(boolean colaboradorUtilizaSimulador) {
        ColaboradorUtilizaSimulador = colaboradorUtilizaSimulador;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String motivo) {
        Motivo = motivo;
    }

    public String getIncidencia() {
        return Incidencia;
    }

    public void setIncidencia(String incidencia) {
        Incidencia = incidencia;
    }

    public String getComentarios() {
        return Comentarios;
    }

    public void setComentarios(String comentarios) {
        Comentarios = comentarios;
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
}
