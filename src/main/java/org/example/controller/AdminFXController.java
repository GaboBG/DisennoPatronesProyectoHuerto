package org.example.controller;

import org.example.Util.FXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Admin;

import java.io.IOException;

public class AdminFXController {

    @FXML
    private VBox contentArea;

    private Admin adminLogueado;

    // Recibe el Admin autenticado y muestra su perfil al iniciar
    public void setAdmin(Admin admin) {
        this.adminLogueado = admin;
        mostrarPerfil();
    }


    @FXML
    public void mostrarAdmins() {
        cargarVista("/views/GestionAdminView.fxml"); // Usa el mismo método que las otras vistas
    }

    @FXML
    public void mostrarAsesores() {
        cargarVista("/views/GestionAsesorView.fxml");
    }

    @FXML
    public void mostrarClientes() {
        cargarVista("/views/GestionClienteView.fxml");
    }

    @FXML
    public void mostrarPlantas() {
        cargarVista("/views/GestionPlantaView.fxml");
    }

    private void cargarVista(String rutaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Node vista = loader.load();
            contentArea.getChildren().setAll(vista); // Asegúrate que contentArea esté anotado con @FXML y cargado correctamente
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

    @FXML
    private void mostrarPerfil() {
        contentArea.getChildren().clear();

        Label titulo = new Label("Perfil de Admin");
        titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        Label nombre = new Label("Nombre: " + adminLogueado.getNombre());
        Label apellido1 = new Label("Primer Apellido: " + adminLogueado.getPrimerApellido());
        Label apellido2 = new Label("Segundo Apellido: " + adminLogueado.getSegundoApellido());
        Label email = new Label("Correo: " + adminLogueado.getEmail());
        Label fechaNacimiento = new Label("Fecha Nacimiento: " + adminLogueado.getFechaNacimiento());
        Label fechaRegistro = new Label("Fecha Registro: " + adminLogueado.getFechaRegistro());
        Label rol = new Label("Rol: " + adminLogueado.getRol());
        Label estado = new Label("Estado: " + (adminLogueado.isEstado() ? "Activo" : "Inactivo"));

        Button btnEditar = new Button("Editar Perfil");
        btnEditar.setOnAction(e -> mostrarFormularioEditarPerfil());

        VBox perfilBox = new VBox(10, titulo, nombre, apellido1, apellido2, email, fechaNacimiento, fechaRegistro, rol, estado, btnEditar);
        contentArea.getChildren().add(perfilBox);
    }
    private void mostrarFormularioEditarPerfil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EditarPerfilAdmin.fxml"));
            VBox formulario = loader.load();

            EditarPerfilAdminFXController controller = loader.getController();
            controller.setAdmin(adminLogueado);

            contentArea.getChildren().setAll(formulario);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}


/*package org.example.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.example.model.Admin;
public class AdminFXController {
    @FXML
    private VBox contentArea;

    private Admin adminLogueado;

    // Recibe el Admin autenticado y muestra su perfil al iniciar
    public void setAdmin(Admin admin) {
        this.adminLogueado = admin;
        mostrarPerfil();
    }

    @FXML
    private void mostrarPerfil() {
        contentArea.getChildren().clear();

        Label titulo = new Label("Perfil de Admin");
        titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        Label nombre = new Label("Nombre: " + adminLogueado.getNombre() + " " + adminLogueado.getPrimerApellido());
        Label email = new Label("Correo: " + adminLogueado.getEmail());
        // Agrega otros datos que quieras mostrar

        contentArea.getChildren().addAll(titulo, nombre, email);
    }

    @FXML
    private void mostrarGestionUsuarios() {
        contentArea.getChildren().clear();
        Label label = new Label("Aquí se gestionan usuarios.");
        contentArea.getChildren().add(label);
    }

    @FXML
    private void mostrarReportes() {
        contentArea.getChildren().clear();
        Label label = new Label("Aquí se muestran reportes.");
        contentArea.getChildren().add(label);
    }
}
*/