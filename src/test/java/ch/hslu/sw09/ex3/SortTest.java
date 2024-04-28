package ch.hslu.sw09.ex3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SortTest {
    @ParameterizedTest
    @CsvSource({
            "0:1:2:3:4:5:6:7,0:1:2:3:4:5:6:7",
            "7:6:5:4:3:2:1:0,0:1:2:3:4:5:6:7",
            "6:7:1:4:2:3:5:0,0:1:2:3:4:5:6:7",
            "5:4:6:7:3:2:1:0,0:1:2:3:4:5:6:7",
            "3:5:1:6:7:2:4:0,0:1:2:3:4:5:6:7",
            "0:7:3:5:6:1:2:3,0:1:2:3:3:5:6:7",
    })
    void testInsertionSort(String dataString, String expedtedString) {
        // arrange
        var data = Arrays.stream(dataString.split(":")).map(Integer::parseInt).toArray(Integer[]::new);
        var expected = Arrays.stream(expedtedString.split(":")).map(Integer::parseInt).toArray(Integer[]::new);

        // act
        Sort.insertionSort(data);

        // assert
        assertThat(data).isEqualTo(expected);
    }
}