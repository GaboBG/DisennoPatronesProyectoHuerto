package org.example.controller;

import org.example.dao.ClienteDAO;
import org.example.dao.UsuarioDAO;
import org.example.model.Cliente;
import org.example.model.Usuario;

import java.util.List;

public class ClienteController {

    private ClienteDAO clienteDAO = new ClienteDAO();

    public void setCliente(Cliente cliente) {
        clienteDAO.setCliente(cliente);
    }
    public void updateCliente(Cliente cliente) {
        clienteDAO.updateCliente(cliente);
    }

    public List<Cliente> getClientes(){
        return clienteDAO.getClientes();
    }
    public Cliente getClienteById(int id){
        return clienteDAO.getClienteById(id);
    }
    public Cliente getClienteByCorreo(String correo){
        return clienteDAO.getClienteByCorreo(correo);
    }

    public void deleteCliente(int id){
        clienteDAO.deleteUsuario(id);
    }


}
