package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;
import seedu.duke.utility.TimeParser;
import seedu.duke.utility.Ui;

import java.util.Scanner;

public class EditCommand {
    private static Show show;
    private static String showName;

    public EditCommand(String input) {
        showName = input;
        show = ShowList.getShow(showName);

    }

    /**
     * Edits the number of seasons for a particular show.
     * @param editCommand number of seasons
     */
    public static void editSeasons(String editCommand) {
        int numSeasons = Integer.parseInt(editCommand.substring(7));
        int initialNumSeasons = show.getNumSeasons();
        show.setNumSeasons(numSeasons);
        int[] episodes;
        //when the updated number of seasons exceed current number of season, initialise the number of episodes of the
        // new season to 1, otherwise remove the episodes for the extra seasons
        if (numSeasons > initialNumSeasons) {
            episodes = new int[numSeasons];
            for (int i = 0; i < initialNumSeasons; i++) {
                episodes[i] = show.getRawEpisodesForSeason(i);
            }
            for (int i = initialNumSeasons; i < numSeasons; i++) {
                episodes[i] = 1;
            }
        } else {
            episodes = new int[numSeasons];
            //Started for 1 to reference the correct season number
            for (int i = 0; i < numSeasons; i++) {
                episodes[i] = show.getRawEpisodesForSeason(i);
            }
        }
        show.setNumEpisodesForSeasons(episodes);
    }

    /**
     * Changes duration of each episodes.
     * @param input length of episode
     */
    public static void editDuration(String input) {
        String[] tokenizedInput = input.split(" ");
        try {
            int duration = TimeParser.parseTime(tokenizedInput[1]);
            show.setEpisodeDuration(duration);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printBadInputException();
        }
    }

    /**
     * changes the number of episodes in each season.
     * @param input number of episodes comma separated
     * @throws NullPointerException when input is empty
     */
    public static void editEpisode(String input) throws NullPointerException {
        String[] numOfEpisodes = input.split(",");
        int i = 0;
        assert numOfEpisodes.length == show.getNumSeasons();
        int[] intNumOfEpisodes = new int[show.getNumSeasons()];
        for (String s : numOfEpisodes) {
            try {
                intNumOfEpisodes[i] = Integer.parseInt(s);
            } catch (Exception e) {
                throw new NullPointerException();
            }
            i++;
        }
        //I put this below for now in case we need to add checks to ensure numOfEpisodes is not empty
        if (i == 0 || numOfEpisodes.length != show.getNumSeasons()) {
            throw new NullPointerException();
        }
        show.setNumEpisodesForSeasons(intNumOfEpisodes);
    }

    /**
     * Runs the parser for the edit command.
     * @throws NullPointerException if the number of episodes input by the user is invalid or empty.
     */
    public static void processCommand() throws NullPointerException {
        boolean isShowInList = ShowList.doesShowExist(showName);
        if (!isShowInList) {
            Ui.printShowNotInList();
            return;
        }

        Scanner in = new Scanner(System.in);
        Ui.printEditPrompt();
        while (true) {
            String editCommand = in.nextLine();
            if (editCommand.startsWith("name")) {
                show.setName(editCommand.substring(5));
            } else if (editCommand.startsWith("episode")) {
                editEpisode(editCommand.substring(8));
            } else if (editCommand.startsWith("season")) {
                editSeasons(editCommand);
            } else if (editCommand.startsWith("duration")) {
                editDuration(editCommand);
            } else if (editCommand.equals("done")) {
                break;
            }
            ShowList.setShow(showName, show);
        }
        Ui.printEditShow(showName);
    }
}
