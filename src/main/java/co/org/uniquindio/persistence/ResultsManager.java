package co.org.uniquindio.persistence;

import java.util.ArrayList;
import java.util.List;


public class ResultsManager {
    private static final String JAVA_RESULTS_PATH = "src/main/resources/results/java_results.xml";
    private static final String PYTHON_RESULTS_PATH = "src/main/resources/results/python_results.xml";

    private ResultsManager() {
        throw new IllegalAccessError("Utility class");
    }

    public static List<ResultData> getCombinedResults() throws Exception {
        List<ResultData> javaResults = readResults(JAVA_RESULTS_PATH);
        List<ResultData> pythonResults = readResults(PYTHON_RESULTS_PATH);

        List<ResultData> combinedResults = new ArrayList<>(javaResults);
        combinedResults.addAll(pythonResults);
        return combinedResults;
    }

    private static List<ResultData> readResults(String filePath) throws Exception {
        return ResultFileHandler.loadResults(filePath);
    }
}
