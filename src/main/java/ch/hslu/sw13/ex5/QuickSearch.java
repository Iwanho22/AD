package ch.hslu.sw13.ex5;

public class QuickSearch {
    public static int search(final String text, final String pattern) {
        int n = text.length();
        int m = pattern.length();
        // ASCII-Alphabet
        int range = 256;

        int[] shift = new int[range];

        // initialize shift array
        for (int i = 0; i < range - m; i++) {
            shift[i] = m + 1;
        }

        // override
        for (int i = 0; i < m; i++) {
            shift[pattern.charAt(i)] = m - i;
        }

        // search
        int i = 0;
        int j = 0;

        do {
            if (text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            } else {
                if (m + i < n) {
                    i += shift[text.charAt(i + m)];
                    j = 0;
                } else {
                    break;
                }
            }
        } while (j < m && i + m <= n);

        if (j == m) {
            return i;
        }

        return -1;
    }

    public static int searchOptimalMismatch(final String text, final String pattern) {
        int n = text.length();
        int m = pattern.length();
        // ASCII-Alphabet
        int range = 256;

        int[] shift = new int[range];

        // initialize shift array
        for (int i = 0; i < range - m; i++) {
            shift[i] = m + 1;
        }

        // override
        for (int i = 0; i < m; i++) {
            shift[pattern.charAt(i)] = m - i;
        }

        // create pattern
        MissMatch[] missMatchPattern = new MissMatch[m];
        for (int i = 0; i < m; i++) {
            var missMatch = new MissMatch(pattern.charAt(i), i);
            missMatchPattern[i] = missMatch;
        }

        // search
        int i = 0;
        int j = 0;

        do {
            if (text.charAt(i + missMatchPattern[j].originalIndex) == missMatchPattern[j].symbol) {
                j++;
            } else {
                // switch miss-match pattern
                if (j != 0) {
                    var tmp = missMatchPattern[j];
                    missMatchPattern[j] = missMatchPattern[j-1];
                    missMatchPattern[j-1] = tmp;
                }

                if (m + i < n) {
                    i += shift[text.charAt(i + m)];
                    j = 0;
                } else {
                    break;
                }
            }
        } while (j < m && i + m <= n);

        if (j == m) {
            return i;
        }

        return -1;
    }

    private static final class MissMatch {
        char symbol;
        int originalIndex;

        public MissMatch(char symbol, int originalIndex) {
            this.symbol = symbol;
            this.originalIndex = originalIndex;
        }
    }
}
