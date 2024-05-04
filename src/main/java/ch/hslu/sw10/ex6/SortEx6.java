package ch.hslu.sw10.ex6;

public class SortEx6 {

    public static <T extends Comparable<T>> void quickSort(T[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] data, int left, int right) {
        int up = left;
        int down = right - 1;
        int median = mediaOfThree(data, left, right);
        exchange(data, right, median);
        T t = data[right];
        boolean allChecked = false;

        do {
            while (data[up].compareTo(t) < 0) {
                up++;
            }
            while (data[down].compareTo(t) > 0 && down > up) {
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

    private static <T extends Comparable<T>> int mediaOfThree(T[] data, int start, int end) {
        T first = data[start];
        T second = data[(end - 1) / 2];
        T last = data[end - 1];

        int median;
        if (first.compareTo(second) > 0) {
            if (first.compareTo(last) < 0) {
                median = start;
            } else if (second.compareTo(last) > 0) {
                median = (end - 1) / 2;
            } else {
                median = end - 1;
            }
        } else {
            if (first.compareTo(last) > 0) {
                median = start;
            } else if (second.compareTo(last) < 0) {
                median = (end - 1) / 2;
            } else {
                median = end - 1;
            }
        }
        return median;
    }

    private static <T> void exchange(T[] data, int first, int second) {
        T tmp = data[first];
        data[first] = data[second];
        data[second] = tmp;
    }
}
