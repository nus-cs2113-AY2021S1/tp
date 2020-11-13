package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;
import seedu.duke.utility.TimeParser;
import seedu.duke.utility.Ui;

import java.util.Scanner;

//@@author shikai-zhou

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
     * @throws ArithmeticException when the input is negative
     * @throws NumberFormatException when input is not int or
     *      when current season is set more than the new number of seasons
     */
    public static void editSeasons(String editCommand) throws ArithmeticException, NumberFormatException {
        int numSeasons = Integer.parseInt(editCommand.substring(7));
        if (numSeasons < 1) {
            throw new ArithmeticException();
        }
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
            if (show.getCurrentSeason() > numSeasons) {
                throw new NumberFormatException();
            }
            episodes = new int[numSeasons];

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
        String[] tokenizedInput = input.split(" ",2);
        try {
            int duration = TimeParser.parseTime(tokenizedInput[1]);
            if (duration < 0) {
                throw new RuntimeException();
            }
            show.setEpisodeDuration(duration);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printBadInputException();
        } catch (RuntimeException e) {
            return;
        }
    }

    /**
     * changes the number of episodes in each season.
     * @param input number of episodes comma separated
     * @throws NullPointerException if the number of episodes input by the user is invalid or empty.
     * @throws NumberFormatException when input is not a number
     */
    public static void editEpisodes(String input) throws NullPointerException, NumberFormatException {
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
            if (intNumOfEpisodes[i] < 1) {
                throw new NumberFormatException();
            }
            i++;
        }

        if (i == 0 || numOfEpisodes.length != show.getNumSeasons()) {
            throw new NullPointerException();
        }
        show.setNumEpisodesForSeasons(intNumOfEpisodes);
    }

    /**
     * Changes the name of current show and add the update show into ShowList.
     * @param newName the updated name
     */
    public static void editName(String newName) {
        show.setName(newName);
        DeleteCommand.delete(showName);
        showName = newName;
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
        try {
            Scanner in = new Scanner(System.in);
            Ui.printEditPrompt();
            while (true) {
                String editCommand = in.nextLine();
                if (editCommand.startsWith("name")) {
                    editName(editCommand.substring(5));
                } else if (editCommand.startsWith("episode")) {
                    editEpisodes(editCommand.substring(8));
                } else if (editCommand.startsWith("season")) {
                    editSeasons(editCommand);
                } else if (editCommand.startsWith("duration")) {
                    editDuration(editCommand);
                } else if (editCommand.equals("done")) {
                    ShowList.setShow(showName, show);
                    break;
                }

            }
            Ui.printEditShow(showName);
            Ui.printShow(showName);
        } catch (NumberFormatException | ArithmeticException e) {
            Ui.printBadInputException();
        } catch (NullPointerException e) {
            Ui.printInvalidEpisodesInputException();
        }
    }
}
