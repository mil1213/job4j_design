package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {

        byte b = 22;
        short s = 30000;
        int i = 3500000;
        long l = 1000000000;
        float f = 0.5F;
        double d = 1.678492;
        boolean bool = true;
        char c = 'a';

        LOG.debug("byte : {}, short : {}, int : {}, long : {}, float : {}, double : {}, boolean : {}, char : {}", b, s, i, l, f, d, bool, c);
    }
}
