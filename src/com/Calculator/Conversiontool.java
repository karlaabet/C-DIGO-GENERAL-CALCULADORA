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

            // CORRECCIÓN: Limpiar el buffer después de leer el int
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Decimal a Binario (Soporta flotantes/comas)
                    System.out.print("Ingrese un número decimal (puede usar . o ,): ");
                    String inputDecimalBin = scanner.nextLine().trim().replace(',', '.');
                    try {
                        String resultadoBinario = DecimalToBinary.convertirDecimalABinarioString(inputDecimalBin);
                        historial.numIngresados.add(inputDecimalBin);
                        historial.numConvertidos.add(resultadoBinario);
                        System.out.printf("El número binario es: %s\n", resultadoBinario);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Decimal a Hexadecimal (Soporta flotantes/comas)
                    System.out.print("Ingrese un número decimal (puede usar . o ,): ");
                    String inputDecimalHex = scanner.nextLine().trim().replace(',', '.');
                    try {
                        String resultadoHex = DecimalToBinary.decimalAHexString(inputDecimalHex);
                        historial.numIngresados.add(inputDecimalHex);
                        historial.numConvertidos.add(resultadoHex);
                        System.out.printf("El número hexadecimal es: %s\n", resultadoHex);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    // Binario a Decimal
                    System.out.print("Ingrese un número binario (puede usar . o ,): ");
                    String binario = scanner.nextLine().trim().replace(',', '.');
                    try {
                        double resultadoDecimal = DecimalToBinary.binarioADecimal(binario);
                        historial.numIngresados.add(binario);
                        historial.numConvertidos.add(Double.toString(resultadoDecimal));
                        System.out.printf("El número decimal es: %.6f\n", resultadoDecimal);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    // Hexadecimal a Decimal
                    System.out.print("Ingrese un número hexadecimal (puede usar . o ,): ");
                    String hexadecimal = scanner.nextLine().toUpperCase().trim().replace(',', '.');
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
                    // CORRECCIÓN: Evitar IndexOutOfBoundsException
                    historial.imprimirResultados(historial.count,
                            historial.opciones.isEmpty() ? 0 : historial.opciones.get(historial.opciones.size() - 1));
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
                // CORRECCIÓN: Se llama UNA sola vez para esperar el Enter (elimina la doble pausa)
                scanner.nextLine();
            }

        } while (opcion != 6);

        scanner.close();
    }
}