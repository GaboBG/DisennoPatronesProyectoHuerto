package Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
}
