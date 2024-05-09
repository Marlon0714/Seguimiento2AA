package co.org.uniquindio.views;

import co.org.uniquindio.persistence.ResultData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultsViewer extends JFrame {
    private ChartPanel chartPanel;

    public ResultsViewer(List<ResultData> results) {
        super("Resultados de Algoritmos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        chartPanel = new ChartPanel(null);
        add(chartPanel, BorderLayout.CENTER);

        JPanel controlsPanel = new JPanel();
        controlsPanel.add(new JLabel("Resultados del caso de prueba más grande:"));
        add(controlsPanel, BorderLayout.NORTH);

        updateChart(results);
    }

    private void updateChart(List<ResultData> results) {
        // Agrupar resultados por tamaño y obtener el tamaño más grande
        Map<Integer, List<ResultData>> groupedResults = results.stream()
                .collect(Collectors.groupingBy(ResultData::getSize));
        int largestSize = groupedResults.keySet().stream().max(Integer::compare).orElse(-1);

        // Obtener resultados solo para el tamaño más grande
        List<ResultData> resultsForLargestSize = groupedResults.getOrDefault(largestSize, List.of());

        // Ordenar resultados por tiempo de ejecución en orden creciente
        Map<String, Double> resultsDataset = resultsForLargestSize.stream()
                .collect(Collectors.groupingBy(
                        ResultData::getAlgorithm,
                        Collectors.averagingDouble(ResultData::getExecutionTime)
                ));

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Agregar resultados al dataset
        resultsDataset.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()) // Ordenar por valor
                .forEach(entry -> dataset.addValue(entry.getValue() / 1_000_000.0, "Tiempo (Milisegundos)", entry.getKey()));

        JFreeChart chart = ChartFactory.createBarChart(
                "Comparación de Tiempos de Ejecución - Tamaño " + largestSize + " dígitos",
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
        renderer.setMaximumBarWidth(0.20);

        // Ajustar el margen entre grupos de barras para separar más los grupos
        plot.getDomainAxis().setCategoryMargin(0.30);

        // Rotar las etiquetas de las categorías en el eje X para mejorar la visibilidad
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
    }

    private void configureChartColors(JFreeChart chart, DefaultCategoryDataset dataset) {
        CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(dataset.getRowIndex("Tiempo (Milisegundos)"), Color.RED);
    }
}
