package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;
import seedu.duke.utility.Ui;

import java.util.ArrayList;

public class UpdateShowEpisodeProgressCommand extends Command {
    ArrayList<String> inputs;

    public UpdateShowEpisodeProgressCommand(String description, ArrayList<String> inputs) throws NullPointerException {
        super(description);
        this.inputs = inputs;
        if (inputs.size() != 3) {
            throw new NullPointerException();
        }
    }



    /**
     * Updates the current episode field. Does not change the watchtime.
     * INPUT : episode "show" "episode".
     */
    public void processCommand() {
        String showName = inputs.get(1);
        int episode = Integer.parseInt(inputs.get(2));
        try {
            Show show = ShowList.getShow(showName);
            int currentSeason = show.getCurrentSeason();
            if (episode > show.getEpisodesForSeason(currentSeason) || episode <= 0) {
                throw new RuntimeException();
            }
            show.setEpisodeWatched(episode);
            ShowList.setShow(showName, show);
            Ui.printChangeEpisode(showName);
        } catch (NullPointerException e) {
            Ui.printNotFoundException();
        } catch (RuntimeException e) {
            Ui.printInputLargerThanExpected();
        }
    }


}
