package co.org.uniquindio.persistence;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para manejar la persistencia de resultados utilizando archivos XML.
 */
public class ResultFileHandler {

    private static final String LANGUAGE = "python";
    private static final String DEFAULT_DIRECTORY = "src/main/resources/results";
    private static final String FILE_NAME = LANGUAGE + "_results.xml";
    private static final Path filePath = Paths.get(DEFAULT_DIRECTORY, FILE_NAME);

    private ResultFileHandler() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Guarda una lista de resultados en un archivo XML.
     *
     * @param results La lista de resultados a guardar
     * @throws Exception Si ocurre un error durante el proceso de guardado
     */
    public static void saveResults(List<ResultData> results) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Results.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        Results resultsWrapper = new Results();
        resultsWrapper.setResults(results);

        Path path = Paths.get(DEFAULT_DIRECTORY);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        marshaller.marshal(resultsWrapper, new File(path.resolve(FILE_NAME).toUri()));
    }

    public static void saveResult(int size, String algorithm, long executionTime) throws Exception {
        List<ResultData> results = loadResults();
        ResultData result = new ResultData(size, algorithm,LANGUAGE, executionTime);
        results.add(result);
        saveResults(results);
    }

    /**
     * Carga una lista de resultados desde un archivo XML.
     *
     * @return La lista de resultados cargados
     * @throws Exception Si ocurre un error durante el proceso de carga
     */
    public static List<ResultData> loadResults() throws Exception {
        JAXBContext context = JAXBContext.newInstance(Results.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Path path = Paths.get(DEFAULT_DIRECTORY, FILE_NAME);
        if (!Files.exists(path)) {
            return new ArrayList<>(); // Si el archivo no existe, retorna una lista vacía
        }
        Results results = (Results) unmarshaller.unmarshal(new File(path.toUri()));
        return results.getResults();
    }

    /**
     * Cargar una lista de resultados desde un archivo XML, con un filePath específico.
     *
     * @param filePath El path del archivo XML
     * @return La lista de resultados cargados
     * @throws Exception Si ocurre un error durante el proceso de carga
     */
    public static List<ResultData> loadResults(String filePath) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Results.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Results results = (Results) unmarshaller.unmarshal(new File(filePath));
        return results.getResults();
    }
}







