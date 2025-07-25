package org.example.model;

import java.time.LocalDate;

public class Admin extends Usuario{
    public Admin(int id, String nombre, String primerApellido, String segundoApellido, String email, String contrasenna, String rol, boolean estado, LocalDate fechaNacimiento, LocalDate fechaRegistro, String pin) {
        super(id, nombre, primerApellido, segundoApellido, email, contrasenna, "Admin", estado, fechaNacimiento, fechaRegistro, pin);
    }


    @Override
    public String toString() {
        return "Admin{"+getBaseToString()+"}";
    }
}
