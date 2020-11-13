package seedu.duke.logic.commands.favcommand;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;
import seedu.duke.model.favorite.Fav;
import seedu.duke.model.favorite.FavList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DescFavCommandTest {

    @BeforeAll
    public static void makeList() {
        new FavList();
        FavList.addFav(new Fav("/route PGP /to University Hall", "Awesome place"));
        FavList.addFav(new Fav("/bus Kent Ridge MRT Station", "NTUC here"));
    }

    @Test
    void executeCommand_descriptionChangeSuccess() throws CustomException {
        String rawMessage = "1 /to It is near RVRC.";
        DescFavCommand com = new DescFavCommand(rawMessage);
        com.executeCommand();
        assertEquals("It is near RVRC.", FavList.getFav(0).getDesc());
    }

}