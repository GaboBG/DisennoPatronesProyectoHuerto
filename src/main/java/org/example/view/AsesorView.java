package org.example.view;


import org.example.controller.AsesorController;
import org.example.model.Asesor;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AsesorView {
    private static AsesorController controller = new AsesorController();
    private static Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        int opcion = 0;

        do {
            System.out.println("\n========= MENÚ DE ASESORES =========");
            System.out.println("1. Registrar nuevo asesor");
            System.out.println("2. Actualizar asesor");
            System.out.println("3. Listar todos los asesores");
            System.out.println("4. Buscar asesor por ID");
            System.out.println("5. Buscar asesor por correo");
            System.out.println("6. Eliminar asesor");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> setAsesor();
                case 2 -> updateAsesor();
                case 3 -> getAsesores();
                case 4 -> {
                    System.out.print("Ingrese el ID del asesor: ");
                    int id = Integer.parseInt(sc.nextLine());
                    getAsesorById(id);
                }
                case 5 -> {
                    System.out.print("Ingrese el correo del asesor: ");
                    String correo = sc.nextLine();
                    getAsesorByCorreo(correo);
                }
                case 6 -> {
                    System.out.print("Ingrese el ID del asesor a eliminar: ");
                    int idEliminar = Integer.parseInt(sc.nextLine());
                    deleteAsesor(idEliminar);
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    public static void setAsesor() {
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

        String rol = "Asesor";

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
        System.out.print("Pin: ");
        String pin = sc.nextLine();

        System.out.print("Especialidad: ");
        String especialidad = sc.nextLine();

        System.out.print("Zona: ");
        String zona = sc.nextLine();

        System.out.print("Certificación: ");
        String certificacion = sc.nextLine();


        Asesor asesor = new Asesor(0, nombre, primerApellido, segundoApellido, email, contrasenna,
                rol, estado, fechaNacimiento, fechaRegistro, pin,especialidad, zona, certificacion);

        controller.setAsesor(asesor);
        System.out.println("Asesor registrado correctamente.");
    }

    private static void updateAsesor() {
        System.out.print("ID del asesor a actualizar: ");
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

        String rol = "Asesor";

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

        System.out.print("Pin: ");
        String pin = sc.nextLine();


        System.out.print("Especialidad: ");
        String especialidad = sc.nextLine();

        System.out.print("Zona: ");
        String zona = sc.nextLine();

        System.out.print("Certificación: ");
        String certificacion = sc.nextLine();

        Asesor asesor = new Asesor(
                id,
                nombre, primerApellido, segundoApellido, email, contrasenna,
                rol, estado, fechaNacimiento, fechaRegistro,pin,
                especialidad, zona, certificacion
        );

        controller.updateAsesor(asesor);
        System.out.println("Asesor actualizado correctamente.");
    }

    private static void getAsesores() {
        List<Asesor> asesores = controller.getAsesores();
        for (Asesor asesor : asesores) {
            System.out.println(asesor);
        }
    }

    private static void getAsesorById(int id) {
        Asesor asesor = controller.getAsesorById(id);
        if (asesor != null) {
            System.out.println(asesor);
        } else {
            System.out.println("No se encontró asesor con ID: " + id);
        }
    }

    private static void getAsesorByCorreo(String correo) {
        Asesor asesor = controller.getAsesorByCorreo(correo);
        if (asesor != null) {
            System.out.println(asesor);
        } else {
            System.out.println("No se encontró asesor con el correo: " + correo);
        }
    }

    private static void deleteAsesor(int id) {
        Asesor asesor = controller.getAsesorById(id);
        if (asesor != null) {
            controller.deleteAsesor(id);
            System.out.println("Asesor eliminado.");
        } else {
            System.out.println("No se encontró asesor con ID: " + id);
        }
    }
}
