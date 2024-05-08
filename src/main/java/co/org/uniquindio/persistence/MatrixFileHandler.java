package co.org.uniquindio.persistence;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MatrixFileHandler {
    private static final String DEFAULT_DIRECTORY = "src/main/resources/matrices";

    private MatrixFileHandler() {
        throw new IllegalAccessError("Utility class");
    }

    public static void saveMatrix(double[][] matrix, String filename) throws Exception {
        filename = filename.endsWith(".xml") ? filename : filename + ".xml";
        Path path = Paths.get(DEFAULT_DIRECTORY);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(MatrixWrapper.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        MatrixWrapper wrapper = new MatrixWrapper(matrix);
        marshaller.marshal(wrapper, new File(path.resolve(filename).toUri()));
    }

    public static double[][] loadMatrix(String filename) throws Exception {
        filename = filename.endsWith(".xml") ? filename : filename + ".xml";
        Path path = Paths.get(DEFAULT_DIRECTORY, filename);

        JAXBContext jaxbContext = JAXBContext.newInstance(MatrixWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        MatrixWrapper wrapper = (MatrixWrapper) unmarshaller.unmarshal(new File(path.toUri()));

        return wrapper.getData();
    }
}


