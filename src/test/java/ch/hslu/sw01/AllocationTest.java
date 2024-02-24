package ch.hslu.sw01;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class AllocationTest {

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(Allocation.class).verify();
    }

    @Test
    public void testToString() {
        // arrange
        var  allocation = new Allocation(0, 16);

        // act
        var res = allocation.toString();

        // assert
        assertThat(res).isEqualTo("Allocation[Address: 0; Size: 16]");
    }

    @Test
    public void testGetStart() {
        // arrange
        var  allocation = new Allocation(0, 16);

        // act
        var res = allocation.getStart();

        // assert
        assertThat(res).isEqualTo(0);
    }

    @Test
    public void testGetSize() {
        // arrange
        var  allocation = new Allocation(0, 16);

        // act
        var res = allocation.getSize();

        // assert
        assertThat(res).isEqualTo(16);
    }

    @Test
    public void testCreationWithNegativeStart() {
        // act
        assertThatThrownBy(() -> {
            var allocation = new Allocation(-1, 16);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Start must be a positive number.");
    }

    @Test
    public void testCreationSizeZero() {
        // act
        assertThatThrownBy(() -> {
            var allocation = new Allocation(0, 0);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Size must be greater than 0.");
    }

    @Test
    public void testCreationSizeNegative() {
        // act
        assertThatThrownBy(() -> {
            var allocation = new Allocation(0, -1);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Size must be greater than 0.");
    }

    @Test
    public void testCompareToEquals() {
        // arrange
        var alloc1 = new Allocation(0, 16);
        var alloc2 = new Allocation(0, 16);

        // act
        var res = alloc1.compareTo(alloc2);

        // assert
        assertThat(res).isEqualTo(0);
    }

    @Test
    public void testCompareToSameReference() {
        // arrange
        var alloc1 = new Allocation(0, 16);
        var alloc2 = alloc1;

        // act
        var res = alloc1.compareTo(alloc2);

        // assert
        assertThat(res).isEqualTo(0);
    }

    @Test
    public void testCompareToSmaller() {
        // arrange
        var alloc1 = new Allocation(0, 16);
        var alloc2 = new Allocation(16, 16);

        // act
        var res = alloc1.compareTo(alloc2);

        // assert
        assertThat(res).isEqualTo(-1);
    }

    @Test
    public void testCompareToGreater() {
        // arrange
        var alloc1 = new Allocation(100, 16);
        var alloc2 = new Allocation(16, 16);

        // act
        var res = alloc1.compareTo(alloc2);

        // assert
        assertThat(res).isEqualTo(1);
    }
}