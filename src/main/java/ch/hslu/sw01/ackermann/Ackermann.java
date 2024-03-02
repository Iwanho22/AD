package ch.hslu.sw01.ackermann;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Ackermann {
    private static final Logger LOG = LoggerFactory.getLogger(Ackermann.class);
    private static int count = 0;
    private static final Set<Integer> FRAME_COUNT = new HashSet<>();

    public static void main(String[] args) {
        var res = ack(2,2);
        LOG.info("Result {}", res);
        LOG.info("Count of Method invocation: {}", count);
        LOG.info("Max count of stack-frames: {}", Collections.max(FRAME_COUNT));
    }

    private static long ack(long n, long m) {
        count++;
        FRAME_COUNT.add(new Throwable().getStackTrace().length);
        if (n == 0) {
            return m + 1;
        } else if (m == 0) {
            return ack(n-1, 1);
        } else {
            return ack(n-1, ack(n, m-1));
        }
    }
}
