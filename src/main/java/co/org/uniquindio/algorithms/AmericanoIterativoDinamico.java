package co.org.uniquindio.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase contiene un método para multiplicar dos listas de forma iterativa y con el metodo americano.
 *
 * @author Marlon Stiven Espinosa Joaqui
 * @author Juan Esteban Quintero Rodriguez
 * @author Jesus Santiago Ramon Ramos
 */
public class AmericanoIterativoDinamico {

    /**
     * Realiza la multiplicación de dos números  representados como listas de enteros.
     *
     * @param arreglo1 El primer número  representado como una lista de enteros.
     * @param arreglo2 El segundo número  representado como una lista de enteros.
     * @return Una lista que contiene la multiplicación de ambos numeros.
     */
    public static List<Integer> multiplicarListas(List<Integer> arreglo1, List<Integer> arreglo2) {

        List<Integer> resultado = new ArrayList<>();

        // Inicializar el resultado con ceros
        for (int i = 0; i < arreglo1.size() + arreglo2.size(); i++) {
            resultado.add(0);
        }

        for (int i = arreglo2.size() - 1; i >= 0; i--) {
            int residuo  = 0;
            for (int j = arreglo1.size() - 1; j >= 0; j--) {
                int multiplicacion = arreglo1.get(j) * arreglo2.get(i) + resultado.get(i + j + 1) + residuo;
                resultado.set(i + j + 1, multiplicacion % 10);
                residuo = multiplicacion / 10;
            }
            resultado.set(i, resultado.get(i) + residuo);
        }

        // Eliminar ceros a la izquierda si los hay
        while (resultado.size() > 1 && resultado.get(0) == 0) {
            resultado.remove(0);
        }

        return resultado;
    }

    /**
     * Convierte dos números grandes a listas de enteros, realiza la multiplicación y muestra el resultado.
     *
     * @param num1 El primer número grande.
     * @param num2 El segundo número grande.
     */
    public static void multiply(BigInteger num1, BigInteger num2) {
        List<Integer> lista1 = Utils.bigIntegerToIntegerList(num1);
        List<Integer> lista2 = Utils.bigIntegerToIntegerList(num2);
        List<Integer> resultado = multiplicarListas(lista1, lista2);
        //System.out.println("Resultado (Dinámico): " + (resultado));
    }
}
