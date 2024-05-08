package co.org.uniquindio.persistence;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD) // Para que JAXB sepa que debe acceder a los campos directamente
public class ResultData {
    @XmlElement(required = true)
    private int size;

    @XmlElement(required = true)
    private String algorithm;

    @XmlElement(required = true)
    private String language;

    @XmlElement(required = true)
    private long executionTime;

    // Constructor sin argumentos para JAXB
    public ResultData() {
    }

    // Constructor con argumentos
    public ResultData(int size, String algorithm, String language, long executionTime) {
        this.size = size;
        this.algorithm = algorithm;
        this.language = language;
        this.executionTime = executionTime;
    }

    // Getters y Setters
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
}
