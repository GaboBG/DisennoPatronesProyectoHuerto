package org.example.model;

import java.time.LocalDate;

public class Cliente extends Usuario {
    private String zona;
    private String tipoFinca;
    private int tamannoTerreno;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String primerApellido, String segundoApellido, String email, String contrasenna, boolean estado, LocalDate fechaNacimiento, LocalDate fechaRegistro, String zona, String tipoFinca, int tamannoTerreno) {
        super(id, nombre, primerApellido, segundoApellido, email, contrasenna, "Cliente", estado, fechaNacimiento, fechaRegistro);
        this.zona = zona;
        this.tipoFinca = tipoFinca;
        this.tamannoTerreno = tamannoTerreno;
    }
}
