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
package ch.hslu.sw11.ex1;


import java.util.concurrent.ForkJoinPool;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Performance Vergleich der Mergesort-Implementation.
 */
public final class DemoMergesort {

    private static final Logger LOG = LoggerFactory.getLogger(DemoMergesort.class);

    /**
     * Privater Konstruktor.
     */
    private DemoMergesort() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final int size = 300_000_000;
        final int laps = 5;
        var cores = Runtime.getRuntime().availableProcessors();
        int calculatedThreshold = (size + cores -1) / cores;
        var mergeSort = new MergesortParallelMeasurement(size, laps);
        mergeSort.startMeasurement("Threshold:"+calculatedThreshold);
        mergeSort.startMeasurement("Threshold:100");
        mergeSort.startMeasurement("Threshold:150");
        mergeSort.startMeasurement("Threshold:500");
        mergeSort.startMeasurement("Threshold:1000");
        mergeSort.renderResult();

//        var mergeSortRec = new MergesortRecursiveMeasurement(size, laps);
//        mergeSortRec.startMeasurement("Standard");
//        mergeSortRec.renderResult();
    }

    private static class MergesortParallelMeasurement extends AbstractMeasurement {

        public MergesortParallelMeasurement(int size, int laps) {
            super(size, laps);
        }

        @Override
        protected Long run(int[] data, String options) {
            var threshold = Integer.parseInt(options.split(":")[1]);
            long start = 0;
            long end = 0;
            try (final ForkJoinPool pool = new ForkJoinPool()) {
                start = System.nanoTime();
                final MergesortTask sortTask = new MergesortTask(data, threshold);
                pool.invoke(sortTask);
                end = System.nanoTime();
            }
            return end - start;
        }
    }

    private static class MergesortRecursiveMeasurement extends AbstractMeasurement {

        public MergesortRecursiveMeasurement(int size, int laps) {
            super(size, laps);
        }

        @Override
        protected Long run(int[] data, String options) {
            long start = System.nanoTime();
            MergesortRecursive.mergeSort(data);
            return System.nanoTime() - start;
        }
    }

}
