package ch.hslu.sw10.ex4;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class FixedSizeHeapTest {

    @Test
    public void testInsert() {
        // arrange
        var heap = new FixedSizeHeap<Integer>(10);

        // act
        for (int i = 0; i < 10; i++) {
            heap.insert(i);
        }

        // assert -> no exception
    }

    @Test
    public void testExtract() {
        // arrange
        var heap = new FixedSizeHeap<Integer>(10);
        for (int i = 0; i < 10; i++) {
            heap.insert(i);
        }

        // act
        while (!heap.isEmpty()) {
            heap.extractFirst();
        }

        // assert -> no exception
    }

    @Test
    public void testInsertAndExtract() {
        // arrange
        var heap = new FixedSizeHeap<Integer>(10);

        // act
        for (int i = 0; i < 10; i++) {
            heap.insert(i);
        }

        // assert
        var last = -1;
        while (!heap.isEmpty()) {
            var tmp = heap.extractFirst();
            assertThat(tmp).isGreaterThan(last);
            last = tmp;
        }
    }

    @Test
    public void testInsertAndExtract_withRandomNumbers() {
        // arrange
        var heap = new FixedSizeHeap<Integer>(10);

        // act
        heap.insert(20);
        heap.insert(10);
        heap.insert(10);
        heap.insert(5);
        heap.insert(12);
        heap.insert(7);
        heap.insert(50);

        // assert
        var last = -1;
        while (!heap.isEmpty()) {
            var tmp = heap.extractFirst();
            assertThat(tmp).isGreaterThanOrEqualTo(last);
            last = tmp;
        }
    }

    @Test
    public void testIsEmpty_shouldBeTrue() {
        // arrange
        var heap = new FixedSizeHeap<Integer>(10);

        // assert
        var res = heap.isEmpty();

        // assert
        assertThat(res).isTrue();
    }

    @Test
    public void testIsEmpty_shouldBeFalse() {
        // arrange
        var heap = new FixedSizeHeap<Integer>(10);
        heap.insert(10);

        // assert
        var res = heap.isEmpty();

        // assert
        assertThat(res).isFalse();
    }
}