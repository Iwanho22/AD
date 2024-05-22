package ch.hslu.sw12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class WordCheckerTest {

    @ParameterizedTest
    @CsvSource({
            "0,true",
            "010,true",
            "01110,true",
            "0111010,true",
            "11011,false",
            "0101010101010,true"
    })
    public void testCheckWord(String word, boolean expected) {
        // act
        var res = WordChecker.checkWord(word);

        // assert
        assertThat(res).isEqualTo(expected);
    }
}