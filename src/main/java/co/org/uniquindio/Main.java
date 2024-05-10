package co.org.uniquindio;

import co.org.uniquindio.algorithms.*;
import co.org.uniquindio.persistence.*;
import co.org.uniquindio.views.ResultsViewer;

import javax.swing.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class Main {
    public static void main(String[] args) throws Exception {

        int[] sizes = {8, 16, 32, 64, 128, 256, 512};

        // Probar cada algoritmo con cada tamaño de numero
        for (int size : sizes) {
            // Generar numeros
            BigInteger numberA = numberGenerator(size);
            BigInteger numberB = numberGenerator(size);

            // Guardar numeros
            saveNumber(numberA, "number_A_" + size);
            saveNumber(numberB, "number_B_" + size);

            processAlgorithm(size, "AmericanoIterativoEstatico", AmericanoIterativoEstatico::multiply);
            processAlgorithm(size, "AmericanoIterativoDinamico", AmericanoIterativoDinamico::multiply);
            processAlgorithm(size, "AmericanoRecursivoEstatico", AmericanoRecursivoEstatico::multiply);
            processAlgorithm(size, "AmericanoRecursivoDinamico", AmericanoRecursivoDinamico::multiply);
            processAlgorithm(size, "InglesaIterativoEstatico", InglesaIterativoEstatico::multiply);
            processAlgorithm(size, "InglesaIterativoDinamico", InglesaIterativoDinamico::multiply);
            processAlgorithm(size, "InglesaRecursivoEstatico", InglesaRecursivoEstatico::multiply);
            processAlgorithm(size, "InglesaRecursivoDinamico", InglesaRecursivoDinamico::multiply);
            processAlgorithm(size, "HinduIterativo", HinduIterativo::multiply);
            processAlgorithm(size, "DivideYVencerasEstatico", DivideYVencerasEstatico::multiply);

        }
        // Cargar los resultados combinados
        List<ResultData> results = loadResults();

        // Mostrar el gráfico con los resultados
        displayChart(results);

    }

    private static void saveResult(int size, String algorithm, long executionTime) throws Exception {
        ResultFileHandler.saveResult(size, algorithm, executionTime);
    }

    /**
     * Genera un número aleatorio de `n` dígitos como `long`
     * @param n cantidad de dígitos
     * @return número aleatorio como `long`
     */
    public static BigInteger numberGenerator(int n) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // El primer dígito no puede ser cero
        sb.append(random.nextInt(9) + 1);

        // Generar el resto de los dígitos
        for (int i = 1; i < n; i++) {
            sb.append(random.nextInt(10));
        }

        return new BigInteger(sb.toString());
    }

    private static void processAlgorithm(int size, String algorithmName, BiConsumer<BigInteger, BigInteger> algorithmExecutor) throws Exception {
        // Cargar los numeros para el tamaño actual
        BigInteger numberA = loadNumber("number_A_" + size);
        BigInteger numberB = loadNumber("number_B_" + size);

        // Ejecutar algoritmo
        long startTime = System.nanoTime();
        algorithmExecutor.accept(numberA, numberB);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // Guardar el resultado en el archivo CSV
        saveResult(size, algorithmName, executionTime);
    }


    public static void saveNumber(BigInteger number, String filename) throws Exception {
        NumberFileHandler.saveNumber(number, filename);
    }

    public static BigInteger loadNumber(String filename) throws Exception {
        return NumberFileHandler.loadNumber(filename);
    }

    public static List<ResultData> loadResults() throws Exception {
        return ResultFileHandler.loadResults();
    }

    public static void displayChart(List<ResultData> results) {
        SwingUtilities.invokeLater(() -> {
            ResultsViewer viewer = new ResultsViewer(results);
            viewer.setVisible(true);
        });
    }

}