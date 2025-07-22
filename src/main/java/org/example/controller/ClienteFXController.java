package org.example.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.example.model.Cliente;

public class ClienteFXController {

    @FXML
    private VBox contentArea;

    private Cliente clienteLogueado;

    public void setCliente(Cliente cliente) {
        this.clienteLogueado = cliente;
        mostrarPerfil();
    }

    @FXML
    private void mostrarPerfil() {
        contentArea.getChildren().clear();

        Label titulo = new Label("Perfil de Cliente");
        titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        Label nombre = new Label("Nombre: " + clienteLogueado.getNombre() + " " + clienteLogueado.getPrimerApellido());
        Label email = new Label("Correo: " + clienteLogueado.getEmail());
        Label zona = new Label("Zona: " + clienteLogueado.getZona());
        Label tipoFinca = new Label("Tipo de finca: " + clienteLogueado.getTipoFinca());
        Label tamannoTerreno = new Label("Tamaño terreno: " + clienteLogueado.getTamannoTerreno() + " m²");

        contentArea.getChildren().addAll(titulo, nombre, email, zona, tipoFinca, tamannoTerreno);
    }

    @FXML
    private void mostrarPedidos() {
        contentArea.getChildren().clear();
        Label label = new Label("Aquí se muestran los pedidos.");
        contentArea.getChildren().add(label);
    }

    @FXML
    private void mostrarSoporte() {
        contentArea.getChildren().clear();
        Label label = new Label("Aquí se muestra soporte.");
        contentArea.getChildren().add(label);
    }
}
