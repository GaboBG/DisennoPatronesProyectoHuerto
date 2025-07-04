package org.example.controller;
import org.example.model.Usuario;
import org.example.dao.UsuarioDAO;

import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void setUsuario(Usuario usuario) {
        usuarioDAO.setUsuario(usuario);
    }
    public void updateUsuario(Usuario usuario) {
        usuarioDAO.updateUsuario(usuario);
    }

    public List<Usuario> getUsuarios(){
        return usuarioDAO.getUsuarios();
    }
    public Usuario getUsuarioById(int id){
        return usuarioDAO.getUsuarioById(id);
    }
    public Usuario getUsuarioByCorreo(String correo){
        return usuarioDAO.getUsuarioByCorreo(correo);
    }

    public void deleteUsuario(int id){
        usuarioDAO.deleteUsuario(id);
    }


}
