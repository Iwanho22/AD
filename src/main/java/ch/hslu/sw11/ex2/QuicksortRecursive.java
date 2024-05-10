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

/**
 * Codevorlage zu RecursiveAction für die Sortierung eines int-Arrays.
 */
public final class QuicksortRecursive {
    private static int M = 25;

    /**
     * Public method exposed to client, sorts given array using QuickSort
     * Algorithm in Java.
     *
     * @param array input array.
     */
    public static void quicksort(int[] array) {
        QuicksortRecursive.quicksort(array, 0, array.length - 1);
    }

    /**
     * Recursive quicksort logic.
     *
     * @param array    input array.
     * @param startIdx start index of the array.
     * @param endIdx   end index of the array.
     */
    public static void quicksort(int[] array, int startIdx, int endIdx) {
        int up = startIdx;
        int down = endIdx - 1;
        int median = mediaOfThree(array, startIdx, endIdx);
        exchange(array, endIdx, median);
        int t = array[endIdx];
        boolean allChecked = false;

        if (endIdx - startIdx <= M) {
            insertionSort(array, startIdx, endIdx);
        } else {
            do {
                while (array[up] < t) {
                    up++;
                }
                while (array[down] >= t && down > up) {
                    down--;
                }
                if (down > up) {
                    exchange(array, up, down);
                    up++;
                    down--;
                } else {
                    allChecked = true;
                }
            } while (!allChecked);
            exchange(array, up, endIdx);
            if (startIdx < (up - 1)) {
                quicksort(array, startIdx, up - 1);
            }
            if (up + 1 < endIdx) {
                quicksort(array, up + 1, endIdx);
            }
        }
    }

    public static int partialSort(final int[] array, final int startIdx, final int endIdx) {
        int up = startIdx;
        int down = endIdx - 1;
        int median = mediaOfThree(array, startIdx, endIdx);
        exchange(array, endIdx, median);
        int t = array[endIdx];
        boolean allChecked = false;

        do {
            while (array[up] < t) {
                up++;
            }
            while (array[down] >= t && down > up) {
                down--;
            }
            if (down > up) {
                exchange(array, up, down);
                up++;
                down--;
            } else {
                allChecked = true;
            }
        } while (!allChecked);
        exchange(array, up, endIdx);
        return up;
    }

    private static void exchange(final int[] array, final int i, final int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void insertionSort(int[] data, int start, int end) {
        int element;
        int j;
        for (int i = start + 1; i <= end; i++) {
            element = data[i];
            j = i;
            while ((j > start) && (data[j - 1] > element)) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = element;
        }
    }

    private static int mediaOfThree(int[] data, int start, int end) {
        int first = data[start];
        int second = data[(end - 1) / 2];
        int last = data[end - 1];

        int median;
        if (first > second) {
            if (first < last) {
                median = start;
            } else if (second > last) {
                median = (end - 1) / 2;
            } else {
                median = end - 1;
            }
        } else {
            if (first > last) {
                median = start;
            } else if (second < last) {
                median = (end - 1) / 2;
            } else {
                median = end - 1;
            }
        }
        return median;
    }
}
