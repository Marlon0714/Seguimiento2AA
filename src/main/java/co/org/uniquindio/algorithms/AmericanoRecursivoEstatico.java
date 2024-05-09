package co.org.uniquindio.algorithms;

import java.beans.PropertyEditorSupport;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Esta clase contiene un método para multiplicar dos arreglos de forma recursiva y con el metodo americano.
 *
 * @author Marlon Stiven Espinosa Joaqui
 * @author Juan Esteban Quintero Rodriguez
 * @author Jesus Santiago Ramon Ramos
 */
public class AmericanoRecursivoEstatico {

    /**
     * Multiplica dos arreglos de enteros de manera recursiva utilizando el método americano.
     *
     * @param arregloA El primer arreglo de números a multiplicar.
     * @param arregloB El segundo arreglo de números a multiplicar.
     * @return Un arreglo que representa el resultado de la multiplicación.
     */
    public static int[] multiplicarMatrices(int [] arregloA, int[] arregloB){
        int [] resultado = new int[arregloA.length+arregloB.length];
        multiplicarMatrices(arregloA,arregloB,resultado,arregloA.length-1, arregloB.length-1);
        return resultado;
    }

    /**
     * Método auxiliar privado para realizar la multiplicación de arreglos de números de forma recursiva.
     *
     * @param arregloA   El primer arreglo de números a multiplicar.
     * @param arregloB   El segundo arreglo de números a multiplicar.
     * @param resultado  El arreglo que almacenará el resultado de la multiplicación.
     * @param indexA     El índice actual en el arregloA.
     * @param indexB     El índice actual en el arregloB.
     * @return El arreglo resultado después de la multiplicación.
     */
    private static int[] multiplicarMatrices (int [] arregloA, int [] arregloB, int [] resultado,int indexA, int indexB){

        if(indexB<0){
            return resultado;
        }
        if (indexA >=0){
            int multiplicacion = arregloB[indexB] *arregloA[indexA] + resultado[indexA+indexB+1];
            resultado[indexA + indexB + 1] = multiplicacion % 10;
            resultado[indexA+indexB] += multiplicacion /10;

            multiplicarMatrices(arregloA,arregloB,resultado,indexA-1,indexB);
        }else {


            multiplicarMatrices(arregloA,arregloB,resultado,arregloA.length-1,indexB-1);
        }
        return resultado;
    }

    public static void multiply(BigInteger num1, BigInteger num2) {
        int[] arreglo1 = Utils.bigIntegerToIntArray(num1);
        int[] arreglo2 = Utils.bigIntegerToIntArray(num2);
        int[] resultado = multiplicarMatrices(arreglo1, arreglo2);
        System.out.println("Resultado (Estático): " + (Arrays.toString(resultado)));
    }

}