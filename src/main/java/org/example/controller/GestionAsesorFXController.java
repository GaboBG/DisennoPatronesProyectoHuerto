package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.example.model.Asesor;

import java.time.LocalDate;

public class GestionAsesorFXController {
    @FXML private TableView<Asesor> asesorTable;
    @FXML private TableColumn<Asesor, Integer> colId;
    @FXML private TableColumn<Asesor, String> colNombre;
    @FXML private TableColumn<Asesor, String> colApellido1;
    @FXML private TableColumn<Asesor, String> colApellido2;
    @FXML private TableColumn<Asesor, String> colCorreo;
    @FXML private TableColumn<Asesor, Boolean> colEstado;
    @FXML private TableColumn<Asesor, String> especialidadColumn;
    @FXML private TableColumn<Asesor, String> zonaColumn;
    @FXML private TableColumn<Asesor, String> certificacionColumn;
    @FXML private TableColumn<Asesor, Integer> Edad;
    @FXML private TableColumn<Asesor, LocalDate> Registro;
    @FXML private TableColumn<Asesor, Void> colAcciones;

    @FXML private TextField nombreField;
    @FXML private TextField apellido1Field;
    @FXML private TextField apellido2Field;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private CheckBox estadoCheck;
    @FXML private TextField especialidadField;
    @FXML private TextField certificacionField;
    @FXML private TextField zonaField;
    @FXML private DatePicker fechaNacimientoPicker;
    @FXML private TextField pinField;
    @FXML private Label statusLabel;

    private final AsesorController asesorController = new AsesorController();
    private Asesor asesorSeleccionado = null;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("segundoApellido"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        zonaColumn.setCellValueFactory(new PropertyValueFactory<>("zona"));
        especialidadColumn.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        certificacionColumn.setCellValueFactory(new PropertyValueFactory<>("certificacion"));
        Edad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        Registro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));

        colAcciones.setCellFactory(param -> new TableCell<>() {
            private final Button btnEditar = new Button("Editar");
            private final Button btnEliminar = new Button("Eliminar");
            private final HBox pane = new HBox(10, btnEditar, btnEliminar);

            {
                btnEditar.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold;");
                btnEliminar.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white; -fx-font-weight: bold;");

                btnEditar.setOnAction(event -> {
                    Asesor asesor = getTableView().getItems().get(getIndex());
                    cargarDatosEnFormulario(asesor);
                });

                btnEliminar.setOnAction(event -> {
                    Asesor asesor = getTableView().getItems().get(getIndex());
                    eliminarAsesor(asesor);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });

        cargarAsesores();
    }

    private void cargarDatosEnFormulario(Asesor asesor) {
        asesorSeleccionado = asesor;

        nombreField.setText(asesor.getNombre());
        apellido1Field.setText(asesor.getPrimerApellido());
        apellido2Field.setText(asesor.getSegundoApellido());
        emailField.setText(asesor.getEmail());
        estadoCheck.setSelected(asesor.isEstado());
        fechaNacimientoPicker.setValue(asesor.getFechaNacimiento());
        pinField.setText(asesor.getPin());
        passwordField.clear(); // seguridad

        zonaField.setText(asesor.getZona());
        especialidadField.setText(asesor.getEspecialidad());
        certificacionField.setText(asesor.getCertificacion());
    }

    @FXML
    public void editarAsesor() {
        if (asesorSeleccionado == null) {
            statusLabel.setText("Seleccione un asesor para editar");
            return;
        }

        if (nombreField.getText().isEmpty() || apellido1Field.getText().isEmpty() ||
                emailField.getText().isEmpty() || fechaNacimientoPicker.getValue() == null ||
                zonaField.getText().isEmpty() || especialidadField.getText().isEmpty() ||
                certificacionField.getText().isEmpty()) {
            statusLabel.setText("Complete todos los campos obligatorios");
            return;
        }

        asesorSeleccionado.setNombre(nombreField.getText());
        asesorSeleccionado.setPrimerApellido(apellido1Field.getText());
        asesorSeleccionado.setSegundoApellido(apellido2Field.getText());
        asesorSeleccionado.setEmail(emailField.getText());
        asesorSeleccionado.setEstado(estadoCheck.isSelected());
        asesorSeleccionado.setFechaNacimiento(fechaNacimientoPicker.getValue());
        asesorSeleccionado.setPin(pinField.getText());
        asesorSeleccionado.setZona(zonaField.getText());
        asesorSeleccionado.setEspecialidad(especialidadField.getText());
        asesorSeleccionado.setCertificacion(certificacionField.getText());

        if (!passwordField.getText().isEmpty()) {
            asesorSeleccionado.setContrasenna(passwordField.getText());
        }

        asesorController.updateAsesor(asesorSeleccionado);
        cargarAsesores();
        statusLabel.setText("Asesor actualizado correctamente.");
        limpiarFormulario();
        asesorSeleccionado = null;
    }

    @FXML
    public void agregarAsesor() {
        String nombre = nombreField.getText();
        String apellido1 = apellido1Field.getText();
        String apellido2 = apellido2Field.getText();
        String email = emailField.getText();
        String contrasenna = passwordField.getText();
        boolean estado = estadoCheck.isSelected();
        LocalDate fechaNacimiento = fechaNacimientoPicker.getValue();
        String pin = pinField.getText();
        String zona = zonaField.getText();
        String especialidad = especialidadField.getText();
        String certificacion = certificacionField.getText();

        if (nombre.isEmpty() || apellido1.isEmpty() || email.isEmpty() || contrasenna.isEmpty()
                || fechaNacimiento == null || pin.isEmpty() || zona.isEmpty()
                || especialidad.isEmpty() || certificacion.isEmpty()) {
            statusLabel.setText("Todos los campos son obligatorios.");
            return;
        }

        LocalDate fechaRegistro = LocalDate.now();

        Asesor nuevo = new Asesor(
                0, nombre, apellido1, apellido2, email, contrasenna,
                "Asesor", estado, fechaNacimiento, fechaRegistro, pin,
                zona, especialidad, certificacion
        );

        asesorController.setAsesor(nuevo);
        cargarAsesores();
        statusLabel.setText("Asesor registrado correctamente.");
        limpiarFormulario();
    }

    private void eliminarAsesor(Asesor asesor) {
        asesorController.deleteAsesor(asesor.getId());
        cargarAsesores();
        statusLabel.setText("Asesor eliminado correctamente.");
    }

    private void cargarAsesores() {
        ObservableList<Asesor> asesores = FXCollections.observableArrayList(asesorController.getAsesores());
        asesorTable.setItems(asesores);
    }

    private void limpiarFormulario() {
        nombreField.clear();
        apellido1Field.clear();
        apellido2Field.clear();
        emailField.clear();
        passwordField.clear();
        estadoCheck.setSelected(false);
        fechaNacimientoPicker.setValue(null);
        pinField.clear();
        zonaField.clear();
        especialidadField.clear();
        certificacionField.clear();
    }
}
