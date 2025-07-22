package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.example.model.Admin;
import org.example.controller.AdminController;

import java.time.LocalDate;
import java.util.List;

public class GestionAdminFXController {

    @FXML
    private TableView<Admin> adminTable;

    @FXML
    private TableColumn<Admin, Integer> colId;

    @FXML
    private TableColumn<Admin, String> colNombre;

    @FXML
    private TableColumn<Admin, String> colCorreo;

    @FXML
    private TableColumn<Admin, Boolean> colEstado;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellido1Field;
    @FXML
    private TextField apellido2Field;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox estadoCheck;
    @FXML
    private DatePicker fechaNacimientoPicker;
    @FXML
    private TextField pinField;

    @FXML
    private TableColumn<Admin, String> colApellido1;

    @FXML
    private TableColumn<Admin, String> colApellido2;

    @FXML
    private TableColumn<Admin, String> colRol;

    @FXML
    private TableColumn<Admin, Integer> Edad;

    @FXML
    private TableColumn<Admin, LocalDate> Registro;

    @FXML
    private TableColumn<Admin, Void> colAcciones;
    @FXML
    private Label statusLabel;


    private AdminController adminController = new AdminController();

    @FXML
    public void initialize() {
        // Configurar columnas
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("segundoApellido"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        Edad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        Registro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));

        // Configurar columna de acciones con botones
        colAcciones.setCellFactory(param -> new TableCell<Admin, Void>() {
            private final Button btnEditar = new Button("Editar");
            private final Button btnEliminar = new Button("Eliminar");
            private final HBox pane = new HBox(10, btnEditar, btnEliminar);

            {
                btnEditar.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold;");
                btnEliminar.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white; -fx-font-weight: bold;");

                btnEditar.setOnAction(event -> {
                    Admin admin = getTableView().getItems().get(getIndex());
                    cargarDatosEnFormulario(admin);
                });

                btnEliminar.setOnAction(event -> {
                    Admin admin = getTableView().getItems().get(getIndex());
                    eliminarAdmin(admin);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        });

        cargarAdmins();

        List<Admin> adminList = adminController.getAdmins();
        if (!adminList.isEmpty()) {
            Admin primerAdmin = adminList.get(0);
            cargarDatosEnFormulario(primerAdmin);
        }
    }

// Recuerda definir estos métodos en tu controlador:

    private Admin adminSeleccionado = null;

    private void cargarDatosEnFormulario(Admin admin) {
        adminSeleccionado = admin;
        nombreField.setText(admin.getNombre());
        apellido1Field.setText(admin.getPrimerApellido());
        apellido2Field.setText(admin.getSegundoApellido());
        emailField.setText(admin.getEmail());
        estadoCheck.setSelected(admin.isEstado());
        fechaNacimientoPicker.setValue(admin.getFechaNacimiento());
        pinField.setText(admin.getPin());
        passwordField.clear(); // Por seguridad no cargamos la contraseña visible
    }

    @FXML
    public void editarAdmin() {
        if (adminSeleccionado == null) {
            statusLabel.setText("Seleccione un admin para editar");
            return;
        }

        if (nombreField.getText().isEmpty() || apellido1Field.getText().isEmpty() || emailField.getText().isEmpty() || fechaNacimientoPicker.getValue() == null) {
            statusLabel.setText("Por favor complete todos los campos obligatorios");
            return;
        }

        adminSeleccionado.setNombre(nombreField.getText());
        adminSeleccionado.setPrimerApellido(apellido1Field.getText());
        adminSeleccionado.setSegundoApellido(apellido2Field.getText());
        adminSeleccionado.setEmail(emailField.getText());
        adminSeleccionado.setEstado(estadoCheck.isSelected());
        adminSeleccionado.setFechaNacimiento(fechaNacimientoPicker.getValue());
        adminSeleccionado.setPin(pinField.getText());

        String nuevaContra = passwordField.getText();
        if (!nuevaContra.isEmpty()) {
            adminSeleccionado.setContrasenna(nuevaContra);
        }

        adminController.updateAdmin(adminSeleccionado);
        cargarAdmins();

        statusLabel.setText("Admin actualizado correctamente.");
        limpiarFormulario();
        adminSeleccionado = null;
    }


    private void eliminarAdmin(Admin admin) {
        adminController.deleteAdmin(admin.getId());
        cargarAdmins();
        System.out.println("Admin eliminado correctamente.");
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
    }



    @FXML
    public void cargarAdmins() {
        ObservableList<Admin> admins = FXCollections.observableArrayList(adminController.getAdmins());
        adminTable.setItems(admins);
    }



    @FXML
    public void agregarAdmin() {
        String nombre = nombreField.getText();
        String apellido1 = apellido1Field.getText();
        String apellido2 = apellido2Field.getText();
        String email = emailField.getText();
        String contrasenna = passwordField.getText();
        boolean estado = estadoCheck.isSelected();
        LocalDate fechaNacimiento = fechaNacimientoPicker.getValue();
        String pin = pinField.getText();

        if (nombre.isEmpty() || apellido1.isEmpty() || email.isEmpty() || contrasenna.isEmpty() || fechaNacimiento == null || pin.isEmpty()) {
            System.out.println("Todos los campos requeridos deben estar llenos.");
            return;
        }

        String rol = "Admin";
        LocalDate fechaRegistro = LocalDate.now();

        Admin nuevo = new Admin(0, nombre, apellido1, apellido2, email, contrasenna,
                rol, estado, fechaNacimiento, fechaRegistro, pin);

        adminController.setAdmin(nuevo);

        cargarAdmins(); // refresca tabla
        System.out.println("Admin registrado correctamente.");

        // Limpiar campos
        nombreField.clear();
        apellido1Field.clear();
        apellido2Field.clear();
        emailField.clear();
        passwordField.clear();
        estadoCheck.setSelected(false);
        fechaNacimientoPicker.setValue(null);
        pinField.clear();
    }




    @FXML
    public void eliminarAdmin() {
        Admin seleccionado = adminTable.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            adminController.deleteAdmin(seleccionado.getId());
            cargarAdmins();
        }
    }
}
