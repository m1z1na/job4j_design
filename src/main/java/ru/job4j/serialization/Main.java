package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) throws JAXBException {
        final Ticket ticket = new Ticket(new Person("Иванов Иван Петрович", 18), "Россия", "Казахстан", 23723992L, false, new int[]{55, 433});

      /*  final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(ticket)); */


        JAXBContext context = JAXBContext.newInstance(Ticket.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(ticket, sw);

        System.out.println(sw.toString());

    }
}