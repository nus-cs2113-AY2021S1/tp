package fitr.parser;

import fitr.command.EditEntryCommand;
import fitr.command.EditProfileCommand;
import fitr.command.InvalidCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EditCommandParserTest {
    @Test
    public void editCommand_validEditFood_success() {
        EditCommandParser editCommandParser = new EditCommandParser("food 1/10/2020 1 apple /100 1");
        assertTrue(editCommandParser.editCommand() instanceof EditEntryCommand);
    }

    @Test
    public void editCommand_validEditName_success() {
        EditCommandParser editCommandParser = new EditCommandParser("name John");
        assertTrue(editCommandParser.editCommand() instanceof EditProfileCommand);
    }

    @Test
    public void editCommand_invalidEditCommand_returnInvalid() {
        EditCommandParser editCommandParser = new EditCommandParser("test");
        assertTrue(editCommandParser.editCommand() instanceof InvalidCommand);
    }

    @Test
    public void editCommand_emptyEditCommand_returnInvalid() {
        EditCommandParser editCommandParser = new EditCommandParser("");
        assertTrue(editCommandParser.editCommand() instanceof InvalidCommand);
    }
}
