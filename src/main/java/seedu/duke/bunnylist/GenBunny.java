package seedu.duke.bunnylist;

import seedu.duke.bunny.Bunny;
import seedu.duke.exceptions.bunnyexceptions.BunnyListEmptyException;
import seedu.duke.ui.UI;

import java.util.ArrayList;
import java.util.Random;

public class GenBunny {

    /**
     * Generate a random bunny from the list of bunnies in the list.
     * @param bunniesList ArrayList of bunnies
     */
    public static void pickRandomBunny(ArrayList<Bunny> bunniesList) throws BunnyListEmptyException {
        Random rand = new Random(System.currentTimeMillis());

        if (bunniesList.size() > 0) {
            UI.bunnyRandomlySelected(Math.abs(rand.nextInt() % bunniesList.size()));
        } else {
            throw new BunnyListEmptyException();
        }

    }
}
