package ch.hslu.sw04.ex2;

import ch.hslu.sw02.ex3.SimpleStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;


public class TestHelper {
    public static final Logger LOG = LoggerFactory.getLogger(TestHelper.class);
    public static final int NUMBER_OF_TEST_DATA = 1_000_000;
        public static final int NUMBER_OF_REPETITIONS = 1000;

    private static TestData[] createTestData() {
        var data = new TestData[NUMBER_OF_TEST_DATA];

        for (int i = 0; i < data.length; i++) {
            data[i] = new TestData(i, String.valueOf(i));
        }

        return data;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        var testData = createTestData();
        long tDataCreated = System.nanoTime() - startTime;
        LOG.info("Time Needed to create Data: {} ns", tDataCreated);

        measureStack(testData);
        LOG.info("-------------------------------------------------------------------------------------------");
        LOG.info("-------------------------------------------------------------------------------------------");
        LOG.info("-------------------------------------------------------------------------------------------");
        measureCustomStack(testData);
        LOG.info("-------------------------------------------------------------------------------------------");
        LOG.info("-------------------------------------------------------------------------------------------");
        LOG.info("-------------------------------------------------------------------------------------------");
        measureDeque(testData);
    }

    private static void measureStack(TestData[] data) {
        LOG.info("Starting dry-run for java.util.Stack");

        var stack = new Stack<TestData>();
        stack.setSize(NUMBER_OF_TEST_DATA);
        long tDryRunStarted = System.nanoTime();
        for (int i = 0; i < data.length; i++) {
            stack.push(data[i]);
        }
        long tDryRunStopped = System.nanoTime();
        LOG.info("Dry-run took {} ns", tDryRunStopped - tDryRunStarted);

        LOG.info("Starting actual measurement with {} repetitions", NUMBER_OF_REPETITIONS);

        var timeNeededByRepetition = new long[NUMBER_OF_REPETITIONS];
        for (int i = 0; i < NUMBER_OF_REPETITIONS; i++) {
            stack.clear();
            long tRepetitionStarted = System.nanoTime();
            for (int j = 0; j < data.length; j++) {
                stack.push(data[j]);
            }
            long tRepetitionEnded = System.nanoTime();
            timeNeededByRepetition[i] = tRepetitionEnded - tRepetitionStarted;
        }

        long totalTimeNeeded = 0;
        for (int i = 0; i < NUMBER_OF_REPETITIONS; i++) {
            //LOG.info("{}. Repetition needed {} ns", i + 1, timeNeededByRepetition[i]);
            totalTimeNeeded += timeNeededByRepetition[i];
        }
        LOG.info("Total Time: {} ns", totalTimeNeeded);
        LOG.info("Average Time per repetition: {} ns", totalTimeNeeded / NUMBER_OF_REPETITIONS);
        LOG.info("Average Time per insert: {} ns", totalTimeNeeded / (NUMBER_OF_REPETITIONS * NUMBER_OF_TEST_DATA));
    }

    private static void measureCustomStack(TestData[] data) {
        LOG.info("Starting dry-run for Custom-Stack");

        var stack = new SimpleStack<>(NUMBER_OF_TEST_DATA);
        long tDryRunStarted = System.nanoTime();
        for (int i = 0; i < data.length; i++) {
            stack.push(data[i]);
        }
        long tDryRunStopped = System.nanoTime();
        LOG.info("Dry-run took {} ns", tDryRunStopped - tDryRunStarted);

        LOG.info("Starting actual measurement with {} repetitions", NUMBER_OF_REPETITIONS);

        var timeNeededByRepetition = new long[NUMBER_OF_REPETITIONS];
        for (int i = 0; i < NUMBER_OF_REPETITIONS; i++) {
            stack.clear();
            long tRepetitionStarted = System.nanoTime();
            for (int j = 0; j < data.length; j++) {
                stack.push(data[j]);
            }
            long tRepetitionEnded = System.nanoTime();
            timeNeededByRepetition[i] = tRepetitionEnded - tRepetitionStarted;
        }

        long totalTimeNeeded = 0;
        for (int i = 0; i < NUMBER_OF_REPETITIONS; i++) {
            //LOG.info("{}. Repetition needed {} ns", i + 1, timeNeededByRepetition[i]);
            totalTimeNeeded += timeNeededByRepetition[i];
        }
        LOG.info("Total Time: {} ns", totalTimeNeeded);
        LOG.info("Average Time per repetition: {} ns", totalTimeNeeded / NUMBER_OF_REPETITIONS);
        LOG.info("Average Time per insert: {} ns", totalTimeNeeded / (NUMBER_OF_REPETITIONS * NUMBER_OF_TEST_DATA));
    }

    private static void measureDeque(TestData[] data) {
        LOG.info("Starting dry-run for java.util.LinkedList");

        Deque<TestData> deque = new ArrayDeque<>();
        long tDryRunStarted = System.nanoTime();
        for (int i = 0; i < data.length; i++) {
            deque.push(data[i]);
        }
        long tDryRunStopped = System.nanoTime();
        LOG.info("Dry-run took {} ns", tDryRunStopped - tDryRunStarted);

        LOG.info("Starting actual measurement with {} repetitions", NUMBER_OF_REPETITIONS);

        var timeNeededByRepetition = new long[NUMBER_OF_REPETITIONS];
        for (int i = 0; i < NUMBER_OF_REPETITIONS; i++) {
            deque.clear();
            long tRepetitionStarted = System.nanoTime();
            for (int j = 0; j < data.length; j++) {
                deque.push(data[j]);
            }
            long tRepetitionEnded = System.nanoTime();
            timeNeededByRepetition[i] = tRepetitionEnded - tRepetitionStarted;
        }

        long totalTimeNeeded = 0;
        for (int i = 0; i < NUMBER_OF_REPETITIONS; i++) {
            //LOG.info("{}. Repetition needed {} ns", i + 1, timeNeededByRepetition[i]);
            totalTimeNeeded += timeNeededByRepetition[i];
        }
        LOG.info("Total Time: {} ns", totalTimeNeeded);
        LOG.info("Average Time per repetition: {} ns", totalTimeNeeded / NUMBER_OF_REPETITIONS);
        LOG.info("Average Time per insert: {} ns", totalTimeNeeded / (NUMBER_OF_REPETITIONS * NUMBER_OF_TEST_DATA));
    }
}
