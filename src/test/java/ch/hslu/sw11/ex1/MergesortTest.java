package ch.hslu.sw11.ex1;

import ch.hslu.sw11.RandomInitTask;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MergesortTest {
    @Test
    public void testRecursiveMergeSort() {
        // arrange
        int[] data = new int[100];
        try (var pool = new ForkJoinPool()) {
            RandomInitTask initTask = new RandomInitTask(data, 100);
            pool.invoke(initTask);
        }

        // act
        MergesortRecursive.mergeSort(data);

        // assert
        var expected = Arrays.copyOf(data, 100);
        Arrays.sort(expected);
        assertThat(data).isEqualTo(expected);
    }

    @Test
    public void testMergeSort() {
        // arrange
        int[] data = new int[100];
        try (var pool = new ForkJoinPool()) {
            RandomInitTask initTask = new RandomInitTask(data, 100);
            pool.invoke(initTask);
            var task = new MergesortTask(data, 5);

            //  act
            pool.invoke(task);
        }

        // assert
        var expected = Arrays.copyOf(data, 100);
        Arrays.sort(expected);
        assertThat(data).isEqualTo(expected);
    }

}