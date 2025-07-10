package org.example.dao;

import org.example.misc.Conexion;
import org.example.model.Admin;
import org.example.model.Asesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO extends UsuarioDAO {
    public void setAdmin(Admin admin) {
        int idGenerado = super.setUsuario(admin);

        if (idGenerado == -1) {
            System.out.println("Error al insertar usuario base.");
            return;
        }

        String sql = "INSERT INTO huerto_Admin (id) VALUES (?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idGenerado);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar en huerto_Admin: " + e.getMessage());
        }
    }


    public void updateAdmin(Admin admin) {
        int filasUsuario = super.updateUsuario(admin);

        if (filasUsuario == 0) {
            System.out.println("No se encontró el usuario con ID: " + admin.getId());
            return;
        }


    }



    public Admin getAdminById(int id) {
        String sql = "SELECT u.* FROM huerto_Usuario u JOIN huerto_Admin a ON u.id = a.id WHERE u.id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Admin(
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
                        rs.getString("pin")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener Admin por ID: " + e.getMessage());
        }

        return null;
    }



    public Admin getAdminByCorreo(String email) {
        String sql = "SELECT u.* FROM huerto_Usuario u JOIN huerto_Admin a ON u.id = a.id WHERE u.email = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Admin(
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
                        rs.getString("pin")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener Admin por correo: " + e.getMessage());
        }

        return null;
    }


    public List<Admin> getAdmins() {
        List<Admin> lista = new ArrayList<>();
        String sql = "SELECT u.* FROM huerto_Usuario u JOIN huerto_Admin a ON u.id = a.id";

        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Admin admin = new Admin(
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
                        rs.getString("pin")
                );
                lista.add(admin);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener la lista de Admins: " + e.getMessage());
        }

        return lista;
    }


    public void deleteAdmin(int id) {
        String sqlAdmin = "DELETE FROM huerto_Admin WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlAdmin)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();

            if (filas == 0) {
                System.out.println("No se encontró el admin con ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar admin: " + e.getMessage());
        }

        super.deleteUsuario(id);
    }

}
