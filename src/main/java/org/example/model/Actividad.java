package org.example.model;

public class Actividad {
    int idActividad;
    String nombre;
    String clima;
    String tipoSuelo;
    String frecuenciaRiego;
    String descripcion;
    boolean estado;

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTipoSuelo() {
        return tipoSuelo;
    }

    public void setTipoSuelo(String tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }

    public String getFrecuenciaRiego() {
        return frecuenciaRiego;
    }

    public void setFrecuenciaRiego(String frecuenciaRiego) {
        this.frecuenciaRiego = frecuenciaRiego;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Actividad(int idActividad, String nombre, String clima, String tipoSuelo, String frecuenciaRiego, String descripcion, boolean estado) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.clima = clima;
        this.tipoSuelo = tipoSuelo;
        this.frecuenciaRiego = frecuenciaRiego;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Actividad(String nombre, String clima, String tipoSuelo, String frecuenciaRiego, String descripcion, boolean estado) {
        this.nombre = nombre;
        this.clima = clima;
        this.tipoSuelo = tipoSuelo;
        this.frecuenciaRiego = frecuenciaRiego;
        this.descripcion = descripcion;
        this.estado = estado;
    }
}
