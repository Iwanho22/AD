package ch.hslu.sw13;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class AnanasCheckerTest {

    @ParameterizedTest
    @CsvSource({
            "ANANASaaa,0",
            "aANANASaaa,1",
            "AANANASaaa,1",
            "AANANASaaa,1",
            "ANNANANASaaa,3",
            "ANAANANASaaa,3",
            "ANANE,-1",
            "ANANANAS,2"
    })
    public void testCheckWord(String word, int expected) {
        // act
        var res = AnanasChecker.find(word);

        // assert
        assertThat(res).isEqualTo(expected);
    }
}