package org.example.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.example.controller.CultivoController;
import org.example.model.Cliente;
import org.example.model.Cultivo;
import org.example.model.Planta;

import java.time.LocalDate;
import java.util.List;

public class GestionCultivoFXController {
    @FXML private TableView<Cultivo> cultivoTable;
    @FXML private TableColumn<Cultivo, Integer> colId;
    @FXML private TableColumn<Cultivo, String> colNombre;
    @FXML private TableColumn<Cultivo, String> colUbicacion;
    @FXML private TableColumn<Cultivo, LocalDate> colFechaSiembra;
    @FXML private TableColumn<Cultivo, LocalDate> colFechaCosecha;
    @FXML private TableColumn<Cultivo, String> colEstado;
    @FXML private TableColumn<Cultivo, Void> colAcciones;

    @FXML private TextField nombreField;
    @FXML private ComboBox<Planta> plantaComboBox;
    @FXML private TextField ubicacionField;
    @FXML private DatePicker fechaSiembraPicker;
    @FXML private DatePicker fechaCosechaPicker;
    @FXML private TextField estadoField;
    @FXML private Label statusLabel;


    private final CultivoController cultivoController = new CultivoController();
    private Cultivo cultivoSeleccionado = null;

    private Cliente clienteLogueado;

    private PlantaController plantaController = new PlantaController();


    public void setCliente(Cliente cliente) {
        this.clienteLogueado = cliente;
        cargarCultivos(); // si deseas filtrar por cliente
    }



    @FXML
    public void initialize() {
        cargarPlantas();
        colId.setCellValueFactory(new PropertyValueFactory<>("idCultivo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colUbicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
        colFechaSiembra.setCellValueFactory(new PropertyValueFactory<>("fechaSiembra"));
        colFechaCosecha.setCellValueFactory(new PropertyValueFactory<>("fechaCosecha"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        colAcciones.setCellFactory(param -> new TableCell<>() {
            private final Button btnEditar = new Button("Editar");
            private final Button btnEliminar = new Button("Eliminar");
            private final HBox pane = new HBox(10, btnEditar, btnEliminar);

            {
                btnEditar.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
                btnEliminar.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white;");
                pane.setStyle("-fx-alignment: center;");

                btnEditar.setOnAction(event -> {
                    Cultivo cultivo = getTableView().getItems().get(getIndex());
                    cargarDatosEnFormulario(cultivo);
                });

                btnEliminar.setOnAction(event -> {
                    Cultivo cultivo = getTableView().getItems().get(getIndex());
                    cultivoController.deleteCultivo(cultivo.getIdCultivo());
                    cargarCultivos();
                    statusLabel.setText("Cultivo eliminado.");
                    statusLabel.setStyle("-fx-text-fill: green;");
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });

        cargarCultivos();
    }

    private void cargarPlantas() {
        List<Planta> plantas = plantaController.getPlantas();
        plantaComboBox.setItems(FXCollections.observableArrayList(plantas));
    }

    private Integer obtenerIdPlantaSeleccionada() {
        Planta seleccionada = plantaComboBox.getValue();
        return (seleccionada != null) ? seleccionada.getIdPlanta() : null;
    }


    public void agregarCultivo() {
        if (!validarFormulario()) {
            statusLabel.setText("Complete todos los campos.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        Integer idPlanta = obtenerIdPlantaSeleccionada();
        if (idPlanta == null) {
            statusLabel.setText("Seleccione una planta.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        Cultivo nuevo = new Cultivo(
                0,
                clienteLogueado.getId(),  // Aquí se usa el cliente logueado
                idPlanta,
                nombreField.getText(),
                ubicacionField.getText(),
                fechaSiembraPicker.getValue(),
                fechaCosechaPicker.getValue(),
                estadoField.getText()
        );

        cultivoController.setCultivo(nuevo);
        cargarCultivos();
        statusLabel.setText("Cultivo agregado.");
        statusLabel.setStyle("-fx-text-fill: green;");
        limpiarFormulario();
    }

    @FXML
    public void editarCultivo() {
        if (cultivoSeleccionado == null) {
            statusLabel.setText("Seleccione un cultivo para editar.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!validarFormulario()) {
            statusLabel.setText("Complete todos los campos.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        cultivoSeleccionado.setNombre(nombreField.getText());
        cultivoSeleccionado.setUbicacion(ubicacionField.getText());
        cultivoSeleccionado.setFechaSiembra(fechaSiembraPicker.getValue());
        cultivoSeleccionado.setFechaCosecha(fechaCosechaPicker.getValue());
        cultivoSeleccionado.setEstado(estadoField.getText());

        cultivoController.updateCultivo(cultivoSeleccionado);
        cargarCultivos();
        statusLabel.setText("Cultivo actualizado.");
        statusLabel.setStyle("-fx-text-fill: green;");
        limpiarFormulario();
        cultivoSeleccionado = null;
    }

    private void cargarCultivos() {
        ObservableList<Cultivo> cultivos = FXCollections.observableArrayList(cultivoController.getCultivo());
        cultivoTable.setItems(cultivos);
    }

    private void cargarDatosEnFormulario(Cultivo cultivo) {
        cultivoSeleccionado = cultivo;
        nombreField.setText(cultivo.getNombre());
        ubicacionField.setText(cultivo.getUbicacion());
        fechaSiembraPicker.setValue(cultivo.getFechaSiembra());
        fechaCosechaPicker.setValue(cultivo.getFechaCosecha());
        estadoField.setText(cultivo.getEstado());
        statusLabel.setText("");
    }

    private void limpiarFormulario() {
        nombreField.clear();
        ubicacionField.clear();
        fechaSiembraPicker.setValue(null);
        fechaCosechaPicker.setValue(null);
        estadoField.clear();
    }

    private boolean validarFormulario() {
        return !nombreField.getText().isEmpty() &&
                !ubicacionField.getText().isEmpty() &&
                fechaSiembraPicker.getValue() != null &&
                fechaCosechaPicker.getValue() != null &&
                !estadoField.getText().isEmpty();
    }
}