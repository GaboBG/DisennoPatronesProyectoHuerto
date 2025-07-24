package org.example.controller;

import javafx.scene.control.Button;
import org.example.Util.FXUtils;
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

        Label nombre = new Label("Nombre: " + clienteLogueado.getNombre());
        Label apellido1 = new Label("Primer Apellido: " + clienteLogueado.getPrimerApellido());
        Label apellido2 = new Label("Segundo Apellido: " + clienteLogueado.getSegundoApellido());
        Label email = new Label("Correo: " + clienteLogueado.getEmail());
        Label fechaNacimiento = new Label("Fecha Nacimiento: " + clienteLogueado.getFechaNacimiento());
        Label fechaRegistro = new Label("Fecha Registro: " + clienteLogueado.getFechaRegistro());
        Label zona = new Label("Zona: " + clienteLogueado.getZona());
        Label tipoFinca = new Label("Tipo Finca: " + clienteLogueado.getTipoFinca());
        Label tamanno = new Label("Tamaño Terreno: " + clienteLogueado.getTamannoTerreno() + " m²");
        Label estado = new Label("Estado: " + (clienteLogueado.isEstado() ? "Activo" : "Inactivo"));

        Button btnEditar = new Button("Editar Perfil");
        btnEditar.setOnAction(e -> mostrarFormularioEditarPerfil());

        VBox perfilBox = new VBox(10, titulo, nombre, apellido1, apellido2, email, fechaNacimiento,
                fechaRegistro, zona, tipoFinca, tamanno, estado, btnEditar);

        contentArea.getChildren().add(perfilBox);
    }
    @FXML
    public void mostrarFormularioEditarPerfil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EditarPerfilClienteView.fxml"));

            Node form = loader.load();

            EditarPerfilClienteFXController controller = loader.getController();
            controller.setCliente(clienteLogueado);

            contentArea.getChildren().setAll(form);
        } catch (IOException e) {
            e.printStackTrace();
            contentArea.getChildren().clear();
            Label errorLabel = new Label("Error al cargar formulario de edición");
            contentArea.getChildren().add(errorLabel);
        }
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
