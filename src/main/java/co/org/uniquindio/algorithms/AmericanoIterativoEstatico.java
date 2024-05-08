package co.org.uniquindio.algorithms;

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
}
