package org.example.view;

import org.example.controller.ActividadController;
import org.example.model.Actividad;

import java.time.LocalDate;
import java.util.List;

import static org.example.Util.BusquedaUtil.buscarPorId;
import static org.example.Util.ValidacionUtil.esListaVacia;
import static org.example.Util.ValidacionUtil.esNulo;
import static org.example.Util.inputUtil.*;

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
                case "5" -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void insertarActividad() {
        try {
            System.out.print("Ingrese ID del cultivo: ");
            int idCultivo = leerEntero();

            System.out.print("Ingrese el nombre de la actividad: ");
            String nombre = leerTexto();

            System.out.print("Ingrese el tipo de actividad: ");
            String tipoActividad = leerTexto();

            System.out.print("Ingrese la fecha programada (YYYY-MM-DD): ");
            LocalDate fecha = leerFecha();

            System.out.print("Ingrese una descripción: ");
            String descripcion = leerTexto();

            System.out.print("¿La actividad ya fue realizada? (si/no): ");
            String estadoTexto = leerTexto();
            boolean realizada = estadoTexto.equalsIgnoreCase("si");

            Actividad nuevaActividad = new Actividad(idCultivo, nombre, tipoActividad, fecha, descripcion, realizada);
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

            System.out.print("Nuevo ID de cultivo (actual: " + actividadModificar.getIdCultivo() + "): ");
            int idCultivo = leerEntero();

            System.out.print("Nuevo nombre (actual: " + actividadModificar.getNombre() + "): ");
            String nombre = leerTexto();

            System.out.print("Nuevo tipo de actividad (actual: " + actividadModificar.getTipoActividad() + "): ");
            String tipoActividad = leerTexto();

            System.out.print("Nueva fecha programada (actual: " + actividadModificar.getFechaProgramada() + "): ");
            LocalDate fecha = leerFecha();

            System.out.print("Nueva descripción (actual: " + actividadModificar.getDescripcion() + "): ");
            String descripcion = leerTexto();

            System.out.print("¿La actividad ya fue realizada? (actual: " + (actividadModificar.isRealizada() ? "sí" : "no") + "): ");
            String estadoTexto = leerTexto();
            boolean realizada = estadoTexto.equalsIgnoreCase("si");

            Actividad actividadActualizada = new Actividad(id, idCultivo, nombre, tipoActividad, fecha, descripcion, realizada);
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
                        + " | Cultivo ID: " + a.getIdCultivo()
                        + " | Nombre: " + a.getNombre()
                        + " | Tipo: " + a.getTipoActividad()
                        + " | Fecha: " + a.getFechaProgramada()
                        + " | Descripción: " + a.getDescripcion()
                        + " | Realizada: " + (a.isRealizada() ? "sí" : "no"));
            }
        } catch (Exception e) {
            System.out.println("Error al consultar las actividades: " + e.getMessage());
        }
    }
}
