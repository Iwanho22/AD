package ch.hslu.sw11.ex3;

import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask<Integer> {
    private final int n;

    public FibonacciTask(final int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        int fib = 1;
        if (n <= 2) {
            return fib;
        }

        var taskLeft = new FibonacciTask(n - 1);
        taskLeft.fork();
        var taskRight = new FibonacciTask(n - 2);

        return taskRight.invoke() + taskLeft.join();
    }
}
