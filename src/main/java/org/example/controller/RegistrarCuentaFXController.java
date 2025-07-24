package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.model.Asesor;
import org.example.model.Cliente;

import java.io.IOException;
import java.time.LocalDate;

public class RegistrarCuentaFXController {

    @FXML private ComboBox<String> tipoCuentaComboBox;
    @FXML private TextField nombreField, apellido1Field, apellido2Field, emailField, passwordField;
    @FXML private DatePicker fechaNacimientoPicker;
    @FXML private TextField pinField, zonaField;
    @FXML private TextField especialidadField, certificacionField; // asesor
    @FXML private TextField tipoFincaField, tamannoTerrenoField; // cliente

    @FXML private Label statusLabel;
    @FXML private Label especialidadLabel, certificacionLabel, tipoFincaLabel, tamannoLabel;

    // Instancias de controladores para agregar usuarios
    private final ClienteController clienteController = new ClienteController();
    private final AsesorController asesorController = new AsesorController();

    @FXML
    public void initialize() {
        tipoCuentaComboBox.setValue("Cliente");
        tipoCuentaComboBox.setOnAction(e -> actualizarVisibilidadCampos());
        actualizarVisibilidadCampos();
    }

    private void actualizarVisibilidadCampos() {
        boolean esAsesor = tipoCuentaComboBox.getValue().equalsIgnoreCase("Asesor");

        especialidadLabel.setVisible(esAsesor);
        especialidadField.setVisible(esAsesor);
        certificacionLabel.setVisible(esAsesor);
        certificacionField.setVisible(esAsesor);

        tipoFincaLabel.setVisible(!esAsesor);
        tipoFincaField.setVisible(!esAsesor);
        tamannoLabel.setVisible(!esAsesor);
        tamannoTerrenoField.setVisible(!esAsesor);
    }

    @FXML
    public void registrarCuenta() {
        String tipo = tipoCuentaComboBox.getValue();
        String nombre = nombreField.getText();
        String apellido1 = apellido1Field.getText();
        String apellido2 = apellido2Field.getText();
        String email = emailField.getText();
        String contrasenna = passwordField.getText();
        LocalDate fechaNacimiento = fechaNacimientoPicker.getValue();
        String pin = pinField.getText();
        String zona = zonaField.getText();

        if (nombre.isEmpty() || apellido1.isEmpty() || email.isEmpty() || contrasenna.isEmpty()
                || fechaNacimiento == null || pin.isEmpty() || zona.isEmpty()) {
            statusLabel.setText("Por favor, completa los campos obligatorios.");
            return;
        }

        boolean estado = false; // por defecto
        LocalDate fechaRegistro = LocalDate.now();

        if (tipo.equalsIgnoreCase("Asesor")) {
            String especialidad = especialidadField.getText();
            String certificacion = certificacionField.getText();

            if (especialidad.isEmpty() || certificacion.isEmpty()) {
                statusLabel.setText("Completa todos los campos de asesor.");
                return;
            }

            Asesor nuevo = new Asesor(
                    0, nombre, apellido1, apellido2, email, contrasenna,
                    "Asesor", estado, fechaNacimiento, fechaRegistro, pin,
                    zona, especialidad, certificacion
            );
            // Aquí usamos el controlador para agregar el asesor
            asesorController.setAsesor(nuevo);
            statusLabel.setText("Asesor registrado exitosamente.");

        } else {
            String tipoFinca = tipoFincaField.getText();
            String terrenoTexto = tamannoTerrenoField.getText();
            int tamannoTerreno;

            if (tipoFinca.isEmpty() || terrenoTexto.isEmpty()) {
                statusLabel.setText("Completa todos los campos de cliente.");
                return;
            }

            try {
                tamannoTerreno = Integer.parseInt(terrenoTexto);
            } catch (NumberFormatException e) {
                statusLabel.setText("Tamaño de terreno inválido.");
                return;
            }

            Cliente nuevo = new Cliente(
                    0, nombre, apellido1, apellido2, email, contrasenna,
                    "Cliente", estado, fechaNacimiento, fechaRegistro, pin,
                    zona, tipoFinca, tamannoTerreno
            );
            // Aquí usamos el controlador para agregar el cliente
            clienteController.setCliente(nuevo);
            statusLabel.setText("Cliente registrado exitosamente.");
        }

        limpiarCampos();
    }

    private void limpiarCampos() {
        nombreField.clear();
        apellido1Field.clear();
        apellido2Field.clear();
        emailField.clear();
        passwordField.clear();
        fechaNacimientoPicker.setValue(null);
        pinField.clear();
        zonaField.clear();
        especialidadField.clear();
        certificacionField.clear();
        tipoFincaField.clear();
        tamannoTerrenoField.clear();
    }

    @FXML
    public void volverLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) tipoCuentaComboBox.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
