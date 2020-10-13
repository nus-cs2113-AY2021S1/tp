package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

public class AddCommand extends Command {
    String[] description;

    /**
     * Add a new show.
     * @param input the user input
     */
    public AddCommand(String[] input) {
        int numSeasons = Integer.parseInt(input[2]);
        String[] tokenizedSeasons = input[3].split(",");
        int[] seasonEpisodes = new int[numSeasons];
        int i = 0;
        for (String s : tokenizedSeasons) {
            seasonEpisodes[i] = Integer.parseInt(s);
            i++;
            //check that the episodes do not exceed seasons
            if (i == numSeasons) {
                System.out.println("Mismatched number of seasons and episodes");
                break;
            }
        }
        Show show = new Show(input[1], Integer.parseInt(input[2]), seasonEpisodes);
        String name = input[1];
        ShowList.setShow(name, show);
    }
}
