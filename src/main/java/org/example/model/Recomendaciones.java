package org.example.model;

import java.time.LocalDate;

public class Recomendaciones {

    int id;
    int cultivoId;
    int IdAsesorCliente;
    LocalDate fechaInicio;
    LocalDate fechaFinal;
    String observaciones;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCultivoId() {
        return cultivoId;
    }

    public void setCultivoId(int cultivoId) {
        this.cultivoId = cultivoId;
    }

    public int getIdAsesorCliente() {
        return IdAsesorCliente;
    }

    public void setIdAsesorCliente(int idAsesorCliente) {
        IdAsesorCliente = idAsesorCliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Recomendaciones(int id, int cultivoId, int idAsesorCliente, LocalDate fechaInicio, LocalDate fechaFinal, String observaciones) {
        this.id = id;
        this.cultivoId = cultivoId;
        IdAsesorCliente = idAsesorCliente;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.observaciones = observaciones;
    }

    public Recomendaciones(int cultivoId, int idAsesorCliente, LocalDate fechaInicio, LocalDate fechaFinal, String observaciones) {
        this.cultivoId = cultivoId;
        IdAsesorCliente = idAsesorCliente;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.observaciones = observaciones;
    }
}
