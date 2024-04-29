package ch.hslu.sw10;

import de.vandermeer.asciitable.AsciiTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuickSortMeasurement {
    private static final int LENGTH = 502_021;
    private static final int LAPS = 5;
    private static final Logger LOG = LoggerFactory.getLogger(QuickSortMeasurement.class);

    public static void main(String[] args) {
        var data = generateRandomChars(LENGTH);

        var times = measeure(data);

        AsciiTable table = new AsciiTable();
        table.addRule();
        var headers = new ArrayList<String>();
        headers.add("Lap");
        headers.add("Time");
        table.addRow(headers);
        table.addRule();

        for (int i = 0; i < times.size(); i++) {
            if (i == 0) {
                addTableRow(table, "Dry run", times.get(i));
            } else {
                addTableRow(table, String.valueOf(i), times.get(i));
            }
        }
        System.out.println(table.render());
    }

    private static void addTableRow(AsciiTable table, String key, Long measurement) {
        var data = new ArrayList<String>();
        data.add(key);
        data.add(measurement / 1_000_000.0 + "ms");
        table.addRow(data);
        table.addRule();
    }

    private static List<Long> measeure(char[] data) {
        var testData = Arrays.copyOf(data, data.length);
        List<Long> times = new ArrayList<>();
        LOG.info("Starting dry run");
        var dryRunStart = System.nanoTime();
        Sort.quickSort(testData);
        var dryRunEnd = System.nanoTime();
        LOG.info("Dry run took {} ns", (dryRunEnd - dryRunStart));
        times.add(dryRunEnd - dryRunStart);

        LOG.info("Starting hot measurement with {} laps", LAPS);

        for (int i = 0; i < LAPS; i++) {
            testData = Arrays.copyOf(data, data.length);
            var start = System.nanoTime();
            Sort.quickSort(testData);
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
}
