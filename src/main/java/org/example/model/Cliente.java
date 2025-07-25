package org.example.model;

import java.time.LocalDate;

public class Cliente extends Usuario {
    private String zona;
    private String tipoFinca;
    private int tamannoTerreno;

    public Cliente(int i, String nombre, String apellido1, String apellido2, String email, String contrasenna, boolean estado, LocalDate fechaNacimiento, LocalDate fechaRegistro, String pin) {
    }


    public Cliente(int id, String nombre, String primerApellido, String segundoApellido, String email,
                   String contrasenna, String rol, boolean estado, LocalDate fechaNacimiento, LocalDate fechaRegistro, String pin,
                   String zona, String tipoFinca, int tamannoTerreno) {
        super(id, nombre, primerApellido, segundoApellido, email, contrasenna, "Cliente", estado, fechaNacimiento, fechaRegistro, pin);
        this.zona = zona;
        this.tipoFinca = tipoFinca;
        this.tamannoTerreno = tamannoTerreno;
    }

    public Cliente(int i, String nombre, String apellido1, String apellido2, String email, String contrasenna, boolean estado, LocalDate fechaNacimiento, String pin, String zona, String tipoFinca, int tamannoTerreno) {

    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getTipoFinca() {
        return tipoFinca;
    }

    public void setTipoFinca(String tipoFinca) {
        this.tipoFinca = tipoFinca;
    }

    public int getTamannoTerreno() {
        return tamannoTerreno;
    }

    public void setTamannoTerreno(int tamannoTerreno) {
        this.tamannoTerreno = tamannoTerreno;
    }
    @Override
    public String toString() {
        return "Cliente{" + getBaseToString() +
                ", zona='" + zona + '\'' +
                ", tipoFinca='" + tipoFinca + '\'' +
                ", tamannoTerreno=" + tamannoTerreno +
                '}';
    }

}
