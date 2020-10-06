package seedu.duke;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Anime {
    private String animeName;
    private Date releaseDate;
    private int rating;
    private String[] genre;
    private int avgEpisodeLength;
    private boolean isCompleted;
    private int totalEpisodes;

    //Default Constructor
    public Anime() {
        this.animeName = "";
        this.rating = 0;
        this.totalEpisodes = 0;
        this.genre = null;
    }

    public Anime(String animeName, String[] releaseDate, int rating, String[] genre, int avgEpisodeLength,
                 int totalEpisodes) {
        setAnimeName(animeName);
        setReleaseDate(releaseDate);
        setRating(rating);
        setGenre(genre);
        setAvgEpisodeLength(avgEpisodeLength);
        setTotalEpisodes(totalEpisodes);
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public int getAvgEpisodeLength() {
        return avgEpisodeLength;
    }

    public void setAvgEpisodeLength(int avgEpisodeLength) {
        this.avgEpisodeLength = avgEpisodeLength;
    }


    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getReleaseDateInString() {
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        return newDateFormat.format(releaseDate);
    }

    public void setReleaseDate(String[] releaseDate) {
        //Will parse according to how date is stored in JSON YYYY MM DD
        try {
            String dateInString = releaseDate[0] + "-" + releaseDate[1] + "-" + releaseDate[2];
            SimpleDateFormat stringToDate = new SimpleDateFormat("yyyy-MM-dd");
            this.releaseDate = stringToDate.parse(dateInString);
        } catch (java.text.ParseException invalidDateFormat) {
            System.out.println("Date error");
        }
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating > 100 || rating < 0) {
            System.out.println("Rating not within 0 to 100 range");
            this.rating = 0;
        } else {
            this.rating = rating;
        }
    }
    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return getAnimeName();
    }
}
