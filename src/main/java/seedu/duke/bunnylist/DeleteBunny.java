package seedu.duke.bunnylist;

import seedu.duke.bunny.Bunny;
import seedu.duke.exceptions.BunnyIndexOutOfBoundsException;
import seedu.duke.ui.UI;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class DeleteBunny {

    public static void deleteBunny(String userInput, ArrayList<Bunny> bunniesList)
            throws BunnyIndexOutOfBoundsException {

        int bunnyNum;

        // try to parse Bunny index and check if it exists
        bunnyNum = getNumFromInput(userInput, BunnyList.numBunny());

        // print response
        UI.bunnyDeleted(bunnyNum);
        bunniesList.remove(bunnyNum - 1);
    }

    private static int getNumFromInput(String userInput, int numBunnies) throws BunnyIndexOutOfBoundsException {
        int bunnyNum;
        try {
            bunnyNum = Integer.parseInt(userInput.replaceAll("[\\D]", ""));
            if (bunnyNum > numBunnies || bunnyNum < 1) {
                throw new BunnyIndexOutOfBoundsException();
            }
        } catch (NumberFormatException exception) {
            throw new BunnyIndexOutOfBoundsException();
        }
        return bunnyNum;
    }
}
