package org.example.view;

import org.example.controller.AdminController;
import org.example.model.Admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AdminView {
    private static AdminController controller = new AdminController();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;

        do {
            System.out.println("\n========= MENÚ DE ADMINISTRADORES =========");
            System.out.println("1. Registrar nuevo admin");
            System.out.println("2. Actualizar admin");
            System.out.println("3. Listar todos los admins");
            System.out.println("4. Buscar admin por ID");
            System.out.println("5. Buscar admin por correo");
            System.out.println("6. Eliminar admin");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> setAdmin();
                case 2 -> updateAdmin();
                case 3 -> getAdmins();
                case 4 -> {
                    System.out.print("Ingrese el ID del admin: ");
                    int id = Integer.parseInt(sc.nextLine());
                    getAdminById(id);
                }
                case 5 -> {
                    System.out.print("Ingrese el correo del admin: ");
                    String correo = sc.nextLine();
                    getAdminByCorreo(correo);
                }
                case 6 -> {
                    System.out.print("Ingrese el ID del admin a eliminar: ");
                    int idEliminar = Integer.parseInt(sc.nextLine());
                    deleteAdmin(idEliminar);
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private static void setAdmin() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Primer Apellido: ");
        String primerApellido = sc.nextLine();

        System.out.print("Segundo Apellido: ");
        String segundoApellido = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Contraseña: ");
        String contrasenna = sc.nextLine();

        String rol = "Admin";

        System.out.print("Estado (true/false): ");
        boolean estado = Boolean.parseBoolean(sc.nextLine());

        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        LocalDate fechaNacimiento;
        try {
            fechaNacimiento = LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto.");
            return;
        }

        LocalDate fechaRegistro = LocalDate.now();



        Admin admin = new Admin(0, nombre, primerApellido, segundoApellido, email, contrasenna,
                rol, estado, fechaNacimiento, fechaRegistro);

        controller.setAdmin(admin);
        System.out.println("Admin registrado correctamente.");
    }

    private static void updateAdmin() {
        System.out.print("ID del admin a actualizar: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Primer Apellido: ");
        String primerApellido = sc.nextLine();

        System.out.print("Segundo Apellido: ");
        String segundoApellido = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Contraseña: ");
        String contrasenna = sc.nextLine();

        String rol = "Admin";

        System.out.print("Estado (true/false): ");
        boolean estado = Boolean.parseBoolean(sc.nextLine());

        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        LocalDate fechaNacimiento;
        try {
            fechaNacimiento = LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto.");
            return;
        }

        LocalDate fechaRegistro = LocalDate.now();


        Admin admin = new Admin(
                id,
                nombre, primerApellido, segundoApellido, email, contrasenna,
                rol, estado, fechaNacimiento, fechaRegistro
        );

        controller.updateAdmin(admin);
        System.out.println("Admin actualizado correctamente.");
    }

    private static void getAdmins() {
        List<Admin> admins = controller.getAdmins();
        for (Admin admin : admins) {
            System.out.println(admin);
        }
    }

    private static void getAdminById(int id) {
        Admin admin = controller.getAdminById(id);
        if (admin != null) {
            System.out.println(admin);
        } else {
            System.out.println("No se encontró admin con ID: " + id);
        }
    }

    private static void getAdminByCorreo(String correo) {
        Admin admin = controller.getAdminByCorreo(correo);
        if (admin != null) {
            System.out.println(admin);
        } else {
            System.out.println("No se encontró admin con el correo: " + correo);
        }
    }

    private static void deleteAdmin(int id) {
        Admin admin = controller.getAdminById(id);
        if (admin != null) {
            controller.deleteAdmin(id);
            System.out.println("Admin eliminado.");
        } else {
            System.out.println("No se encontró admin con ID: " + id);
        }
    }
}

