package co.org.uniquindio.views;

import co.org.uniquindio.persistence.ResultData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultsViewer extends JFrame {
    private int currentMatrixSize; // Tamaño actual de la matriz visualizada
    private Map<Integer, List<ResultData>> groupedResults;
    private ChartPanel chartPanel;

    public ResultsViewer(List<ResultData> results) {
        super("Resultados de Algoritmos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        groupedResults = results.stream().collect(Collectors.groupingBy(ResultData::getSize));

        List<Integer> sortedSizes = new ArrayList<>(groupedResults.keySet());
        Collections.sort(sortedSizes); // Asegurar que los tamaños estén ordenados
        currentMatrixSize = sortedSizes.isEmpty() ? -1 : sortedSizes.get(0); // Inicializar con el menor tamaño disponible

        JComboBox<Integer> sizeSelector = new JComboBox<>(sortedSizes.toArray(new Integer[0]));
        sizeSelector.addActionListener(e -> {
            Integer selectedSize = (Integer) sizeSelector.getSelectedItem();
            if (selectedSize != null) {
                currentMatrixSize = selectedSize;
                updateChart();
            }
        });

        chartPanel = new ChartPanel(null);
        add(chartPanel, BorderLayout.CENTER);

        JPanel controlsPanel = new JPanel();
        controlsPanel.add(new JLabel("Seleccione el tamaño de la matriz:"));
        controlsPanel.add(sizeSelector);

        add(controlsPanel, BorderLayout.NORTH);

        updateChart(); // Cargar el gráfico inicial
    }

    private void updateChart() {
        List<ResultData> resultsForSize = groupedResults.getOrDefault(currentMatrixSize, Collections.emptyList());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Asegurarse de que los resultados se procesan en orden por algoritmo y lenguaje
        resultsForSize.stream()
                .collect(Collectors.groupingBy(ResultData::getAlgorithm))
                .forEach((algorithm, resultsByAlg) -> {
                    Map<String, Double> languageTimes = resultsByAlg.stream()
                            .collect(Collectors.groupingBy(ResultData::getLanguage,
                                    Collectors.averagingDouble(ResultData::getExecutionTime)));

                    // Añadir Java y Python para cada algoritmo, convertidos a milisegundos
                    dataset.addValue(languageTimes.getOrDefault("java", 0.0) / 1_000_000.0, "Java", algorithm);
                    dataset.addValue(languageTimes.getOrDefault("python", 0.0) / 1_000_000.0, "Python", algorithm);
                });

        JFreeChart chart = ChartFactory.createBarChart(
                "Comparación de Tiempos de Ejecución - Tamaño " + currentMatrixSize,
                "Algoritmo",
                "Tiempo (Milisegundos)",
                dataset,
                PlotOrientation.VERTICAL,
                true,  // include legend
                true,
                false);

        configureChart(chart);
        configureChartColors(chart, dataset);
        chartPanel.setChart(chart);
    }

    private void configureChart(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Ajustar el ancho máximo de la barra para que no sea demasiado delgado
        renderer.setMaximumBarWidth(0.20);  // Ajusta esto según lo delgado que quieras que sea cada barra

        // Establecer el margen entre las barras dentro de cada categoría a 0 para que estén pegadas
        renderer.setItemMargin(0.0);  // Esto eliminará el espacio entre las barras Java y Python dentro de cada algoritmo

        // Ajustar el margen entre grupos de barras para separar más los grupos
        plot.getDomainAxis().setCategoryMargin(0.30);  // Incrementa el espacio entre grupos de categorías

        // Configurar colores específicos para las series
        renderer.setSeriesPaint(plot.getDataset().getRowIndex("Java"), Color.RED);
        renderer.setSeriesPaint(plot.getDataset().getRowIndex("Python"), Color.BLUE);

        chartPanel.setChart(chart);
    }


    private void configureChartColors(JFreeChart chart, DefaultCategoryDataset dataset) {
        CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer();

        renderer.setSeriesPaint(dataset.getRowIndex("Java"), Color.RED);
        renderer.setSeriesPaint(dataset.getRowIndex("Python"), Color.BLUE);
    }


}
