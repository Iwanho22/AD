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

        for (int i = rightPart.length - 1; i >= 0; i--) {
            if (rightPart[i] < leftPart[i]) {
                if (isSizeOfNumberEven) {
                    rightPart[i]++;
                } else if (middle + 1 >= 10) {
                    rightPart[i] = (rightPart[i] + 1) % 10;
                    Arrays.fill(leftPart, 0);
                }
                middle = (middle + 1) % 10;
            }
        }

        var rightParAsString = Arrays.stream(rightPart).mapToObj(String::valueOf).collect(Collectors.joining(""));
        return rightParAsString + (isSizeOfNumberEven ? "" : middle) + rightParAsString;
    }

    private int[] toIntArray(String number) {
        return Arrays.stream(number.split("")).mapToInt(Integer::parseInt).toArray();
    }
}
