package org.example.view;

import org.example.controller.AdminController;
import org.example.controller.AuthController;
import org.example.controller.UsuarioController;
import org.example.model.Usuario;

import java.util.Scanner;

public class AuthView {
    private static final Scanner sc = new Scanner(System.in);
    private static final AuthController authController = new AuthController();
    private static final UsuarioController usuarioController = new UsuarioController();

    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("\n========= MENÚ DE AUTENTICACIÓN =========");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Recuperar Contraseña");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> iniciarSesion();
                case 2 -> recuperarContrasenna();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    public static void iniciarSesion() {
        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Contraseña: ");
        String contrasenna = sc.nextLine();

        // Buscar usuario para obtener el rol
        Usuario usuario = usuarioController.getUsuarioByCorreo(correo);

        if (usuario == null) {
            System.out.println("Correo no registrado.");
            return;
        }

        String rol = usuario.getRol();

        // Intentar login
        Usuario logueado = authController.login(correo, contrasenna, rol);

        if (logueado != null) {
            System.out.println("Inicio de sesión exitoso como " + rol);
            // Aquí puedes redirigir al menú correspondiente
            switch (rol) {
                case "Admin":
                    AdminView.mostrarMenu();
                    break;
                case "Asesor":
                    AsesorView.mostrarMenu();
                    break;
                case "Cliente":
                    ClienteView.mostrarMenu();
                    break;
                default:
                    System.out.println("Rol desconocido.");
            }
        } else {
            System.out.println("Credenciales inválidas.");
        }
    }

    public static void recuperarContrasenna() {
        System.out.println("\n========= RECUPERAR CONTRASEÑA =========");

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        Usuario usuario = usuarioController.getUsuarioByCorreo(correo);

        if (usuario == null) {
            System.out.println("Correo no encontrado.");
            return;
        }

        System.out.print("PIN de seguridad: ");
        String pinIngresado = sc.nextLine();

        // Validación
        if (!usuario.getPin().equals(pinIngresado)) {
            System.out.println("PIN incorrecto.");
            return;
        }

        System.out.print("Ingrese nueva contraseña: ");
        String nuevaContrasenna = sc.nextLine();

        usuario.setContrasenna(nuevaContrasenna);
        usuarioController.updateUsuario(usuario);

        System.out.println("Contraseña actualizada correctamente.");
    }
}
