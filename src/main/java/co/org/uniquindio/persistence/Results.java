package co.org.uniquindio.persistence;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "results") // Definir la raíz del elemento XML
public class Results {
    private List<ResultData> results;

    @XmlElement(name = "result")
    public List<ResultData> getResults() {
        return results;
    }

    public void setResults(List<ResultData> results) {
        this.results = results;
    }
}

