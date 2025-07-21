package org.example.view;

import org.example.controller.ActividadController;
import org.example.model.Actividad;

import java.util.List;

import static Util.BusquedaUtil.buscarPorId;
import static Util.ValidacionUtil.esListaVacia;
import static Util.ValidacionUtil.esNulo;
import static Util.inputUtil.*;

public class ActividadView {

    private static final ActividadController actividadController = new ActividadController();

    public static void menuActividades() {
        while (true) {
            System.out.println("\n--- Gestión de Actividades ---");
            System.out.println("1. Insertar Actividad");
            System.out.println("2. Modificar Actividad");
            System.out.println("3. Eliminar Actividad");
            System.out.println("4. Consultar Actividades");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            String opcion = leerTexto();

            switch (opcion) {
                case "1" -> insertarActividad();
                case "2" -> modificarActividad();
                case "3" -> eliminarActividad();
                case "4" -> consultarActividades();
                case "5" -> { return; }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void insertarActividad() {
        try {
            System.out.print("Ingrese el nombre de la actividad: ");
            String nombre = leerTexto();

            System.out.print("Ingrese el clima ideal: ");
            String clima = leerTexto();

            System.out.print("Ingrese el tipo de suelo: ");
            String tipoSuelo = leerTexto();

            System.out.print("Ingrese la frecuencia de riego: ");
            String frecuencia = leerTexto();

            System.out.print("Ingrese una descripción: ");
            String descripcion = leerTexto();

            System.out.print("Ingrese el estado de la actividad (activo/inactivo): ");
            String estadoTexto = leerEstado();
            boolean estado = estadoTexto.equalsIgnoreCase("activo");

            Actividad nuevaActividad = new Actividad(nombre, clima, tipoSuelo, frecuencia, descripcion, estado);
            actividadController.setActividad(nuevaActividad);
            System.out.println("Actividad insertada exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al insertar la actividad: " + e.getMessage());
        }
    }

    private static void modificarActividad() {
        try {
            System.out.print("Ingrese ID de la actividad a modificar: ");
            int id = leerEntero();

            List<Actividad> actividades = actividadController.getActividad();
            Actividad actividadModificar = buscarPorId(actividades, id, Actividad::getIdActividad, "actividad");
            if (esNulo(actividadModificar)) return;

            System.out.print("Nuevo nombre (actual: " + actividadModificar.getNombre() + "): ");
            String nombre = leerTexto();

            System.out.print("Nuevo clima (actual: " + actividadModificar.getClima() + "): ");
            String clima = leerTexto();

            System.out.print("Nuevo tipo de suelo (actual: " + actividadModificar.getTipoSuelo() + "): ");
            String tipoSuelo = leerTexto();

            System.out.print("Nueva frecuencia de riego (actual: " + actividadModificar.getFrecuenciaRiego() + "): ");
            String frecuencia = leerTexto();

            System.out.print("Nueva descripción (actual: " + actividadModificar.getDescripcion() + "): ");
            String descripcion = leerTexto();

            System.out.print("Nuevo estado (actual: " + (actividadModificar.isEstado() ? "activo" : "inactivo") + "): ");
            String estadoTexto = leerEstado();
            boolean estado = estadoTexto.equalsIgnoreCase("activo");

            Actividad actividadActualizada = new Actividad(id, nombre, clima, tipoSuelo, frecuencia, descripcion, estado);
            actividadController.updateActividad(actividadActualizada);
            System.out.println("Actividad modificada exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al modificar la actividad: " + e.getMessage());
        }
    }

    private static void eliminarActividad() {
        try {
            System.out.print("Ingrese ID de la actividad a eliminar: ");
            int id = leerEntero();

            List<Actividad> actividades = actividadController.getActividad();
            Actividad eliminarActividad = buscarPorId(actividades, id, Actividad::getIdActividad, "actividad");
            if (esNulo(eliminarActividad)) return;

            actividadController.deleteActividad(id);
            System.out.println("Actividad eliminada exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al eliminar la actividad: " + e.getMessage());
        }
    }

    private static void consultarActividades() {
        try {
            List<Actividad> lista = actividadController.getActividad();

            if (esListaVacia(lista)) {
                System.out.println("No hay actividades registradas.");
                return;
            }

            System.out.println("\n--- Lista de Actividades ---");
            for (Actividad a : lista) {
                System.out.println("ID: " + a.getIdActividad()
                        + " | Nombre: " + a.getNombre()
                        + " | Clima: " + a.getClima()
                        + " | Suelo: " + a.getTipoSuelo()
                        + " | Frecuencia: " + a.getFrecuenciaRiego()
                        + " | Descripción: " + a.getDescripcion()
                        + " | Estado: " + (a.isEstado() ? "activo" : "inactivo"));
            }
        } catch (Exception e) {
            System.out.println("Error al consultar las actividades: " + e.getMessage());
        }
    }
}
