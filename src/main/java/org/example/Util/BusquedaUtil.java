package org.example.Util;

import java.util.List;
import java.util.function.Function;

import static org.example.Util.ValidacionUtil.esListaVacia;

public class BusquedaUtil {

        public static <T> T buscarPorId(List<T> lista, int id, Function<T, Integer> getId, String nombreEntidad) {
            if (esListaVacia(lista)) {
                System.out.println("No hay " + nombreEntidad + " registrados.");
                return null;
            }

            for (T item : lista) {
                if (getId.apply(item) == id) {
                    return item;
                }
            }

            System.out.println("No se encontró " + nombreEntidad + " con ese ID.");
            return null;
        }


}
