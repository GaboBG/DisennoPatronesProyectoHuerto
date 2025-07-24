package org.example.model;

import java.time.LocalDate;

public class Asesor extends Usuario{
    private String especialidad;
    private String zona;
    private String certificacion;
    public Asesor(int id, String nombre, String primerApellido, String segundoApellido, String email,
                   String contrasenna, String rol, boolean estado, LocalDate fechaNacimiento, LocalDate fechaRegistro, String pin,
                   String zona, String especialidad, String certificacion) {
        super(id, nombre, primerApellido, segundoApellido, email, contrasenna, "Asesor", estado, fechaNacimiento, fechaRegistro, pin);
        this.zona = zona;
        this.certificacion = certificacion;
        this.especialidad = especialidad;
    }

    public Asesor(int i, String nombre, String apellido1, String apellido2, String email, String contrasenna, boolean estado, LocalDate fechaNacimiento, String pin, String zona, String especialidad, String certificacion) {

    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(String certificacion) {
        this.certificacion = certificacion;
    }

    @Override
    public String toString() {
        return "Asesor{" + getBaseToString() +
                ", zona='" + zona + '\'' +
                ", certificación='" + certificacion + '\'' +
                ", especialidad=" + especialidad +
                '}';
    }
}
