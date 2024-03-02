package ch.hslu.sw01.fibonacci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class Fibonacci {
    private static final Map<Integer, Integer> FIBONACCI_CACHE = new HashMap<>();

    static {
        FIBONACCI_CACHE.put(0, 0);
        FIBONACCI_CACHE.put(1, 1);
    }

    public static int fiboRec1(int n) {
        // recursion base
        if (n == 0 || n == 1) {
            return n;
        }

        // recursion rule
        return fiboRec1(n - 1) + fiboRec1(n - 2);
    }

    public static int fiboRec2(int n) {
        if (FIBONACCI_CACHE.containsKey(n) || n <= 1) {
            return n;
        }

        int previous = 0;
        if (FIBONACCI_CACHE.containsKey(n - 1)) {
            previous = FIBONACCI_CACHE.get(n - 1);
        } else {
            previous = fiboRec2(n - 1);
            FIBONACCI_CACHE.putIfAbsent(n - 1, previous);
        }

        int preprevious = 0;
        if (FIBONACCI_CACHE.containsKey(n - 2)) {
            preprevious = FIBONACCI_CACHE.get(n - 2);
        } else {
            preprevious = fiboRec2(n - 2);
            FIBONACCI_CACHE.putIfAbsent(n - 2, preprevious);
        }

        FIBONACCI_CACHE.putIfAbsent(n, previous + preprevious);
        return previous + preprevious;
    }

    public static int fiboIter(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        var previous = 0;
        var current = 1;

        for (int i = 0; i < n - 1; i++) {
            var swap = previous + current;
            previous = current;
            current = swap;
        }

        return current;
    }
}
