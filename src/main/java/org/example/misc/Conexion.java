package org.example.misc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://srv863.hstgr.io:3306/u484426513_ppoc225";
    private static final String USER = "u484426513_ppoc225";
    private static final String PASSWORD = "jXc7w:|7Gy;";


    //Ocupamos una funcion conexion
    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al conectar:" + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Connection con = getConnection();

        if (con != null) {
            System.out.println("Conectado a la base de datos");

            try{
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al desconectar:" + e.getMessage());
            }

        }else {
            System.out.println("Error al conectar");
        }

    }
}
