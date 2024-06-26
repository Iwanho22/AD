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

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * Codebeispiel zu RecursiveAction für die Sortierung eines int-Arrays.
 */
@SuppressWarnings("serial")
public final class MergesortTask extends RecursiveAction {

    private int threshold;
    private final int[] array;
    private final int min;
    private final int max;

    /**
     * Erzeugt einen Array-Sortier Task.
     *
     * @param array Interger-Array.
     */
    public MergesortTask(final int[] array, int threshold) {
        this(array, 0, array.length, threshold);
    }

    private MergesortTask(final int[] array, final int min, final int max, int threshold) {
        this.array = array;
        this.min = min;
        this.max = max;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if (max - min < threshold) {
//            InsertionSort.exec(array, min, max);
            Arrays.sort(array, min, max);
        } else {
            final int mid = min + (max - min) / 2;
            invokeAll(new MergesortTask(array, min, mid, threshold), new MergesortTask(array, mid, max, threshold));
            merge(min, mid, max);
        }
    }

    private void merge(final int min, int mid, int max) {
        // vordere Hälfte von array in Hilfsarray buf kopieren
        int[] buf = Arrays.copyOfRange(array, min, mid);
        int i = 0;
        int j = min;
        int k = mid;
        while (i < buf.length) {
            // jeweils das nächstgrösste Element zurückkopieren
            // bei k == max, Rest von buf zurückkopieren, falls vorhanden
            if (k == max || buf[i] < array[k]) {
                array[j] = buf[i];
                i++;
            } else {
                array[j] = array[k];
                k++;
            }
            j++;
        }
    }
}
