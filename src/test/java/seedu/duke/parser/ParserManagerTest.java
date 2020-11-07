package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.InvalidCommand;
import seedu.duke.model.project.ProjectManager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserManagerTest {

    ParserManager parser = new ParserManager();
    ProjectManager projectManager = new ProjectManager();

    @Test
    void parser_invalidCommands_returnsInvalidCommand() {
        String userInput = "projekt /create -title Duke -desc lmao -end 11102020 -sd 7";
        Command command = parser.parser(userInput, projectManager);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void isStringIntParsable_stringDoesNotContainInteger_returnsFalse() {
        String notNumber = "not number";
        assertFalse(ParserManager.isStringIntParsable(notNumber));
    }

    @Test
    void isStringIntParsable_stringContainsInteger_returnsTrue() {
        String number = "1";
        assertTrue(ParserManager.isStringIntParsable(number));
    }

    @Test
    void isExit_commandIsBye_returnsTrue() {
        Command command = parser.parser("bye", projectManager);
        command.execute();
        assertTrue(parser.isExit());
    }

    @Test
    void isExit_commandIsNotBye_returnsFalse() {
        Command command = parser.parser("help", projectManager);
        command.execute();
        assertFalse(parser.isExit());
    }
}