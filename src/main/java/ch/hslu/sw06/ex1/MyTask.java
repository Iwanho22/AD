package ch.hslu.sw06.ex1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTask implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(MyTask.class);
    private final Object lock;

    public MyTask(final Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        LOG.info("Waiting...");
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                LOG.error("Thread interrupted: ", e);
                Thread.currentThread().interrupt();
                return;
            }
        }
        LOG.info("Waiting terminated...");
    }
}
