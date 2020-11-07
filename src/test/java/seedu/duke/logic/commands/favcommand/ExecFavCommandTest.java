package seedu.duke.logic.commands.favcommand;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;
import seedu.duke.model.favorite.Fav;
import seedu.duke.model.favorite.FavList;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExecFavCommandTest {

    @BeforeAll
    public static void makeList() {
        new FavList();
        FavList.addFav(new Fav("/route University Town /to PGP", "Go home"));
        FavList.addFav(new Fav("/invalid command", "test"));
        FavList.addFav(new Fav("/bus Museum", "Cool place"));
        FavList.addFav(new Fav("/dine science", "Science food"));
    }


    @Test
    void executeCommand_indexOutOfBounds_expectException() throws CustomException {
        assertThrows(CustomException.class, ExecFavCommandTest::performCheck_indexOutOfBounds);
    }

    static void performCheck_indexOutOfBounds() throws CustomException {
        String input = "5";
        ExecFavCommand command = new ExecFavCommand(input);
        command.executeCommand();
    }

    @Test
    void executeCommand_inputWords_expectException() {
        assertThrows(CustomException.class, ExecFavCommandTest::performCheck_inputWords);
    }

    static void performCheck_inputWords() throws CustomException {
        String input = "random words";
        ExecFavCommand command = new ExecFavCommand(input);
        command.executeCommand();
    }

    @Test
    void executeCommand_inputBlank_expectException() {
        assertThrows(CustomException.class, ExecFavCommandTest::performCheck_inputBlank);
    }

    static void performCheck_inputBlank() throws CustomException {
        String input = " ";
        ExecFavCommand command = new ExecFavCommand(input);
        command.executeCommand();
    }

    @Test
    void executeCommand_inputNumberAndWords_expectException() {
        assertThrows(CustomException.class, ExecFavCommandTest::performCheck_inputNumberAndWords);
    }

    static void performCheck_inputNumberAndWords() throws CustomException {
        String input = "1 random words";
        ExecFavCommand command = new ExecFavCommand(input);
        command.executeCommand();
    }

    @Test
    void executeCommand_invalidCommand_expectException() {
        assertThrows(CustomException.class, ExecFavCommandTest::performCheck_executeInvalidCommand);
        assertEquals(3, FavList.getSize());
    }
    static void performCheck_executeInvalidCommand() throws CustomException {
        String input = "2";
        ExecFavCommand command = new ExecFavCommand(input);
        command.executeCommand();
    }

    @Test
    void executeCommand_inputValidIndex_TaskRuns() {
        assertDoesNotThrow(ExecFavCommandTest::performCheck_inputValidIndex);
    }

    /*
    As size task in index 2 will be deleted from test of invalid command, valid index boundary is 3
     */
    static void performCheck_inputValidIndex() throws CustomException {
        String input = "3";
        ExecFavCommand command = new ExecFavCommand(input);
        command.executeCommand();
    }
    
}
