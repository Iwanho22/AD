package ch.hslu.sw01.fibonacci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class Fibonacci {
    private static final Map<Integer, Integer> FIBONACCI_CACHE = new HashMap<>();

    public static int fiboRec1(int n) {
        // recursion base
        if (n == 0 || n == 1) {
            return n;
        }

        // recursion rule
        return fiboRec1(n - 1) + fiboRec1(n - 2);
    }

    public static int fiboRec2(int n) {
        if (n <= 1) {
            return n;
        }

        if (FIBONACCI_CACHE.containsKey(n)) {
            return FIBONACCI_CACHE.get(n);
        }

        var fib = fiboRec2(n-1) + fiboRec2(n-2);
        FIBONACCI_CACHE.put(n, fib);
        return fib;
    }

    public static int fiboIter(int n) {
        if (n <= 1) {
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
