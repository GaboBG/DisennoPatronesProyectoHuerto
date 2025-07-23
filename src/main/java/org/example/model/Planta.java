package org.example.model;

public class Planta {

    int idPlanta;
    String nombre;
    String clima;
    String frecuenciaRiego;
    String descripcion;
    boolean estado;

    public int getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(int idPlanta) {
        this.idPlanta = idPlanta;
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

    public Planta(int idPlanta, String nombre, String clima, String frecuenciaRiego, String descripcion, boolean estado) {
        this.idPlanta = idPlanta;
        this.nombre = nombre;
        this.clima = clima;
        this.frecuenciaRiego = frecuenciaRiego;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Planta(String nombre, String clima, String frecuenciaRiego, String descripcion, boolean estado) {
        this.nombre = nombre;
        this.clima = clima;
        this.frecuenciaRiego = frecuenciaRiego;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre; // o incluso puedes hacer: return nombre + " - " + clima;
    }
}
