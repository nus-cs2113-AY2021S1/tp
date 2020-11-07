package seedu.duke.logic.commands.favcommand;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;
import seedu.duke.model.favorite.Fav;
import seedu.duke.model.favorite.FavList;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String input = "5";
        ExecFavCommand command = new ExecFavCommand(input);
        try {
            command.executeCommand();
        } catch (CustomException e) {
            assertEquals("Sorry, that isn't the index of any command in the list.", e.toString());
        }
    }

    @Test
    void execFavCommandConstructor_inputWords_expectException() throws CustomException {
        String input = "random words";
        try {
            ExecFavCommand command = new ExecFavCommand(input);
        } catch (CustomException e) {
            assertEquals("Yikes! That is not even a number.", e.toString());
        }
    }

    @Test
    void execFavCommandConstructor_inputBlank_expectException() {
        String input = " ";
        try {
            ExecFavCommand command = new ExecFavCommand(input);
        } catch (CustomException e) {
            assertEquals("Oh no! I cannot detect the input index.", e.toString());
        }
    }

    @Test
    void execFavCommandConstructor_inputNumberAndWords_expectException() {
        String input = "1 random words";
        try {
            ExecFavCommand command = new ExecFavCommand(input);
        } catch (CustomException e) {
            assertEquals("Yikes! That is not even a number.", e.toString());
        }
    }


    @Test
    void executeCommand_invalidCommand_expectException() throws CustomException {
        Fav dummyFav = new Fav("/bus museum", "dummy");
        String input = "2";
        ExecFavCommand command = new ExecFavCommand(input);
        try {
            command.executeCommand();
        } catch (CustomException e) {
            assertEquals(3, FavList.getSize());
            assertEquals("Oh no! it seems that this command has been corrupted.\n"
                    + "Don't worry, I have deleted it from your favourites list!", e.toString());
            FavList.addFav(dummyFav);
        }
    }

    /*
    As size task in index 2 will be deleted from test of invalid command, valid index boundary is 3
     */
    @Test
    void executeCommand_inputValidIndex_success() throws CustomException {
        String input = "4";
        ExecFavCommand command = new ExecFavCommand(input);
        assertDoesNotThrow(()->command.executeCommand());
    }
}
