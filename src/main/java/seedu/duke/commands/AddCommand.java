package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

public class AddCommand extends Command {
    String[] description;

    /**
     * Add a new show.
     *
     * @param input the user input
     */
    public AddCommand(String[] input) throws NullPointerException {
        if (input.length < 3) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int numSeasons = Integer.parseInt(input[2]);
        String[] tokenizedSeasons = input[3].split(",");
        int[] seasonEpisodes = new int[numSeasons];
        int i = 0;
        if (tokenizedSeasons.length != numSeasons) {
            throw new NullPointerException();
        }
        for (String s : tokenizedSeasons) {
            seasonEpisodes[i] = Integer.parseInt(s);
            i++;
        }
        //check that the episodes do not exceed seasons

        Show show = new Show(input[1], numSeasons, seasonEpisodes);
        String name = input[1];
        ShowList.setShow(name, show);
    }
}
