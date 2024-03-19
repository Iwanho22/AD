package ch.hslu.sw04.ex4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleHashStructureTest {

    @Test
    public void testAdd_withSingle_shouldBeAdded() {
        // arrange
       HashStructure<Integer> hashStructure = new SimpleHashStructure<>();

        // act
        var res = hashStructure.add(1);

        // assert
        assertThat(res).isTrue();
    }

    @Test
    public void testAdd_withSingle_shouldContain() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();

        // act
        hashStructure.add(1);

        // assert
        assertThat(hashStructure.contains(1)).isTrue();
    }

    @Test
    public void testAdd_withCollision_shouldContain() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();

        // act
        hashStructure.add(1);
        hashStructure.add(11);

        // assert
        assertThat(hashStructure.contains(11)).isTrue();
    }

    @Test
    public void testAdd_withCollision_shouldUseExploratoryChain() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();

        // act
        hashStructure.add(1);
        hashStructure.add(11);

        // assert
        assertThat(hashStructure.toString()).isEqualTo("SimpleHashStructure{data=[null, Node{value=11, previous=Node{value=1, previous=null}}, null, null, null, null, null, null, null, null]}");
    }

    @Test
    public void testAdd_withTenNumbers_shouldAddEleventh() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();
        for (int i = 0; i < 10; i++) {
            hashStructure.add(i);
        }

        // act
        var res = hashStructure.add(27);

        // assert
        assertThat(res).isTrue();
    }

    @Test
    public void testAdd_withFull_sizeShouldIncrease() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();
        for (int i = 0; i < 10; i++) {
            hashStructure.add(i);
        }

        // act
        hashStructure.add(27);

        // assert
        assertThat(hashStructure.size()).isEqualTo(11);
    }

    @Test
    public void testContains_onEmpty_shouldBeFalse() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();

        // act
        var res = hashStructure.contains(455);

        // assert
        assertThat(res).isFalse();
    }

    @Test
    public void testContains_contained_shouldBeTrue() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();
        hashStructure.add(455);

        // act
        var res = hashStructure.contains(455);

        // assert
        assertThat(res).isTrue();
    }

    @Test
    public void testContains_containedWithTombstoneInBetween_LastShouldBeTrue() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();
        hashStructure.add(1);
        hashStructure.add(11);
        hashStructure.add(21);
        hashStructure.remove(11);

        // act
        var res = hashStructure.contains(21);

        // assert
        assertThat(res).isTrue();
    }

    @Test
    public void testContains_containedWithTombstoneInBetween_FirstShouldBeTrue() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();
        hashStructure.add(1);
        hashStructure.add(11);
        hashStructure.add(21);
        hashStructure.remove(11);

        // act
        var res = hashStructure.contains(1);

        // assert
        assertThat(res).isTrue();
    }

    @Test
    public void testContains_notContained_shouldBeFalse() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();
        hashStructure.add(455);

        // act
        var res = hashStructure.contains(456);

        // assert
        assertThat(res).isFalse();
    }

    @Test
    public void testRemove_onEmpty_shouldBeFalse() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();

        // act
        var res = hashStructure.remove(455);

        // assert
        assertThat(res).isFalse();
    }

    @Test
    public void testRemove_contained_shouldBeRemoved() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();
        hashStructure.add(455);

        // act
        var res = hashStructure.remove(455);

        // assert
        assertThat(res).isTrue();
    }

    @Test
    public void testRemove_notContained_shouldBeFalse() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();
        hashStructure.add(455);

        // act
        var res = hashStructure.remove(456);

        // assert
        assertThat(res).isFalse();
    }

    @Test
    public void testRemove_notContainedOnFull_shouldBeFalse() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();
        for (int i = 10; i < 20; i++) {
            hashStructure.add(i);
        }

        // act
        var res = hashStructure.remove(456);

        // assert
        assertThat(res).isFalse();
    }

    @Test
    public void testRemove_notContained_sizeShouldNotDecrease() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();
        hashStructure.add(455);

        // act
        hashStructure.remove(456);

        // assert
        assertThat(hashStructure.size()).isEqualTo(1);
    }

    @Test
    public void testSize_withEmpty_shouldBeZero() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();

        // act
        var res = hashStructure.size();

        // assert
        assertThat(res).isEqualTo(0);
    }

    @Test
    public void testSize_withSingle_shouldBeOne() {
        // arrange
        HashStructure<Integer> hashStructure = new SimpleHashStructure<>();
        hashStructure.add(1);

        // act
        var res = hashStructure.size();

        // assert
        assertThat(res).isEqualTo(1);
    }
}