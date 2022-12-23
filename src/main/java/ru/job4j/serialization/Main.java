package ru.job4j.serialization;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class Main {

    public static void main(String[] args) throws JAXBException {
        final Ticket ticket = new Ticket(new Person("Иванов Иван Петрович", 18), "Россия", "Казахстан", 23723992L, false, new int[]{55, 433});

      /*  final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(ticket)); */


        JAXBContext context = JAXBContext.newInstance(Ticket.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(ticket, System.out);



    }
}