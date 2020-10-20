package seedu.duke.parser;

import org.junit.jupiter.api.Test;

import seedu.duke.exception.AniException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddWorkspaceParserTest {

    @Test
    void parse_invalidParameter_ThrowsAniException() {
        AddWorkspaceParser testParse = new AddWorkspaceParser();

        assertThrows(AniException.class, () -> testParse.parse(""));
        assertThrows(AniException.class, () -> testParse.parse(" "));
        assertThrows(AniException.class, () -> testParse.parse(" -"));
        assertThrows(AniException.class, () -> testParse.parse(" -x"));
        assertThrows(AniException.class, () -> testParse.parse(" -n"));
        assertThrows(AniException.class, () -> testParse.parse(" - "));
        assertThrows(AniException.class, () -> testParse.parse(" -abcdefg12345!@#$%^*&(#)$%| "));
    }

    @Test
    void parser_legitimateName_success() {
        AddWorkspaceParser testParse = new AddWorkspaceParser();

        assertDoesNotThrow(() -> testParse.parse("-n Crunchy"));
    }

    @Test
    void parser_legitimateNameComplex_success() {
        AddWorkspaceParser testParse2 = new AddWorkspaceParser();

        assertDoesNotThrow(() -> testParse2.parse("-n Crunchy OREO c00k!3s"));
    }
}
