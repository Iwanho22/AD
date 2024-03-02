package ch.hslu.sw01.symmetricnumbers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SymmetricNumbersTest {

    @ParameterizedTest
    @CsvSource({
            "0,true",
            "12, false",
            "242,true",
            "5555,true",
            "101,true",
            "100,false"
    })
    public void testCheckSymmetry(String numb1, boolean expected) {
        // arrange
        var symmetricNumbers = new SymmetricNumbers();

        // act
        var res = symmetricNumbers.checkSymmetry(numb1);

        // assert
        assertThat(res).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "193,202",
            "189,191",
            "498,505",
            "88999,89089",
            "999,999",
            "182,191",
            "21,22",
            "37,44",
            "4441,4444",
            "4445,4545"
    })
    public void testGetNexSymmetricNumber(String numb1, String expected) {
        // arrange
        var symmetricNumbers = new SymmetricNumbers();

        // act
        var res = symmetricNumbers.getNexSymmetricNumber(numb1);

        // assert
        assertThat(res).isEqualTo(expected);
    }
}