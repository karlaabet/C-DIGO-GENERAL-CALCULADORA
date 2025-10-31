package com.Calculator.CalculatorFunctions;
import java.util.Scanner;

public class DecimalToBinary {
    // Constante para validación hexadecimal
    private static final String HEX_REGEX = "^[0-9A-F]+(\\.[0-9A-F]+)?$";

    private DecimalToBinary() {
    }

    // ========== CONVERSIÓN DECIMAL A BINARIO ==========
    // NUEVO: Método para manejar la entrada como String (con flotantes)
    public static String convertirDecimalABinarioString(String decimalStr) {
        decimalStr = decimalStr.replace(',', '.');
        String[] partes = decimalStr.split("\\.");
        long parteEntera = partes[0].isEmpty() ? 0 : Long.parseLong(partes[0]);
        validarNumeroPositivo(parteEntera);
        String binarioEntero = convertirManual(parteEntera);

        if (partes.length > 1 && !partes[1].isEmpty()) {
            double parteFraccionaria = Double.parseDouble("0." + partes[1]);
            String binarioFraccion = convertirFraccionABinario(parteFraccionaria);
            return binarioEntero + "." + binarioFraccion;
        }
        return binarioEntero;
    }

    public static String convertirDecimalABinario(long decimal) { // Versión solo enteros
        validarNumeroPositivo(decimal);
        return convertirManual(decimal);
    }

    private static String convertirManual(long decimal) {
        if (decimal == 0) return "0";
        StringBuilder binario = new StringBuilder();
        while (decimal > 0) {
            long residuo = decimal % 2;
            binario.insert(0, residuo);
            decimal /= 2;
        }
        return binario.toString();
    }

    // NUEVO: Método para convertir la parte fraccionaria a binario
    private static String convertirFraccionABinario(double fraccion) {
        if (fraccion == 0) return "0";
        StringBuilder binario = new StringBuilder();
        int maxBits = 32;

        for (int i = 0; i < maxBits && fraccion > 0; i++) {
            fraccion *= 2;
            int bit = (int) fraccion;
            binario.append(bit);
            fraccion -= bit;
        }
        return binario.toString();
    }


    // ========== CONVERSIÓN DECIMAL A HEXADECIMAL ==========
    // NUEVO: Método para manejar la entrada como String (con flotantes)
    public static String decimalAHexString(String decimalStr) {
        decimalStr = decimalStr.replace(',', '.');
        String[] partes = decimalStr.split("\\.");
        long parteEntera = partes[0].isEmpty() ? 0 : Long.parseLong(partes[0]);
        validarNumeroPositivo(parteEntera);
        String hexEntero = decimalAHexRecursivo(parteEntera);

        if (partes.length > 1 && !partes[1].isEmpty()) {
            double parteFraccionaria = Double.parseDouble("0." + partes[1]);
            String hexFraccion = convertirFraccionAHex(parteFraccionaria);
            return hexEntero + "." + hexFraccion;
        }
        return hexEntero;
    }


    public static String decimalAHexRecursivo(long decimal) { // Versión solo enteros
        validarNumeroPositivo(decimal);
        if (decimal == 0) return "0";
        return divisionRecursiva(decimal);
    }

    private static String divisionRecursiva(long numero) {
        if (numero == 0) {
            return "";
        } else {
            int resto = (int) (numero % 16);
            String digitoHexadecimal;
            if (resto < 10) digitoHexadecimal = String.valueOf(resto);
            else digitoHexadecimal = String.valueOf((char) (65 + resto - 10));
            String resultadoParcial = divisionRecursiva(numero / 16);
            return resultadoParcial + digitoHexadecimal;
        }
    }

    // NUEVO: Método para convertir la parte fraccionaria a hexadecimal
    private static String convertirFraccionAHex(double fraccion) {
        if (fraccion == 0) return "0";
        StringBuilder hex = new StringBuilder();
        int maxDigits = 8;

        for (int i = 0; i < maxDigits && fraccion > 0; i++) {
            fraccion *= 16;
            int digit = (int) fraccion;
            char hexChar;
            if (digit < 10) hexChar = (char) ('0' + digit);
            else hexChar = (char) ('A' + digit - 10);
            hex.append(hexChar);
            fraccion -= digit;
        }
        return hex.toString();
    }

    // ========== CONVERSIÓN BINARIO A DECIMAL (Soporta flotantes) ==========
    public static double binarioADecimal(String binario) {
        binario = binario.replace(',', '.');
        String[] partes = binario.split("\\.");
        String parteEntera = partes[0];
        String parteFraccionaria = (partes.length > 1) ? partes[1] : "";

        long entero = parteEntera.isEmpty() ? 0 : Long.parseLong(parteEntera, 2);
        double fraccion = 0;
        for (int i = 0; i < parteFraccionaria.length(); i++) {
            char bit = parteFraccionaria.charAt(i);
            if (bit == '1') fraccion += Math.pow(2, -(i + 1));
            else if (bit != '0') throw new NumberFormatException("Carácter no válido en la parte fraccionaria: " + bit);
        }
        return entero + fraccion;
    }

    // ========== CONVERSIÓN HEXADECIMAL A DECIMAL (Soporta flotantes) ==========
    public static double hexFlotanteADecimal(String hexadecimal) {
        hexadecimal = hexadecimal.replace(',', '.');
        try {
            String[] partes = hexadecimal.split("\\.");
            String parteEntera = partes[0];
            String parteFraccionaria = partes.length > 1 ? partes[1] : "0";

            long entero = parteEntera.isEmpty() ? 0 : Long.parseLong(parteEntera, 16);
            double fraccion = 0.0;
            for (int i = 0; i < parteFraccionaria.length(); i++) {
                char hex = parteFraccionaria.charAt(i);
                int digit = Character.digit(hex, 16);
                if (digit == -1) throw new NumberFormatException();
                fraccion += digit / Math.pow(16, i + 1);
            }
            return entero + fraccion;
        } catch (Exception e) {
            throw new NumberFormatException("Error al convertir número hexadecimal flotante. Verifique el formato.");
        }
    }

    // ========== MÉTODOS AUXILIARES ==========
    private static void validarNumeroPositivo(long numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("El número no puede ser negativo.");
        }
    }

    // CORRECCIÓN: Método MAIN PARA PRUEBAS corregido para eliminar el error de sintaxis
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione conversión:");
        System.out.println("1. Decimal a Hexadecimal");
        System.out.println("2. Hexadecimal a Decimal");
        System.out.print("Opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1:
                System.out.print("Ingrese número decimal (puede ser flotante): ");
                String decimalStr = scanner.nextLine().trim().replace(',', '.');
                try {
                    String hexResultado = decimalAHexString(decimalStr);
                    System.out.printf("Hexadecimal: %s\n", hexResultado);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 2:
                System.out.print("Ingrese número hexadecimal: ");
                String hexString = scanner.nextLine().toUpperCase();

                if (hexString.contains(" ")) {
                    System.out.println("El numero ingresado no debe tener espacios.");
                    break;
                }

                if (!hexString.matches(HEX_REGEX)) {
                    System.out.println("Formato hexadecimal no válido.");
                    break;
                }

                try {
                    double decimalResult = hexFlotanteADecimal(hexString);
                    System.out.printf("El valor decimal de %s es: %f\n", hexString, decimalResult);
                } catch (NumberFormatException e) {
                    System.out.println("Error en la conversión: " + e.getMessage());
                }
                break;

            default:
                System.out.println("Opción no válida.");
        }

        scanner.close();
    }
}