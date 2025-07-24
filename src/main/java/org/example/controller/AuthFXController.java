package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.model.Admin;
import org.example.model.Asesor;
import org.example.model.Cliente;
import org.example.model.Usuario;

import java.io.IOException;

public class AuthFXController {

    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasenna;
    @FXML private Label lblMensaje;

    private final AuthController authController = new AuthController();
    private final UsuarioController usuarioController = new UsuarioController();

    @FXML
    public void handleLogin() {
        String correo = txtCorreo.getText().trim();
        String contrasenna = txtContrasenna.getText();

        if (correo.isEmpty() || contrasenna.isEmpty()) {
            lblMensaje.setText("Por favor ingresa correo y contraseña.");
            return;
        }

        Usuario usuario = usuarioController.getUsuarioByCorreo(correo);
        if (usuario == null) {
            lblMensaje.setText("Correo no registrado.");
            return;
        }

        // ⛔ Verificación de estado inactivo
        if (!usuario.isEstado()) {
            lblMensaje.setStyle("-fx-text-fill: red;");
            lblMensaje.setText("Tu cuenta está inactiva. Contacta al administrador.");
            return;
        }

        String rol = usuario.getRol();
        Usuario logueado = authController.login(correo, contrasenna, rol);

        if (logueado != null) {
            lblMensaje.setStyle("-fx-text-fill: green;");
            lblMensaje.setText("Inicio exitoso como " + rol);

            try {
                FXMLLoader loader;
                if ("admin".equalsIgnoreCase(rol)) {
                    loader = new FXMLLoader(getClass().getResource("/views/AdminView.fxml"));
                } else if ("asesor".equalsIgnoreCase(rol)) {
                    loader = new FXMLLoader(getClass().getResource("/views/AsesorView.fxml"));
                } else if ("cliente".equalsIgnoreCase(rol)) {
                    loader = new FXMLLoader(getClass().getResource("/views/ClienteView.fxml"));
                } else {
                    loader = new FXMLLoader(getClass().getResource("/views/users_menu.fxml"));
                }

                Parent root = loader.load();

                // Pasar el usuario al controlador de la vista cargada
                Object controller = loader.getController();

                if (controller instanceof AdminFXController && "admin".equalsIgnoreCase(rol)) {
                    ((AdminFXController) controller).setAdmin((Admin) logueado);
                } else if (controller instanceof AsesorFXController && "asesor".equalsIgnoreCase(rol)) {
                    ((AsesorFXController) controller).setAsesor((Asesor) logueado);
                } else if (controller instanceof ClienteFXController && "cliente".equalsIgnoreCase(rol)) {
                    ((ClienteFXController) controller).setCliente((Cliente) logueado);
                }

                Stage stage = (Stage) txtCorreo.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                lblMensaje.setStyle("-fx-text-fill: red;");
                lblMensaje.setText("Error al cargar la vista del menú.");
            }

        } else {
            lblMensaje.setStyle("-fx-text-fill: red;");
            lblMensaje.setText("Credenciales inválidas.");
        }
    }


    @FXML
    public void handleRecuperarContrasenna() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RecuperarContrasenaView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) txtCorreo.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Recuperar Contraseña");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            lblMensaje.setText("No se pudo cargar la vista de recuperación.");
        }
    }



    @FXML
    public void volverInicio() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/inicio.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) txtCorreo.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión Huertos - Inicio");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            lblMensaje.setText("No se pudo volver al inicio.");
        }
    }

}
