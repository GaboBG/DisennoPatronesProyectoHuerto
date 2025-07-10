package org.example.dao;



import org.example.misc.Conexion;
import org.example.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {

    public int setUsuario(Usuario usuario) {
        String sql = "INSERT INTO huerto_Usuario (nombre, primerApellido, segundoApellido, email, contrasenna, rol, estado, fechaNacimiento, fechaRegistro, pin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPrimerApellido());
            ps.setString(3, usuario.getSegundoApellido());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getContrasenna());
            ps.setString(6, usuario.getRol());
            ps.setBoolean(7, usuario.isEstado());
            ps.setDate(8, Date.valueOf(usuario.getFechaNacimiento()));
            ps.setDate(9, Date.valueOf(usuario.getFechaRegistro()));
            ps.setString(10, usuario.getPin());

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // error
    }


    public int updateUsuario(Usuario usuario) {
        String sql = "UPDATE huerto_Usuario SET nombre = ?, primerApellido = ?, segundoApellido = ?, email = ?, contrasenna = ?, rol = ?, estado = ?, fechaNacimiento = ?, fechaRegistro = ?, pin = ? WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPrimerApellido());
            ps.setString(3, usuario.getSegundoApellido());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getContrasenna());
            ps.setString(6, usuario.getRol());
            ps.setBoolean(7, usuario.isEstado());
            ps.setDate(8, Date.valueOf(usuario.getFechaNacimiento()));
            ps.setDate(9, Date.valueOf(usuario.getFechaRegistro()));
            ps.setString(10,usuario.getPin());
            ps.setInt(11, usuario.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


    public List<Usuario> getUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, primerApellido, segundoApellido, email, contrasenna, rol, estado, fechaNacimiento, fechaRegistro, pin FROM huerto_Usuario";


        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
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
                lista.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Usuario getUsuarioById(int id) {
        String sql = "SELECT id, nombre, primerApellido, segundoApellido, email, contrasenna, rol, estado, fechaNacimiento, fechaRegistro, pin FROM huerto_Usuario";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
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
            e.printStackTrace();
        }

        return null;
    }

    public Usuario getUsuarioByCorreo(String correo) {
        String sql = "SELECT id, nombre, primerApellido, segundoApellido, email, contrasenna, rol, estado, fechaNacimiento, fechaRegistro, pin FROM huerto_Usuario WHERE email = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
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
            e.printStackTrace();
        }

        return null;
    }


    public void deleteUsuario(int id) {
        String sql = "DELETE FROM huerto_Usuario WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
