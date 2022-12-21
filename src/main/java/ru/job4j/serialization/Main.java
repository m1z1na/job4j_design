package ru.job4j.serialization;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {
        final Ticket ticket = new Ticket(new Person( "Иванов Иван Петрович", 18), "Россия", "Казахстан", 23723992L, false, new int[]{55, 433});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(ticket));

    }
}