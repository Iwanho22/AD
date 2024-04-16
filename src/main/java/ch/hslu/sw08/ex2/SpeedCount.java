/*
 * Copyright 2024 Hochschule Luzern Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.sw08.ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Speed-Test für unterschiedlich impementierte Counters.
 */
public final class SpeedCount {

    private static final Logger LOG = LoggerFactory.getLogger(SpeedCount.class);

    /**
     * Privater Konstruktor.
     */
    private SpeedCount() {
    }

    /**
     * Test für einen Counter.
     * @param counter Zählertyp.
     * @param counts Anzahl Zähl-Vorgänge.
     * @param threads Anzahl Tester-Threads.
     * @return Dauer des Tests in mSec.
     */
    public static long speedTest(Counter counter, int counts, int threads) throws ExecutionException, InterruptedException {
        try (final ExecutorService executor = Executors.newCachedThreadPool()) {
            var start = System.nanoTime();
            List<Future<Integer>> futures = new ArrayList<>(threads);
            for (int i = 0; i < threads; i++) {
                futures.add(executor.submit(new CountTask(counter, counts)));
            }

            for (var future : futures) {
                future.get();
            }
            return (System.nanoTime() - start);
        }
    }

    /**
     * Main-Counter-Test.
     * @param args not used.
     */
    public static void main(final String[] args) throws ExecutionException, InterruptedException {
        final int passes = 200;
        final int threads = 10;
        final int counts = 1_000;
        final Counter counterSync = new SynchronizedCounter();
        long sumSync = 0;
        //dry run
        speedTest(counterSync, counts, threads);
        for (int i = 0; i < passes; i++) {
            sumSync += speedTest(counterSync, counts, threads);
        }
        final Counter counterAtom = new AtomicCounter();
        //dry run
        speedTest(counterAtom, counts, threads);
        long sumAtom = 0;
        for (int i = 0; i < passes; i++) {
            sumAtom += speedTest(counterAtom, counts, threads);
        }
        if (counterSync.get() == 0) {
            LOG.info("Sync counter ok");
            LOG.info("Sync counter average test duration = {} ns", sumSync / (float) passes);
        } else {
            LOG.info("Sync counter failed");
        }
        if (counterAtom.get() == 0) {
            LOG.info("Atom counter ok");
            LOG.info("Atom counter average test duration = {} ns", sumAtom / (float) passes);
        } else {
            LOG.info("Atom counter failed");
        }
    }
}
