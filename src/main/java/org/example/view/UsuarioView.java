package org.example.view;

import controller.UsuarioController;
import model.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UsuarioView {
    private static UsuarioController controller = new UsuarioController();
    private  static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n========= MENÚ DE USUARIOS =========");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Actualizar usuario");
            System.out.println("3. Listar todos los usuarios");
            System.out.println("4. Buscar usuario por ID");
            System.out.println("5. Buscar usuario por correo");
            System.out.println("6. Eliminar usuario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    setUsuario();
                    break;
                case 2:
                    updateUsuario();
                    break;
                case 3:
                    getUsuario();
                    break;
                case 4:
                    System.out.print("Ingrese el ID del usuario a buscar: ");
                    int id = Integer.parseInt(sc.nextLine());
                    getUsuarioById(id);
                    break;
                case 5:
                    System.out.print("Ingrese el correo del usuario a buscar: ");
                    String correo = sc.nextLine();
                    getUsuarioByCorreo(correo);
                    break;
                case 6:
                    System.out.print("Ingrese el ID del usuario a eliminar: ");
                    int idEliminar = Integer.parseInt(sc.nextLine());
                    deleteUsuario(idEliminar);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 0);
    }


    private static void setUsuario() {
        Scanner sc = new Scanner(System.in);
        int id = 0;

        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese primer apellido del usuario: ");
        String primerApellido = sc.nextLine();

        System.out.print("Ingrese segundo apellido del usuario: ");
        String segundoApellido = sc.nextLine();

        System.out.print("Ingrese email del usuario: ");
        String email = sc.nextLine();

        System.out.print("Ingrese contraseña del usuario: ");
        String contrasenna = sc.nextLine();

        System.out.print("Ingrese rol del usuario: ");
        String rol = sc.nextLine();

        System.out.print("Ingrese estado del usuario (true/false): ");
        boolean estado = Boolean.parseBoolean(sc.nextLine());

        System.out.print("Ingrese fecha de nacimiento del usuario (formato yyyy-MM-dd): ");
        LocalDate fechaNacimiento = null;
        try {
            fechaNacimiento = LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto. Debe ser yyyy-MM-dd");
            return;
        }

        LocalDate fechaRegistro = LocalDate.now();

        Usuario usuario = new Usuario(
                id, nombre, primerApellido, segundoApellido,
                email, contrasenna, rol, estado,
                fechaNacimiento, fechaRegistro
        );

        controller.setUsuario(usuario);
        System.out.println("Usuario registrado correctamente.");
    }

    private static void updateUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el ID del usuario que desea actualizar: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese primer apellido del usuario: ");
        String primerApellido = sc.nextLine();

        System.out.print("Ingrese segundo apellido del usuario: ");
        String segundoApellido = sc.nextLine();

        System.out.print("Ingrese email del usuario: ");
        String email = sc.nextLine();

        System.out.print("Ingrese contraseña del usuario: ");
        String contrasenna = sc.nextLine();

        System.out.print("Ingrese rol del usuario: ");
        String rol = sc.nextLine();

        System.out.print("Ingrese estado del usuario (true/false): ");
        boolean estado = Boolean.parseBoolean(sc.nextLine());

        System.out.print("Ingrese fecha de nacimiento del usuario (formato yyyy-MM-dd): ");
        LocalDate fechaNacimiento = null;
        try {
            fechaNacimiento = LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto. Debe ser yyyy-MM-dd");
            return;
        }

        LocalDate fechaRegistro = LocalDate.now();

        Usuario usuario = new Usuario(
                id, nombre, primerApellido, segundoApellido,
                email, contrasenna, rol, estado,
                fechaNacimiento, fechaRegistro
        );

        controller.updateUsuario(usuario);
        System.out.println("Usuario actualizado correctamente.");
    }

    private static void getUsuario(){
        List <Usuario> usuarios = controller.getUsuarios();

        for (Usuario usuario : usuarios){
            System.out.println("id: "+ usuario.getId()+ "\tNombre: "+usuario.getNombre()+"\tPrimer apellido: "+usuario.getPrimerApellido()+"\tSegundo apellido: "+usuario.getSegundoApellido()+"\tEmail: "+usuario.getEmail()+"\tContraseña: "+usuario.getContrasenna()+"\tRol: "+usuario.getRol()+"\tEstado: "+usuario.isEstado()+"\tFecha de nacimiento: " +usuario.getFechaNacimiento()+ "\tFecha de Registro: "+usuario.getFechaRegistro());
        }
    }

    private static void getUsuarioById(int id) {
        List<Usuario> usuarios = controller.getUsuarios();
        boolean encontrado = false;

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                System.out.println("ID: " + usuario.getId()
                        + "\tNombre: " + usuario.getNombre()
                        + "\tPrimer Apellido: " + usuario.getPrimerApellido()
                        + "\tSegundo Apellido: " + usuario.getSegundoApellido()
                        + "\tEmail: " + usuario.getEmail()
                        + "\tContraseña: " + usuario.getContrasenna()
                        + "\tRol: " + usuario.getRol()
                        + "\tEstado: " + usuario.isEstado()
                        + "\tFecha de Nacimiento: " + usuario.getFechaNacimiento()
                        + "\tFecha de Registro: " + usuario.getFechaRegistro());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró un usuario con ID: " + id);
        }
    }

    private static void getUsuarioByCorreo(String correo) {
        List <Usuario> usuarios = controller.getUsuarios();
        boolean encontrado = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(correo)) {
                System.out.println("ID: " + usuario.getId()
                        + "\tNombre: " + usuario.getNombre()
                        + "\tPrimer Apellido: " + usuario.getPrimerApellido()
                        + "\tSegundo Apellido: " + usuario.getSegundoApellido()
                        + "\tEmail: " + usuario.getEmail()
                        + "\tContraseña: " + usuario.getContrasenna()
                        + "\tRol: " + usuario.getRol()
                        + "\tEstado: " + usuario.isEstado()
                        + "\tFecha de Nacimiento: " + usuario.getFechaNacimiento()
                        + "\tFecha de Registro: " + usuario.getFechaRegistro());
                encontrado = true;
                break;
            }
        }
         if (!encontrado) {
             System.out.println("No se encontró un usuario con el email: " + correo);
         }
     }

     static void deleteUsuario(int id){
        List <Usuario> usuarios = controller.getUsuarios();
        boolean encontrado = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                controller.deleteUsuario(id);
                encontrado = true;
                break;
            }
        }
         if (!encontrado) {
             System.out.println("No se encontró un usuario con ID: " + id);
         }
     }


}
