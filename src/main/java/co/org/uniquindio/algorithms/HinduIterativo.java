package co.org.uniquindio.algorithms;



import java.math.BigInteger;
import java.util.Arrays;

/**
 * Esta clase contiene un método para multiplicar dos listas con el metodo Hindu.
 * Tomado de https://www.geeksforgeeks.org/multiply-large-numbers-using-grid-method/?ref=header_search
 */
public class HinduIterativo {

    /**
     * Realiza la multiplicación de dos arreglos de enteros utilizando el algoritmo hindú iterativo.
     *
     * @param a El primer arreglo de enteros a multiplicar.
     * @param b El segundo arreglo de enteros a multiplicar.
     * @return Un arreglo de enteros que representa el resultado de la multiplicación.
     */
    public static int[] multiply(int[] a, int[] b) {
        // Flags para indicar si los números son negativos
        boolean flag1 = false;
        boolean flag2 = false;

        // Verificar si los números son negativos y ajustarlos si es necesario
        if (a[0] < 0) {
            a = Arrays.copyOfRange(a, 1, a.length);
            flag1 = true;
        }
        if (b[0] < 0) {
            b = Arrays.copyOfRange(b, 1, b.length);
            flag2 = true;
        }

        // Crear una cadena mutable para almacenar el resultado de la multiplicación
        StringBuilder out = new StringBuilder();

        // Crear una matriz para almacenar los productos parciales
        int row = a.length;
        int column = b.length;
        int[][] c = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                c[i][j] = a[i] * b[j];
            }
        }

        // Sumar los productos parciales para obtener el resultado final
        int[] sum = new int[row + column - 1];
        int m = 0;
        for (int i = 0; i < row; i++) {
            int k = i;
            int add = 0;
            for (int j = 0; j < column && k >= 0; j++, k--) {
                add += c[k][j];
            }
            sum[m] = add;
            m++;
        }
        for (int k = 1; k < column; k++) {
            int i = row - 1;
            int j = k;
            int add = 0;
            while (j < column && i >= 0) {
                add += c[i][j];
                j++;
                i--;
            }
            sum[m] = add;
            m++;
        }

        // Construir la representación en cadena del resultado
        if (sum.length != 1) {
            int t = 0;
            for (int n = sum.length - 1; n >= 1; n--) {
                t += sum[n];
                String temp = Integer.toString(t);
                if (temp.length() > 1) {
                    String str = temp.substring(0, temp.length() - 1);
                    t = Integer.parseInt(str);
                } else {
                    t = 0;
                }
                out.insert(0, temp.charAt(temp.length() - 1));
            }
            t += sum[0];
            out.insert(0, t);
        } else {
            out.append(sum[0]);
        }

        // Comprobar si el resultado es cero
        if (!out.toString().equals("0")) {
            // Agregar signo negativo si uno de los números es negativo
            if (flag1 != flag2) {
                out.insert(0, '-');
            }
        }

        // Convertir la cadena de resultado en un arreglo de enteros y devolverlo
        int[] result = new int[out.length()];
        for (int i = 0; i < out.length(); i++) {
            result[i] = Character.getNumericValue(out.charAt(i));
        }

        return result;
    }

    /**
     * Convierte dos números grandes a arreglos de enteros, realiza la multiplicación y muestra el resultado.
     *
     * @param a El primer número grande.
     * @param b El segundo número grande.
     */
    public static void multiply(BigInteger a, BigInteger b) {
        int[] arreglo1 = Utils.bigIntegerToIntArray(a);
        int[] arreglo2 = Utils.bigIntegerToIntArray(b);
        int[] resultado = multiply(arreglo1, arreglo2);
        /* Muestra los números en consola
        System.out.println("Resultado (Estático): " + (Arrays.toString(resultado)));
         */

    }

}
