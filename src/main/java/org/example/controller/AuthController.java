package org.example.controller;

import org.example.bl.Authentication;
import org.example.model.Usuario;

public class AuthController {
    private Authentication authentication = new Authentication();

    public Usuario login(String correo, String contrasenna, String rol) {
        return authentication.iniciarSesion(correo, contrasenna, rol);
    }

    public boolean recuperarContrasenna(String correo, String pin, String nuevaContrasenna) {
        return authentication.recuperarContrasenna(correo, pin, nuevaContrasenna);
    }
}
