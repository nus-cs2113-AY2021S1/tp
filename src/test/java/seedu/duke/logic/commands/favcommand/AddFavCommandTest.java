package seedu.duke.logic.commands.favcommand;


import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;
import seedu.duke.model.favorite.FavList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddFavCommandTest {
    FavList list = new FavList();

    @Test
    void executeCommand_nullCommand_expectException() {
        String command = null;
        String desc = "List all bus stops";
        AddFavCommand addFav = new AddFavCommand(command, desc);
        assertThrows(CustomException.class, addFav::executeCommand);
    }

    @Test
    void executeCommand_validCommand_expectNoException() {
        String command = "liststops";
        String desc = "List all bus stops";
        AddFavCommand addFav = new AddFavCommand(command, desc);
        assertDoesNotThrow(addFav::executeCommand);
    }

    @Test
    void executeCommand_nullDesc_expectNoException() {
        String command = "liststops";
        String desc = " ";
        AddFavCommand addFav = new AddFavCommand(command, desc);
        assertDoesNotThrow(addFav::executeCommand);
    }
}