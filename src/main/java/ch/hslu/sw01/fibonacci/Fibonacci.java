package ch.hslu.sw01.fibonacci;

public class Fibonacci {
    public static int fiboRec1(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fiboRec1(n - 1) + fiboRec1(n - 2);
    }
}
