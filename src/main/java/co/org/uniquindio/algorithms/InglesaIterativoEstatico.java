package co.org.uniquindio.algorithms;

import java.math.BigInteger;
import java.util.Arrays;

public class InglesaIterativoEstatico {

    /**
     * Multiplicación inglesa usando estructuras estáticas (arreglos)
     * @param num1 arreglo representando el primer número
     * @param num2 arreglo representando el segundo número
     * @return arreglo con el resultado de la multiplicación
     */
    public static int[] multiplicacionInglesaEstatico(int[] num1, int[] num2) {
        int tam = num1.length + num2.length;
        int[] resultado = new int[tam];

        for (int i = num2.length - 1; i >= 0; i--) {
            for (int j = num1.length - 1; j >= 0; j--) {
                resultado[i + j + 1] += num1[j] * num2[i];
            }
        }

        for (int k = tam - 1; k > 0; k--) {
            resultado[k - 1] += resultado[k] / 10;
            resultado[k] %= 10;
        }

        return resultado;
    }

    /**
     * Recibe números como Integer, los convierte a una estructura estática y realiza la multiplicación inglesa
     * @param num1 primer número
     * @param num2 segundo número
     */
    public static void multiply(BigInteger num1, BigInteger num2) {
        int[] arreglo1 = Utils.bigIntegerToIntArray(num1);
        int[] arreglo2 = Utils.bigIntegerToIntArray(num2);
        int[] resultado = multiplicacionInglesaEstatico(arreglo1, arreglo2);
        System.out.println("Resultado (Estático): " + (Arrays.toString(resultado)));
    }
}
