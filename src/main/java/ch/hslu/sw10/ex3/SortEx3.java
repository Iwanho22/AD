package ch.hslu.sw10.ex3;

import ch.hslu.sw09.ex3.Sort;

public class SortEx3 {
    private static int M = 25;

    private static void quickSort(int[] data, int left, int right) {
        int up = left;
        int down = right - 1;
        int t = data[right];
        boolean allChecked = false;

        if (right - left <= M) {
            insertionSort(data, left, right);
        } else {
            do {
                while (data[up] < t) {
                    up++;
                }
                while (data[down] >= t && down > up) {
                    down--;
                }
                if (down > up) {
                    exchange(data, up, down);
                    up++;
                    down--;
                } else {
                    allChecked = true;
                }
            } while (!allChecked);
            exchange(data, up, right);
            if (left < (up - 1)) {
                quickSort(data, left, up - 1);
            }
            if (up + 1 < right) {
                quickSort(data, up + 1, right);
            }
        }
    }

    public static void quickSort(int[] data) {
        quickSort(data, 0, data.length - 1);
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

    private static void exchange(int[] data, int first, int second) {
        int tmp = data[first];
        data[first] = data[second];
        data[second] = tmp;
    }
}
