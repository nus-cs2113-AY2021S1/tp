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
    public void parse_exampleCommand_parsedCorrectly() {
        final String input = "example";
        assertEquals("example",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_editCommand_parsedCorrectly() {
        final String input = "edit friends";
        assertEquals("edit",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_addCommand_parsedCorrectly() {
        final String input = "add Blacklist 1 10";
        assertEquals("add",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_addreviewCommand_parsedCorrectly() {
        final String input = "addreview friends 1 / funny";
        assertEquals("addreview",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_changereviewCommand_parsedCorrectly() {
        final String input = "changereview friends / not funny";
        assertEquals("changereview",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_changeratingCommand_parsedCorrectly() {
        final String input = "changerating Friends 9";
        assertEquals("changerating",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_deleteratingCommand_parsedCorrectly() {
        final String input = "deleterating Friends";
        assertEquals("deleterating",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_seasonCommand_parsedCorrectly() {
        final String input = "season friends 1";
        assertEquals("season",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_watchCommand_parsedCorrectly() {
        final String input = "watch friends";
        assertEquals("watch",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_updatetimelimitCommand_parsedCorrectly() {
        final String input = "updatetimelimit 100";
        assertEquals("updatetimelimit",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_deleteCommand_parsedCorrectly() {
        final String input = "delete friends";
        assertEquals("delete",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_episodeCommand_parsedCorrectly() {
        final String input = "episode friends 10";
        assertEquals("episode",parseAndShowCommandType(input));
    }

    @org.junit.jupiter.api.Test
    public void parse_searchCommand_parsedCorrectly() {
        final String input = "search friends";
        assertEquals("search",parseAndShowCommandType(input));
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
