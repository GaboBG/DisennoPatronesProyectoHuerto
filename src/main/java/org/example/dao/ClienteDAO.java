package org.example.dao;
import org.example.misc.Conexion;
import org.example.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends UsuarioDAO {

    public void setCliente(Cliente cliente) {
        int idGenerado = super.setUsuario(cliente); // ← obtiene el ID generado

        if (idGenerado == -1) {
            System.out.println("Error al insertar usuario base.");
            return;
        }

        String sql = "INSERT INTO huerto_Cliente (id, zona, tipoFinca, tamannoTerreno) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idGenerado); // ← usa el mismo ID que el usuario
            ps.setString(2, cliente.getZona());
            ps.setString(3, cliente.getTipoFinca());
            ps.setInt(4, cliente.getTamannoTerreno());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCliente(Cliente cliente) {
        int filasUsuario = super.updateUsuario(cliente);

        if (filasUsuario == 0) {
            System.out.println("No se encontró el usuario con ID: " + cliente.getId());
            return;
        }

        String sql = "UPDATE huerto_Cliente SET zona = ?, tipoFinca = ?, tamannoTerreno = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getZona());
            ps.setString(2, cliente.getTipoFinca());
            ps.setInt(3, cliente.getTamannoTerreno());
            ps.setInt(4, cliente.getId());

            int filasCliente = ps.executeUpdate();

            if (filasCliente == 0) {
                System.out.println("No se actualizó el cliente con ID: " + cliente.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Cliente getClienteById(int id) {
        String sql = "SELECT u.*, c.zona, c.tipoFinca, c.tamannoTerreno " +
                "FROM huerto_Usuario u JOIN huerto_Cliente c ON u.id = c.id WHERE u.id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("primerApellido"),
                        rs.getString("segundoApellido"),
                        rs.getString("email"),
                        rs.getString("contrasenna"),
                        rs.getString("rol"),
                        rs.getBoolean("estado"),
                        rs.getDate("fechaNacimiento").toLocalDate(),
                        rs.getDate("fechaRegistro").toLocalDate(),
                        rs.getString("pin"),
                        rs.getString("zona"),
                        rs.getString("tipoFinca"),
                        rs.getInt("tamannoTerreno")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Cliente getClienteByCorreo(String email) {
        String sql = "SELECT u.*, c.zona, c.tipoFinca, c.tamannoTerreno " +
                "FROM huerto_Usuario u " +
                "JOIN huerto_Cliente c ON u.id = c.id " +
                "WHERE u.email = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("primerApellido"),
                        rs.getString("segundoApellido"),
                        rs.getString("email"),
                        rs.getString("contrasenna"),
                        rs.getString("rol"),
                        rs.getBoolean("estado"),
                        rs.getDate("fechaNacimiento").toLocalDate(),
                        rs.getDate("fechaRegistro").toLocalDate(),
                        rs.getString("pin"),
                        rs.getString("zona"),
                        rs.getString("tipoFinca"),
                        rs.getInt("tamannoTerreno")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<Cliente> getClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT u.*, c.zona, c.tipoFinca, c.tamannoTerreno " +
                "FROM huerto_Usuario u JOIN huerto_Cliente c ON u.id = c.id";

        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("primerApellido"),
                        rs.getString("segundoApellido"),
                        rs.getString("email"),
                        rs.getString("contrasenna"),
                        rs.getString("rol"),
                        rs.getBoolean("estado"),
                        rs.getDate("fechaNacimiento").toLocalDate(),
                        rs.getDate("fechaRegistro").toLocalDate(),
                        rs.getString("pin"),
                        rs.getString("zona"),
                        rs.getString("tipoFinca"),
                        rs.getInt("tamannoTerreno")
                );
                lista.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void deleteCliente(int id) {
        // Primero se elimina de huerto_Cliente (por integridad referencial)
        String sqlCliente = "DELETE FROM huerto_Cliente WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlCliente)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Luego se elimina de huerto_Usuario
        super.deleteUsuario(id);
    }

}
