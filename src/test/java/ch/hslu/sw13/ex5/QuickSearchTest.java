package ch.hslu.sw13.ex5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class QuickSearchTest {

    @ParameterizedTest
    @CsvSource({
            "acdhjkohaba,aa,-1",
            "acdhjkohaaba,aa,8",
            "acdhjkohaaba,dhjko,2",
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
    public void testSearch(String word, String pattern, int expected) {
        // act
        var res = QuickSearch.search(word, pattern);

        // assert
        assertThat(res).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "acdhjkohaba,aa,-1",
            "acdhjkohaaba,aa,8",
            "acdhjkohaaba,dhjko,2",
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
    public void testSearchMissMatch(String word, String pattern, int expected) {
        // act
        var res = QuickSearch.search(word, pattern);

        // assert
        assertThat(res).isEqualTo(expected);
    }
}