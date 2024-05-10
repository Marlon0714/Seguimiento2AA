package co.org.uniquindio.algorithms;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Esta clase contiene un método para multiplicar dos matrices de forma iterativa y con el metodo americano.
 *
 * @author Marlon Stiven Espinosa Joaqui
 * @author Juan Esteban Quintero Rodriguez
 * @author Jesus Santiago Ramon Ramos
 */
public class AmericanoIterativoEstatico {

    /**
     * Realiza la multiplicación de dos números  representados como arreglos de enteros.
     * @param arregloA El primer número representado como un arreglo de enteros.
     * @param arregloB El segundo número representado como un arreglo de enteros.
     * @return Un arreglo que contiene la multiplicacón de ambos numeros
     */
    public static int[] multiplicarMatrices (int [] arregloA, int [] arregloB){
        int [] resultado = new int[arregloA.length + arregloB.length];
        
        for (int i = arregloB.length - 1; i >= 0; i--) {
            int residuo = 0;
            for (int j = arregloA.length - 1; j >= 0; j--) {
                int multiplicacion = arregloA[j] * arregloB[i] + resultado[i + j + 1] + residuo;
                resultado[i + j + 1] = multiplicacion % 10;
                residuo = multiplicacion / 10;
            }
            resultado[i] += residuo;
        }
        return  resultado;
    }

    /**
     * Convierte dos números grandes a arreglos de enteros, realiza la multiplicación y muestra el resultado.
     *
     * @param num1 El primer número grande.
     * @param num2 El segundo número grande.
     */
    public static void multiply(BigInteger num1, BigInteger num2) {
        int[] arreglo1 = Utils.bigIntegerToIntArray(num1);
        int[] arreglo2 = Utils.bigIntegerToIntArray(num2);
        int[] resultado = multiplicarMatrices(arreglo1, arreglo2);
        /* Muestra los números en consola
        System.out.println("Resultado (Estático): " + (Arrays.toString(resultado)));
         */
    }
}
