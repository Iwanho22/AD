package ch.hslu.sw06.ex1.alternative;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class MyTask implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(MyTask.class);
    private final CountDownLatch latch;

    public MyTask(final CountDownLatch lock) {
        this.latch = lock;
    }

    @Override
    public void run() {
        LOG.info("Waiting...");
        try {
            latch.await();
        } catch (InterruptedException e) {
            return;
        }
        LOG.info("Waiting terminated...");
    }
}
