package ch.hslu.sw01.complexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        getComplexity(1);
        LOG.info("");
        getComplexity(2);
        LOG.info("");
        getComplexity(5);
        LOG.info("");
        getComplexity(10);
        LOG.info("");
        getComplexity(20);
        LOG.info("");
        getComplexity(50);
        LOG.info("");
        getComplexity(100);
    }

    public static void getComplexity(int n) {
        LOG.info("log n     {}", Math.log10(n));
        LOG.info("ld n      {}", Math.log(n) / Math.log(2));
        LOG.info("n         {}", n);
        LOG.info("n * log n {}", n * Math.log10(n));
        LOG.info("n^2       {}", n * n);
        LOG.info("n^3       {}", n * n * n);
        LOG.info("2^n       {}", Math.pow(2, n));
        LOG.info("3^n       {}", Math.pow(3, n));
        LOG.info("n!        {}", fac(n));
    }

    public static long fac(int n) {
        long fac = n;

        do {
            n--;
            fac *= n;
        } while (n < 0);
        return fac;
    }
}
