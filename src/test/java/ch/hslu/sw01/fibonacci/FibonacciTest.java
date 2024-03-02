package ch.hslu.sw01.fibonacci;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "1,1",
            "2,1",
            "10,55",
            "16,987",
            "30,832040"
    })
    public void testFiboRec1(int number, int expected) {
        // act
        var res = Fibonacci.fiboRec1(number);

        // assert
        assertThat(res).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "1,1",
            "2,1",
            "10,55",
            "16,987",
            "30,832040"
    })
    public void testFiboRec2(int number, int expected) {
        // act
        var res = Fibonacci.fiboRec2(number);

        // assert
        assertThat(res).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "1,1",
            "2,1",
            "10,55",
            "16,987",
            "30,832040"
    })
    public void testFiboIter(int number, int expected) {
        // act
        var res = Fibonacci.fiboIter(number);

        // assert
        assertThat(res).isEqualTo(expected);
    }
}