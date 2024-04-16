package ch.hslu.sw06.ex2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class Turf {
    private static final Logger LOG = LoggerFactory.getLogger(Turf.class);

    public static void main(String[] args) throws InterruptedException {
        int horseCount = 5;
        var horsesReadyLatch = new CountDownLatch(horseCount);
        var horsesFinishedLatch = new CountDownLatch(horseCount);
        Sync starterBox = new Latch();
        for (int i = 1; i <= horseCount; i++) {
            Thread.startVirtualThread(new RaceHorse(starterBox, "Horse " + i, horsesReadyLatch, horsesFinishedLatch));
        }

        horsesReadyLatch.await();
        LOG.info("Start...");
        starterBox.release();
        horsesFinishedLatch.await();
    }
}
