package ru.job4j.serialization.java;

import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;

@XmlRootElement(name = "contract")
@XmlAccessorType(XmlAccessType.FIELD)
public class Contract {
    private Contractor contractor;
    private boolean status;
    private int sum;
    private String subject;
    @XmlElementWrapper(name = "dates")
    @XmlElement(name = "date")
    private String[] schedule;

    public Contract(Contractor contractor, boolean status, int sum, String subject, String[] schedule) {
        this.contractor = contractor;
        this.status = status;
        this.sum = sum;
        this.subject = subject;
        this.schedule = schedule;
    }

    public Contract() {
    }


    @Override
    public String toString() {
        return "Contract{"
                + "contractor=" + contractor
                + ", status=" + status
                + ", sum=" + sum
                + ", subject='" + subject + '\''
                + ", schedule=" + Arrays.toString(schedule)
                + '}';
    }

    public Contractor getContractor() {
        return contractor;
    }

    public boolean isStatus() {
        return status;
    }

    public int getSum() {
        return sum;
    }

    public String getSubject() {
        return subject;
    }

    public String[] getSchedule() {
        return schedule;
    }
}
