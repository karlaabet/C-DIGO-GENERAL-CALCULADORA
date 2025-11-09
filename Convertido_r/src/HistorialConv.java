import java.util.ArrayList;

public class HistorialConv {
    ArrayList<String> numIngresados = new ArrayList<>();
    ArrayList<String> numConvertidos = new ArrayList<>();
    ArrayList<Integer> opciones = new ArrayList<>();

    // Método alternativo no recursivo (más simple)
    void imprimirHistorialSimple() {
        if (numIngresados.isEmpty()) {
            System.out.println("No se han realizado conversiones todavía.");
            return;
        }

        System.out.println("Historial de conversiones (más reciente primero):");
        System.out.println("==================================================");

        for (int i = numIngresados.size() - 1; i >= 0; i--) {
            String baseInput = "", baseReturn = "";


            if (i >= opciones.size()) {
                baseInput = " (Opción No Registrada)";
                baseReturn = "";
                continue;
            }

            int opcion = opciones.get(i);

            switch (opcion) {
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

                    baseInput = " (Opción Desconocida)";
                    baseReturn = " (Opción Desconocida)";
                    break;
            }

            int numeroItem = numIngresados.size() - i;
            System.out.println("\t" + numeroItem + ". " + "\t" + numIngresados.get(i) + baseInput +
                    "\t\t\t\t" + "➟" + "\t\t\t\t" + numConvertidos.get(i) + baseReturn);
        }
    }
}