package org.example.model;

import java.time.LocalDate;

public class Actividad {
    int idActividad;
    int idCultivo;
    String nombre;
    String tipoActividad;
    LocalDate fechaProgramada;
    String descripcion;
    boolean realizada;

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdCultivo() {
        return idCultivo;
    }

    public void setIdCultivo(int idCultivo) {
        this.idCultivo = idCultivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public LocalDate getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDate fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public Actividad(int idActividad, int idCultivo, String nombre, String tipoActividad, LocalDate fechaProgramada, String descripcion, boolean realizada) {
        this.idActividad = idActividad;
        this.idCultivo = idCultivo;
        this.nombre = nombre;
        this.tipoActividad = tipoActividad;
        this.fechaProgramada = fechaProgramada;
        this.descripcion = descripcion;
        this.realizada = realizada;
    }

    public Actividad(int idCultivo, String nombre, String tipoActividad, LocalDate fechaProgramada, String descripcion, boolean realizada) {
        this.idCultivo = idCultivo;
        this.nombre = nombre;
        this.tipoActividad = tipoActividad;
        this.fechaProgramada = fechaProgramada;
        this.descripcion = descripcion;
        this.realizada = realizada;
    }
}
