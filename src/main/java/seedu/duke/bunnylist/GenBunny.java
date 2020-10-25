package seedu.duke.bunnylist;

import seedu.duke.bunny.Bunny;
import seedu.duke.ui.UI;

import java.util.ArrayList;
import java.util.Random;

import static seedu.duke.bunnylist.BunnyList.bunniesList;

public class GenBunny {

    public static void pickRandomBunny(ArrayList<Bunny> bunniesList) {
        Random rand = new Random();
        int bunnySelected = rand.nextInt(bunniesList.size());
        // print response
        UI.bunnyRandomlySelected(bunnySelected);
    }
}
