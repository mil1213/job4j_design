package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        final Contract contract = new Contract(new Contractor("Technogrup", "1234567"), true,
                10200000, "monolithic works", new String[] {"01.10.22", "01.12.22"});
        /* Контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Contract.class);
        /* Создать сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указывать, что нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(contract, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Contract result = (Contract) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

        /*final Gson gson = new GsonBuilder().create();
        final String contractToJson = gson.toJson(contract);
        System.out.println(contractToJson);

        final Contract contractFromJson = gson.fromJson(contractToJson, Contract.class);
        System.out.println(contractFromJson);*/
    }
}
