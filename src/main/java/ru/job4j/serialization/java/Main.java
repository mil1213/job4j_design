package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        final Contract contract = new Contract(new Contractor("Technogrup", "1234567"), true,
                10200000, "monolithic works", new String[] {"01.10.22", "01.12.22"});
        System.out.println("XML" + "\n");

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

        System.out.println("\n" + "JSON" + "\n");

        /*из строки*/
        JSONObject jsonContractor = new JSONObject("{\"name\":\"Technogrup\",\"idNumber\":\"1234567\"}");

        /*из list*/
        List<String> listData = new ArrayList<>();
        listData.add("01.10.22");
        listData.add("31.12.22");
        JSONArray jsonDates = new JSONArray(listData);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contractor", jsonContractor);
        jsonObject.put("status", contract.isStatus());
        jsonObject.put("sum", contract.getSum());
        jsonObject.put("subject", contract.getSubject());
        jsonObject.put("schedule", jsonDates);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(contract).toString());
    }
}
