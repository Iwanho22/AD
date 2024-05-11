package ch.hslu.sw11.ex3;

import ch.hslu.sw01.fibonacci.Fibonacci;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;

public class FibonacciDemo {
    private static final Logger LOG = LoggerFactory.getLogger(FibonacciDemo.class);

    public static void main(String[] args) {
        var laps = 5;
        var measurement = new FibonacciMeasurement(40, laps);
        measurement.startMeasurement("Parallel-F");
        measurement.startMeasurement("Parallel-C");
        measurement.startMeasurement("Recursive");
        measurement.startMeasurement("Iterative");
        measurement.renderResult();
    }

    private static class FibonacciMeasurement extends AbstractMeasurement {

        public FibonacciMeasurement(int fib, int laps) {
            super(fib, laps);
        }

        @Override
        protected Long run(int fib, String options) {
            long start = 0;
            long end = 0;
            var result = 0;
            var time = switch (options) {
                case "Parallel-F" -> {
                    try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
                        start = System.nanoTime();
                        var fibonacciTask = new FibonacciTask(fib);
                        result = forkJoinPool.invoke(fibonacciTask);
                        end = System.nanoTime();
                    }
                    yield end - start;
                }
                case "Parallel-C" -> {
                    start = System.nanoTime();
                    var fibonacciTask = new FibonacciTask(fib);
                    result = fibonacciTask.invoke();
                    end = System.nanoTime();
                    yield end - start;
                }
                case "Recursive" -> {
                    start = System.nanoTime();
                    result = Fibonacci.fiboRec1(fib);
                    end = System.nanoTime();
                    yield end - start;
                }
                case "Iterative" -> {
                    start = System.nanoTime();
                    result = Fibonacci.fiboIter(fib);
                    end = System.nanoTime();
                    yield end - start;
                }
                default -> throw new IllegalStateException("Unexpected value: " + options);
            };
            LOG.info("Result {}", result);
            return time;
        }
    }
}
