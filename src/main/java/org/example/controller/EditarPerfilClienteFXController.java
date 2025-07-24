package org.example.controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.model.Cliente;

import java.time.LocalDate;

public class EditarPerfilClienteFXController {

    @FXML private TextField nombreField;
    @FXML private TextField apellido1Field;
    @FXML private TextField apellido2Field;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private DatePicker fechaNacimientoPicker;
    // @FXML private TextField pinField;  // ELIMINADO
    @FXML private TextField zonaField;
    @FXML private TextField tipoFincaField;
    @FXML private TextField tamannoTerrenoField;
    @FXML private CheckBox estadoCheck;
    @FXML private Label statusLabel;
    @FXML private PasswordField pinVerificacionField;

    private Cliente cliente;
    private ClienteController clienteController = new ClienteController();

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        cargarDatos();
    }

    private void cargarDatos() {
        nombreField.setText(cliente.getNombre());
        apellido1Field.setText(cliente.getPrimerApellido());
        apellido2Field.setText(cliente.getSegundoApellido());
        emailField.setText(cliente.getEmail());
        passwordField.setDisable(true);
        fechaNacimientoPicker.setValue(cliente.getFechaNacimiento());
        // Ya no se usa pinField
        zonaField.setText(cliente.getZona());
        tipoFincaField.setText(cliente.getTipoFinca());
        tamannoTerrenoField.setText(String.valueOf(cliente.getTamannoTerreno()));
        estadoCheck.setSelected(cliente.isEstado());
    }

    @FXML
    private void guardarCambios() {
        if (validarCampos()) {
            cliente.setNombre(nombreField.getText());
            cliente.setPrimerApellido(apellido1Field.getText());
            cliente.setSegundoApellido(apellido2Field.getText());
            cliente.setEmail(emailField.getText());
            cliente.setContrasenna(passwordField.getText());  // solo se cambia si passwordField está habilitado
            cliente.setFechaNacimiento(fechaNacimientoPicker.getValue());
            // No se actualiza PIN aquí, no se cambia
            cliente.setZona(zonaField.getText());
            cliente.setTipoFinca(tipoFincaField.getText());
            cliente.setEstado(estadoCheck.isSelected());

            try {
                cliente.setTamannoTerreno(Integer.parseInt(tamannoTerrenoField.getText()));
                clienteController.updateCliente(cliente);
                statusLabel.setStyle("-fx-text-fill: green;");
                statusLabel.setText("Perfil actualizado correctamente.");
            } catch (NumberFormatException e) {
                statusLabel.setStyle("-fx-text-fill: red;");
                statusLabel.setText("Tamaño de terreno inválido.");
            }
        }
    }

    private boolean validarCampos() {
        if (nombreField.getText().isEmpty() ||
                apellido1Field.getText().isEmpty() ||
                emailField.getText().isEmpty() ||
                // Solo validar password si está habilitado para cambiar
                (!passwordField.isDisabled() && passwordField.getText().isEmpty()) ||
                fechaNacimientoPicker.getValue() == null ||
                zonaField.getText().isEmpty() ||
                tipoFincaField.getText().isEmpty() ||
                tamannoTerrenoField.getText().isEmpty()) {
            statusLabel.setText("Todos los campos son obligatorios.");
            return false;
        }
        return true;
    }

    @FXML
    private void verificarPin() {
        String pinIngresado = pinVerificacionField.getText();
        if (pinIngresado.equals(cliente.getPin())) {
            passwordField.setDisable(false);
            statusLabel.setText("PIN correcto. Puedes cambiar la contraseña.");
            statusLabel.setStyle("-fx-text-fill: green;");
        } else {
            passwordField.setDisable(true);
            statusLabel.setText("PIN incorrecto. No puedes cambiar la contraseña.");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void cancelarEdicion() {
        // lógica para cancelar la edición
    }



}
