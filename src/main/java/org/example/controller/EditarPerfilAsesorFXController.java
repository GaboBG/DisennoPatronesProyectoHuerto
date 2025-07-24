package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.example.model.Asesor;
import org.example.Util.FXUtils;

public class EditarPerfilAsesorFXController {

    @FXML private TextField nombreField;
    @FXML private TextField apellido1Field;
    @FXML private TextField apellido2Field;
    @FXML private TextField emailField;
    @FXML private TextField especialidadField;
    @FXML private TextField zonaField;
    @FXML private TextField certificacionField;
    @FXML private CheckBox estadoCheck;
    @FXML private Label statusLabel;

    private Asesor asesor;

    public void setAsesor(Asesor asesor) {
        this.asesor = asesor;

        // Cargar los datos actuales en los campos
        nombreField.setText(asesor.getNombre());
        apellido1Field.setText(asesor.getPrimerApellido());
        apellido2Field.setText(asesor.getSegundoApellido());
        emailField.setText(asesor.getEmail());
        especialidadField.setText(asesor.getEspecialidad());
        zonaField.setText(asesor.getZona());
        certificacionField.setText(asesor.getCertificacion());
        estadoCheck.setSelected(asesor.isEstado());
    }

    @FXML
    private void guardarCambios() {
        // Validaciones básicas (puedes extenderlas)
        if (nombreField.getText().isEmpty() || emailField.getText().isEmpty()) {
            statusLabel.setText("El nombre y correo no pueden estar vacíos.");
            return;
        }

        // Actualizar los datos en el objeto asesor
        asesor.setNombre(nombreField.getText());
        asesor.setPrimerApellido(apellido1Field.getText());
        asesor.setSegundoApellido(apellido2Field.getText());
        asesor.setEmail(emailField.getText());
        asesor.setEspecialidad(especialidadField.getText());
        asesor.setZona(zonaField.getText());
        asesor.setCertificacion(certificacionField.getText());
        asesor.setEstado(estadoCheck.isSelected());

        // Aquí podrías guardar a base de datos si corresponde

        statusLabel.setStyle("-fx-text-fill: green;");
        statusLabel.setText("Perfil actualizado correctamente.");
    }
    private AsesorFXController controladorPrincipal;

    public void setControladorPrincipal(AsesorFXController controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
    }


    @FXML
    private void cancelarEdicion() {
        if (controladorPrincipal != null) {
            controladorPrincipal.setAsesor(asesor); // Volver a vista de perfil
        }
    }

}
