package com.Calculator.CalculatorFunctions;
import java.util.Scanner;
public class DecimalToBinary {

    private static final String HEX_REGEX = "^[0-9A-F]+(\\.[0-9A-F]+)?$";

    private DecimalToBinary() {
    }

    // ========== CONVERSIÓN DECIMAL A BINARIO ==========
    public static String convertirDecimalABinario(double decimal) {
        validarNumeroPositivo(decimal);
        return convertirDecimalBinarioCompleto(decimal);
    }

    private static String convertirDecimalBinarioCompleto(double decimal) {
        if (decimal == 0) {
            return "0";
        }


        long parteEntera = (long) decimal;
        double parteFraccionaria = decimal - parteEntera;


        String binarioEntero = convertirParteEntera(parteEntera);


        String binarioFraccionario = convertirParteFraccionaria(parteFraccionaria);

        if (binarioFraccionario.isEmpty()) {
            return binarioEntero;
        } else {
            return binarioEntero + "." + binarioFraccionario;
        }
    }

    private static String convertirParteEntera(long parteEntera) {
        if (parteEntera == 0) {
            return "0";
        }

        StringBuilder binario = new StringBuilder();
        while (parteEntera > 0) {
            long residuo = parteEntera % 2;
            binario.insert(0, residuo);
            parteEntera /= 2;
        }
        return binario.toString();
    }

    private static String convertirParteFraccionaria(double parteFraccionaria) {
        if (parteFraccionaria == 0) {
            return "";
        }

        StringBuilder binario = new StringBuilder();
        int precision = 20; // Precisión máxima para evitar bucles infinitos
        double fraccion = parteFraccionaria;

        while (fraccion > 0 && binario.length() < precision) {
            fraccion *= 2;
            if (fraccion >= 1) {
                binario.append("1");
                fraccion -= 1;
            } else {
                binario.append("0");
            }
        }

        return binario.toString();
    }

    // ========== CONVERSIÓN DECIMAL A HEXADECIMAL ==========

    // Método 1: Conversión recursiva (más elegante) - Ahora con soporte para decimales
    public static String decimalAHexRecursivo(double decimal) {
        validarNumeroPositivo(decimal);
        if (decimal == 0) {
            return "0";
        }
        return convertirDecimalHexCompleto(decimal);
    }

    private static String convertirDecimalHexCompleto(double decimal) {

        long parteEntera = (long) decimal;
        double parteFraccionaria = decimal - parteEntera;


        String hexEntero = divisionRecursiva(parteEntera);
        if (hexEntero.isEmpty()) {
            hexEntero = "0";
        }


        String hexFraccionario = convertirParteFraccionariaHex(parteFraccionaria);

        if (hexFraccionario.isEmpty()) {
            return hexEntero;
        } else {
            return hexEntero + "." + hexFraccionario;
        }
    }

    private static String divisionRecursiva(long numero) {
        if (numero == 0) {
            return "";
        } else {
            long resto = numero % 16;
            String digitoHexadecimal;
            if (resto < 10) {
                digitoHexadecimal = String.valueOf(resto);
            } else {
                digitoHexadecimal = String.valueOf((char) (65 + resto - 10));
            }
            String resultadoParcial = divisionRecursiva(numero / 16);
            return resultadoParcial + digitoHexadecimal;
        }
    }

    private static String convertirParteFraccionariaHex(double parteFraccionaria) {
        if (parteFraccionaria == 0) {
            return "";
        }

        StringBuilder hexadecimal = new StringBuilder();
        int precision = 10; // Precisión máxima
        double fraccion = parteFraccionaria;

        while (fraccion > 0 && hexadecimal.length() < precision) {
            fraccion *= 16;
            int digit = (int) fraccion;
            if (digit < 10) {
                hexadecimal.append(digit);
            } else {
                hexadecimal.append((char) ('A' + digit - 10));
            }
            fraccion -= digit;
        }

        return hexadecimal.toString();
    }

