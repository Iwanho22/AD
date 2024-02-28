package ch.hslu.sw01.fibonacci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("Fibbonacci 5: {}", Fibonacci.fiboRec1(5));
    }


}
