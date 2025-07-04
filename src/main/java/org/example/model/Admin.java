package org.example.model;

import java.time.LocalDate;

public class Admin extends Usuario{
    public Admin() {}

    public Admin(int id, String nombre, String primerApellido, String segundoApellido, String email, String contrasenna, boolean estado, LocalDate fechaNacimiento, LocalDate fechaRegistro) {
        super(id, nombre, primerApellido, segundoApellido, email, contrasenna, "Admin", estado, fechaNacimiento, fechaRegistro);
    }
}