    // Método 2: Conversión iterativa (alternativa)
    public static String decimalAHexIterativo(long decimal) {
        validarNumeroPositivo(decimal);
        if (decimal == 0) {
            return "0";
        }

        StringBuilder hexadecimal = new StringBuilder();
        long numero = decimal;

        while (numero > 0) {
            long resto = numero % 16;
            char digitoHex;
            if (resto < 10) {
                digitoHex = (char) ('0' + resto);
            } else {
                digitoHex = (char) ('A' + resto - 10);
            }
            hexadecimal.insert(0, digitoHex);
            numero /= 16;
        }

        return hexadecimal.toString();
    }

    // ========== CONVERSIÓN BINARIO A DECIMAL ==========
    public static double binarioADecimal(String binario) {
        binario = binario.replaceAll("\\s+", "");
        binario = binario.replace(',', '.');
        String[] partes = binario.split("\\.");
        String parteEntera = partes[0];
        String parteFraccionaria = (partes.length > 1) ? partes[1] : "";


        if (!parteEntera.matches("^[01]+$") || (!parteFraccionaria.isEmpty() && !parteFraccionaria.matches("^[01]+$"))) {
            throw new NumberFormatException("El número binario contiene caracteres no válidos. Solo se permiten 0 y 1.");
        }


        int entero = Integer.parseInt(parteEntera, 2);


        double fraccion = 0;
        for (int i = 0; i < parteFraccionaria.length(); i++) {
            char bit = parteFraccionaria.charAt(i);
            if (bit == '1') {
                fraccion += Math.pow(2, -(i + 1));
            }
        }
        return entero + fraccion;
    }

    // ========== CONVERSIÓN HEXADECIMAL A DECIMAL ==========
    public static double hexFlotanteADecimal(String hexadecimal) {
        hexadecimal = hexadecimal.replaceAll("\\s+", "").replace(',', '.').toUpperCase();
        try {
            String[] partes = hexadecimal.split("\\.");
            String parteEntera = partes[0];
            String parteFraccionaria = partes.length > 1 ? partes[1] : "0";


            if (!parteEntera.matches("^[0-9A-F]+$") || !parteFraccionaria.matches("^[0-9A-F]*$")) {
                throw new NumberFormatException("Formato hexadecimal no válido.");
            }

            long entero = Long.parseLong(parteEntera, 16);
            double fraccion = 0.0;
            for (int i = 0; i < parteFraccionaria.length(); i++) {
                char hex = parteFraccionaria.charAt(i);
                int digit = Character.digit(hex, 16);
                fraccion += digit / Math.pow(16, i + 1);
            }
            return entero + fraccion;
        } catch (Exception e) {
            throw new NumberFormatException("Error al convertir número hexadecimal flotante: " + e.getMessage());
        }
    }

    // ========== MÉTODOS AUXILIARES ==========
    private static void validarNumeroPositivo(double numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("El número no puede ser negativo.");
        }
    }

    // ========== MÉTODO MAIN PARA PRUEBAS ==========
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione conversión:");
        System.out.println("1. Decimal a Binario");
        System.out.println("2. Decimal a Hexadecimal");
        System.out.println("3. Binario a Decimal");
        System.out.println("4. Hexadecimal a Decimal");
        System.out.print("Opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1:
                System.out.print("Ingrese número decimal: ");
                String inputDecBin = scanner.nextLine().replace(',', '.');
                try {
                    double decimal = Double.parseDouble(inputDecBin);
                    String binario = convertirDecimalABinario(decimal);
                    System.out.printf("Binario: %s\n", binario);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 2:
                System.out.print("Ingrese número decimal: ");
                String inputDecHex = scanner.nextLine().replace(',', '.');
                try {
                    double decimal = Double.parseDouble(inputDecHex);
                    String hexadecimal = decimalAHexRecursivo(decimal);
                    System.out.printf("Hexadecimal: %s\n", hexadecimal);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 3:
                System.out.print("Ingrese número binario: ");
                String binarioInput = scanner.nextLine();
                try {
                    double decimal = binarioADecimal(binarioInput);
                    System.out.printf("Decimal: %.6f\n", decimal);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 4:
                System.out.print("Ingrese número hexadecimal: ");
                String hexInput = scanner.nextLine().toUpperCase();
                try {
                    double decimal = hexFlotanteADecimal(hexInput);
                    System.out.printf("Decimal: %.6f\n", decimal);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            default:
                System.out.println("Opción no válida.");
        }

        scanner.close();
    }
}