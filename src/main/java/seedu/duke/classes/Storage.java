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

//@@author judowha

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
            fw.write("\t\tSeason: " + entry.getValue().getNumSeasons() + System.lineSeparator());
            String episodes = "";
            for (int i = 1; i <= entry.getValue().getNumSeasons(); i++) {
                episodes = episodes + entry.getValue().getEpisodesForSeason(i) + " ";
            }
            fw.write("\t\tEpisodes: " + episodes + System.lineSeparator());
            fw.write("\t\tRating: " + entry.getValue().getRating() + System.lineSeparator());
            fw.write("\t\tReview: " + entry.getValue().getReview() + System.lineSeparator());
            fw.write("\t\tDuration: " + entry.getValue().getEpisodeDuration() + System.lineSeparator());
            fw.write("\t\tCurrent Season: " + entry.getValue().getCurrentSeason() + System.lineSeparator());
            fw.write("\t\tCurrent Episode: " + entry.getValue().getCurrentEpisode() + System.lineSeparator());
            index++;

        }
        fw.close();
    }


    private WatchTime loadWatchTimeDetail(Scanner s) {
        if (s.hasNext()) {
            String[] splitRecordedDate = s.nextLine().split("recordedDate: ");
            try {
                recordedDate = LocalDate.parse(splitRecordedDate[1]);
            } catch (Exception e) {
                recordedDate = LocalDate.now();
                durationWatchedToday = 0;
                dailyWatchLimit = 0;
            }
            String[] splitDurationWatched = s.nextLine().split("durationWatchedToday: ");
            durationWatchedToday = Integer.parseInt(splitDurationWatched[1]);
            String[] splitDailyWatchedLimit = s.nextLine().split("dailyWatchLimit: ");
            dailyWatchLimit = Integer.parseInt(splitDailyWatchedLimit[1]);
        }
        if (WatchTime.checkIfDifferentDay(recordedDate)) {
            recordedDate = LocalDate.now();
            durationWatchedToday = 0;
            dailyWatchLimit = 0;
        }
        return new WatchTime(recordedDate,durationWatchedToday,dailyWatchLimit);
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
        loadWatchTimeDetail(s);
        while (s.hasNext()) {
            String name = s.nextLine().substring(3);
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

            String[] splitReview = s.nextLine().split("Review: ");
            String review = splitReview[1];

            String[] splitDuration = s.nextLine().split("Duration: ");
            int duration = Integer.parseInt(splitDuration[1]);
            shows.setShow(name, new Show(name, season, episodes, duration));
            shows.getShow(name).setReview(review);
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