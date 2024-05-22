package ch.hslu.sw13.ex3;

public class KNP {
    public static int search(final String text, final String pattern) {
        final int n = text.length();
        final int m = pattern.length();
        int i = 0;
        int j = 0;

        int[] next = initNext(pattern);

        do {
            if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        } while (j < m && i < n);

        if (j == m) {
            return i - m;
        }

        return -1;
    }

    private static int[] initNext(final String pattern) {
        final int m = pattern.length();
        final int[] next = new int[m];
        int i = 0;
        int j = -1;
        next[0] = -1;

        do {
            if (j == -1 || (pattern.charAt(i) == pattern.charAt(j))) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        } while (i < m - 1);

        return next;
    }
}
