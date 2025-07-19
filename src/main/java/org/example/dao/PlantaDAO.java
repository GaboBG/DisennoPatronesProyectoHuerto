package org.example.dao;

import org.example.misc.Conexion;
import org.example.model.Planta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantaDAO {

    private final String tabla = "huerto_Planta";
    public int setPlanta(Planta planta) {
        String sql = "INSERT INTO tabla (nombre, clima, frecuenciaRiego, descripcion, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, planta.getNombre());
            ps.setString(2, planta.getClima());
            ps.setString(3, planta.getFrecuenciaRiego());
            ps.setString(4, planta.getDescripcion());
            ps.setBoolean(5, planta.isEstado());

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

    public int updatePlanta(Planta planta) {
        String sql = "UPDATE tabla SET nombre = ?, clima = ?, frecuenciaRiego = ?, descripcion = ?, estado = ? WHERE idPlanta = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, planta.getNombre());
            ps.setString(2, planta.getClima());
            ps.setString(3, planta.getFrecuenciaRiego());
            ps.setString(4, planta.getDescripcion());
            ps.setBoolean(5, planta.isEstado());
            ps.setInt(6, planta.getIdPlanta());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Planta> getPlantas() {
        List<Planta> lista = new ArrayList<>();
        String sql = "SELECT idPlanta, nombre, clima, frecuenciaRiego, descripcion, estado FROM tabla";

        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Planta planta = new Planta(
                        rs.getInt("idPlanta"),
                        rs.getString("nombre"),
                        rs.getString("clima"),
                        rs.getString("frecuenciaRiego"),
                        rs.getString("descripcion"),
                        rs.getBoolean("estado")
                );
                lista.add(planta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Planta getPlantaById(int id) {
        String sql = "SELECT idPlanta, nombre, clima, frecuenciaRiego, descripcion, estado FROM tabla WHERE idPlanta = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Planta(
                        rs.getInt("idPlanta"),
                        rs.getString("nombre"),
                        rs.getString("clima"),
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

    public Planta getPlantaByName(String name) {
        String sql = "SELECT idPlanta, nombre, clima, frecuenciaRiego, descripcion, estado FROM tabla WHERE nombre = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Planta(
                        rs.getInt("idPlanta"),
                        rs.getString("nombre"),
                        rs.getString("clima"),
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

    public void deletePlanta(int id) {
        String sql = "DELETE FROM tabla WHERE idPlanta = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
