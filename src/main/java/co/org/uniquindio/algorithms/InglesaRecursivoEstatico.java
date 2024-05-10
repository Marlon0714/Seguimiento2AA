package co.org.uniquindio.algorithms;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * La clase InglesaRecursivoEstatico implementa el algoritmo de multiplicación inglesa de forma recursiva y estática.
 * Convierte los números grandes en arreglos de enteros y realiza la multiplicación.
 * El resultado se devuelve como un arreglo de enteros.
 *
 * @author Marlon Stiven Espinosa Joaqui
 * @author Juan Esteban Quintero Rodriguez
 * @author Jesus Santiago Ramon Ramos
 */
public class InglesaRecursivoEstatico {

    /**
     * Multiplicación inglesa recursiva usando estructuras estáticas (arreglos)
     * @param num1 arreglo representando el primer número
     * @param num2 arreglo representando el segundo número
     * @return arreglo con el resultado de la multiplicación
     */
    public static int[] multiplicacionInglesaEstatico(int[] num1, int[] num2) {
        int tam = num1.length + num2.length;
        int[] resultado = new int[tam];
        inicializarArreglo(resultado, tam, 0);
        multiplicacionRecursiva(num1, num2, resultado, num1.length - 1, num2.length - 1);
        corregirAcarreos(resultado, resultado.length - 1);
        return resultado;
    }

    /**
     * Inicializa un arreglo con ceros de forma recursiva
     * @param resultado arreglo a inicializar
     * @param tam tamaño del arreglo
     * @param index índice actual
     */
    private static void inicializarArreglo(int[] resultado, int tam, int index) {
        if (index >= tam) return;
        resultado[index] = 0;
        inicializarArreglo(resultado, tam, index + 1);
    }

    /**
     * Realiza la multiplicación inglesa de forma recursiva
     * @param num1 arreglo representando el primer número
     * @param num2 arreglo representando el segundo número
     * @param resultado arreglo con el resultado de la multiplicación
     * @param index1 índice del primer número
     * @param index2 índice del segundo número
     */
    private static void multiplicacionRecursiva(int[] num1, int[] num2, int[] resultado, int index1, int index2) {
        if (index2 < 0) return;
        if (index1 < 0) {
            multiplicacionRecursiva(num1, num2, resultado, num1.length - 1, index2 - 1);
            return;
        }

        int suma = resultado[index1 + index2 + 1] + num1[index1] * num2[index2];
        resultado[index1 + index2 + 1] = suma;

        multiplicacionRecursiva(num1, num2, resultado, index1 - 1, index2);
    }

    /**
     * Corrige los acarreos en el arreglo resultado de forma recursiva
     * @param resultado arreglo con el resultado de la multiplicación
     * @param index índice actual para corregir el acarreo
     */
    private static void corregirAcarreos(int[] resultado, int index) {
        if (index <= 0) return;

        int carry = resultado[index] / 10;
        resultado[index] %= 10;
        resultado[index - 1] += carry;

        corregirAcarreos(resultado, index - 1);
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
        /* Muestra los números en consola
        System.out.println("Resultado (Recursivo Estático): " + Arrays.toString(resultado));
         */
    }

}
