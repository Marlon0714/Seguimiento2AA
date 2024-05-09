package co.org.uniquindio.persistence;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigInteger;

@XmlRootElement(name = "number")
@XmlAccessorType(XmlAccessType.FIELD)
public class NumberWrapper {

    @XmlElement(name = "value")
    private BigInteger value;

    public NumberWrapper() {
    }

    public NumberWrapper(BigInteger value) {
        this.value = value;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }
}
