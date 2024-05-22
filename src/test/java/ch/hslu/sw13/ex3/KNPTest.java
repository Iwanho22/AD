package ch.hslu.sw13.ex3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class KNPTest {

    @ParameterizedTest
    @CsvSource({
            "ANANASaaa,ANANAS,0",
            "aANANASaaa,ANANAS,1",
            "AANANASaaa,ANANAS,1",
            "AANANASaaa,ANANAS,1",
            "ANNANANASaaa,ANANAS,3",
            "ANAANANASaaa,ANANAS,3",
            "ANANE,ANANAS,-1",
            "ANANANAS,ANANAS,2",
            "asldkjfalsdkjfö,jfa,5"
    })
    public void testCheckWord(String word, String pattern, int expected) {
        // act
        var res = KNP.search(word, pattern);

        // assert
        assertThat(res).isEqualTo(expected);
    }
}