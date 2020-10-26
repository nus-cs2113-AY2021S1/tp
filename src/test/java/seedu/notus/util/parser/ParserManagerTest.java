package seedu.notus.util.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.notus.command.AddNoteCommand;
import seedu.notus.command.Command;
import seedu.notus.command.CreateTagCommand;
import seedu.notus.command.DeleteTagCommand;
import seedu.notus.command.IncorrectCommand;
import seedu.notus.command.TagNoteCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserManagerTest {

    private ParserManager parserManager;
    private Command command;
    private String inputString;

    @BeforeEach
    void setUp() {
        this.parserManager = new ParserManager();
    }

    //@@author Nazryl
    @Test
    void parseCommand_validAddNoteString_returnAddNoteCommand() {
        inputString = "add-n /t note1";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof AddNoteCommand);

        inputString = "add-n /tag cs2113/t note1";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof AddNoteCommand);
    }

    @Test
    void parseCommand_invalidAddNoteString_returnIncorrectCommand() {
        inputString = "add-n";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = "add-n ";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = "add-n/t";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = "add-n /";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = "add-n /t";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = "add-n /tag tag 1";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);
    }

    //@@author Chongjx
    @Test
    void parseCommand_validCreateTagString_returnCreateTagCommand() {
        inputString = "create-t /tag tag1";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof CreateTagCommand);

        inputString = "create-t /tag tag1/tag tag2 red";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof CreateTagCommand);
    }

    @Test
    void parseCommand_invalidCreateTagString_returnIncorrectCommand() {
        inputString = "create-t";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = "create-t ";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = "create-t /tag";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = "create-t /i";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);
    }

    @Test
    void parseCommand_validDeleteTagString_returnDeleteTagCommand() {
        inputString = "delete-t /tag tag1";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof DeleteTagCommand);

        inputString = "delete-t /tag tag1/tag tag2 red";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof DeleteTagCommand);
    }

    @Test
    void parseCommand_invalidDeleteTagString_returnIncorrectCommand() {
        inputString = "delete-t";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = "delete-t ";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = "delete-t /tag";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = "delete-t /i";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);
    }

    @Test
    void parseCommand_validTagNoteString_returnTagNoteCommand() {
        inputString = "tag-n /tag tag1 /i 1";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof TagNoteCommand);

        inputString = "tag-n /i 1 /tag tag1 /tag tag2 red";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof TagNoteCommand);
    }

    @Test
    void parseCommand_invalidTagNoteString_returnIncorrect() {
        inputString = "tag-n /tag ";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = "tag-n /i 1 ";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = "tag-n /i 1 /tag ";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);
    }

    @Test
    void parseCommand_invalidString_returnIncorrectCommand() {
        // Unrecognized commands
        inputString = "hello";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = "";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);

        inputString = " ";
        command = parserManager.parseCommand(inputString);
        assertTrue(command instanceof IncorrectCommand);
    }
}