package ch.hslu.sw10.ex2;

public class SortEx2 {

    private static void quickSort(int[] data, int left, int right) {
        int up = left;
        int down = right - 1;
        int t = data[right];
        boolean allChecked = false;

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

    public static void quickSort(int[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private static void quickSort(char[] data, int left, int right) {
        int up = left;
        int down = right - 1;
        char t = data[right];
        boolean allChecked = false;

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

    public static void quickSort(char[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private static void exchange(char[] data, int first, int second) {
        char tmp = data[first];
        data[first] = data[second];
        data[second] = tmp;
    }

    private static void exchange(int[] data, int first, int second) {
        int tmp = data[first];
        data[first] = data[second];
        data[second] = tmp;
    }
}
