package ch.hslu.sw10.ex3;

import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class SortEx3Test {
    private static int ARRAY_SIZE = 100;

    @Test
    void testInsertionSort() {
        // arrange
        var data = generateTestData();
        var expected = Arrays.copyOf(data, data.length);
        Arrays.sort(expected);

        // act
        SortEx3.quickSort(data);

        // assert
        assertThat(data).isEqualTo(expected);
    }

    private int[] generateTestData() {
        var data = new int[ARRAY_SIZE];
        var rand = new Random();
        for(int i = 0; i < ARRAY_SIZE; i++) {
            data[i] = rand.nextInt(ARRAY_SIZE);
        }

        return data;
    }
}