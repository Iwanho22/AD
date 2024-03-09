package ch.hslu.sw02.ex2.collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedListTest {
    @Test
    public void testSize_sizeShouldBe5() {
        // arrange
        LinkedList<Double> list = new LinkedList<>();

        // act
        list.add(1d);
        list.add(2d);
        list.add(3d);
        list.add(4d);
        list.add(5d);
        list.add(6d);
        list.add(7d);
        list.pop();
        list.removeLast();

        // assert
        assertThat(list.size()).isEqualTo(5);
    }

    @Test
    public void testPush_sizeShouldBeOne() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.push(5);

        // assert
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void testPush_getFirstShouldReturnElement() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.push(5);

        // assert
        assertThat(list.getFirst()).isEqualTo(5);
    }

    @Test
    public void testPush_getLastShouldReturnElement() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.push(5);

        // assert
        assertThat(list.getLast()).isEqualTo(5);
    }

    @Test
    public void testPush_multiple_sizeShouldBeTwo() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.push(4);
        list.push(5);
        list.push(6);

        // assert
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    public void testPush_multiple_getFirstShouldBeLastAdded() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.push(4);
        list.push(5);
        list.push(6);

        // assert
        assertThat(list.getFirst()).isEqualTo(6);
    }

    @Test
    public void testPush_multiple_getLastShouldBeFirstAdded() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.push(4);
        list.push(5);
        list.push(6);

        // assert
        assertThat(list.getLast()).isEqualTo(4);
    }

    @Test
    public void testAddsizeShouldBeOne() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.add(5);

        // assert
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void testAddgetFirstShouldReturnElement() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.add(5);

        // assert
        assertThat(list.getFirst()).isEqualTo(5);
    }

    @Test
    public void testAddgetLastShouldReturnElement() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.add(5);

        // assert
        assertThat(list.getLast()).isEqualTo(5);
    }

    @Test
    public void testAddmultiple_sizeShouldBeTwo() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.add(4);
        list.add(5);
        list.add(6);

        // assert
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    public void testAddmultiple_getFirstShouldBeFirstAdded() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.add(4);
        list.add(5);
        list.add(6);

        // assert
        assertThat(list.getFirst()).isEqualTo(4);
    }

    @Test
    public void testAddmultiple_getLastShouldBeLastAdded() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.add(4);
        list.add(5);
        list.add(6);

        // assert
        assertThat(list.getLast()).isEqualTo(6);
    }

    @Test
    public void testGetFirst_shouldBeLatestAddedItem() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        list.push(6);
        list.push(7);

        // assert
        assertThat(list.getFirst()).isEqualTo(7);
    }

    @Test
    public void testGetLast_shouldBeFirstPushedItem() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        list.push(6);
        list.push(7);

        // assert
        assertThat(list.getLast()).isEqualTo(1);
    }

    @Test
    public void testPop_withOne_ElementShouldBeSame() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);

        // act
        var res = list.pop();

        // assert
        assertThat(res).isEqualTo(1);
    }

    @Test
    public void testPop_withOne_ListShouldBeEmpty() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);

        // act
        list.pop();

        // assert
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    public void testPop_withOne_getFirstShouldReturnNull() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);

        // act
        list.pop();

        // assert
        assertThat(list.getFirst()).isNull();
    }

    @Test
    public void testPop_withOne_getLastShouldReturnNull() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);

        // act
        list.pop();

        // assert
        assertThat(list.getFirst()).isNull();
    }

    @Test
    public void testPop_multiple_getFirstShouldReturn2LatestElement() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);

        // act
        list.pop();

        // assert
        assertThat(list.getFirst()).isEqualTo(2);
    }

    @Test
    public void testPop_multiple_geLastShouldReturnOldestElement() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);

        // act
        list.pop();

        // assert
        assertThat(list.getLast()).isEqualTo(1);
    }

    @Test
    public void testPop_multiple_sizeShouldDecrease() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);

        // act
        list.pop();

        // assert
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void testRemoveLast_withOne_ListShouldBeEmpty() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);

        // act
        list.removeLast();

        // assert
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    public void testRemoveLast_withOne_getFirstShouldReturnNull() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);

        // act
        list.removeLast();

        // assert
        assertThat(list.getFirst()).isNull();
    }

    @Test
    public void testRemoveLast_withOne_getLastShouldReturnNull() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);

        // act
        list.removeLast();

        // assert
        assertThat(list.getLast()).isNull();
    }

    @Test
    public void testRemoveLast_multiple_getLastShouldReturnSecondOldestElement() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);

        // act
        list.removeLast();

        // assert
        assertThat(list.getLast()).isEqualTo(2);
    }

    @Test
    public void testRemoveLast_multiple_getFirstShouldReturnNewestElement() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);

        // act
        list.removeLast();

        // assert
        assertThat(list.getFirst()).isEqualTo(3);
    }

    @Test
    public void testRemoveLast_multiple_sizeShouldDecrease() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);

        // act
        list.removeLast();

        // assert
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void testAddAll_sizeShouldBeSame() {
        // arrange
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.pushAll(numbers);

        // assert
        assertThat(list.size()).isEqualTo(6);
    }

    @Test
    public void testAddAll_getFirst_shouldBeFirstElementInList() {
        // arrange
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.addAll(numbers);

        // assert
        assertThat(list.getFirst()).isEqualTo(1);
    }

    @Test
    public void testPushAll_getLast_shouldBeLastElementInList() {
        // arrange
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.pushAll(numbers);

        // assert
        assertThat(list.getLast()).isEqualTo(6);
    }

    @Test
    public void testPushAll_checkGenericFlexibility() {
        // arrange
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LinkedList<Number> list = new LinkedList<>();

        // act
        list.pushAll(numbers);

        // assert
        assertThat(list.getLast()).isEqualTo(6);
    }

    @Test
    public void testPushAll_ListNotEmpty_ShouldBeAddedAtHead() {
        // arrange
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LinkedList<Integer> list = new LinkedList<>();
        list.push(7);

        // act
        list.pushAll(numbers);

        // assert
        assertThat(list.getFirst()).isEqualTo(1);
        assertThat(list.getLast()).isEqualTo(7);
    }

    @Test
    public void testContains_doesNotContain() {
        // arrange
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LinkedList<Integer> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        var res = list.contains(7);

        // assert
        assertThat(res).isFalse();
    }

    @Test
    public void testContains_doesContain() {
        // arrange
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LinkedList<Integer> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        var res = list.contains(4);

        // assert
        assertThat(res).isTrue();
    }

    @Test
    public void testRemove_contained_sizeShouldDecrease() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d, 5d, 6d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.remove(4d);

        // assert
        assertThat(list.size()).isEqualTo(5);
    }

    @Test
    public void testRemove_notContained_sizeShouldNotDecrease() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d, 5d, 6d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.remove(8d);

        // assert
        assertThat(list.size()).isEqualTo(6);
    }

    @Test
    public void testRemove_firstElement_getFirstShouldBeSecondItem() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d, 5d, 6d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.remove(1d);

        // assert
        assertThat(list.getFirst()).isEqualTo(2);
    }

    @Test
    public void testRemove_firstElement_getLastShouldBeLastItem() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d, 5d, 6d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.remove(1d);

        // assert
        assertThat(list.getLast()).isEqualTo(6);
    }

    @Test
    public void testRemove_lastElement_getLastShouldBeSecondLastItem() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d, 5d, 6d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.remove(6d);

        // assert
        assertThat(list.getLast()).isEqualTo(5);
    }

    @Test
    public void testRemove_LastElement_getFirstShouldBeFirstItem() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d, 5d, 6d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.remove(6d);

        // assert
        assertThat(list.getFirst()).isEqualTo(1);
    }

    @Test
    public void testIterator() {
        // arrange
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LinkedList<Integer> list = new LinkedList<>();
        list.addAll(numbers);

        int i = 1;
        // act & assert
        for (int item : list) {
            assertThat(item).isEqualTo(i);
            i++;
        }
    }

    @Test
    public void testIterator_oneElement() {
        // arrange
        List<Integer> numbers = List.of(1);
        LinkedList<Integer> list = new LinkedList<>();
        list.pushAll(numbers);

        int i = 1;
        // act & assert
        for (int item : list) {
            assertThat(item).isEqualTo(i);
            i++;
        }
    }
}