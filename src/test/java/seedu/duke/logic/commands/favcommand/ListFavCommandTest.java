package seedu.duke.logic.commands.favcommand;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;
import seedu.duke.logic.commands.dinecommand.DineInfoCommand;
import seedu.duke.model.favorite.Fav;
import seedu.duke.model.favorite.FavList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListFavCommandTest {

    @BeforeAll
    public static void makeList() {
        new FavList();
        FavList.addFav(new Fav("/dine science", "Science food"));
    }

    @Test
    public void executeCommand_expectNoException() {
        ListFavCommand com = new ListFavCommand();
        assertDoesNotThrow(com::executeCommand);
    }

    @Test
    public void executeCommand_expectException() {
        FavList.clearFav();
        ListFavCommand com = new ListFavCommand();
        assertThrows(CustomException.class, com::executeCommand);
    }

}
