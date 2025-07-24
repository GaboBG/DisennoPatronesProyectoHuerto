package org.example.view;

import org.example.controller.RecomendacionesController;
import org.example.model.Recomendaciones;

import java.time.LocalDate;
import java.util.List;

import static org.example.Util.BusquedaUtil.buscarPorId;
import static org.example.Util.ValidacionUtil.esListaVacia;
import static org.example.Util.ValidacionUtil.esNulo;
import static org.example.Util.inputUtil.*;

public class RecomendacionesView {

    private static final RecomendacionesController controller = new RecomendacionesController();

    public static void menuRecomendaciones() {
        while (true) {
            System.out.println("\n--- Gestión de Recomendaciones ---");
            System.out.println("1. Insertar Recomendación");
            System.out.println("2. Modificar Recomendación");
            System.out.println("3. Eliminar Recomendación");
            System.out.println("4. Consultar Recomendaciones");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            String opcion = leerTexto();

            switch (opcion) {
                case "1" -> insertarRecomendacion();
                case "2" -> modificarRecomendacion();
                case "3" -> eliminarRecomendacion();
                case "4" -> consultarRecomendaciones();
                case "5" -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void insertarRecomendacion() {
        try {
            System.out.print("Ingrese ID del cultivo: ");
            int cultivoId = leerEntero();

            System.out.print("Ingrese ID del asesor-cliente: ");
            int idAsesorCliente = leerEntero();

            System.out.print("Ingrese fecha de inicio (YYYY-MM-DD): ");
            LocalDate fechaInicio = leerFecha();

            System.out.print("Ingrese fecha final (YYYY-MM-DD): ");
            LocalDate fechaFinal = leerFecha();

            System.out.print("Ingrese observaciones: ");
            String observaciones = leerTexto();

            Recomendaciones nueva = new Recomendaciones(cultivoId, idAsesorCliente, fechaInicio, fechaFinal, observaciones);
            controller.setRecomendacion(nueva);
            System.out.println("Recomendación insertada exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al insertar la recomendación: " + e.getMessage());
        }
    }

    private static void modificarRecomendacion() {
        try {
            System.out.print("Ingrese ID de la recomendación a modificar: ");
            int id = leerEntero();

            List<Recomendaciones> lista = controller.getRecomendaciones();
            Recomendaciones r = buscarPorId(lista, id, Recomendaciones::getId, "recomendación");
            if (esNulo(r)) return;

            System.out.print("Nuevo ID de cultivo (actual: " + r.getCultivoId() + "): ");
            int cultivoId = leerEntero();

            System.out.print("Nuevo ID asesor-cliente (actual: " + r.getIdAsesorCliente() + "): ");
            int idAsesorCliente = leerEntero();

            System.out.print("Nueva fecha de inicio (actual: " + r.getFechaInicio() + "): ");
            LocalDate fechaInicio = leerFecha();

            System.out.print("Nueva fecha final (actual: " + r.getFechaFinal() + "): ");
            LocalDate fechaFinal = leerFecha();

            System.out.print("Nuevas observaciones (actual: " + r.getObservaciones() + "): ");
            String observaciones = leerTexto();

            Recomendaciones actualizada = new Recomendaciones(id, cultivoId, idAsesorCliente, fechaInicio, fechaFinal, observaciones);
            controller.updateRecomendacion(actualizada);
            System.out.println("Recomendación modificada exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al modificar la recomendación: " + e.getMessage());
        }
    }

    private static void eliminarRecomendacion() {
        try {
            System.out.print("Ingrese ID de la recomendación a eliminar: ");
            int id = leerEntero();

            List<Recomendaciones> lista = controller.getRecomendaciones();
            Recomendaciones r = buscarPorId(lista, id, Recomendaciones::getId, "recomendación");
            if (esNulo(r)) return;

            controller.deleteRecomendacion(id);
            System.out.println("Recomendación eliminada exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al eliminar la recomendación: " + e.getMessage());
        }
    }

    private static void consultarRecomendaciones() {
        try {
            List<Recomendaciones> lista = controller.getRecomendaciones();

            if (esListaVacia(lista)) {
                System.out.println("No hay recomendaciones registradas.");
                return;
            }

            System.out.println("\n--- Lista de Recomendaciones ---");
            for (Recomendaciones r : lista) {
                System.out.println("ID: " + r.getId()
                        + " | Cultivo ID: " + r.getCultivoId()
                        + " | Asesor-Cliente ID: " + r.getIdAsesorCliente()
                        + " | Fecha Inicio: " + r.getFechaInicio()
                        + " | Fecha Final: " + r.getFechaFinal()
                        + " | Observaciones: " + r.getObservaciones());
            }
        } catch (Exception e) {
            System.out.println("Error al consultar las recomendaciones: " + e.getMessage());
        }
    }
}