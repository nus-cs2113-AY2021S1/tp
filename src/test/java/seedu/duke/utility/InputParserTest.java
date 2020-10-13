package seedu.duke.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputParserTest {
    private InputParser parser;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        parser = new InputParser();
        ShowList showList = new ShowList();

    }

    @org.junit.jupiter.api.Test
    void isByeTime() {
        assertEquals(false, parser.isByeTime());
    }

    @org.junit.jupiter.api.Test
    public void parse_helpCommand_parsedCorrectly() {
        final String input = "help";
        assertEquals("help",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_editCommand_parsedCorrectly() {
    }

    @org.junit.jupiter.api.Test
    public void parse_addCommand_parsedCorrectly() {
        final String input = "add Blacklist 1 10";
        assertEquals("add",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_ratingCommand_parsedCorrectly() {
    }

    @org.junit.jupiter.api.Test
    public void parse_changeratingCommand_parsedCorrectly() {
    }

    @org.junit.jupiter.api.Test
    public void parse_deleteratingCommand_parsedCorrectly() {
    }

    @org.junit.jupiter.api.Test
    public void parse_seasonCommand_parsedCorrectly() {
    }

    @org.junit.jupiter.api.Test
    public void parse_episodeCommand_parsedCorrectly() {
    }

    @org.junit.jupiter.api.Test
    public void parse_byeCommand_parsedCorrectly() {
        final String input = "bye";
        assertEquals("bye",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_listCommand_parsedCorrectly() {
        final String input = "list";
        assertEquals("list",parseAndShowCommandType(input));
    }

    private String parseAndShowCommandType(String input) {
        final String result = parser.parseInput(input);
        return result;
    }
}
