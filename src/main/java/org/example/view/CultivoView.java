package org.example.view;

import org.example.controller.CultivoController;
import org.example.model.Cultivo;

import java.time.LocalDate;
import java.util.List;

import static org.example.Util.BusquedaUtil.buscarPorId;
import static org.example.Util.ValidacionUtil.esListaVacia;
import static org.example.Util.ValidacionUtil.esNulo;
import static org.example.Util.inputUtil.*;

public class CultivoView {

    private static final CultivoController cultivoController = new CultivoController();

    public static void menuCultivos() {
        while (true) {
            System.out.println("\n--- Gestión de Cultivos ---");
            System.out.println("1. Insertar Cultivo");
            System.out.println("2. Modificar Cultivo");
            System.out.println("3. Eliminar Cultivo");
            System.out.println("4. Consultar Cultivos");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            String opcion = leerTexto();

            switch (opcion) {
                case "1" -> insertarCultivo();
                case "2" -> modificarCultivo();
                case "3" -> eliminarCultivo();
                case "4" -> consultarCultivos();
                case "5" -> { return; }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void insertarCultivo() {
        try {
            System.out.print("Ingrese el ID del cliente: ");
            int idCliente = leerEntero();

            System.out.print("Ingrese el ID de la planta: ");
            int idPlanta = leerEntero();

            System.out.print("Ingrese el nombre del cultivo: ");
            String nombre = leerTexto();

            System.out.print("Ingrese la ubicación del cultivo: ");
            String ubicacion = leerTexto();

            System.out.print("Ingrese fecha de siembra (YYYY-MM-DD): ");
            LocalDate fechaSiembra = leerFecha();

            System.out.print("Ingrese fecha de cosecha (YYYY-MM-DD): ");
            LocalDate fechaCosecha = leerFecha();

            System.out.print("Ingrese el estado del cultivo (activo/inactivo): ");
            String estado = leerEstado();

            Cultivo cultivo = new Cultivo(idCliente, idPlanta, nombre, ubicacion, fechaSiembra, fechaCosecha, estado);
            cultivoController.setCultivo(cultivo);
            System.out.println("Cultivo insertado exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al insertar el cultivo: " + e.getMessage());
        }
    }

    private static void modificarCultivo() {
        try {
            System.out.print("Ingrese ID del cultivo a modificar: ");
            int id = leerEntero();

            List<Cultivo> cultivos = cultivoController.getCultivo();
            Cultivo cultivo = buscarPorId(cultivos, id, Cultivo::getIdCultivo, "cultivo");
            if (esNulo(cultivo)) return;

            System.out.print("Nuevo ID de cliente (actual: " + cultivo.getIdCliente() + "): ");
            int idCliente = leerEntero();

            System.out.print("Nuevo ID de planta (actual: " + cultivo.getIdPlanta() + "): ");
            int idPlanta = leerEntero();

            System.out.print("Nuevo nombre (actual: " + cultivo.getNombre() + "): ");
            String nombre = leerTexto();

            System.out.print("Nueva ubicación (actual: " + cultivo.getUbicacion() + "): ");
            String ubicacion = leerTexto();

            System.out.print("Nueva fecha de siembra (actual: " + cultivo.getFechaSiembra() + ") [YYYY-MM-DD]: ");
            LocalDate fechaSiembra = leerFecha();

            System.out.print("Nueva fecha de cosecha (actual: " + cultivo.getFechaCosecha() + ") [YYYY-MM-DD]: ");
            LocalDate fechaCosecha = leerFecha();

            System.out.print("Nuevo estado (actual: " + cultivo.getEstado() + "): ");
            String estado = leerEstado();

            Cultivo cultivoActualizado = new Cultivo(id, idCliente, idPlanta, nombre, ubicacion, fechaSiembra, fechaCosecha, estado);
            cultivoController.updateCultivo(cultivoActualizado);
            System.out.println("Cultivo modificado exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al modificar el cultivo: " + e.getMessage());
        }
    }

    private static void eliminarCultivo() {
        try {
            System.out.print("Ingrese ID del cultivo a eliminar: ");
            int id = leerEntero();

            List<Cultivo> cultivos = cultivoController.getCultivo();
            Cultivo cultivo = buscarPorId(cultivos, id, Cultivo::getIdCultivo, "cultivo");
            if (esNulo(cultivo)) return;

            cultivoController.deleteCultivo(id);
            System.out.println("Cultivo eliminado exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al eliminar el cultivo: " + e.getMessage());
        }
    }

    private static void consultarCultivos() {
        try {
            List<Cultivo> lista = cultivoController.getCultivo();

            if (esListaVacia(lista)) {
                System.out.println("No hay cultivos registrados.");
                return;
            }

            System.out.println("\n--- Lista de Cultivos ---");
            for (Cultivo c : lista) {
                System.out.println("ID: " + c.getIdCultivo()
                        + " | Cliente ID: " + c.getIdCliente()
                        + " | Planta ID: " + c.getIdPlanta()
                        + " | Nombre: " + c.getNombre()
                        + " | Ubicación: " + c.getUbicacion()
                        + " | Fecha Siembra: " + c.getFechaSiembra()
                        + " | Fecha Cosecha: " + c.getFechaCosecha()
                        + " | Estado: " + c.getEstado());
            }

        } catch (Exception e) {
            System.out.println("Error al consultar los cultivos: " + e.getMessage());
        }
    }
}
