package org.example.controller;

import Util.FXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Cliente;

import java.io.IOException;

public class ClienteFXController {

    @FXML
    private VBox contentArea;

    private Cliente clienteLogueado;

    // Establece el cliente autenticado y muestra el perfil
    public void setCliente(Cliente cliente) {
        this.clienteLogueado = cliente;
        mostrarPerfil();
    }

    @FXML
    private void mostrarPerfil() {
        contentArea.getChildren().clear();

        Label titulo = new Label("Perfil del Cliente");
        titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        Label nombre = new Label("Nombre: " + clienteLogueado.getNombre() + " " + clienteLogueado.getPrimerApellido());
        Label email = new Label("Correo: " + clienteLogueado.getEmail());
        // Puedes agregar más información como teléfono, cédula, etc.

        contentArea.getChildren().addAll(titulo, nombre, email);
    }

    @FXML
    public void mostrarCultivos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GestionClienteCultivo.fxml"));
            Node vista = loader.load();

            GestionCultivoFXController cultivoController = loader.getController();
            cultivoController.setCliente(clienteLogueado);

            contentArea.getChildren().setAll(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


@FXML
public void mostrarActividades() {
    cargarVista("/views/GestionFacturaClienteView.fxml");
}

@FXML
public void mostrarRecomendaciones() {
    cargarVista("/views/GestionServicioClienteView.fxml");
}

@FXML
public void mostrarReportes() {
    cargarVista("/views/GestionPlantaClienteView.fxml");
}

private void cargarVista(String rutaFXML) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
        Node vista = loader.load();
        contentArea.getChildren().setAll(vista);
        System.out.println("Vista cargada: " + rutaFXML);
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Error al cargar la vista: " + rutaFXML);
    }
}
    @FXML
    private void cerrarSesion() {
        Stage stage = (Stage) contentArea.getScene().getWindow();
        FXUtils.cerrarSesion(stage);
    }
}
