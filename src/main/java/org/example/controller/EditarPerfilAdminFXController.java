package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.model.Admin;
import org.example.controller.AdminController;

public class EditarPerfilAdminFXController {

    @FXML private TextField nombreField;
    @FXML private TextField apellido1Field;
    @FXML private TextField apellido2Field;
    @FXML private TextField correoField;
    @FXML private DatePicker fechaNacimientoPicker;
    @FXML private CheckBox estadoCheckBox;
    @FXML private Label mensajeLabel;

    private Admin admin;
    private AdminController adminController = new AdminController();

    public void setAdmin(Admin admin) {
        this.admin = admin;
        cargarDatos();
    }

    private void cargarDatos() {
        nombreField.setText(admin.getNombre());
        apellido1Field.setText(admin.getPrimerApellido());
        apellido2Field.setText(admin.getSegundoApellido());
        correoField.setText(admin.getEmail());
        fechaNacimientoPicker.setValue(admin.getFechaNacimiento());
        estadoCheckBox.setSelected(admin.isEstado());
    }

    @FXML
    private void guardarCambios() {
        // Validar campos si quieres
        admin.setNombre(nombreField.getText());
        admin.setPrimerApellido(apellido1Field.getText());
        admin.setSegundoApellido(apellido2Field.getText());
        admin.setEmail(correoField.getText());
        admin.setFechaNacimiento(fechaNacimientoPicker.getValue());
        admin.setEstado(estadoCheckBox.isSelected());

        adminController.updateAdmin(admin);
        mensajeLabel.setStyle("-fx-text-fill: green;");
        mensajeLabel.setText("Perfil actualizado correctamente.");
    }

    @FXML
    private void cancelarEdicion() {
        // Regresar a la vista de perfil (suponiendo que el controlador padre tiene ese método)
        // Aquí debes obtener el controlador padre o cerrar esta vista y mostrar la anterior

        // Por simplicidad, podrías llamar directamente al controlador padre si lo tienes,
        // o cargar la vista de perfil desde aquí.

        // Ejemplo (reemplaza según tu lógica de navegación):
        mostrarPerfilEnPadre();
    }

    private void mostrarPerfilEnPadre() {
        // Lógica para regresar a mostrar perfil, por ejemplo:
        // cargar la vista perfil o llamar método en controlador padre
    }
}

