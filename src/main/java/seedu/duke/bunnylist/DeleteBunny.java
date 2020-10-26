package seedu.duke.bunnylist;

import seedu.duke.bunny.Bunny;
import seedu.duke.exceptions.BunnyIndexOutOfBoundsException;
import seedu.duke.ui.UI;

import java.util.ArrayList;

/**
 * Functions to delete a bunny from the list of bunnies.
 */
public class DeleteBunny {

    /**
     * Delete a bunny from the list of bunnies.
     * @param userInput user input command
     * @param bunniesList ArrayList containing all the bunnies
     * @throws BunnyIndexOutOfBoundsException index of bunny provided by user is out of bounds
     */
    public static void deleteBunny(String userInput, ArrayList<Bunny> bunniesList)
            throws BunnyIndexOutOfBoundsException {

        int bunnyNum;

        // try to parse Bunny index and check if it exists
        bunnyNum = getNumFromInput(userInput, BunnyList.numBunny());

        // print response
        UI.bunnyDeleted(bunnyNum);
        bunniesList.remove(bunnyNum - 1);
    }

    /**
     * Extract the index of the bunny to be deleted.
     * @param userInput user input command
     * @param numBunnies number of bunnies in list
     * @return index of bunny detected
     * @throws BunnyIndexOutOfBoundsException the bunny index is out of bounds
     */
    public static int getNumFromInput(String userInput, int numBunnies) throws BunnyIndexOutOfBoundsException {
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
