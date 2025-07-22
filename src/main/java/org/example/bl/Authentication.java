package org.example.bl;

import org.example.dao.AdminDAO;
import org.example.dao.AsesorDAO;
import org.example.dao.ClienteDAO;
import org.example.dao.UsuarioDAO;
import org.example.model.Admin;
import org.example.model.Asesor;
import org.example.model.Cliente;
import org.example.model.Usuario;

public class Authentication {


    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario iniciarSesion(String correo, String contrasenna, String rol) {
        Usuario usuario = usuarioDAO.getUsuarioByCorreo(correo);

        if (usuario == null) {
            System.out.println("Correo no registrado.");
            return null;
        }

        if (usuario.getContrasenna().equals(contrasenna) && usuario.getRol().equalsIgnoreCase(rol)) {
            switch (rol.toLowerCase()) {
                case "admin":
                    return new AdminDAO().getAdminByCorreo(correo);   // devuelve Admin concreto
                case "asesor":
                    return new AsesorDAO().getAsesorByCorreo(correo); // devuelve Asesor concreto
                case "cliente":
                    return new ClienteDAO().getClienteByCorreo(correo);// devuelve Cliente concreto
                default:
                    return usuario;  // fallback a Usuario base (o null)
            }
        }

        System.out.println("Correo o contraseña incorrectos.");
        return null;
    }

    public boolean recuperarContrasenna(String email, String pinIngresado, String nuevaContrasenna) {
        Usuario usuario = usuarioDAO.getUsuarioByCorreo(email);

        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return false;
        }

        // Verifica PIN (si está encriptado, compara el hash)
        if (!usuario.getPin().equals(pinIngresado)) {
            System.out.println("PIN incorrecto.");
            return false;
        }

        usuario.setContrasenna(nuevaContrasenna);
        usuarioDAO.updateUsuario(usuario);
        System.out.println("Contraseña actualizada correctamente.");
        return true;
    }


}
