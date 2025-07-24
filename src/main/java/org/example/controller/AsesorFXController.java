package org.example.controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import org.example.Util.FXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Asesor;

import java.io.IOException;

public class AsesorFXController {
    @FXML
    private VBox contentArea;

    private Asesor asesorLogueado;

    public void setAsesor(Asesor asesor) {
        this.asesorLogueado = asesor;
        mostrarPerfil();
    }

    @FXML
    private void mostrarPerfil() {
        contentArea.getChildren().clear();

        Label titulo = new Label("Perfil del Asesor");
        titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        Label nombre = new Label("Nombre: " + asesorLogueado.getNombre());
        Label apellido1 = new Label("Primer Apellido: " + asesorLogueado.getPrimerApellido());
        Label apellido2 = new Label("Segundo Apellido: " + asesorLogueado.getSegundoApellido());
        Label email = new Label("Correo: " + asesorLogueado.getEmail());
        Label especialidad = new Label("Especialidad: " + asesorLogueado.getEspecialidad());
        Label zona = new Label("Zona: " + asesorLogueado.getZona());
        Label certificacion = new Label("Certificación: " + asesorLogueado.getCertificacion());
        Label estado = new Label("Estado: " + (asesorLogueado.isEstado() ? "Activo" : "Inactivo"));

        Button btnEditar = new Button("Editar Perfil");
        btnEditar.setOnAction(e -> mostrarFormularioEditarPerfil());

        VBox perfilBox = new VBox(10, titulo, nombre, apellido1, apellido2, email, especialidad, zona, certificacion, estado, btnEditar);
        contentArea.getChildren().add(perfilBox);
    }

    @FXML
    private void mostrarFormularioEditarPerfil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EditarPerfilAsesorView.fxml"));
            Node form = loader.load();

            EditarPerfilAsesorFXController controller = loader.getController();
            controller.setAsesor(asesorLogueado);

            contentArea.getChildren().setAll(form);
        } catch (IOException e) {
            e.printStackTrace();
            contentArea.getChildren().clear();
            Label errorLabel = new Label("Error al cargar formulario de edición");
            contentArea.getChildren().add(errorLabel);
        }
    }



    @FXML
    private void mostrarClientes() {
        contentArea.getChildren().clear();
        Label label = new Label("Aquí se muestran los clientes.");
        contentArea.getChildren().add(label);
    }

    @FXML
    private void mostrarAgenda() {
        contentArea.getChildren().clear();
        Label label = new Label("Aquí se muestra la agenda.");
        contentArea.getChildren().add(label);
    }
    @FXML
    private void cerrarSesion() {
        Stage stage = (Stage) contentArea.getScene().getWindow();
        FXUtils.cerrarSesion(stage);
    }
}
