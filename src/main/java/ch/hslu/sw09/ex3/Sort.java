package ch.hslu.sw09.ex3;

public final class Sort {

    private Sort() {
        // ensure not instantiable
    }

    public static <T extends Comparable<T>> T[] insertionSort(T[] data) {
        T element;
        int j;
        for (int i = 1; i < data.length; i++) {
            element = data[i];
            j = i;
            while ((j > 0) && (data[j - 1].compareTo(element) > 0)) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = element;
        }
        return data;
    }

    public static <T extends Comparable<T>> T[] selectionSort(T[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[min].compareTo(data[j]) > 0) {
                    min = j;
                }
            }
            var tmp = data[min];
            data[min] = data[i];
            data[i] = tmp;
        }
        return data;
    }
}
