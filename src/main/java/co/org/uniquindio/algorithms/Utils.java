package co.org.uniquindio.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    /**
     * Convierte un `BigInteger` a un arreglo de enteros
     * @param num número `BigInteger`
     * @return arreglo de enteros
     */
    public static int[] bigIntegerToIntArray(BigInteger num) {
        String numStr = num.toString();
        int[] arr = new int[numStr.length()];
        for (int i = 0; i < numStr.length(); i++) {
            arr[i] = Character.getNumericValue(numStr.charAt(i));
        }
        return arr;
    }

    /**
     * Convierte un `BigInteger` a una lista de enteros
     * @param num número `BigInteger`
     * @return lista de enteros
     */
    public static List<Integer> bigIntegerToIntegerList(BigInteger num) {
        String numStr = num.toString();
        List<Integer> list = new ArrayList<>(numStr.length());
        for (int i = 0; i < numStr.length(); i++) {
            list.add(Character.getNumericValue(numStr.charAt(i)));
        }
        return list;
    }

}
