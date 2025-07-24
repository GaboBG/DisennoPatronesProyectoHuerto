package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;


public class RecuperarContrasenaFXController {

    @FXML private TextField correoField;
    @FXML private TextField pinField;
    @FXML private PasswordField nuevaContrasenaField;
    @FXML private Label statusLabel;

    private final AuthController authController = new AuthController();

    @FXML
    private void recuperarContrasena() {
        String correo = correoField.getText();
        String pin = pinField.getText();
        String nuevaContrasena = nuevaContrasenaField.getText();

        if (correo.isEmpty() || pin.isEmpty() || nuevaContrasena.isEmpty()) {
            statusLabel.setText("Por favor completa todos los campos.");
            return;
        }

        boolean exito = authController.recuperarContrasenna(correo, pin, nuevaContrasena);

        if (exito) {
            statusLabel.setStyle("-fx-text-fill: green;");
            statusLabel.setText("Contraseña recuperada con éxito.");
            limpiarCampos();
        } else {
            statusLabel.setStyle("-fx-text-fill: red;");
            statusLabel.setText("Correo o PIN incorrectos.");
        }
    }

    @FXML
    private void volverLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Gestión Huertos - Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Error al cargar la ventana de login.");
        }
    }

    private void limpiarCampos() {
        correoField.clear();
        pinField.clear();
        nuevaContrasenaField.clear();
    }
}
