package co.org.uniquindio;

import co.org.uniquindio.metodos.*;
import co.org.uniquindio.persistence.*;
import co.org.uniquindio.views.ResultsViewer;

import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class Main {
    public static void main(String[] args) throws Exception {


        // Cargar los resultados combinados
        List<ResultData> results = loadCombinedResults();

        // Mostrar el gráfico con los resultados
        displayChart(results);

    }

    private static void saveResult(int size, String algorithm, long executionTime) throws Exception {
        ResultFileHandler.saveResult(size, algorithm, executionTime);
    }

    public static double[][] matrixGenerator(int n,int minDigits){
        Random rand = new Random();
        double[][] matrix = new double[n][n];
        int maxVal = (int) Math.pow(10, minDigits) - 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 1 + rand.nextInt(maxVal);
            }
        }

        return matrix;
    }

    private static void processAlgorithm(int size, String algorithmName, BiConsumer<double[][], double[][]> algorithmExecutor) throws Exception {
        // Cargar las matrices para el tamaño actual
        double[][] matrixA = loadMatrix("matrix_A_" + size + "x" + size);
        double[][] matrixB = loadMatrix("matrix_B_" + size + "x" + size);

        // Ejecutar algoritmo
        long startTime = System.nanoTime();
        algorithmExecutor.accept(matrixA, matrixB);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // Guardar el resultado en el archivo CSV
        saveResult(size, algorithmName, executionTime);
    }


    public static void saveMatrix(double[][] matrix, String filename) throws Exception {
        MatrixFileHandler.saveMatrix(matrix, filename);
    }

    public static double[][] loadMatrix(String filename) throws Exception {
        return MatrixFileHandler.loadMatrix(filename);
    }

    public static List<ResultData> loadCombinedResults() throws Exception {
        return ResultsManager.getCombinedResults();
    }

    public static void displayChart(List<ResultData> results) {
        SwingUtilities.invokeLater(() -> {
            ResultsViewer viewer = new ResultsViewer(results);
            viewer.setVisible(true);
        });
    }

}