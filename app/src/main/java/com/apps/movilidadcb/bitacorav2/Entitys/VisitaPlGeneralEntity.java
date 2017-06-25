package com.apps.movilidadcb.bitacorav2.Entitys;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jcarl on 06/11/2016.
 */

public class VisitaPlGeneralEntity implements Serializable{
    private int Id;
    private int CC_ERP;
    private String OficinaVisitada;
    private String FechaVisita;
    private int NominaColaboradorPL;
    private String NombreColaboradorPL;
    private String IdRutaVisitada;
    private int NominaColaboradorVisita;
    private String NombreColaboradorVisita;
    private boolean IdentificaZonaTrabajo;
    private String TiempoZona;
    private boolean ExistenZonaRiesgo;
    private boolean RegistranZonaRiesgo;
    private boolean ConoceIdRuta;
    private boolean VisualizaGeocercaGrupos;
    private boolean ObservaGruposEnSmartPhone;
    private boolean sincronizado;

    public VisitaPlGeneralEntity(int id, int CC_ERP, String oficinaVisitada, String fechaVisita, int nominaColaboradorPL, String nombreColaboradorPL, String idRutaVisitada, int nominaColaboradorVisita, String nombreColaboradorVisita, boolean identificaZonaTrabajo, String tiempoZona, boolean existenZonaRiesgo, boolean registranZonaRiesgo, boolean conoceIdRuta, boolean visualizaGeocercaGrupos, boolean observaGruposEnSmartPhone, boolean sincronizado) {
        Id = id;
        this.CC_ERP = CC_ERP;
        OficinaVisitada = oficinaVisitada;
        FechaVisita = fechaVisita;
        NominaColaboradorPL = nominaColaboradorPL;
        NombreColaboradorPL = nombreColaboradorPL;
        IdRutaVisitada = idRutaVisitada;
        NominaColaboradorVisita = nominaColaboradorVisita;
        NombreColaboradorVisita = nombreColaboradorVisita;
        IdentificaZonaTrabajo = identificaZonaTrabajo;
        TiempoZona = tiempoZona;
        ExistenZonaRiesgo = existenZonaRiesgo;
        RegistranZonaRiesgo = registranZonaRiesgo;
        ConoceIdRuta = conoceIdRuta;
        VisualizaGeocercaGrupos = visualizaGeocercaGrupos;
        ObservaGruposEnSmartPhone = observaGruposEnSmartPhone;
        this.sincronizado = sincronizado;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getNombreColaboradorPL() {
        return NombreColaboradorPL;
    }

    public void setNombreColaboradorPL(String nombreColaboradorPL) {
        NombreColaboradorPL = nombreColaboradorPL;
    }

    public String getIdRutaVisitada() {
        return IdRutaVisitada;
    }

    public void setIdRutaVisitada(String idRutaVisitada) {
        IdRutaVisitada = idRutaVisitada;
    }

    public int getNominaColaboradorVisita() {
        return NominaColaboradorVisita;
    }

    public void setNominaColaboradorVisita(int nominaColaboradorVisita) {
        NominaColaboradorVisita = nominaColaboradorVisita;
    }

    public String getNombreColaboradorVisita() {
        return NombreColaboradorVisita;
    }

    public void setNombreColaboradorVisita(String nombreColaboradorVisita) {
        NombreColaboradorVisita = nombreColaboradorVisita;
    }

    public boolean isIdentificaZonaTrabajo() {
        return IdentificaZonaTrabajo;
    }

    public void setIdentificaZonaTrabajo(boolean identificaZonaTrabajo) {
        IdentificaZonaTrabajo = identificaZonaTrabajo;
    }

    public String getTiempoZona() {
        return TiempoZona;
    }

    public void setTiempoZona(String tiempoZona) {
        TiempoZona = tiempoZona;
    }

    public boolean isExistenZonaRiesgo() {
        return ExistenZonaRiesgo;
    }

    public void setExistenZonaRiesgo(boolean existenZonaRiesgo) {
        ExistenZonaRiesgo = existenZonaRiesgo;
    }

    public boolean isRegistranZonaRiesgo() {
        return RegistranZonaRiesgo;
    }

    public void setRegistranZonaRiesgo(boolean registranZonaRiesgo) {
        RegistranZonaRiesgo = registranZonaRiesgo;
    }

    public boolean isConoceIdRuta() {
        return ConoceIdRuta;
    }

    public void setConoceIdRuta(boolean conoceIdRuta) {
        ConoceIdRuta = conoceIdRuta;
    }

    public boolean isVisualizaGeocercaGrupos() {
        return VisualizaGeocercaGrupos;
    }

    public void setVisualizaGeocercaGrupos(boolean visualizaGeocercaGrupos) {
        VisualizaGeocercaGrupos = visualizaGeocercaGrupos;
    }

    public boolean isObservaGruposEnSmartPhone() {
        return ObservaGruposEnSmartPhone;
    }

    public void setObservaGruposEnSmartPhone(boolean observaGruposEnSmartPhone) {
        ObservaGruposEnSmartPhone = observaGruposEnSmartPhone;
    }

    public boolean isSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(boolean sincronizado) {
        this.sincronizado = sincronizado;
    }

    public int getCC_ERP() {
        return CC_ERP;
    }

    public void setCC_ERP(int CC_ERP) {
        this.CC_ERP = CC_ERP;
    }


    @Override
    public String toString() {
        return "VisitaPlGeneralEntity [Id = " + Id + ", CC_ERP = " + CC_ERP + ", OficinaVisitada = " + OficinaVisitada + ", FechaVisita = " + FechaVisita +
                ", NominaColaboradorPL = " + NominaColaboradorPL + ", NombreColaboradorPL = " + NombreColaboradorPL + ", IdRutaVisitada = " + IdRutaVisitada +
                ", NominaColaboradorVisita = " + NominaColaboradorVisita + ", NombreColaboradorVisita = " + NombreColaboradorVisita + ", IdentificaZonaTrabajo = " + IdentificaZonaTrabajo +
                ", TiempoZona = " + TiempoZona + ", ExistenZonaRiesgo = " + ExistenZonaRiesgo + ", RegistranZonaRiesgo = " + RegistranZonaRiesgo + ", ConoceIdRuta = " + ConoceIdRuta +
                ", VisualizaGeocercaGrupos = " + VisualizaGeocercaGrupos + ", ObservaGruposEnSmartPhone = " + ObservaGruposEnSmartPhone + ", sincronizado = " + sincronizado + "]";

    }
}
