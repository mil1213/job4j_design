package ru.job4j.serialization.java;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contractor")
public class Contractor {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String idNumber;

    public Contractor(String name, String idNumber) {
        this.name = name;
        this.idNumber = idNumber;
    }

    public Contractor() {
    }

    @Override
    public String toString() {
        return "Contractor{"
                + "name='" + name + '\''
                + ", idNumber='" + idNumber + '\''
                + '}';
    }
}
