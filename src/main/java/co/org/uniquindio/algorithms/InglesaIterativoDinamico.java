package co.org.uniquindio.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class InglesaIterativoDinamico {

    /**
     * Multiplicación inglesa usando estructuras dinámicas (ArrayList)
     * @param num1 lista representando el primer número
     * @param num2 lista representando el segundo número
     * @return lista con el resultado de la multiplicación
     */
    public static List<Integer> multiplicacionInglesaDinamico(List<Integer> num1, List<Integer> num2) {
        int tam = num1.size() + num2.size();
        ArrayList<Integer> resultado = new ArrayList<>();

        // Inicializar el resultado con ceros
        for (int i = 0; i < tam; i++) {
            resultado.add(0);
        }

        for (int i = num2.size() - 1; i >= 0; i--) {
            for (int j = num1.size() - 1; j >= 0; j--) {
                int suma = resultado.get(i + j + 1) + num1.get(j) * num2.get(i);
                resultado.set(i + j + 1, suma);
            }
        }

        for (int k = tam - 1; k > 0; k--) {
            int carry = resultado.get(k) / 10;
            resultado.set(k, resultado.get(k) % 10);
            resultado.set(k - 1, resultado.get(k - 1) + carry);
        }

        return resultado;
    }

    /**
     * Recibe números como Integer, los convierte a una estructura dinámica y realiza la multiplicación inglesa
     * @param num1 primer número
     * @param num2 segundo número
     */
    public static void multiply(BigInteger num1, BigInteger num2) {
        List<Integer> lista1 = Utils.bigIntegerToIntegerList(num1);
        List<Integer> lista2 = Utils.bigIntegerToIntegerList(num2);
        List<Integer> resultado = multiplicacionInglesaDinamico(lista1, lista2);
        System.out.println("Resultado (Dinámico): " + (resultado));
    }

}
