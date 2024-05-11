package ch.hslu.sw11.ex3;

import ch.hslu.sw11.RandomInitTask;
import de.vandermeer.asciitable.AsciiTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public abstract class AbstractMeasurement {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractMeasurement.class);
    private int laps;
    private HashMap<String, List<Long>> times;
    private int fib;


    public AbstractMeasurement(int fib, int laps) {
        this.laps = laps;
        this.times = new HashMap<>();
        this.fib = fib;
    }

    protected abstract Long run(int fib, String options);

    public void startMeasurement(String configuration) {
        times.putIfAbsent(configuration, new ArrayList<>());

        LOG.info("Starting Dry-Run");
        times.get(configuration).add(run(fib, configuration));

        LOG.info("Starting measurements with {} laps", laps);
        for (int i = 0; i < laps; i++) {
            times.get(configuration).add(run(fib, configuration));
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
        for (int i = 1; i < laps + 1; i++) {
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
