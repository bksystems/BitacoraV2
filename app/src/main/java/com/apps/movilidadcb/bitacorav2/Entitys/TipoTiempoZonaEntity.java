package com.apps.movilidadcb.bitacorav2.Entitys;

/**
 * Created by jcarl on 06/11/2016.
 */

public class TipoTiempoZonaEntity {
    public int Id;
    public String Tiempo;
    public String Descripcion;

    public TipoTiempoZonaEntity(int id, String tiempo, String descripcion) {
        Id = id;
        Tiempo = tiempo;
        Descripcion = descripcion;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String tiempo) {
        Tiempo = tiempo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
