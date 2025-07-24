package org.example.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.controller.MiPerfilFXController;
import org.example.model.Admin;

import java.io.IOException;

public class FXUtils {
    public static void cerrarSesion(Stage ventanaActual) {
        try {
            FXMLLoader loader = new FXMLLoader(FXUtils.class.getResource("/views/Login.fxml"));
            Parent root = loader.load();

            ventanaActual.setScene(new Scene(root));
            ventanaActual.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al volver a Login.fxml");
        }
    }
    public static <T> T getControllerFromFXML(String rutaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(FXUtils.class.getResource(rutaFXML));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo cargar el FXML: " + rutaFXML);
        }
    }

    public static <T> void cargarVistaConUsuario(String rutaFXML, Object usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(FXUtils.class.getResource(rutaFXML));
            Parent root = loader.load();

            // Obtener controlador y pasar usuario si tiene método setAdmin o similar
            Object controller = loader.getController();

            if (usuario instanceof Admin && controller instanceof MiPerfilFXController) {
                ((MiPerfilFXController) controller).setAdmin((Admin) usuario);
            }
            // Aquí puedes agregar otros casos para otros tipos de usuario y controladores

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar vista con usuario: " + rutaFXML);
        }
    }


}
