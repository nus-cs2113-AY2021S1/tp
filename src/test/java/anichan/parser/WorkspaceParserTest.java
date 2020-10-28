package anichan.parser;

import org.junit.jupiter.api.Test;

import anichan.exception.AniException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WorkspaceParserTest {

    @Test
    void parse_invalidParameter_throwsAniException() {
        WorkspaceParser testParse = new WorkspaceParser();

        assertThrows(AniException.class, () -> testParse.parse(""));
        assertThrows(AniException.class, () -> testParse.parse(" "));
        assertThrows(AniException.class, () -> testParse.parse(" -"));
        assertThrows(AniException.class, () -> testParse.parse(" -x"));
        assertThrows(AniException.class, () -> testParse.parse(" -n"));
        assertThrows(AniException.class, () -> testParse.parse(" - "));
        assertThrows(AniException.class, () -> testParse.parse(" -abcdefg12345!@#$%^*&(#)$%| "));
        assertThrows(AniException.class, () -> testParse.parse(" -h hello -m world -t confusion "));
    }


    @Test
    void parser_legitimateName_success() {
        WorkspaceParser testParse = new WorkspaceParser();

        assertDoesNotThrow(() -> testParse.parse("-n Crunchy"));
    }

    @Test
    void parser_legitimateNameComplex_success() {
        WorkspaceParser testParse2 = new WorkspaceParser();

        assertDoesNotThrow(() -> testParse2.parse("-n Crunchy OREO c00k!3s"));
    }
}
