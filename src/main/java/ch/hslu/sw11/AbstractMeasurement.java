package ch.hslu.sw11;

import de.vandermeer.asciitable.AsciiTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public abstract class AbstractMeasurement {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractMeasurement.class);
    private int[] data;
    private int laps;
    private HashMap<String, List<Long>> times;

    public AbstractMeasurement(int size, int laps) {
        this.data = new int[size];
        this.laps = laps;
        this.times = new HashMap<>();
    }

    protected abstract Long run(int[] data, String options);

    public void startMeasurement(String configuration) {
        times.putIfAbsent(configuration, new ArrayList<>());

        LOG.info("Generating Test Data");
        try (final ForkJoinPool pool = new ForkJoinPool()) {
            RandomInitTask initTask = new RandomInitTask(data, 100);
            pool.invoke(initTask);
        }

        LOG.info("Starting Dry-Run");
        times.get(configuration).add(run(Arrays.copyOf(data, data.length), configuration));

        LOG.info("Starting measurements with {} laps", laps);
        for (int i = 0; i < laps; i++) {
            times.get(configuration).add(run(Arrays.copyOf(data, data.length), configuration));
        }
        LOG.info("Finished Measurement");
    }

    public void renderResult() {
        AsciiTable table = new AsciiTable();
        table.addRule();
        var headers = new ArrayList<String>();
        headers.add("Lap");
        headers.addAll(times.keySet());
        table.addRow(headers);
        table.addRule();

        var dryRuns = times.values().stream().map(List::getFirst).map(Long::doubleValue).collect(Collectors.toList());
        addTableRow(table, "Dry run", dryRuns);
        for (int i = 1; i < laps; i++) {
            var iCopy = i;
            var time = times.values().stream().map(t -> t.get(iCopy)).map(Long::doubleValue).toList();
            addTableRow(table, String.valueOf(i), time);
        }
        var averages = times.values().stream().map(time -> time.stream().skip(1).mapToLong(Long::valueOf).average().orElse(0L)).toList();
        addTableRow(table, "Average", averages);
        System.out.println(table.render());
    }

    private void addTableRow(AsciiTable table, String key,  List<Double> measurement) {
        var data = new ArrayList<String>();
        data.add(key);
        measurement.stream().mapToDouble(Double::doubleValue).forEach(m -> data.add(m / 1_000_000 + "ms"));
        table.addRow(data);
        table.addRule();
    }
}
