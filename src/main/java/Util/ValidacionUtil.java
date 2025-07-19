package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

//Esta clase fue añadida para centralizar y volver reutilizables las validaciones utilizadas en el view principal
public class ValidacionUtil {

    public static boolean esEnteroValido(String valor) {
        return valor != null && valor.matches("\\d+");
    }

    public static boolean esTextoVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    public static boolean esEstadoValido(String estado) {
        return estado != null &&
                (estado.equalsIgnoreCase("activo") || estado.equalsIgnoreCase("inactivo"));
    }

    public static boolean esListaVacia(List<?> lista) {
        return lista == null || lista.isEmpty();
    }

    public static boolean esFechaValida(String fechaStr) {
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Para que no acepte cosas como 2025-02-30
        try {
            sdf.parse(fechaStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean esNulo(Object obj) {
        return obj == null;
    }


}
