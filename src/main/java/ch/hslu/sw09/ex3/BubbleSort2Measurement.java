package ch.hslu.sw09.ex3;

import de.vandermeer.asciitable.AsciiTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class BubbleSort2Measurement {
    private static final Logger LOG = LoggerFactory.getLogger(SelectionSortMeasurement.class);
    private static final int TEST_DATA_COUNT = 100_000;
    private static final int LAPS = 5;

    public static void main(String[] args) {
        var sortedData = generateSortedTestData();
        var reversedData = generateReversedTestData();
        var randomData = generateRandomtData();

        LOG.info("Starting measurement for sorted data");
        var timesForSorted = measeure(sortedData);
        var timeReverse = measeure(reversedData);
        var timeRandom = measeure(randomData);

        // Create table
        AsciiTable table = new AsciiTable();
        table.addRule();
        var headers = new ArrayList<String>();
        headers.add("Data");
        headers.add("Dry Run");
        for (int i = 1; i <= LAPS; i++) {
            headers.add("Lap " + i);
        }
        headers.add("Average");
        table.addRow(headers);
        table.addRule();

        addTableRow(table, "sorted", timesForSorted);
        addTableRow(table, "reversed", timeReverse);
        addTableRow(table, "random", timeRandom);

        System.out.println(table.render());

    }

    private static void addTableRow(AsciiTable table, String key, List<Long> measurements) {
        var data = new ArrayList<String>();
        data.add(key);
        measurements.forEach(time -> data.add(String.valueOf(time / 1_000_000.0)));
        data.add(measurements.stream().skip(1).mapToLong(Long::longValue).average().orElse(0) / 1_000_000 + " ms");
        table.addRow(data);
        table.addRule();
    }

    private static List<Long> measeure(Integer[] data) {
        var testData = deepCopyArray(data);
        List<Long> times = new ArrayList<>();
        LOG.info("Starting dry run");
        var dryRunStart = System.nanoTime();
        Sort.bubbleSort2(testData);
        var dryRunEnd = System.nanoTime();
        LOG.info("Dry run took {} ns", (dryRunEnd - dryRunStart));
        times.add(dryRunEnd - dryRunStart);

        LOG.info("Starting hot measurement with {} laps", LAPS);

        for (int i = 0; i < LAPS; i++) {
            testData = deepCopyArray(data);
            var start = System.nanoTime();
            Sort.bubbleSort2(testData);
            var end = System.nanoTime();
            LOG.info("Lap {} took {} ns", i, (end - start));
            times.add(end - start);
        }
        return times;
    }


    private static Integer[] generateSortedTestData() {
        var data = new Integer[TEST_DATA_COUNT];
        for (int i = 0; i < TEST_DATA_COUNT; i++) {
            data[i] = i;
        }
        return data;
    }

    private static Integer[] generateReversedTestData() {
        var data = new Integer[TEST_DATA_COUNT];
        for (int i = TEST_DATA_COUNT - 1; i >= 0; i--) {
            data[TEST_DATA_COUNT - 1 - i] = i;
        }
        return data;
    }

    private static Integer[] generateRandomtData() {
        var data = new Integer[TEST_DATA_COUNT];
        var random = new Random();
        for (int i = TEST_DATA_COUNT - 1; i >= 0; i--) {
            data[i] = random.nextInt(Integer.MAX_VALUE);
        }
        return data;
    }

    private static Integer[] deepCopyArray(Integer[] data) {
        return Arrays.stream(data).toArray(Integer[]::new);
    }
}
