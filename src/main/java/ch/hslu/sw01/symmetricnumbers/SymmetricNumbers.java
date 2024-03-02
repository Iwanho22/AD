package ch.hslu.sw01.symmetricnumbers;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SymmetricNumbers {

    public boolean checkSymmetry(String numb1) {
        // reverse number 2
        var stringBuilder = new StringBuilder(numb1);
        var numb1Reversed = stringBuilder.reverse().toString();

        return numb1Reversed.equals(numb1);
    }

    public String getNexSymmetricNumber(String number) {
        if (number.length() == 1) {
            return number;
        }
        int partLength = number.length() / 2;
        boolean isSizeOfNumberEven = number.length() % 2 == 0;

        int[] rightPart = toIntArray(number.substring(0, partLength));
        int middle = 0;
        int[] leftPart = toIntArray(number.substring(partLength));
        if (!isSizeOfNumberEven) {
            middle = Integer.parseInt(number.substring(partLength, partLength + 1));
            leftPart = toIntArray(number.substring(partLength + 1));
        }

        if (checkSymmetry(number)) {
            return number;
        }

        var lastIndex = rightPart.length - 1;
        for (int start = 0; start < rightPart.length; start++) {
            var end = lastIndex - start;
            if (rightPart[start] < leftPart[end]) {
                if (isSizeOfNumberEven) {
                    rightPart[lastIndex]++;
                    Arrays.fill(leftPart, 0);
                } else if (middle + 1 >= 10) {
                    rightPart[lastIndex] = (rightPart[lastIndex] + 1) % 10;
                    Arrays.fill(leftPart, 0);
                }
                middle = (middle + 1) % 10;
            }
        }

        var rightParAsString = Arrays.stream(rightPart).mapToObj(String::valueOf).collect(Collectors.joining(""));
        return rightParAsString + (isSizeOfNumberEven ? "" : middle) + new StringBuilder(rightParAsString).reverse();
    }

    private int[] toIntArray(String number) {
        return Arrays.stream(number.split("")).mapToInt(Integer::parseInt).toArray();
    }
}
