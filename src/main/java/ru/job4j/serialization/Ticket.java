package ru.job4j.serialization;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement
public class Ticket {
    @XmlElement
    private  Person passenger;
    @XmlElement
    private  String countryFrom;
    @XmlElement
    private  String countryTo;
    @XmlElement
    private  Long number;
    @XmlElement
    private  boolean is_used;
    @XmlElement
    private  int[] vouchers;


    public Ticket(Person passenger, String countryFrom, String countryTo, Long number, boolean is_used, int[] vouchers) {
        this.passenger = passenger;
        this.countryFrom = countryFrom;
        this.countryTo = countryTo;
        this.number = number;
        this.is_used = is_used;
        this.vouchers = vouchers;
    }

    public Ticket( ) {

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
