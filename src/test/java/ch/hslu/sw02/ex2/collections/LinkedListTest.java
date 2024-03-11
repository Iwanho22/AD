package ch.hslu.sw02.ex2.collections;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    public void testAdd_sizeShouldBeOne() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.add(5);

        // assert
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void testAdd_getFirstShouldReturnElement() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.add(5);

        // assert
        assertThat(list.getFirst()).isEqualTo(5);
    }

    @Test
    public void testAdd_getLastShouldReturnElement() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        list.add(5);

        // assert
        assertThat(list.getLast()).isEqualTo(5);
    }

    @Test
    public void testAdd_multiple_sizeShouldBeTwo() {
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
    public void testAdd_multiple_getFirstShouldBeFirstAdded() {
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
    public void testAdd_multiple_getLastShouldBeLastAdded() {
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
    public void testAdd_shouldChange() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        var changed = list.add(4);


        // assert
        assertThat(changed).isTrue();
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
    public void testRemoveLast_emptyList_shouldThrowException() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act & assert
        assertThatThrownBy(list::removeLast).isInstanceOf(NoSuchElementException.class).hasMessage("List is empty.");
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
    public void testAddAll_withIndex0_listShouldChange() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(3);
        list.add(4);
        list.add(5);

        // act
        var changed = list.addAll(0, List.of(1, 2));

        // assert
        assertThat(changed).isTrue();
    }

    @Test
    public void testAddAll_withIndex0_shouldBeAddedAtStart() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(3);
        list.add(4);
        list.add(5);

        // act
        list.addAll(0, List.of(1, 2));

        // assert
        assertThat(list.getFirst()).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.get(3)).isEqualTo(4);
        assertThat(list.getLast()).isEqualTo(5);
    }

    @Test
    public void testAddAll_withIndex3_shouldBeAddedAtEnd() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // act
        list.addAll(3, List.of(4, 5));

        // assert
        assertThat(list.getFirst()).isEqualTo(1);
        assertThat(list.get(3)).isEqualTo(4);
        assertThat(list.getLast()).isEqualTo(5);
    }

    @Test
    public void testAddAll_withIndex1_shouldBeAddedInMiddle() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(4);

        // act
        list.addAll(1, List.of(2, 3));

        // assert
        assertThat(list.getFirst()).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.getLast()).isEqualTo(4);
    }

    @Test
    public void testAddAll_withIndexMinus1_shouldThrowIndexOutOfBounds() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(4);

        // act & assert
        assertThatThrownBy(() -> list.addAll(-1, List.of(2, 3))).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testAddAll_withIndexToHigh_shouldThrowIndexOutOfBounds() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(4);

        // act & assert
        assertThatThrownBy(() -> list.addAll(3, List.of(2, 3))).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testAdd_withIndex0_shouldBeAddedAtStart() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(3);
        list.add(4);
        list.add(5);

        // act
        list.add(0, 2);

        // assert
        assertThat(list.getFirst()).isEqualTo(2);
    }

    @Test
    public void testAdd_withIndex3_shouldBeAddedAtEnd() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // act
        list.add(3,4);

        // assert
        assertThat(list.getLast()).isEqualTo(4);
    }

    @Test
    public void testAdd_withIndex3_sizeShouldIncrease() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // act
        list.add(3,4);

        // assert
        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    public void testAdd_withIndex1_shouldBeAddedInMiddle() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(3);

        // act
        list.add(1, 2);

        // assert
        assertThat(list.getFirst()).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.getLast()).isEqualTo(3);
    }

    @Test
    public void testAdd_withIndexMinus1_shouldThrowIndexOutOfBounds() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(4);

        // act & assert
        assertThatThrownBy(() -> list.add(-1, 2)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testAdd_withIndexToHigh_shouldThrowIndexOutOfBounds() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(4);

        // act & assert
        assertThatThrownBy(() -> list.add(3, 2)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testRemove_withIndex0_shouldRemoveAtStart() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(3);
        list.add(4);
        list.add(5);

        // act
        list.remove(0);

        // assert
        assertThat(list.getFirst()).isEqualTo(4);
    }

    @Test
    public void testRemove_withIndex3_shouldRemoveAtEnd() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // act
        list.remove(2);

        // assert
        assertThat(list.getLast()).isEqualTo(2);
    }

    @Test
    public void testRemove_withIndex3_sizeShouldDencrease() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // act
        list.remove(1);

        // assert
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void testRemove_withIndex1_shouldRemoveInMiddle() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // act
        list.remove(1);

        // assert
        assertThat(list.getFirst()).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(3);
    }

    @Test
    public void testRemove_withIndexMinus1_shouldThrowIndexOutOfBounds() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(4);

        // act & assert
        assertThatThrownBy(() -> list.remove(-1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testRemove_withIndexToHigh_shouldThrowIndexOutOfBounds() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(4);

        // act & assert
        assertThatThrownBy(() -> list.remove(3)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testIndexOf_notContained_ShouldReturnMinus1() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // act
        var res = list.indexOf(4);

        // assert
        assertThat(res).isEqualTo(-1);
    }

    @Test
    public void testIndexOf_first_ShouldReturn0() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // act
        var res = list.indexOf(1);

        // assert
        assertThat(res).isEqualTo(0);
    }

    @Test
    public void testIndexOf_last_ShouldReturn2() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // act
        var res = list.indexOf(3);

        // assert
        assertThat(res).isEqualTo(2);
    }

    @Test
    public void testIndexOf_middle_ShouldReturn1() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // act
        var res = list.indexOf(2);

        // assert
        assertThat(res).isEqualTo(1);
    }

    @Test
    public void testIndexOf_duplicated_shouldReturnFirst() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(2);

        // act
        var res = list.indexOf(2);

        // assert
        assertThat(res).isEqualTo(1);
    }

    @Test
    public void testLastIndexOf_notContained_ShouldReturnMinus1() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // act
        var res = list.lastIndexOf(4);

        // assert
        assertThat(res).isEqualTo(-1);
    }

    @Test
    public void testLastIndexOf_first_ShouldReturn0() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // act
        var res = list.lastIndexOf(1);

        // assert
        assertThat(res).isEqualTo(0);
    }

    @Test
    public void testLastIndexOf_last_ShouldReturn2() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // act
        var res = list.lastIndexOf(3);

        // assert
        assertThat(res).isEqualTo(2);
    }

    @Test
    public void testLastIndexOf_middle_ShouldReturn1() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // act
        var res = list.lastIndexOf(2);

        // assert
        assertThat(res).isEqualTo(1);
    }

    @Test
    public void testLastIndexOf_duplicated_shouldReturnLast() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(2);

        // act
        var res = list.lastIndexOf(2);

        // assert
        assertThat(res).isEqualTo(2);
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
    public void testRemoveAll_noElementContained_shouldNotChange() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d, 5d, 6d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        var changed = list.removeAll(List.of(7d, 8d));

        // assert
        assertThat(changed).isFalse();
    }

    @Test
    public void testRemoveAll_oneElementContained_shouldHaveChanged() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d, 5d, 6d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        var changed = list.removeAll(List.of(6d, 8d));

        // assert
        assertThat(changed).isTrue();
    }

    @Test
    public void testRemoveAll_allContained_shouldBeEmpty() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d, 5d, 6d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.removeAll(List.of(1d, 2d, 3d, 4d, 5d, 6d));

        // assert
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    public void testRemoveAll_allContained_shouldRemoveOnlyContained() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.removeAll(List.of(2d, 3d));

        // assert
        assertThat(list.getFirst()).isEqualTo(1);
        assertThat(list.getLast()).isEqualTo(4);
    }

    @Test
    public void testRemoveAll_notaAlContained_shouldRemoveOnlyContained() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.removeAll(List.of(1d, 3d, 9d));

        // assert
        assertThat(list.getFirst()).isEqualTo(2);
        assertThat(list.getLast()).isEqualTo(4);
    }

    @Test
    public void testRemoveAll_notAllContained_sizeShouldDecrase() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.removeAll(List.of(2d, 3d));

        // assert
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void testRetainAll_emptyList_shouldChange() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        var changed = list.retainAll(List.of());

        // assert
        assertThat(changed).isTrue();
    }

    @Test
    public void testRetainAll_emptyList_shouldRemoveAll() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.retainAll(List.of());

        // assert
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    public void testRetainAll_sameList_shouldNotChange() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        var changed = list.retainAll(List.of(1d, 2d, 3d, 4d));

        // assert
        assertThat(changed).isFalse();
    }

    @Test
    public void testRetainAll_containsAll_shouldChange() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        var changed = list.retainAll(List.of(1d, 2d));

        // assert
        assertThat(changed).isTrue();
    }

    @Test
    public void testRetainAll_containsTwo_shouldRemoveNotIntersectedElements() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.retainAll(List.of(1d, 2d, 7d));

        // assert
        assertThat(list.contains(3d)).isFalse();
        assertThat(list.contains(4d)).isFalse();
    }

    @Test
    public void testClear_fullList_shouldBeEmpty() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.clear();

        // assert
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    public void testClear_fullList_sizeShouldBe0() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.clear();

        // assert
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    public void testGet_Second_ShouldReturnSecondElement() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        var res = list.get(1);

        // assert
        assertThat(res).isEqualTo(2);
    }

    @Test
    public void testGet_indexMinus1_ShouldThrowException() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act & assert
        assertThatThrownBy(() -> list.get(-1)).isInstanceOf(IndexOutOfBoundsException.class).hasMessage("Index -1 out " +
                "of bounds for list with size 4.");
    }

    @Test
    public void testGet_index4_ShouldThrowException() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act & assert
        assertThatThrownBy(() -> list.get(4)).isInstanceOf(IndexOutOfBoundsException.class).hasMessage("Index 4 out " +
                "of bounds for list with size 4.");
    }

    @Test
    public void testSet_index0_ChangeFirstElement() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        list.set(0, 5d);

        // assert
        assertThat(list.getFirst()).isEqualTo(5d);
    }

    @Test
    public void testSet_index0_shouldReturnOldElement() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        var res = list.set(0, 5d);

        // assert
        assertThat(res).isEqualTo(1d);
    }

    @Test
    public void testSet_indexMinus1_ShouldThrowException() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act & assert
        assertThatThrownBy(() -> list.get(-1)).isInstanceOf(IndexOutOfBoundsException.class).hasMessage("Index -1 out " +
                "of bounds for list with size 4.");
    }

    @Test
    public void testSet_index4_ShouldThrowException() {
        // arrange
        List<Double> numbers = List.of(1d, 2d, 3d, 4d);
        LinkedList<Double> list = new LinkedList<>();
        list.pushAll(numbers);

        // act & assert
        assertThatThrownBy(() -> list.get(4)).isInstanceOf(IndexOutOfBoundsException.class).hasMessage("Index 4 out " +
                "of bounds for list with size 4.");
    }

    @Test
    public void testContainsAll_emptyList_ShouldBeTrue() {
        // arrange
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LinkedList<Integer> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        var res = list.containsAll(Collections.emptyList());

        // assert
        assertThat(res).isTrue();
    }

    @Test
    public void testContainsAll_containsAllElements_ShouldBeTrue() {
        // arrange
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LinkedList<Integer> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        var res = list.containsAll(List.of(2, 3, 4, 5));

        // assert
        assertThat(res).isTrue();
    }

    @Test
    public void testContainsAll_containsOneElement_ShouldBeFalse() {
        // arrange
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LinkedList<Integer> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        var res = list.containsAll(List.of(2, 7, 8, 9));

        // assert
        assertThat(res).isFalse();
    }

    @Test
    public void testContainsAll_notContainsOneElement_ShouldBeFalse() {
        // arrange
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LinkedList<Integer> list = new LinkedList<>();
        list.pushAll(numbers);

        // act
        var res = list.containsAll(List.of(2, 3, 4, 9));

        // assert
        assertThat(res).isFalse();
    }

    @Test
    public void testIsEmpty_emptyList_shouldBeTrue() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        var res = list.isEmpty();

        // assert
        assertThat(res).isTrue();
    }

    @Test
    public void testIsEmpty_notEmptyList_shouldBeFalse() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(2);

        // act
        var res = list.isEmpty();

        // assert
        assertThat(res).isFalse();
    }

    @Test
    public void testIsEmpty_addAndRemove_shouldBeTrue() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();
        list.add(2);
        list.add(2);
        list.removeLast();
        list.removeLast();

        // act
        var res = list.isEmpty();

        // assert
        assertThat(res).isTrue();
    }


    @Test
    public void testListIterator_shouldReturnIterator() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        var res = list.listIterator();

        // assert
        assertThat(res).isInstanceOf(ListIterator.class);
    }

    @Test
    public void testListIterator_withIndex_shouldReturnIterator() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act
        var res = list.listIterator(0);

        // assert
        assertThat(res).isInstanceOf(ListIterator.class);
    }

    @Test
    public void testSubList_shouldThrowException() {
        // arrange
        LinkedList<Integer> list = new LinkedList<>();

        // act & assert
        assertThatThrownBy(() -> list.subList(0, 1)).isInstanceOf(UnsupportedOperationException.class);
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