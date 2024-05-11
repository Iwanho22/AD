package ch.hslu.sw11.ex4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

public class SearchTask extends CountedCompleter<String> {
    private static final Logger LOG = LoggerFactory.getLogger(SearchTask.class);

    private String name;
    private File dir;
    private SearchTask parent;
    private AtomicReference<String> resultPath;

    public SearchTask(String name, File dir) {
        this.name = name;
        this.dir = dir;
        this.resultPath = new AtomicReference<>("");
    }

    public SearchTask(String name, File dir, CountedCompleter<?> parent, AtomicReference<String> resultPath) {
        super(parent);
        this.name = name;
        this.dir = dir;
        this.resultPath = resultPath;
    }

    @Override
    public void compute() {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    this.addToPendingCount(1);
                    new SearchTask(name, file, this, resultPath).fork();
                } else if (name.equalsIgnoreCase(file.getName()) && this.resultPath.compareAndSet("", dir.getAbsolutePath())) {
                    LOG.info("Found File {} at path {}", file.getName(), dir.getAbsolutePath());
                    this.quietlyCompleteRoot();
                    break;
                }
            }
        }
        this.tryComplete();
    }

    @Override
    public String getRawResult() {
        return resultPath.toString();
    }
}
