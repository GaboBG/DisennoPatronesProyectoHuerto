package org.example.dao;

import org.example.misc.Conexion;
import org.example.model.Recomendaciones;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecomendacionesDAO {

    private final String tabla = "huerto_Recomendaciones";

    public int setRecomendacion(Recomendaciones recomendacion) {
        String sql = "INSERT INTO " + tabla + " (cultivoId, IdAsesorCliente, fechaInicio, fechaFinal, observaciones) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, recomendacion.getCultivoId());
            ps.setInt(2, recomendacion.getIdAsesorCliente());
            ps.setDate(3, Date.valueOf(recomendacion.getFechaInicio()));
            ps.setDate(4, Date.valueOf(recomendacion.getFechaFinal()));
            ps.setString(5, recomendacion.getObservaciones());

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

    public int updateRecomendacion(Recomendaciones recomendacion) {
        String sql = "UPDATE " + tabla + " SET cultivoId = ?, IdAsesorCliente = ?, fechaInicio = ?, fechaFinal = ?, observaciones = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, recomendacion.getCultivoId());
            ps.setInt(2, recomendacion.getIdAsesorCliente());
            ps.setDate(3, Date.valueOf(recomendacion.getFechaInicio()));
            ps.setDate(4, Date.valueOf(recomendacion.getFechaFinal()));
            ps.setString(5, recomendacion.getObservaciones());
            ps.setInt(6, recomendacion.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Recomendaciones> getRecomendaciones() {
        List<Recomendaciones> lista = new ArrayList<>();
        String sql = "SELECT id, cultivoId, IdAsesorCliente, fechaInicio, fechaFinal, observaciones FROM " + tabla;

        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Recomendaciones rec = new Recomendaciones(
                        rs.getInt("id"),
                        rs.getInt("cultivoId"),
                        rs.getInt("IdAsesorCliente"),
                        rs.getDate("fechaInicio").toLocalDate(),
                        rs.getDate("fechaFinal").toLocalDate(),
                        rs.getString("observaciones")
                );
                lista.add(rec);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Recomendaciones getRecomendacionById(int id) {
        String sql = "SELECT id, cultivoId, IdAsesorCliente, fechaInicio, fechaFinal, observaciones FROM " + tabla + " WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Recomendaciones(
                        rs.getInt("id"),
                        rs.getInt("cultivoId"),
                        rs.getInt("IdAsesorCliente"),
                        rs.getDate("fechaInicio").toLocalDate(),
                        rs.getDate("fechaFinal").toLocalDate(),
                        rs.getString("observaciones")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteRecomendacion(int id) {
        String sql = "DELETE FROM " + tabla + " WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}