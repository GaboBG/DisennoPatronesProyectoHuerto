package org.example.Util;

import java.sql.Date;
import java.util.Scanner;

import static org.example.Util.ValidacionUtil.*;


public class inputUtil {

    private static final Scanner sc = new Scanner(System.in);
    public static String leerTexto() {
        while (true) {
            String texto = sc.nextLine().trim();
            if (!esTextoVacio(texto)) {
                return texto;
            }
            System.out.println("El texto no puede estar vacío.");
        }
    }

    public static int leerEntero() {
        while (true) {
            String texto = sc.nextLine().trim();
            if (esEnteroValido(texto)) {
                return Integer.parseInt(texto);
            }
            System.out.println("Debe ingresar un número entero válido.");
        }
    }

    public static String leerEstado() {
        while (true) {
            String estado = sc.nextLine().trim().toLowerCase();
            if (esEstadoValido(estado)) {
                return estado;
            }
            System.out.println("Estado inválido. Debe ser 'activo' o 'inactivo'.");
        }
    }

    public static Date leerFecha() {
        while (true) {
            String fechaStr = sc.nextLine().trim();
            if (esFechaValida(fechaStr)) {
                return Date.valueOf(fechaStr);
            }
            System.out.println("Fecha inválida. Debe tener el formato YYYY-MM-DD y ser una fecha real.");
        }
    }

    public static int leerEnteroOpcional(String mensaje, int valorActual) {
        System.out.print(mensaje + " (actual: " + valorActual + ") (dejar en blanco para no cambiar): ");
        String entrada = sc.nextLine().trim();
        if (esTextoVacio(entrada)) {
            return valorActual;
        }
        while (!esEnteroValido(entrada)) {
            System.out.println("Debe ingresar un número entero válido o dejar en blanco.");
            System.out.print(mensaje + " (actual: " + valorActual + "): ");
            entrada = sc.nextLine().trim();
            if (esTextoVacio(entrada)) {
                return valorActual;
            }
        }
        return Integer.parseInt(entrada);
    }



}
