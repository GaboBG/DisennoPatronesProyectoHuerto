package org.example.dao;

import org.example.model.Cultivo;
import org.example.misc.Conexion;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CultivoDAO {

    private final String tabla = "huerto_Cultivo";

    public int setCultivo(Cultivo cultivo) {
        String sql = "INSERT INTO " + tabla + " (idCliente, idPlanta, nombre, ubicacion, fechaSiembra, fechaCosecha, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, cultivo.getIdCliente());
            ps.setInt(2, cultivo.getIdPlanta());
            ps.setString(3, cultivo.getNombre());
            ps.setString(4, cultivo.getUbicacion());
            ps.setDate(5, Date.valueOf(cultivo.getFechaSiembra()));
            ps.setDate(6, Date.valueOf(cultivo.getFechaCosecha()));
            ps.setString(7, cultivo.getEstado());

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

    public int updateCultivo(Cultivo cultivo) {
        String sql = "UPDATE " + tabla + " SET idCliente = ?, idPlanta = ?, nombre = ?, ubicacion = ?, fechaSiembra = ?, fechaCosecha = ?, estado = ? WHERE idCultivo = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cultivo.getIdCliente());
            ps.setInt(2, cultivo.getIdPlanta());
            ps.setString(3, cultivo.getNombre());
            ps.setString(4, cultivo.getUbicacion());
            ps.setDate(5, Date.valueOf(cultivo.getFechaSiembra()));
            ps.setDate(6, Date.valueOf(cultivo.getFechaCosecha()));
            ps.setString(7, cultivo.getEstado());
            ps.setInt(8, cultivo.getIdCultivo());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Cultivo> getCultivos() {
        List<Cultivo> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + tabla;

        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cultivo cultivo = new Cultivo(
                        rs.getInt("idCultivo"),
                        rs.getInt("idCliente"),
                        rs.getInt("idPlanta"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion"),
                        rs.getDate("fechaSiembra").toLocalDate(),
                        rs.getDate("fechaCosecha").toLocalDate(),
                        rs.getString("estado")
                );
                lista.add(cultivo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Cultivo getCultivoById(int id) {
        String sql = "SELECT * FROM " + tabla + " WHERE idCultivo = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Cultivo(
                        rs.getInt("idCultivo"),
                        rs.getInt("idCliente"),
                        rs.getInt("idPlanta"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion"),
                        rs.getDate("fechaSiembra").toLocalDate(),
                        rs.getDate("fechaCosecha").toLocalDate(),
                        rs.getString("estado")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }




    public void deleteCultivo(int id) {
        String sql = "DELETE FROM " + tabla + " WHERE idCultivo = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Cultivo getCultivoByName(String nombre) {
        String sql = "SELECT * FROM " + tabla + " WHERE nombre = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Cultivo(
                        rs.getInt("idCultivo"),
                        rs.getInt("idCliente"),
                        rs.getInt("idPlanta"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion"),
                        rs.getDate("fechaSiembra").toLocalDate(),
                        rs.getDate("fechaCosecha").toLocalDate(),
                        rs.getString("estado")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
