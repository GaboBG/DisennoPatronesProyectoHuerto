package org.example.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.example.model.Asesor;
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

        Label titulo = new Label("Perfil de Asesor");
        titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        Label nombre = new Label("Nombre: " + asesorLogueado.getNombre() + " " + asesorLogueado.getPrimerApellido());
        Label email = new Label("Correo: " + asesorLogueado.getEmail());
        Label especialidad = new Label("Especialidad: " + asesorLogueado.getEspecialidad());
        Label zona = new Label("Zona: " + asesorLogueado.getZona());
        Label certificacion = new Label("Certificación: " + asesorLogueado.getCertificacion());

        contentArea.getChildren().addAll(titulo, nombre, email, especialidad, zona, certificacion);
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
}
