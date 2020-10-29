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
        WorkspaceParser testParse = new WorkspaceParser();

        assertDoesNotThrow(() -> testParse.parse("-n Crunchy OREO c00ki3s"));
    }

    @Test
    void parser_illegalNameComplex_throwsAniException() {
        WorkspaceParser testParse = new WorkspaceParser();

        assertThrows(AniException.class, () -> testParse.parse("-n ."));
        assertThrows(AniException.class, () -> testParse.parse("-n /"));
        assertThrows(AniException.class, () -> testParse.parse("-n .hello"));
        assertThrows(AniException.class, () -> testParse.parse("-n /hello_world"));
        assertThrows(AniException.class, () -> testParse.parse("-n /test"));
        assertThrows(AniException.class, () -> testParse.parse("-n /more/tests"));
        assertThrows(AniException.class, () -> testParse.parse("-n !"));
        assertThrows(AniException.class, () -> testParse.parse("-n how about this?"));
        assertThrows(AniException.class, () -> testParse.parse("-n and this%"));
        assertThrows(AniException.class, () -> testParse.parse("-n ["));
        assertThrows(AniException.class, () -> testParse.parse("-n ;"));
    }
}
