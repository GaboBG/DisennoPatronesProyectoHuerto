package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.model.Admin;
import org.example.Util.FXUtils;
import org.example.controller.AdminController;

import java.time.LocalDate;

public class MiPerfilFXController {

    @FXML private TextField nombreField, apellido1Field, apellido2Field, correoField, telefonoField, rolField;
    @FXML private DatePicker fechaNacimientoPicker;
    @FXML private Label mensajeLabel;

    private Admin admin;
    private final AdminController adminController = new AdminController();

    public void setAdmin(Admin admin) {
        this.admin = admin;
        cargarDatos();
    }

    private void cargarDatos() {
        nombreField.setText(admin.getNombre());
        apellido1Field.setText(admin.getPrimerApellido());
        apellido2Field.setText(admin.getSegundoApellido());
        correoField.setText(admin.getEmail());
        rolField.setText(admin.getRol());
        fechaNacimientoPicker.setValue(admin.getFechaNacimiento());
    }

    @FXML
    private void guardarCambios() {
        admin.setNombre(nombreField.getText());
        admin.setPrimerApellido(apellido1Field.getText());
        admin.setSegundoApellido(apellido2Field.getText());
        admin.setEmail(correoField.getText());
        admin.setFechaNacimiento(fechaNacimientoPicker.getValue());

        adminController.updateAdmin(admin);
        mensajeLabel.setText("Datos actualizados correctamente.");
    }

    @FXML
    private void abrirCambioContrasena() {
        FXUtils.cargarVistaConUsuario("/views/CambiarContrasena.fxml", admin);
    }
}
