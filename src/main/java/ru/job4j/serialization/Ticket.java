package ru.job4j.serialization;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement
public class Ticket {

    private  Person passenger;
    @XmlElement
    private  String countryFrom;
    @XmlElement
    private  String countryTo;
    @XmlElement
    private  Long number;
    @XmlElement
    private  boolean is_used;
    @XmlElementWrapper(name = "vouchers")
    @XmlElement(name = "voucher")
    private  int[] voucher;


    public Ticket(Person passenger, String countryFrom, String countryTo, Long number, boolean is_used, int[] voucher) {
        this.passenger = passenger;
        this.countryFrom = countryFrom;
        this.countryTo = countryTo;
        this.number = number;
        this.is_used = is_used;
        this.voucher = voucher;
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
                ", vouchers=" + Arrays.toString(voucher) +
                '}';
    }
}
