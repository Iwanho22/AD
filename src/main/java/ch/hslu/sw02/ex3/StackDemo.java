package ch.hslu.sw02.ex3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StackDemo {
    public static final Logger LOG = LoggerFactory.getLogger(StackDemo.class);
    public static void main(String[] args) {
        Stack<String> stack = new SimpleStack<>();
        stack.push("toll");
        stack.push("sind");
        stack.push("Datenstrukturen");

        stack.forEach(LOG::info);
    }
}
