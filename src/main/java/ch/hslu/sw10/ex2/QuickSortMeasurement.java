package ch.hslu.sw10.ex2;

import de.vandermeer.asciitable.AsciiTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuickSortMeasurement {
    private static final int LENGTH = 20_000_000;
    private static final int LAPS = 5;
    private static final Logger LOG = LoggerFactory.getLogger(QuickSortMeasurement.class);

    public static void main(String[] args) {
//        var data = generateRandomChars(LENGTH);
        var data = generateRandomInts(LENGTH);

        var times = measure(data);

        AsciiTable table = new AsciiTable();
        table.addRule();
        var headers = new ArrayList<String>();
        headers.add("Lap");
        headers.add("Time");
        table.addRow(headers);
        table.addRule();

        addTableRow(table, "Dry run", (double) times.getFirst());
        for (int i = 1; i < times.size(); i++) {
            addTableRow(table, String.valueOf(i), (double) times.get(i));
        }
        addTableRow(table, "Average",
                (times.stream().skip(1).mapToLong(Long::longValue).sum() / (double) LAPS));
        System.out.println(table.render());
    }

    private static void addTableRow(AsciiTable table, String key, Double measurement) {
        var data = new ArrayList<String>();
        data.add(key);
        data.add(measurement / 1_000_000.0 + "ms");
        table.addRow(data);
        table.addRule();
    }

    private static List<Long> measure(char[] data) {
        var testData = Arrays.copyOf(data, data.length);
        List<Long> times = new ArrayList<>();
        LOG.info("Starting dry run");
        var dryRunStart = System.nanoTime();
        SortEx2.quickSort(testData);
        var dryRunEnd = System.nanoTime();
        LOG.info("Dry run took {} ns", (dryRunEnd - dryRunStart));
        times.add(dryRunEnd - dryRunStart);

        LOG.info("Starting hot measurement with {} laps", LAPS);

        for (int i = 0; i < LAPS; i++) {
            testData = Arrays.copyOf(data, data.length);
            var start = System.nanoTime();
            SortEx2.quickSort(testData);
            var end = System.nanoTime();
            LOG.info("Lap {} took {} ns", i, (end - start));
            times.add(end - start);
        }
        return times;
    }
    private static List<Long> measure(int[] data) {
        var testData = Arrays.copyOf(data, data.length);
        List<Long> times = new ArrayList<>();
        LOG.info("Starting dry run");
        var dryRunStart = System.nanoTime();
        SortEx2.quickSort(testData);
        var dryRunEnd = System.nanoTime();
        LOG.info("Dry run took {} ns", (dryRunEnd - dryRunStart));
        times.add(dryRunEnd - dryRunStart);

        LOG.info("Starting hot measurement with {} laps", LAPS);

        for (int i = 0; i < LAPS; i++) {
            testData = Arrays.copyOf(data, data.length);
            var start = System.nanoTime();
            SortEx2.quickSort(testData);
            var end = System.nanoTime();
            LOG.info("Lap {} took {} ns", i, (end - start));
            times.add(end - start);
        }
        return times;
    }

    private static char[] generateRandomChars(int length) {
        var data = new char[length];
        var rand = new Random();
        for (int i = 0; i < length; i++) {
            data[i] = (char) (rand.nextInt('z' - 'a') + 'a');
        }
        return data;
    }

    private static int[] generateRandomInts(int length) {
        var data = new int[length];
        var dataList = new ArrayList<Integer>(length);
        for(int i =0; i < length; i++) {
            dataList.add(i);
        }

        var rand = new Random();
        for (int i = 0; i < length; i++) {
            data[i] = dataList.get(rand.nextInt(dataList.size()));
        }
        return data;
    }
}
