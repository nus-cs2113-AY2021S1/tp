package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;
import seedu.duke.utility.Ui;

import java.util.Scanner;

public class EditCommand {
    private static Show show;
    private static String showName;

    public EditCommand(String input) {
        showName = input;
        show = ShowList.getShow(showName);

    }

    public static void editSeasons(String editCommand) {
        int numSeasons = Integer.parseInt(editCommand.substring(7));
        show.setNumSeasons(numSeasons);
        int[] episodes;
        if (numSeasons > show.getNumSeasons()) {
            episodes = new int[numSeasons];
            for (int i = 0; i < show.getNumSeasons(); i++) {
                episodes[i] = show.getEpisodesForSeason(i);
            }
            for (int i = show.getNumSeasons(); i < numSeasons; i++) {
                episodes[i] = 1;
            }
        } else {
            episodes = new int[numSeasons];
            for (int i = 0; i < show.getNumSeasons(); i++) {
                episodes[i] = show.getEpisodesForSeason(i);
            }
        }
        show.setNumEpisodesForSeasons(episodes);
    }

    public static void processCommand() throws NullPointerException {
        Scanner in = new Scanner(System.in);
        System.out.println("What do you want to change , input done to stop editing");
        System.out.println("{name,season,episode}");
        while (true) {
            String editCommand = in.nextLine();
            if (editCommand.startsWith("name")) {
                show.setName(editCommand.substring(5));
            } else if (editCommand.startsWith("episode")) {
                String[] numOfEpisodes = editCommand.substring(8).split(",");
                int i = 0;
                int[] intNumOfEpisodes = new int[show.getNumSeasons()];
                for (String s : numOfEpisodes) {
                    intNumOfEpisodes[i] = Integer.parseInt(s);
                    i++;
                }
                //I put this below for now in case we need to add checks to ensure numOfEpisodes is not empty
                if (i == 0 || numOfEpisodes.length != show.getNumSeasons()) {
                    throw new NullPointerException();
                }
                show.setNumEpisodesForSeasons(intNumOfEpisodes);
            } else if (editCommand.startsWith("season")) {
                editSeasons(editCommand);
            } else if (editCommand.equals("done")) {
                break;
            }
            ShowList.setShow(showName, show);
        }
        Ui.printEditShow(showName);
    }
}
