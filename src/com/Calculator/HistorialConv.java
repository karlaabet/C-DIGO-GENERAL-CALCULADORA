package com.Calculator;
import java.util.ArrayList;

public class HistorialConv {
    ArrayList<String> numIngresados = new ArrayList<>();
    ArrayList<String> numConvertidos = new ArrayList<>();
    ArrayList<Integer> opciones = new ArrayList<>();
    int count;

    void imprimirResultados(int count, int opcion) {
        int n = 1;
        String baseInput = "", baseReturn = "";
        // Se determina la base de entrada y salida para el historial
        switch (opcion){
            case 1:
                baseInput = " (Base 10 [Decimal])";
                baseReturn = " (Base 2 [Binario])";
                break;
            case 2:
                baseInput = " (Base 10 [Decimal])";
                baseReturn = " (Base 16 [Hexadecimal])";
                break;
            case 3:
                baseInput = " (Base 2 [Binario])";
                baseReturn = " (Base 10 [Decimal])";
                break;
            case 4:
                baseInput = " (Base 16 [Hexadecimal])";
                baseReturn = " (Base 10 [Decimal])";
                break;
            default:
                baseInput = "";
                baseReturn = "";
                break;
        }

        if (count == 1) {
            System.out.println("\t" + count + ". " + "\t" + numIngresados.get(count - 1) + baseInput + "\t\t\t\t" + "➟" + "\t\t\t\t" + numConvertidos.get(count - 1)  + baseReturn);
        }else if(count == 0){
            System.out.println("No se han realizado conversiones o ya se han mostrado todas");
        }else {
            System.out.println("\t" + count + ". " + "\t" + numIngresados.get(count-1) + baseInput + "\t\t\t\t" + "➟" + "\t\t\t\t"+ numConvertidos.get(count-1)  + baseReturn);
            n += 1;
            // Se corrige la recursión para manejar el caso de opciones vacías o fuera de rango
            if (opciones.size() >= n) {
                imprimirResultados(count - 1, opciones.get(opciones.size() - n));
            } else {
                imprimirResultados(count - 1, 0); // Llama con default si no hay opción
            }
        }
    }
}