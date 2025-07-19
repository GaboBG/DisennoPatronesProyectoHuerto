package org.example.model;

import java.time.LocalDate;

public class Cultivo {

    int idCultivo;
    int idCliente;
    int idPlanta;
    String nombre;
    String ubicacion;
    LocalDate fechaSiembra;
    LocalDate fechaCosecha;
    String estado;

    public int getIdCultivo() {
        return idCultivo;
    }

    public void setIdCultivo(int idCultivo) {
        this.idCultivo = idCultivo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDate getFechaSiembra() {
        return fechaSiembra;
    }

    public void setFechaSiembra(LocalDate fechaSiembra) {
        this.fechaSiembra = fechaSiembra;
    }

    public LocalDate getFechaCosecha() {
        return fechaCosecha;
    }

    public void setFechaCosecha(LocalDate fechaCosecha) {
        this.fechaCosecha = fechaCosecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cultivo(int idCultivo, int idCliente, int idPlanta, String nombre, String ubicacion, LocalDate fechaSiembra, LocalDate fechaCosecha, String estado) {
        this.idCultivo = idCultivo;
        this.idCliente = idCliente;
        this.idPlanta = idPlanta;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fechaSiembra = fechaSiembra;
        this.fechaCosecha = fechaCosecha;
        this.estado = estado;
    }

    public Cultivo(int idCliente, int idPlanta, String nombre, String ubicacion, LocalDate fechaSiembra, LocalDate fechaCosecha, String estado) {
        this.idCliente = idCliente;
        this.idPlanta = idPlanta;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fechaSiembra = fechaSiembra;
        this.fechaCosecha = fechaCosecha;
        this.estado = estado;
    }
}
