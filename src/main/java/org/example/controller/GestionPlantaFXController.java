package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.example.model.Planta;

public class GestionPlantaFXController {

    @FXML private TableView<Planta> plantaTable;
    @FXML private TableColumn<Planta, Integer> colId;
    @FXML private TableColumn<Planta, String> colNombre;
    @FXML private TableColumn<Planta, String> colClima;
    @FXML private TableColumn<Planta, String> colFrecuencia;
    @FXML private TableColumn<Planta, String> colDescripcion;
    @FXML private TableColumn<Planta, Boolean> colEstado;
    @FXML private TableColumn<Planta, Void> colAcciones;

    @FXML private TextField nombreField;
    @FXML private TextField climaField;
    @FXML private TextField frecuenciaField;
    @FXML private TextField descripcionField;
    @FXML private CheckBox estadoCheck;
    @FXML private Label statusLabel;

    private final PlantaController plantaController = new PlantaController();
    private Planta plantaSeleccionada = null;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idPlanta"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colClima.setCellValueFactory(new PropertyValueFactory<>("clima"));
        colFrecuencia.setCellValueFactory(new PropertyValueFactory<>("frecuenciaRiego"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        colAcciones.setCellFactory(param -> new TableCell<>() {
            private final Button btnEditar = new Button("Editar");
            private final Button btnEliminar = new Button("Eliminar");
            private final HBox pane = new HBox(10, btnEditar, btnEliminar);

            {
                btnEditar.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold;");
                btnEliminar.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white; -fx-font-weight: bold;");
                pane.setStyle("-fx-alignment: center;");

                btnEditar.setOnAction(event -> {
                    Planta planta = getTableView().getItems().get(getIndex());
                    cargarDatosEnFormulario(planta);
                });

                btnEliminar.setOnAction(event -> {
                    Planta planta = getTableView().getItems().get(getIndex());
                    eliminarPlanta(planta);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });

        cargarPlantas();
    }

    private void cargarDatosEnFormulario(Planta planta) {
        plantaSeleccionada = planta;

        nombreField.setText(planta.getNombre());
        climaField.setText(planta.getClima());
        frecuenciaField.setText(planta.getFrecuenciaRiego());
        descripcionField.setText(planta.getDescripcion());
        estadoCheck.setSelected(planta.isEstado());
        statusLabel.setText("");
    }

    @FXML
    public void editarPlanta() {
        if (plantaSeleccionada == null) {
            statusLabel.setText("Seleccione una planta para editar.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!validarFormulario()) {
            statusLabel.setText("Complete todos los campos antes de editar.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        plantaSeleccionada.setNombre(nombreField.getText());
        plantaSeleccionada.setClima(climaField.getText());
        plantaSeleccionada.setFrecuenciaRiego(frecuenciaField.getText());
        plantaSeleccionada.setDescripcion(descripcionField.getText());
        plantaSeleccionada.setEstado(estadoCheck.isSelected());

        plantaController.updatePlanta(plantaSeleccionada);
        cargarPlantas();
        statusLabel.setText("Planta actualizada correctamente.");
        statusLabel.setStyle("-fx-text-fill: green;");
        limpiarFormulario();
        plantaSeleccionada = null;
    }

    @FXML
    public void agregarPlanta() {
        if (!validarFormulario()) {
            statusLabel.setText("Complete todos los campos antes de agregar.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        Planta nueva = new Planta(
                0,
                nombreField.getText(),
                climaField.getText(),
                frecuenciaField.getText(),
                descripcionField.getText(),
                estadoCheck.isSelected()
        );

        plantaController.setPlanta(nueva);
        cargarPlantas();
        statusLabel.setText("Planta registrada correctamente.");
        statusLabel.setStyle("-fx-text-fill: green;");
        limpiarFormulario();
    }

    private void eliminarPlanta(Planta planta) {
        plantaController.deletePlanta(planta.getIdPlanta());
        cargarPlantas();
        statusLabel.setText("Planta eliminada correctamente.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    private void cargarPlantas() {
        ObservableList<Planta> plantas = FXCollections.observableArrayList(plantaController.getPlantas());
        plantaTable.setItems(plantas);
    }

    private void limpiarFormulario() {
        nombreField.clear();
        climaField.clear();
        frecuenciaField.clear();
        descripcionField.clear();
        estadoCheck.setSelected(false);
        plantaSeleccionada = null;
    }

    private boolean validarFormulario() {
        return !nombreField.getText().isEmpty() &&
                !climaField.getText().isEmpty() &&
                !frecuenciaField.getText().isEmpty() &&
                !descripcionField.getText().isEmpty();
    }
}
