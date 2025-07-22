package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.example.model.Cliente;
import org.example.controller.ClienteController;

import java.time.LocalDate;

public class GestionClienteFXController {

    @FXML private TableView<Cliente> clienteTable;
    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colApellido1;
    @FXML private TableColumn<Cliente, String> colApellido2;
    @FXML private TableColumn<Cliente, String> colCorreo;
    @FXML private TableColumn<Cliente, Boolean> colEstado;
    @FXML private TableColumn<Cliente, String> zonaColumn;
    @FXML private TableColumn<Cliente, String> tipoFincaColumn;
    @FXML private TableColumn<Cliente, Integer> tamannoTerrenoColumn;

    @FXML private TableColumn<Cliente, Integer> Edad;
    @FXML private TableColumn<Cliente, LocalDate> Registro;
    @FXML private TableColumn<Cliente, Void> colAcciones;

    @FXML private TextField nombreField;
    @FXML private TextField apellido1Field;
    @FXML private TextField apellido2Field;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private CheckBox estadoCheck;
    @FXML private TextField zonaField;
    @FXML private TextField tipoFincaField;
    @FXML private TextField tamannoTerrenoField;

    @FXML private DatePicker fechaNacimientoPicker;
    @FXML private TextField pinField;
    @FXML private Label statusLabel;

    private final ClienteController clienteController = new ClienteController();
    private Cliente clienteSeleccionado = null;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("segundoApellido"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        zonaColumn.setCellValueFactory(new PropertyValueFactory<>("zona"));
        tipoFincaColumn.setCellValueFactory(new PropertyValueFactory<>("tipoFinca"));
        tamannoTerrenoColumn.setCellValueFactory(new PropertyValueFactory<>("tamannoTerreno"));
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
                    Cliente cliente = getTableView().getItems().get(getIndex());
                    cargarDatosEnFormulario(cliente);
                });

                btnEliminar.setOnAction(event -> {
                    Cliente cliente = getTableView().getItems().get(getIndex());
                    eliminarCliente(cliente);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });

        cargarClientes();
    }

    private void cargarDatosEnFormulario(Cliente cliente) {
        clienteSeleccionado = cliente;

        nombreField.setText(cliente.getNombre());
        apellido1Field.setText(cliente.getPrimerApellido());
        apellido2Field.setText(cliente.getSegundoApellido());
        emailField.setText(cliente.getEmail());
        estadoCheck.setSelected(cliente.isEstado());
        fechaNacimientoPicker.setValue(cliente.getFechaNacimiento());
        pinField.setText(cliente.getPin());
        passwordField.clear(); // No se carga la contraseña por seguridad

        zonaField.setText(cliente.getZona());
        tipoFincaField.setText(cliente.getTipoFinca());
        tamannoTerrenoField.setText(String.valueOf(cliente.getTamannoTerreno()));
    }


    @FXML
    public void editarCliente() {
        if (clienteSeleccionado == null) {
            statusLabel.setText("Seleccione un cliente para editar");
            return;
        }

        // Validación básica
        if (nombreField.getText().isEmpty() || apellido1Field.getText().isEmpty() ||
                emailField.getText().isEmpty() || fechaNacimientoPicker.getValue() == null ||
                zonaField.getText().isEmpty() || tipoFincaField.getText().isEmpty() || tamannoTerrenoField.getText().isEmpty()) {
            statusLabel.setText("Complete todos los campos obligatorios");
            return;
        }

        // Setear los nuevos valores al cliente seleccionado
        clienteSeleccionado.setNombre(nombreField.getText());
        clienteSeleccionado.setPrimerApellido(apellido1Field.getText());
        clienteSeleccionado.setSegundoApellido(apellido2Field.getText());
        clienteSeleccionado.setEmail(emailField.getText());
        clienteSeleccionado.setEstado(estadoCheck.isSelected());
        clienteSeleccionado.setFechaNacimiento(fechaNacimientoPicker.getValue());
        clienteSeleccionado.setPin(pinField.getText());
        clienteSeleccionado.setZona(zonaField.getText());
        clienteSeleccionado.setTipoFinca(tipoFincaField.getText());

        try {
            int tamanno = Integer.parseInt(tamannoTerrenoField.getText());
            clienteSeleccionado.setTamannoTerreno(tamanno);
        } catch (NumberFormatException e) {
            statusLabel.setText("Tamaño de terreno debe ser un número válido");
            return;
        }

        if (!passwordField.getText().isEmpty()) {
            clienteSeleccionado.setContrasenna(passwordField.getText());
        }

        clienteController.updateCliente(clienteSeleccionado);
        cargarClientes();
        statusLabel.setText("Cliente actualizado correctamente.");
        limpiarFormulario();
        clienteSeleccionado = null;
    }


    @FXML
    public void agregarCliente() {
        String nombre = nombreField.getText();
        String apellido1 = apellido1Field.getText();
        String apellido2 = apellido2Field.getText();
        String email = emailField.getText();
        String contrasenna = passwordField.getText();
        boolean estado = estadoCheck.isSelected();
        LocalDate fechaNacimiento = fechaNacimientoPicker.getValue();
        String pin = pinField.getText();
        String zona = zonaField.getText();
        String tipoFinca = tipoFincaField.getText();
        String terrenoTexto = tamannoTerrenoField.getText();

        if (nombre.isEmpty() || apellido1.isEmpty() || email.isEmpty() || contrasenna.isEmpty()
                || fechaNacimiento == null || pin.isEmpty() || zona.isEmpty()
                || tipoFinca.isEmpty() || terrenoTexto.isEmpty()) {
            statusLabel.setText("Todos los campos son obligatorios.");
            return;
        }

        int tamannoTerreno;
        try {
            tamannoTerreno = Integer.parseInt(terrenoTexto);
        } catch (NumberFormatException e) {
            statusLabel.setText("Tamaño terreno debe ser un número entero.");
            return;
        }

        LocalDate fechaRegistro = LocalDate.now();

        Cliente nuevo = new Cliente(
                0, nombre, apellido1, apellido2, email, contrasenna,
                "Cliente", estado, fechaNacimiento, fechaRegistro, pin,
                zona, tipoFinca, tamannoTerreno
        );

        clienteController.setCliente(nuevo);
        cargarClientes();
        statusLabel.setText("Cliente registrado correctamente.");
        limpiarFormulario();
    }


    private void eliminarCliente(Cliente cliente) {
        clienteController.deleteCliente(cliente.getId());
        cargarClientes();
        statusLabel.setText("Cliente eliminado correctamente.");
    }

    private void cargarClientes() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(clienteController.getClientes());
        clienteTable.setItems(clientes);
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
        tipoFincaField.clear();
        tamannoTerrenoField.clear();

    }
}
