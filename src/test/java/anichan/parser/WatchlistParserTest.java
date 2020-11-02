package anichan.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import anichan.exception.AniException;

import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author OngDeZhi
class WatchlistParserTest {
    private WatchlistParser watchlistParser;

    @BeforeEach
    void setUp() {
        watchlistParser = new WatchlistParser();
    }

    @Test
    void parse_validParameters_success() throws AniException {
        watchlistParser.parse("-n correct");
        watchlistParser.parse("-l");
        watchlistParser.parse("-s 1");
        watchlistParser.parse("-d 1");

        // With whitespaces
        watchlistParser.parse("        -l        ");
    }

    @Test
    void parse_invalidParameter_throwsAniException() {
        assertThrows(AniException.class, () -> watchlistParser.parse(""));
        assertThrows(AniException.class, () -> watchlistParser.parse("-invalid"));
        assertThrows(AniException.class, () -> watchlistParser.parse("-n one -one two"));
        assertThrows(AniException.class, () -> watchlistParser.parse("hello -n testing"));
    }

    @Test
    void parse_emptyParameterValue_throwsAniException() {
        assertThrows(AniException.class, () -> watchlistParser.parse("-n"));
        assertThrows(AniException.class, () -> watchlistParser.parse("-s"));
        assertThrows(AniException.class, () -> watchlistParser.parse("-d"));
    }

    @Test
    void parse_invalidParameterValue_throwsAniException() {
        assertThrows(AniException.class, () -> watchlistParser.parse("-n *-*"));
        assertThrows(AniException.class, () -> watchlistParser.parse("-l a"));
        assertThrows(AniException.class, () -> watchlistParser.parse("-s 0"));
        assertThrows(AniException.class, () -> watchlistParser.parse("-d -1"));
        assertThrows(AniException.class, () -> watchlistParser.parse("-s one"));
        assertThrows(AniException.class, () -> watchlistParser.parse("-d 1 2 3"));
    }
}
