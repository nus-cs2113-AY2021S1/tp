package parser;

import access.Access;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    @Test
    public void parse_addCommandEmptyArgs_expectException() {
        Access access = new Access();
        final String[] inputs = {"add", "add "};
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> Parser.parse(input, access));
        }
    }

    @Test
    public void parse_addCardInvalidArgs_expectException() {
        Access access = new Access();
        access.setIsChapterLevel();
        final String[] inputs = {
            "add wrong args format",
            // no content for question and answer
            "add q: | a:",
            // no content for question
            "add q: | a:2",
            // no content for answer
            "add q:1+1 | a:",
            // no question and answer separator prefix
            "add q:1+1 a:2",
            // no question prefix
            "add 1+1 | a:2",
            // no answer prefix
            "add q:1+1 | 2",
        };
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> Parser.parse(input, access));
        }
    }

    @Test
    public void parse_goCommandEmptyArgs_expectException() {
        Access access = new Access();
        final String[] inputs = {"go", "go "};
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> Parser.parse(input, access));
        }
    }

    @Test
    public void parse_backCommandWithArgs_expectException() {
        Access access = new Access();
        final String[] inputs = {"back args"};
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> Parser.parse(input, access));
        }
    }


    @Test
    public void parse_listCommandWithArgs_expectException() {
        Access access = new Access();
        final String input = "list args";
        assertThrows(InvalidInputException.class, () -> Parser.parse(input, access));
    }

    @Test
    public void parse_exitCommandWithArgs_expectException() {
        Access access = new Access();
        final String input = "exit args";
        assertThrows(InvalidInputException.class, () -> Parser.parse(input, access));
    }

    @Test
    public void parse_removeCommandEmptyArgs_exception() {
        Parser parser = new Parser();
        Access access = new Access();
        final String[] inputs = {
            "remove",
            "remove ",
        };
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> parser.parse(input, access));
        }
    }

    @Test
    public void parse_removeCommandNonIntegerArgs_exception() {
        Parser parser = new Parser();
        Access access = new Access();
        String input = "remove two";
        assertThrows(InvalidInputException.class, () -> parser.parse(input, access));
    }

    @Test
    public void parse_editCommandEmptyArgs_expectException() {
        Access access = new Access();
        final String[] inputs = {"edit", "edit "};
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> Parser.parse(input, access));
        }
    }

    @Test
    public void parse_editCardInvalidArgs_expectException() {
        Access access = new Access();
        access.setIsChapterLevel();
        final String[] inputs = {
            "edit wrong args format",
            // card number is not integer
            "edit two q:1*1 | a:",
            // no card number
            "edit q:1*1 | a:",
            // no content for question and answer
            "edit 1 q: | a:",
            // no question and answer separator prefix
            "edit 1 q:1*1 a:1",
            // no question prefix
            "edit 1 1+1 | a:",
            // no answer prefix
            "add q:1+1 | 2",
        };
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> Parser.parse(input, access));
        }
    }

    @Test
    public void parse_editModuleInvalidArgs_expectException() {
        Access access = new Access();
        access.setIsAdminLevel();
        final String[] inputs = {
            "edit wrong args format",
            // module number is not integer
            "edit two cs2113",
            // no module number
            "edit cs2113",
            // no content for module name
            "edit 1",
        };
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> Parser.parse(input, access));
        }
    }

    @Test
    public void parse_editModuleInvalidCommandFormat_expectException() {
        Access access = new Access();
        access.setIsAdminLevel();
        String input = "edit 1 q:1+1 | a:";
        assertThrows(IncorrectAccessLevelException.class, () -> Parser.parse(input, access));
    }

    @Test
    public void parse_editChapterInvalidArgs_expectException() {
        Access access = new Access();
        access.setIsModuleLevel();
        final String[] inputs = {
            "edit wrong args format",
            // chapter number is not integer
            "edit two chapter 1",
            // no chapter number
            "edit chapter 1",
            // no content for chapter name
            "edit 1",
        };
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> Parser.parse(input, access));
        }
    }

    @Test
    public void parse_editChapterInvalidCommandFormat_expectException() {
        Access access = new Access();
        access.setIsModuleLevel();
        String input = "edit 1 q:1+1 | a:";
        assertThrows(IncorrectAccessLevelException.class, () -> Parser.parse(input, access));
    }

    @Test
    public void parse_editHistoryInvalidCommandFormat_expectException() {
        Access access = new Access();
        final String[] inputs = {
            "history wrong args format",
            // not date format
            "history 20-10-2020",
            // not yyyy-mm-dd but dd-mm-yyyy
            "history 10-20-2020",
            // not yyyy-mm-dd but mm-dd-yyyy
            "history 1",
        };
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> Parser.parse(input, access));
        }
    }
}
