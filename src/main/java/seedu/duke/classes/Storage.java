package seedu.duke.classes;

import seedu.duke.utility.SaveState;
import seedu.duke.utility.ShowList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Storage implements SaveState {

    private String filePath;

    private LocalDate recordedDate;
    private int durationWatchedToday;
    private int dailyWatchLimit;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void saveState() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(WatchTime.saveStateFormat());
        int index = 1;
        for (Map.Entry<String, Show> entry : ShowList.getShowList().entrySet()) {
            fw.write(index + ". " + entry.getValue().getName() + System.lineSeparator());
            fw.write("      Season: " + entry.getValue().getNumSeasons() + System.lineSeparator());
            String episodes = "";
            for (int i = 1; i <= entry.getValue().getNumSeasons(); i++) {
                episodes = episodes + entry.getValue().getEpisodesForSeason(i) + " ";
            }
            //TODO : Change the many spaces to a tab '\t'
            fw.write("      Episodes: " + episodes + System.lineSeparator());
            fw.write("      Rating: " + entry.getValue().getRating() + System.lineSeparator());
            fw.write("      Duration: " + entry.getValue().getEpisodeDuration() + System.lineSeparator());
            fw.write("      Current Season: " + entry.getValue().getCurrentSeason() + System.lineSeparator());
            fw.write("      Current Episode: " + entry.getValue().getCurrentEpisode() + System.lineSeparator());

            index++;

            //this is another save format
            /*
            String textToAdd=index+". "+entry.getValue().getName()+" | Season: "+entry.getValue().getNumSeasons()
                    +" | Episodes: " +entry.getValue().getEpisodesForSeason(entry.getValue().getNumSeasons())+" |
                    Rating: "+entry.getValue().getRating();
            fw.write(textToAdd);
             */
        }
        fw.close();
    }

    //TODO:  JIQING CATCH EXCEPTION PLEASEE :((((
    private boolean watchTimeSaveInputParse(String input) {
        String[] splitRecordedDate = input.split("recordedDate: ");
        if (splitRecordedDate.length > 1) {
            recordedDate = LocalDate.parse(splitRecordedDate[1]);
            return true;
        }
        String[] splitDurationWatched = input.split("durationWatchedToday: ");
        if (splitDurationWatched.length > 1) {
            durationWatchedToday = Integer.parseInt(splitDurationWatched[1]);
            return true;
        }
        String[] splitDailyWatchedLimit = input.split("dailyWatchLimit: ");
        if (splitDailyWatchedLimit.length > 1) {
            dailyWatchLimit = Integer.parseInt(splitDailyWatchedLimit[1]);
            return true;
        }
        return false;
    }

    @Override
    public ShowList loadState() throws FileNotFoundException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File f = new File(filePath);
        try {
            f.createNewFile();
        } catch (IOException ex) {
            seedu.duke.utility.Ui.showCreateFileError();
        }
        Scanner s = new Scanner(f);
        ShowList shows = new ShowList();

        // we just assume that advanced users who manually change the file can adhere to the correct format
        while (s.hasNext()) {
            String input = s.nextLine();
            if (watchTimeSaveInputParse(input)) {
                continue;
            }
            String name = input.substring(3);

            String[] splitSeason = s.nextLine().split("Season: ");
            int season = Integer.parseInt(splitSeason[1]);
            String[] splitEpisodes = s.nextLine().split("Episodes: ");
            String[] episodeString = splitEpisodes[1].split(" ");
            int[] episodes = new int[season];

            for (int i = 0; i < season; i++) {
                episodes[i] = Integer.parseInt(episodeString[i]);
            }

            String[] splitRating = s.nextLine().split("Rating: ");
            int rating = Integer.parseInt(splitRating[1]);
            String[] splitDuration = s.nextLine().split("Duration: ");
            int duration = Integer.parseInt(splitDuration[1]);
            shows.setShow(name, new Show(name, season, episodes, duration));
            shows.getShow(name).setRating(rating);

            String[] splitCurrentSeason = s.nextLine().split("Current Season: ");
            int currentSeason = Integer.parseInt(splitCurrentSeason[1]);
            shows.getShow(name).setCurrentSeason(currentSeason);

            String[] splitCurrentEpisode = s.nextLine().split("Current Episode: ");
            int currentEpisode = Integer.parseInt(splitCurrentEpisode[1]);
            shows.getShow(name).setEpisodeWatched(currentEpisode);

        }
        return shows;
    }

    public LocalDate getRecordedDate() {
        return recordedDate;
    }

    public int getDurationWatchedToday() {
        return durationWatchedToday;
    }

    public int getDailyWatchLimit() {
        return dailyWatchLimit;
    }
}