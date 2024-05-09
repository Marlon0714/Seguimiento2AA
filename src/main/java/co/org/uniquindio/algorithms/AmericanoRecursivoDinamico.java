package co.org.uniquindio.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase contiene un método para multiplicar dos listas de forma recursiva y con el metodo americano.
 *
 * @author Marlon Stiven Espinosa Joaqui
 * @author Juan Esteban Quintero Rodriguez
 * @author Jesus Santiago Ramon Ramos
 */
public class AmericanoRecursivoDinamico {

    /**
     * Método auxiliar privado para realizar la multiplicación de listas de números de forma recursiva.
     *
     * @param arregloA   La primera lista de números a multiplicar.
     * @param arregloB   La segunda lista de números a multiplicar.
     * @param resultado  La lista que almacenará el resultado de la multiplicación.
     * @param indexA     El índice actual en la lista arregloA.
     * @param indexB     El índice actual en la lista arregloB.
     * @return La lista resultado después de la multiplicación.
     */
    private static List<Integer> multiplicarMatrices (List<Integer> arregloA,List<Integer> arregloB, List<Integer> resultado, int indexA, int indexB){

        if(indexB<0){
            return resultado;
        }
        if (indexA >=0){
            int multiplicacion = arregloB.get(indexB) *arregloA.get(indexA) + resultado.get(indexA+indexB+1);
            resultado.set(indexA + indexB + 1, multiplicacion%10);
            resultado.set(indexA + indexB, resultado.get(indexA+indexB)+multiplicacion/10);

            multiplicarMatrices(arregloA,arregloB,resultado,indexA-1,indexB);
        }else {


            multiplicarMatrices(arregloA,arregloB,resultado,arregloA.size()-1,indexB-1);
        }
        return resultado;
    }

    /**
     * Multiplica dos listas de números de manera recursiva utilizando el método americano.
     *
     * @param arregloA La primera lista de números a multiplicar.
     * @param arregloB La segunda lista de números a multiplicar.
     * @return Una lista que representa el resultado de la multiplicación.
     */
    public static List<Integer> multiplicarMatrices(List<Integer> arregloA, List<Integer> arregloB  ){
        List<Integer> resultado = new ArrayList<>();
        // Inicializar el resultado con ceros
        for (int i = 0; i < arregloA.size() + arregloB.size(); i++) {
            resultado.add(0);
        }

        multiplicarMatrices(arregloA,arregloB,resultado,arregloA.size()-1, arregloB.size()-1);
        return resultado;
    }

}