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
package ch.hslu.sw11.ex2;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import ch.hslu.sw11.RandomInitTask;
import ch.hslu.sw11.ex1.DemoMergesort;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Vergleich von verschiedenen Quicksort-Implementationen.
 */
public final class DemoQuicksort {

    private static final Logger LOG = LoggerFactory.getLogger(DemoQuicksort.class);

    /**
     * Privater Konstruktor.
     */
    private DemoQuicksort() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final int size = 300_000_000;
        var cores = Runtime.getRuntime().availableProcessors();
        int calculatedThreshold = (size + cores - 1) / cores;
        var laps = 5;

        var quickSortMeasurement = new QuickSortMeasurement(size, laps);
        quickSortMeasurement.startMeasurement("Threshold:" + calculatedThreshold);

//        var recursiveQuickSortMeasurement = new RecursiveQuickSortMeasurement(size, laps, quickSortMeasurement.getData());
//        recursiveQuickSortMeasurement.startMeasurement("Standard");

        var arraysSortMeasurement = new ArraysSortMeasurement(size, laps, quickSortMeasurement.getData());
        arraysSortMeasurement.startMeasurement("Standard");

        quickSortMeasurement.renderResult();
//        recursiveQuickSortMeasurement.renderResult();
        arraysSortMeasurement.renderResult();
    }

    private static class QuickSortMeasurement extends AbstractMeasurement {
        public QuickSortMeasurement(final int size, final int laps) {
            super(size, laps);
        }

        @Override
        protected Long run(int[] data, String options) {
            long start = 0;
            long end = 0;
            try (final ForkJoinPool pool = new ForkJoinPool()) {
                var threshold = Integer.parseInt(options.split(":")[1]);
                final QuicksortTask sortTask = new QuicksortTask(data, threshold);
                start = System.nanoTime();
                pool.invoke(sortTask);
                end = System.nanoTime();
            }
            return end - start;
        }
    }

    private static class RecursiveQuickSortMeasurement extends AbstractMeasurement {
        public RecursiveQuickSortMeasurement(final int size, final int laps, int[] data) {
            super(size, laps, data);
        }

        @Override
        protected Long run(int[] data, String options) {
            long start = 0;
            long end = 0;

            start = System.nanoTime();
            QuicksortRecursive.quicksort(data);
            end = System.nanoTime();

            return end - start;
        }
    }

    private static class ArraysSortMeasurement extends AbstractMeasurement {
        public ArraysSortMeasurement(final int size, final int laps, int[] data) {
            super(size, laps, data);
        }

        @Override
        protected Long run(int[] data, String options) {
            long start = 0;
            long end = 0;

            start = System.nanoTime();
            Arrays.sort(data);
            end = System.nanoTime();

            return end - start;
        }
    }
}
