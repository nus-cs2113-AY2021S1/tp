package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;
import java.util.Scanner;

public class EditCommand {
    private static Show show;
    private static String showName;
    public EditCommand(String input) {
        showName = input;
        show = ShowList.getShow(showName);

    }
    public static void processCommand() {
        Scanner in = new Scanner(System.in);
        System.out.println("What do you want to change , input done to stop editing");
        System.out.println("{name,season,episode}");
        while (true) {
            String editCommand = in.nextLine();
            if (editCommand.startsWith("name")) {
                show.setName(editCommand.substring(5));
            }
            else if (editCommand.startsWith("episode")) {
                String[] numOfEpisodes = editCommand.substring(8).split(",");
                int i = 0;
                int[] intNumOfEpisodes = new int[show.getNumSeasons()];
                for (String s : numOfEpisodes) {
                   intNumOfEpisodes[i] = Integer.parseInt(s);
                   i++;
                }
                show.setNumEpisodesForSeasons(intNumOfEpisodes);
            }
            else if (editCommand.startsWith("season")) {
                show.setNumSeasons(Integer.parseInt(editCommand.substring(7)));
            }
            else if (editCommand.equals("done")) {
                break;
            }
            ShowList.setShow(showName, show);
        }
    }
}
