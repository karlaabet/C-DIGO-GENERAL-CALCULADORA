package com.Calculator;
import com.Calculator.CalculatorFunctions.DecimalToBinary;
import java.util.ArrayList;
import java.util.Scanner;

public class Conversiontool{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        ArrayList<Integer> aBorrar = new ArrayList<>();
        HistorialConv historial = new HistorialConv();
        do {
            System.out.println("=============================================");
            System.out.println("           CALCULADORA DE CONVERSIÓN         ");
            System.out.println("=============================================");
            System.out.println("1. Convertir decimal a binario");
            System.out.println("2. Convertir decimal a hexadecimal");
            System.out.println("3. Convertir binario a decimal");
            System.out.println("4. Convertir hexadecimal a decimal");
            System.out.println("5. Mostrar historial de conversiones");
            System.out.println("6. Salir");
            System.out.println("=============================================");
            System.out.print("Seleccione una opción: ");

            // Validar opción del menú
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor ingrese un número válido: ");
                scanner.next().trim();
            }
            opcion = scanner.nextInt();
            historial.opciones.add(opcion);
            switch (opcion) {
                case 1:
                    // Decimal a Binario
                    System.out.print("Ingrese un número decimal: ");
                    while (!scanner.hasNextLong()) { //cambio a long
                        System.out.print("Entrada no válida. Ingrese un número entero: ");
                        scanner.next().trim();
                    }
                    long numeroDecimal = scanner.nextLong();
                    try {
                        String resultadoBinario = DecimalToBinary.convertirDecimalABinario(numeroDecimal);
                        historial.numIngresados.add(String.valueOf(numeroDecimal));
                        historial.numConvertidos.add(resultadoBinario);
                        System.out.printf("El número binario es: %s\n", resultadoBinario);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Decimal a Hexadecimal
                    System.out.print("Ingrese un número decimal: ");
                    while (!scanner.hasNextLong()) {
                        System.out.print("Entrada no válida. Ingrese un número entero: ");
                        scanner.next();
                    }
                    long numeroHex = scanner.nextLong(); //refactorizacion de long
                    try {
                        String resultadoHex = DecimalToBinary.decimalAHexRecursivo(numeroHex);
                        historial.numIngresados.add(String.valueOf(numeroHex));
                        historial.numConvertidos.add(resultadoHex);
                        System.out.printf("El número hexadecimal es: %s\n", resultadoHex);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    // Binario a Decimal
                    System.out.print("Ingrese un número binario: ");
                    scanner.nextLine(); // Limpiar buffer
                    String binario = scanner.nextLine().trim();
                    try {
                        double resultadoDecimal = DecimalToBinary.binarioADecimal(binario);
                        historial.numIngresados.add(binario);
                        historial.numConvertidos.add(Double.toString(resultadoDecimal));
                        System.out.printf("El número decimal es: %.2f\n", resultadoDecimal);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    // Hexadecimal a Decimal
                    System.out.print("Ingrese un número hexadecimal: ");
                    scanner.nextLine(); // Limpiar buffer
                    String hexadecimal = scanner.nextLine().toUpperCase().trim();
                    try {
                        double resultadoHexADecimal = DecimalToBinary.hexFlotanteADecimal(hexadecimal);
                        historial.numIngresados.add(hexadecimal);
                        historial.numConvertidos.add(Double.toString(resultadoHexADecimal));
                        System.out.printf("El número decimal es: %.6f\n", resultadoHexADecimal);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    aBorrar.add(5);
                    historial.opciones.removeAll(aBorrar);
                    historial.count = historial.numIngresados.size();
                    System.out.println("Las transformaciones realizadas de la mas reciente a la más remota son: ");
                    historial.imprimirResultados(historial.count, historial.opciones.get(historial.opciones.size()-1));
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }

            // Pausa antes de mostrar el menú nuevamente (solo si no es salir)
            if (opcion != 6) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine(); // Consumir el newline pendiente
                scanner.nextLine(); // Esperar Enter
            }

        } while (opcion != 6);

        scanner.close();
    }
}