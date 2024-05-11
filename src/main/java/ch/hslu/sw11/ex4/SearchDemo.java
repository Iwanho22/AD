package ch.hslu.sw11.ex4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class SearchDemo {
    private static final Logger LOG = LoggerFactory.getLogger(SearchDemo.class);
    public static void main(String[] args) {
        try (final ForkJoinPool pool = new ForkJoinPool()) {
            var startDir = new File("/");
            var task = new SearchTask("package-info.java", startDir);
            LOG.info("Result: {}", pool.invoke(task));
        }
    }
}
