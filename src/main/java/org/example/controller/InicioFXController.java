package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioFXController {

    @FXML
    private void irLogin(ActionEvent event) {
        cambiarVentana("/views/login.fxml", event);
    }

    @FXML
    private void irRegistro(ActionEvent event) {
        cambiarVentana("/views/RegistrarCuenta.fxml", event);
    }


    private void cambiarVentana(String rutaFXML, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();

            // Cierra la ventana actual (la de inicio)
            Stage actual = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            actual.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}