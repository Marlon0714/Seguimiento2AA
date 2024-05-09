package co.org.uniquindio.persistence;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NumberFileHandler {
    private static final String DEFAULT_DIRECTORY = "src/main/resources/numbers";

    private NumberFileHandler() {
        throw new IllegalAccessError("Utility class");
    }

    public static void saveNumber(BigInteger number, String filename) throws Exception {
        filename = filename.endsWith(".xml") ? filename : filename + ".xml";
        Path path = Paths.get(DEFAULT_DIRECTORY);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(NumberWrapper.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        NumberWrapper wrapper = new NumberWrapper(number);
        marshaller.marshal(wrapper, new File(path.resolve(filename).toUri()));
    }

    public static BigInteger loadNumber(String filename) throws Exception {
        filename = filename.endsWith(".xml") ? filename : filename + ".xml";
        Path path = Paths.get(DEFAULT_DIRECTORY, filename);

        JAXBContext jaxbContext = JAXBContext.newInstance(NumberWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        NumberWrapper wrapper = (NumberWrapper) unmarshaller.unmarshal(new File(path.toUri()));

        return wrapper.getValue();
    }
}


