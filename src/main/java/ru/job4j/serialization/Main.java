package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


public class Main {

    public static void main(String[] args) throws JAXBException, IOException {
        final Ticket ticket = new Ticket(new Person("Иванов Иван Петрович", 18), "Россия", "Казахстан", 23723992L, false, new int[]{55, 433});

      /*  final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(ticket)); */


       /* JAXBContext context = JAXBContext.newInstance(Ticket.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(ticket, System.out);*/

        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Ticket.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(ticket, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Ticket result = (Ticket) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }




    }
}