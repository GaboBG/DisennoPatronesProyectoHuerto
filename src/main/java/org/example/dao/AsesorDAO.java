package org.example.dao;

import org.example.misc.Conexion;
import org.example.model.Asesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsesorDAO extends UsuarioDAO {

    public void setAsesor(Asesor asesor) {
        int idGenerado = super.setUsuario(asesor);

        if (idGenerado == -1) {
            System.out.println("Error al insertar usuario base.");
            return;
        }

        String sql = "INSERT INTO huerto_Asesor (id, especialidad, zona, certificacion) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idGenerado);
            ps.setString(2, asesor.getEspecialidad());
            ps.setString(3, asesor.getZona());
            ps.setString(4, asesor.getCertificacion());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAsesor(Asesor asesor) {
        int filasUsuario = super.updateUsuario(asesor);

        if (filasUsuario == 0) {
            System.out.println("No se encontró el usuario con ID: " + asesor.getId());
            return;
        }

        String sql = "UPDATE huerto_Asesor SET especialidad = ?, zona = ?, certificacion = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, asesor.getEspecialidad());
            ps.setString(2, asesor.getZona());
            ps.setString(3, asesor.getCertificacion());
            ps.setInt(4, asesor.getId());

            int filasAsesor = ps.executeUpdate();

            if (filasAsesor == 0) {
                System.out.println("No se actualizó el asesor con ID: " + asesor.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Asesor getAsesorById(int id) {
        String sql = "SELECT u.*, a.especialidad, a.zona, a.certificacion " +
                "FROM huerto_Usuario u JOIN huerto_Asesor a ON u.id = a.id WHERE u.id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Asesor(
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
                        rs.getString("zona"),
                        rs.getString("especialidad"),
                        rs.getString("certificacion")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Asesor getAsesorByCorreo(String email) {
        String sql = "SELECT u.*, a.especialidad, a.zona, a.certificacion " +
                "FROM huerto_Usuario u " +
                "JOIN huerto_Asesor a ON u.id = a.id " +
                "WHERE u.email = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Asesor(
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
                        rs.getString("zona"),
                        rs.getString("especialidad"),
                        rs.getString("certificacion")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Asesor> getAsesores() {
        List<Asesor> lista = new ArrayList<>();
        String sql = "SELECT u.*, a.especialidad, a.zona, a.certificacion " +
                "FROM huerto_Usuario u JOIN huerto_Asesor a ON u.id = a.id";

        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Asesor asesor = new Asesor(
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
                        rs.getString("zona"),
                        rs.getString("especialidad"),
                        rs.getString("certificacion")
                );
                lista.add(asesor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void deleteAsesor(int id) {
        String sqlAsesor = "DELETE FROM huerto_Asesor WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlAsesor)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        super.deleteUsuario(id);
    }
}
