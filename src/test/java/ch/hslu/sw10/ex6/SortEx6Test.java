package ch.hslu.sw10.ex6;

import ch.hslu.sw10.ex2.SortEx2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SortEx6Test {
    private static int ARRAY_SIZE = 10;

    @Test
    void testInsertionSort() {
        // arrange
        var data = generateTestData();
        var expected = deepCopyArray(data);
        Arrays.sort(expected);

        // act
        SortEx6.quickSort(data);

        // assert
        assertThat(data).isEqualTo(expected);
    }

    private Integer[] generateTestData() {
        var data = new Integer[ARRAY_SIZE];
        var rand = new Random();
        for(int i = 0; i < ARRAY_SIZE; i++) {
            data[i] = rand.nextInt(ARRAY_SIZE);
        }

        return data;
    }

    private Integer[] deepCopyArray(Integer[] data) {
        return Arrays.stream(data).toArray(Integer[]::new);
    }
}