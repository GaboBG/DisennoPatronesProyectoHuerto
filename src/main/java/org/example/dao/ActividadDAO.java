package org.example.dao;

import org.example.misc.Conexion;
import org.example.model.Actividad;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActividadDAO {

    private final String tabla = "huerto_Actividad";

    public int setActividad(Actividad actividad) {
        String sql = "INSERT INTO " + tabla + " (idCultivo, nombre, tipoActividad, fechaProgramada, descripcion, realizada) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, actividad.getIdCultivo());
            ps.setString(2, actividad.getNombre());
            ps.setString(3, actividad.getTipoActividad());
            ps.setDate(4, Date.valueOf(actividad.getFechaProgramada()));
            ps.setString(5, actividad.getDescripcion());
            ps.setBoolean(6, actividad.isRealizada());

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int updateActividad(Actividad actividad) {
        String sql = "UPDATE " + tabla + " SET idCultivo = ?, nombre = ?, tipoActividad = ?, fechaProgramada = ?, descripcion = ?, realizada = ? WHERE idActividad = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, actividad.getIdCultivo());
            ps.setString(2, actividad.getNombre());
            ps.setString(3, actividad.getTipoActividad());
            ps.setDate(4, Date.valueOf(actividad.getFechaProgramada()));
            ps.setString(5, actividad.getDescripcion());
            ps.setBoolean(6, actividad.isRealizada());
            ps.setInt(7, actividad.getIdActividad());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Actividad> getActividades() {
        List<Actividad> lista = new ArrayList<>();
        String sql = "SELECT idActividad, idCultivo, nombre, tipoActividad, fechaProgramada, descripcion, realizada FROM " + tabla;

        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Actividad actividad = new Actividad(
                        rs.getInt("idActividad"),
                        rs.getInt("idCultivo"),
                        rs.getString("nombre"),
                        rs.getString("tipoActividad"),
                        rs.getDate("fechaProgramada").toLocalDate(),
                        rs.getString("descripcion"),
                        rs.getBoolean("realizada")
                );
                lista.add(actividad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Actividad getActividadById(int id) {
        String sql = "SELECT idActividad, idCultivo, nombre, tipoActividad, fechaProgramada, descripcion, realizada FROM " + tabla + " WHERE idActividad = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Actividad(
                        rs.getInt("idActividad"),
                        rs.getInt("idCultivo"),
                        rs.getString("nombre"),
                        rs.getString("tipoActividad"),
                        rs.getDate("fechaProgramada").toLocalDate(),
                        rs.getString("descripcion"),
                        rs.getBoolean("realizada")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Actividad getActividadByName(String name) {
        String sql = "SELECT idActividad, idCultivo, nombre, tipoActividad, fechaProgramada, descripcion, realizada FROM " + tabla + " WHERE name = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Actividad(
                        rs.getInt("idActividad"),
                        rs.getInt("idCultivo"),
                        rs.getString("nombre"),
                        rs.getString("tipoActividad"),
                        rs.getDate("fechaProgramada").toLocalDate(),
                        rs.getString("descripcion"),
                        rs.getBoolean("realizada")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }




    public void deleteActividad(int id) {
        String sql = "DELETE FROM " + tabla + " WHERE idActividad = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
