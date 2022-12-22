package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte myByte = 120;
        short myShort = 1123;
        int myInt = 64536;
        long myLong = 2147483648L;
        char myChar = 'a';
        double myDouble = 4.12;
        float myFloat = 3.14f;
        boolean myBoolean = true;

        LOG.debug(" My test primitives: byte : {}, short : {} ", myByte, myShort);
        LOG.debug(" My test primitives:  int : {}, long : {}", myInt, myLong);
        LOG.debug(" My test primitives: char : {}, double : {}", myChar, myDouble);
        LOG.debug(" My test primitives: float : {}, boolean : {}", myFloat, myBoolean);
    }
}
