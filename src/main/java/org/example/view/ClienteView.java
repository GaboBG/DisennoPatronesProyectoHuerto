package org.example.view;

import org.example.controller.ClienteController;
import org.example.model.Asesor;
import org.example.model.Cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ClienteView {

    private static ClienteController controller = new ClienteController();
    private static Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        int opcion = 0;

        do {
            System.out.println("\n========= MENÚ DE CLIENTES =========");
            System.out.println("1. Registrar nuevo cliente");
            System.out.println("2. Actualizar cliente");
            System.out.println("3. Listar todos los clientes");
            System.out.println("4. Buscar cliente por ID");
            System.out.println("5. Buscar cliente por correo");
            System.out.println("6. Eliminar cliente");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> setCliente();
                case 2 -> updateCliente();
                case 3 -> getClientes();
                case 4 -> {
                    System.out.print("Ingrese el ID del cliente: ");
                    int id = Integer.parseInt(sc.nextLine());
                    getClienteById(id);
                }
                case 5 -> {
                    System.out.print("Ingrese el correo del cliente: ");
                    String correo = sc.nextLine();
                    getClienteByCorreo(correo);
                }
                case 6 -> {
                    System.out.print("Ingrese el ID del cliente a eliminar: ");
                    int idEliminar = Integer.parseInt(sc.nextLine());
                    deleteCliente(idEliminar);
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    public static void setCliente() {
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

        String rol = "Cliente";

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


        System.out.print("Zona: ");
        String zona = sc.nextLine();

        System.out.print("Tipo de finca: ");
        String tipoFinca = sc.nextLine();

        System.out.print("Tamaño del terreno (entero): ");
        int tamannoTerreno = Integer.parseInt(sc.nextLine());

        Cliente cliente = new Cliente(0, nombre, primerApellido, segundoApellido, email, contrasenna,
                rol, estado, fechaNacimiento, fechaRegistro,pin, zona, tipoFinca, tamannoTerreno);

        controller.setCliente(cliente);
        System.out.println("Cliente registrado correctamente.");
    }
    private static void updateCliente() {
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

        String rol = "Cliente";

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


        System.out.print("Zona: ");
        String zona = sc.nextLine();

        System.out.print("Tipo de finca: ");
        String tipoFinca = sc.nextLine();

        System.out.print("Tamaño del terreno (entero): ");
        int tamannoTerreno = Integer.parseInt(sc.nextLine());



        Cliente cliente = new Cliente(
                id,
                nombre, primerApellido, segundoApellido, email, contrasenna,
                rol, estado, fechaNacimiento, fechaRegistro, pin,
                zona, tipoFinca, tamannoTerreno
        );

        controller.updateCliente(cliente);
        System.out.println("Cliente actualizado correctamente.");
    }



    private static void getClientes() {
        List<Cliente> clientes = controller.getClientes();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private static void getClienteById(int id) {
        Cliente cliente = controller.getClienteById(id);
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("No se encontró cliente con ID: " + id);
        }
    }

    private static void getClienteByCorreo(String correo) {
        Cliente cliente = controller.getClienteByCorreo(correo);
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("No se encontró cliente con el correo: " + correo);
        }
    }

    private static void deleteCliente(int id) {
        Cliente cliente = controller.getClienteById(id);
        if (cliente != null) {
            controller.deleteCliente(id);
            System.out.println("Cliente eliminado.");
        } else {
            System.out.println("No se encontró cliente con ID: " + id);
        }
    }


}