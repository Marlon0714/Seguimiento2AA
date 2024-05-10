package co.org.uniquindio.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase InglesaRecursivoDinamico implementa el algoritmo de multiplicación inglesa de forma recursiva y dinámica.
 * Convierte los números grandes en listas de enteros y realiza la multiplicación.
 * El resultado se devuelve como una lista de enteros.
 *
 * @author Marlon Stiven Espinosa Joaqui
 * @author Juan Esteban Quintero Rodriguez
 * @author Jesus Santiago Ramon Ramos
 */
public class InglesaRecursivoDinamico {

    /**
     * Multiplicación inglesa recursiva usando estructuras dinámicas (List)
     * @param num1 lista representando el primer número
     * @param num2 lista representando el segundo número
     * @return lista con el resultado de la multiplicación
     */
    public static List<Integer> multiplicacionInglesaRecursiva(List<Integer> num1, List<Integer> num2) {
        int tam = num1.size() + num2.size();
        List<Integer> resultado = inicializarLista(new ArrayList<>(), tam);
        multiplicacionRecursiva(num1, num2, resultado, num1.size() - 1, num2.size() - 1);
        corregirAcarreos(resultado, resultado.size() - 1);
        return resultado;
    }

    /**
     * Inicializa una lista con ceros de forma recursiva
     * @param resultado lista a inicializar
     * @param tam tamaño de la lista
     * @return lista inicializada
     */
    private static List<Integer> inicializarLista(List<Integer> resultado, int tam) {
        if (tam == 0) return resultado;
        resultado.add(0);
        return inicializarLista(resultado, tam - 1);
    }

    /**
     * Realiza la multiplicación inglesa de forma recursiva
     * @param num1 lista representando el primer número
     * @param num2 lista representando el segundo número
     * @param resultado lista con el resultado de la multiplicación
     * @param index1 índice del primer número
     * @param index2 índice del segundo número
     */
    private static void multiplicacionRecursiva(List<Integer> num1, List<Integer> num2, List<Integer> resultado, int index1, int index2) {
        if (index2 < 0) return;
        if (index1 < 0) {
            multiplicacionRecursiva(num1, num2, resultado, num1.size() - 1, index2 - 1);
            return;
        }

        int suma = resultado.get(index1 + index2 + 1) + num1.get(index1) * num2.get(index2);
        resultado.set(index1 + index2 + 1, suma);

        multiplicacionRecursiva(num1, num2, resultado, index1 - 1, index2);
    }

    /**
     * Corrige los acarreos en la lista resultado de forma recursiva
     * @param resultado lista con el resultado de la multiplicación
     * @param index índice actual para corregir el acarreo
     */
    private static void corregirAcarreos(List<Integer> resultado, int index) {
        if (index <= 0) return;

        int carry = resultado.get(index) / 10;
        resultado.set(index, resultado.get(index) % 10);
        resultado.set(index - 1, resultado.get(index - 1) + carry);

        corregirAcarreos(resultado, index - 1);
    }

    /**
     * Recibe números como Integer, los convierte a una estructura dinámica y realiza la multiplicación inglesa
     * @param num1 primer número
     * @param num2 segundo número
     */
    public static void multiply(BigInteger num1, BigInteger num2) {
        List<Integer> lista1 = Utils.bigIntegerToIntegerList(num1);
        List<Integer> lista2 = Utils.bigIntegerToIntegerList(num2);
        List<Integer> resultado = multiplicacionInglesaRecursiva(lista1, lista2);
        //System.out.println("Resultado (Recursivo Dinámico): " + resultado);
    }

}

