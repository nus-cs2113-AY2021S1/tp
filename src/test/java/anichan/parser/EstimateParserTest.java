package anichan.parser;

import anichan.exception.AniException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author OngDeZhi
public class EstimateParserTest {
    private EstimateParser estimateParser;

    @BeforeEach
    void setUp() {
        estimateParser = new EstimateParser();
    }

    @Test
    void parse_validParameters_success() throws AniException {
        estimateParser.parse("script.txt");
        estimateParser.parse("random_script.txt -wph 777");
    }

    @Test
    void parse_invalidScriptFileParameter_throwsAniException() {
        assertThrows(AniException.class, () -> estimateParser.parse(""));
        assertThrows(AniException.class, () -> estimateParser.parse("script"));
        assertThrows(AniException.class, () -> estimateParser.parse("/path/to/script"));
        assertThrows(AniException.class, () -> estimateParser.parse("script.txt helloworld"));
    }

    @Test
    void parse_multipleScriptFile_throwsAniException() {
        assertThrows(AniException.class, () -> estimateParser.parse("script.txt script_two.txt"));
    }

    @Test
    void parse_invalidOption_throwsAniException() {
        assertThrows(AniException.class, () -> estimateParser.parse("script.txt -o"));
        assertThrows(AniException.class, () -> estimateParser.parse("script.txt -one -two"));
    }

    @Test
    void parse_invalidWordsPerHour_throwsAniException() {
        assertThrows(AniException.class, () -> estimateParser.parse("script.txt -wph"));
        assertThrows(AniException.class, () -> estimateParser.parse("script.txt -wph one"));
        assertThrows(AniException.class, () -> estimateParser.parse("script.txt -wph 0"));
        assertThrows(AniException.class, () -> estimateParser.parse("script.txt -wph -8"));
        assertThrows(AniException.class, () -> estimateParser.parse("script.txt -wph 777 0"));
        assertThrows(AniException.class, () -> estimateParser.parse("script.txt -wph 123 -two"));
        assertThrows(AniException.class, () -> estimateParser.parse("script.txt -wph 999999999999"));
    }

    @Test
    void parse_nullDescription_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> estimateParser.parse(null));
    }
}
