import com.Calculator.CalculatorFunctions.DecimalToBinary;
import java.util.ArrayList;
import java.util.Scanner;

public class Conversiontool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        ArrayList<Integer> aBorrar = new ArrayList<>();
        HistorialConv historial = new HistorialConv();

        do {
            System.out.println("=============================================");
            System.out.println(" CALCULADORA DE CONVERSIÓN ");
            System.out.println("=============================================");
            System.out.println("1. Convertir decimal a binario");
            System.out.println("2. Convertir decimal a hexadecimal");
            System.out.println("3. Convertir binario a decimal");
            System.out.println("4. Convertir hexadecimal a decimal");
            System.out.println("5. Mostrar historial de conversiones");
            System.out.println("6. Salir");
            System.out.println("=============================================");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Por favor ingrese un número válido: ");
                scanner.next();
            }

            opcion = scanner.nextInt();
            scanner.nextLine();


            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un número decimal: ");
                    String inputDecimalBin = scanner.nextLine().replaceAll("\\s+", "").replace(',', '.');
                    try {
                        double numeroDecimal = Double.parseDouble(inputDecimalBin);
                        String resultadoBinario = DecimalToBinary.convertirDecimalABinario(numeroDecimal);


                        historial.numIngresados.add(String.valueOf(numeroDecimal));
                        historial.numConvertidos.add(resultadoBinario);
                        historial.opciones.add(opcion);

                        System.out.printf("El número binario es: %s\n", resultadoBinario);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Formato de número decimal no válido.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    if (opcion != 6) {
                        System.out.println("\nPresione Enter para continuar...");
                        scanner.nextLine();
                    }
                    break;
                case 2:
                    System.out.print("Ingrese un número decimal: ");
                    String inputDecimalHex = scanner.nextLine().replaceAll("\\s+", "").replace(',', '.');
                    try {
                        double numeroDecimal = Double.parseDouble(inputDecimalHex);
                        String resultadoHex = DecimalToBinary.decimalAHexRecursivo(numeroDecimal);


                        historial.numIngresados.add(String.valueOf(numeroDecimal));
                        historial.numConvertidos.add(resultadoHex);
                        historial.opciones.add(opcion);

                        System.out.printf("El número hexadecimal es: %s\n", resultadoHex);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Formato de número decimal no válido.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    if (opcion != 6) {
                        System.out.println("\nPresione Enter para continuar...");
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    System.out.print("Ingrese un número binario: ");
                    String binario = scanner.nextLine();
                    try {
                        double resultadoDecimal = DecimalToBinary.binarioADecimal(binario);


                        historial.numIngresados.add(binario);
                        historial.numConvertidos.add(Double.toString(resultadoDecimal));
                        historial.opciones.add(opcion);

                        System.out.printf("El número decimal es: %.6f\n", resultadoDecimal);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    if (opcion != 6) {
                        System.out.println("\nPresione Enter para continuar...");
                        scanner.nextLine();
                    }
                    break;

                case 4:
                    System.out.print("Ingrese un número hexadecimal: ");
                    String hexadecimal = scanner.nextLine().toUpperCase();
                    try {
                        double resultadoHexADecimal = DecimalToBinary.hexFlotanteADecimal(hexadecimal);

                        historial.numIngresados.add(hexadecimal);
                        historial.numConvertidos.add(Double.toString(resultadoHexADecimal));
                        historial.opciones.add(opcion);

                        System.out.printf("El número decimal es: %.6f\n", resultadoHexADecimal);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    if (opcion != 6) {
                        System.out.println("\nPresione Enter para continuar...");
                        scanner.nextLine();
                    }
                    break;

                case 5:
                    if (historial.numIngresados.isEmpty()) {
                        System.out.println("No se han realizado conversiones todavía.");
                    } else {
                        System.out.println("Las transformaciones realizadas de la más reciente a la más remota son: ");
                        historial.imprimirHistorialSimple();
                    }

                    if (opcion != 6) {
                        System.out.println("\nPresione Enter para continuar...");
                        scanner.nextLine();
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");

                    System.out.println("\nPresione Enter para continuar...");
                    scanner.nextLine();
                    break;
            }

        } while (opcion != 6);

        scanner.close();
        scanner.nextLine();
    }
}