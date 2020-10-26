package seedu.duke.bunnylist;

import seedu.duke.bunny.Bunny;
import seedu.duke.ui.UI;

import java.util.ArrayList;
import java.util.Random;

public class GenBunny {

    /**
     * Generate a random bunny from the list of bunnies in the list.
     * @param bunniesList ArrayList of bunnies
     */
    public static void pickRandomBunny(ArrayList<Bunny> bunniesList) {
        Random rand = new Random();
        int bunnySelected = rand.nextInt(bunniesList.size());

        assert !(bunnySelected < 0 || bunnySelected > bunniesList.size()) : "bunny generated cannot be out of bounds";

        // print response
        UI.bunnyRandomlySelected(bunnySelected);
    }
}
