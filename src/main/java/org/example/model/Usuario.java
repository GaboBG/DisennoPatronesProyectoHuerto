package org.example.model;

import java.time.LocalDate;
import java.time.Period;

public class Usuario {
    private int id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String email;
    private String contrasenna;
    private String rol;
    private boolean estado;
    private LocalDate fechaNacimiento;
    private LocalDate fechaRegistro;
    private String pin;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String primerApellido, String segundoApellido, String email, String contrasenna, String rol, boolean estado, LocalDate fechaNacimiento, LocalDate fechaRegistro, String pin) {
        this.id = id;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.email = email;
        this.contrasenna = contrasenna;
        this.rol = rol;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.pin = pin;
    }
    public Usuario(String nombre, String primerApellido, String segundoApellido,
                   String email, String contrasenna, String rol, boolean estado,
                   LocalDate fechaNacimiento, LocalDate fechaRegistro, String pin) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.email = email;
        this.contrasenna = contrasenna;
        this.rol = rol;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.pin = pin;
    }



    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    public int getEdad() {
        if (fechaNacimiento != null) {
            return Period.between(fechaNacimiento, LocalDate.now()).getYears();
        }
        return 0; // o lanzar excepción si lo prefieres
    }

    public void setId(int id) {
        this.id = id;
    }
    protected String getBaseToString() {
        return "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", email='" + email + '\'' +
                ", contrasenna='" + contrasenna + '\'' +
                ", rol='" + rol + '\'' +
                ", estado=" + estado +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaRegistro=" + fechaRegistro +
                ", pin="+ pin;
    }


}
