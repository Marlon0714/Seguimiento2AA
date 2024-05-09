package co.org.uniquindio.algorithms;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Esta clase contiene un método para multiplicar dos listas con un enfoque Divide y venceras.
 * @author Marlon Stiven Espinosa Joaqui
 * @author Juan Esteban Quintero Rodriguez
 * @author Jesus Santiago Ramon Ramos
 */
public class DivideYVencerasEstatico {
    /**
     * Método para realizar la multiplicación de dos números grandes utilizando el algoritmo de Karatsuba.
     * @param x Primer número a multiplicar representado como un arreglo de dígitos.
     * @param y Segundo número a multiplicar representado como un arreglo de dígitos.
     * @return El resultado de la multiplicación de los dos números.
     */
    public static int multiplicarMatrices(int[] x, int[] y) {
        int n = Math.max(x.length, y.length);

        // Caso base para números de un dígito
        if (n == 1) {
            return x[0] * y[0];
        }

        // Rellenar los arreglos para que tengan la misma longitud
        if (x.length < n) {
            x = rellenarArreglo(x, n - x.length);
        }
        if (y.length < n) {
            y = rellenarArreglo(y, n - y.length);
        }

        // Dividir los números en mitades
        int mid = n / 2;
        int[] w = Arrays.copyOfRange(x, 0, mid);
        int[] x1 = Arrays.copyOfRange(x, mid, n);
        int[] y1 = Arrays.copyOfRange(y, 0, mid);
        int[] y2 = Arrays.copyOfRange(y, mid, n);

        // Calcular resultados parciales
        int[] z0 = multiplicarNumeros(w, y1);
        int[] z1 = multiplicarNumeros(x1, y2);
        int[] z2 = multiplicarNumeros(sumaNumeros(w, x1), sumaNumeros(y1, y2));
        int[] z3 = restarNumeros(restarNumeros(z2, z1), z0);

        // Desplazar y sumar los resultados parciales
        return (int) (Math.pow(10, n) * arrayToNumber(z0) + Math.pow(10, mid) * arrayToNumber(z3) + arrayToNumber(z1));
    }

    /**
     * Método para multiplicar dos arreglos de números.
     * @param x Primer número a multiplicar representado como un arreglo de dígitos.
     * @param y Segundo número a multiplicar representado como un arreglo de dígitos.
     * @return El resultado de la multiplicación de los dos arreglos.
     */
    public static int[] multiplicarNumeros(int[] x, int[] y) {
        int n = Math.max(x.length, y.length);
        if (n == 1) {
            return new int[]{x[0] * y[0]};
        }

        // Rellenar los arreglos para que tengan la misma longitud
        if (x.length < n) {
            x = rellenarArreglo(x, n - x.length);
        }
        if (y.length < n) {
            y = rellenarArreglo(y, n - y.length);
        }

        // Dividir los números en mitades
        int mid = n / 2;
        int[] w = Arrays.copyOfRange(x, 0, mid);
        int[] x1 = Arrays.copyOfRange(x, mid, n);
        int[] y1 = Arrays.copyOfRange(y, 0, mid);
        int[] y2 = Arrays.copyOfRange(y, mid, n);

        // Calcular resultados parciales
        int[] z0 = multiplicarNumeros(w, y1);
        int[] z1 = multiplicarNumeros(x1, y2);
        int[] z2 = multiplicarNumeros(sumaNumeros(w, x1), sumaNumeros(y1, y2));
        int[] z3 = restarNumeros(restarNumeros(z2, z1), z0);

        // Desplazar y sumar los resultados parciales
        return sumaNumeros(sumaNumeros(z0, rellenarArreglo(z3, mid)), rellenarArreglo(z1, 2 * mid));
    }

    /**
     * Método para sumar dos arreglos de números.
     * @param arr1 Primer arreglo de números.
     * @param arr2 Segundo arreglo de números.
     * @return El resultado de la suma de los dos arreglos.
     */
    public static int[] sumaNumeros(int[] arr1, int[] arr2) {
        int n = Math.max(arr1.length, arr2.length);
        int[] resultado = new int[n];
        for (int i = 0; i < n; i++) {
            resultado[i] = (i < arr1.length ? arr1[i] : 0) + (i < arr2.length ? arr2[i] : 0);
        }
        return resultado;
    }

    /**
     * Método para restar dos arreglos de números.
     * @param arr1 Primer arreglo de números.
     * @param arr2 Segundo arreglo de números.
     * @return El resultado de la resta de los dos arreglos.
     */
    public static int[] restarNumeros(int[] arr1, int[] arr2) {
        int n = Math.max(arr1.length, arr2.length);
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = (i < arr1.length ? arr1[i] : 0) - (i < arr2.length ? arr2[i] : 0);
        }
        return result;
    }

    /**
     * Método para convertir un arreglo de dígitos en un número entero.
     * @param arr Arreglo de dígitos.
     * @return El número entero representado por el arreglo de dígitos.
     */
    public static int arrayToNumber(int[] arr) {
        int num = 0;
        for (int digit : arr) {
            num = num * 10 + digit;
        }
        return num;
    }

    /**
     * Método para agregar ceros a la izquierda de un arreglo.
     * @param arr Arreglo al que se le agregarán ceros.
     * @param count Cantidad de ceros a agregar.
     * @return El arreglo con los ceros agregados a la izquierda.
     */
    public static int[] rellenarArreglo(int[] arr, int count) {
        int[] result = new int[arr.length + count];
        for (int i = 0; i < count; i++) {
            result[i] = 0;
        }
        System.arraycopy(arr, 0, result, count, arr.length);
        return result;
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
        int resultado = multiplicarMatrices(arreglo1, arreglo2);
        System.out.println("Resultado (Estático): " + resultado);
    }

}

