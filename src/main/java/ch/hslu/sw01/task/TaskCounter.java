package ch.hslu.sw01.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskCounter {
    public static final Logger LOG = LoggerFactory.getLogger(TaskCounter.class);

    private static int taskCounter = 0;

    public static void main(String[] args) {
        for (int i = 1; i < 10000; i = i * 2) {
            task(i);
            LOG.info("N: {}", i);
            LOG.info("Total invocations : {}", taskCounter );
            LOG.info("");
            taskCounter = 0;
        }


    }

    public static void task(int n) {
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

    private static void task3() {
        taskCounter++;
    }

    private static void task2() {
        taskCounter++;
    }

    private static void task1() {
        taskCounter++;
    }
}
