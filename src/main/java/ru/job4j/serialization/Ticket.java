package ru.job4j.serialization;

import java.util.Arrays;

public class Ticket {

    private final Person passenger;
    private final String countryFrom;
    private final String countryTo;
    private final Long number;
    private final boolean is_used;
    private final int[] vouchers;


    public Ticket(Person passenger, String countryFrom, String countryTo, Long number, boolean is_used, int[] vouchers) {
        this.passenger = passenger;
        this.countryFrom = countryFrom;
        this.countryTo = countryTo;
        this.number = number;
        this.is_used = is_used;
        this.vouchers = vouchers;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "passenger=" + passenger +
                ", countryFrom='" + countryFrom + '\'' +
                ", countryTo='" + countryTo + '\'' +
                ", number=" + number +
                ", is_used=" + is_used +
                ", vouchers=" + Arrays.toString(vouchers) +
                '}';
    }
}
