package ru.job4j.serialization;

public class Contact2  {
    private final String phone;

    public Contact2(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}