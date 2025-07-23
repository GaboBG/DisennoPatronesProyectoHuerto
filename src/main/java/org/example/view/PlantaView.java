package org.example.view;

import org.example.controller.PlantaController;
import org.example.model.Planta;

import java.util.List;

import static Util.BusquedaUtil.buscarPorId;
import static Util.ValidacionUtil.esListaVacia;
import static Util.ValidacionUtil.esNulo;
import static Util.inputUtil.*;


public class PlantaView {

    private static final PlantaController plantaController = new PlantaController();

    public static void menuPlantas() {
        while (true) {
            System.out.println("\n--- Gestión de Plantas ---");
            System.out.println("1. Insertar Planta");
            System.out.println("2. Modificar Planta");
            System.out.println("3. Eliminar Planta");
            System.out.println("4. Consultar Plantas");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            String opcion = leerTexto();

            switch (opcion) {
                case "1" -> insertarPlanta();
                case "2" -> modificarPlanta();
                case "3" -> eliminarPlanta();
                case "4" -> consultarPlantas();
                case "5" -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void insertarPlanta() {
        try {
            System.out.print("Ingrese el nombre de la planta: ");
            String nombre = leerTexto();

            System.out.print("Ingrese el clima ideal: ");
            String clima = leerTexto();

            System.out.print("Ingrese la frecuencia de riego: ");
            String frecuencia = leerTexto();

            System.out.print("Ingrese una descripción: ");
            String descripcion = leerTexto();

            System.out.print("Ingrese el estado de la planta (activo/inactivo): ");
            String estadoTexto = leerEstado();
            boolean estado = estadoTexto.equalsIgnoreCase("activo");

            Planta nuevaPlanta = new Planta(nombre, clima, frecuencia, descripcion, estado);
            plantaController.setPlanta(nuevaPlanta);
            System.out.println("Planta insertada exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al insertar la planta: " + e.getMessage());
        }
    }

    private static void modificarPlanta() {
        try {
            System.out.print("Ingrese ID de la planta a modificar: ");
            int id = leerEntero();

            List<Planta> plantas = plantaController.getPlantas();
            Planta plantaModificar = buscarPorId(plantas, id, Planta::getIdPlanta, "planta");
            if (esNulo(plantaModificar)) return;

            System.out.print("Nuevo nombre (actual: " + plantaModificar.getNombre() + "): ");
            String nombre = leerTexto();

            System.out.print("Nuevo clima (actual: " + plantaModificar.getClima() + "): ");
            String clima = leerTexto();

            System.out.print("Nueva frecuencia de riego (actual: " + plantaModificar.getFrecuenciaRiego() + "): ");
            String frecuencia = leerTexto();

            System.out.print("Nueva descripción (actual: " + plantaModificar.getDescripcion() + "): ");
            String descripcion = leerTexto();

            System.out.print("Nuevo estado (actual: " + (plantaModificar.isEstado() ? "activo" : "inactivo") + "): ");
            String estadoTexto = leerEstado();
            boolean estado = estadoTexto.equalsIgnoreCase("activo");

            Planta plantaActualizada = new Planta(id, nombre, clima, frecuencia, descripcion, estado);
            plantaController.updatePlanta(plantaActualizada);
            System.out.println("Planta modificada exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al modificar la planta: " + e.getMessage());
        }
    }

    private static void eliminarPlanta() {
        try {
            System.out.print("Ingrese ID de la planta a eliminar: ");
            int id = leerEntero();

            List<Planta> plantas = plantaController.getPlantas();
            Planta eliminarPlanta = buscarPorId(plantas, id, Planta::getIdPlanta, "planta");
            if (esNulo(eliminarPlanta)) return;

            plantaController.deletePlanta(id);
            System.out.println("Planta eliminada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar la planta: " + e.getMessage());
        }
    }

    private static void consultarPlantas() {
        try {
            List<Planta> lista = plantaController.getPlantas();

            if (esListaVacia(lista)) {
                System.out.println("No hay plantas registradas.");
                return;
            }

            System.out.println("\n--- Lista de Plantas ---");
            for (Planta p : lista) {
                System.out.println("ID: " + p.getIdPlanta()
                        + " | Nombre: " + p.getNombre()
                        + " | Clima: " + p.getClima()
                        + " | Frecuencia: " + p.getFrecuenciaRiego()
                        + " | Descripción: " + p.getDescripcion()
                        + " | Estado: " + (p.isEstado() ? "activo" : "inactivo"));
            }
        } catch (Exception e) {
            System.out.println("Error al consultar las plantas: " + e.getMessage());
        }
    }
}
