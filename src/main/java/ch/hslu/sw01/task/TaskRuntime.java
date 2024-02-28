package ch.hslu.sw01.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskRuntime {
    public static final Logger LOG = LoggerFactory.getLogger(TaskRuntime.class);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 128; i = i * 2) {
            var start = System.currentTimeMillis();
            task(i);
            var end = System.currentTimeMillis();
            LOG.info("N: {}", i);
            LOG.info("Time : {}", end - start);
            LOG.info("");
        }


    }

    public static void task(int n) throws InterruptedException {
        task1();
        task1();
        task1();
        task1();
        for (int i = 0; i < n; i++) {
            task2();
            task2();
            task2();
            for (int j = 0; j < n; j++) {
                task3();
                task3();
            }
        }
    }

    private static void task3() throws InterruptedException {
        Thread.sleep(5);
    }

    private static void task2() throws InterruptedException {
        Thread.sleep(4);
    }

    private static void task1() throws InterruptedException {
        Thread.sleep(3);
    }
}
