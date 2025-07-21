package org.example.dao;

import org.example.misc.Conexion;
import org.example.model.Actividad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActividadDAO {

    private final String tabla = "huerto_Actividad";

    public int setActividad(Actividad actividad) {
        String sql = "INSERT INTO " + tabla + " (nombre, clima, tipoSuelo, frecuenciaRiego, descripcion, estado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, actividad.getNombre());
            ps.setString(2, actividad.getClima());
            ps.setString(3, actividad.getTipoSuelo());
            ps.setString(4, actividad.getFrecuenciaRiego());
            ps.setString(5, actividad.getDescripcion());
            ps.setBoolean(6, actividad.isEstado());

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
        String sql = "UPDATE " + tabla + " SET nombre = ?, clima = ?, tipoSuelo = ?, frecuenciaRiego = ?, descripcion = ?, estado = ? WHERE idActividad = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, actividad.getNombre());
            ps.setString(2, actividad.getClima());
            ps.setString(3, actividad.getTipoSuelo());
            ps.setString(4, actividad.getFrecuenciaRiego());
            ps.setString(5, actividad.getDescripcion());
            ps.setBoolean(6, actividad.isEstado());
            ps.setInt(7, actividad.getIdActividad());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Actividad> getActividades() {
        List<Actividad> lista = new ArrayList<>();
        String sql = "SELECT idActividad, nombre, clima, tipoSuelo, frecuenciaRiego, descripcion, estado FROM " + tabla;

        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Actividad actividad = new Actividad(
                        rs.getInt("idActividad"),
                        rs.getString("nombre"),
                        rs.getString("clima"),
                        rs.getString("tipoSuelo"),
                        rs.getString("frecuenciaRiego"),
                        rs.getString("descripcion"),
                        rs.getBoolean("estado")
                );
                lista.add(actividad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Actividad getActividadById(int id) {
        String sql = "SELECT idActividad, nombre, clima, tipoSuelo, frecuenciaRiego, descripcion, estado FROM " + tabla + " WHERE idActividad = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Actividad(
                        rs.getInt("idActividad"),
                        rs.getString("nombre"),
                        rs.getString("clima"),
                        rs.getString("tipoSuelo"),
                        rs.getString("frecuenciaRiego"),
                        rs.getString("descripcion"),
                        rs.getBoolean("estado")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Actividad getActividadByName(String name) {
        String sql = "SELECT idActividad, nombre, clima, tipoSuelo, frecuenciaRiego, descripcion, estado FROM " + tabla + " WHERE nombre = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Actividad(
                        rs.getInt("idActividad"),
                        rs.getString("nombre"),
                        rs.getString("clima"),
                        rs.getString("tipoSuelo"),
                        rs.getString("frecuenciaRiego"),
                        rs.getString("descripcion"),
                        rs.getBoolean("estado")
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
