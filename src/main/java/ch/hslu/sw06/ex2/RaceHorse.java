package ch.hslu.sw06.ex2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RaceHorse implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(RaceHorse.class);
    private final Sync startSignal;
    private final String name;
    private final Random random;
    private CountDownLatch horseReady;
    private CountDownLatch horseFinished;

    public RaceHorse(Sync startSignal, String name, CountDownLatch horseReady, CountDownLatch horseFinished) {
        this.startSignal = startSignal;
        this.name = name;
        this.random = new Random();
        this.horseReady = horseReady;
        this.horseFinished = horseFinished;
    }

    @Override
    public void run() {
        LOG.info("Racehorse {} went into box", name);
        try {
            horseReady.countDown();
            startSignal.acquire();
            LOG.info("Racehorse {} starts running", name);
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            LOG.debug("Thread interrupted" ,e);
            Thread.currentThread().interrupt();
        }
        LOG.info("Racehorse {} finished.", name);
        horseFinished.countDown();
    }
}
