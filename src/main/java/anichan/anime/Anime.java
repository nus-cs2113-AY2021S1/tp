package anichan.anime;

import anichan.exception.AniException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a single anime series.
 */
public class Anime {
    private static final String SET_RELEASE_DATE_PARSE_ERROR_MESSAGE = "Tried to parse an invalid date format!";
    private static final String DATE_PATTERN_PARSED = "yyyy-MM-dd";
    private static final String DASH_DELIMIT = "-";
    private static final String DATE_PATTERN_PRINT = "dd/MMM/yyyy";
    private static final int ZERO_VALUE = 0;
    private static final int MAX_RATING = 100;
    private static final int MIN_RATING = ZERO_VALUE;
    private static final String EMPTY_STRING = "";
    private static int totalAnime = ZERO_VALUE;

    private int animeID;
    private String animeName;
    private Date releaseDate;
    private int rating;
    private String[] genre;
    private int avgEpisodeLength;
    private int totalEpisodes;

    public Anime() {
        this.animeID = ZERO_VALUE;
        this.animeName = EMPTY_STRING;
        this.rating = ZERO_VALUE;
        this.totalEpisodes = ZERO_VALUE;
        this.genre = null;
        animeID = totalAnime;
    }

    public Anime(String animeName, String[] releaseDate, int rating, String[] genre,
                 int avgEpisodeLength, int totalEpisodes) throws AniException {
        setAnimeName(animeName);
        setReleaseDate(releaseDate);
        setRating(rating);
        setGenre(genre);
        setAvgEpisodeLength(avgEpisodeLength);
        setTotalEpisodes(totalEpisodes);
        incrementTotalAnime();
        setAnimeID(totalAnime);
    }

    public int getAnimeID() {
        return animeID;
    }

    public void setAnimeID(int animeID) {
        this.animeID = animeID;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
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
        SimpleDateFormat newDateFormat = new SimpleDateFormat(DATE_PATTERN_PRINT);
        return newDateFormat.format(releaseDate);
    }

    public int getRating() {
        return rating;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public static void setTotalAnime(int totalAnime) {
        Anime.totalAnime = totalAnime;
    }

    /**
     * Sets the rating of anime, by performing a check to ensure that it is within a specified range.
     *
     * @param rating is the rating to set it to
     */
    public void setRating(int rating) {
        if (rating > MAX_RATING || rating < MIN_RATING) {
            this.rating = ZERO_VALUE;
        } else {
            this.rating = rating;
        }
    }

    /**
     * Increments the class-level variable by 1.
     */
    private void incrementTotalAnime() {
        totalAnime++;
    }

    /**
     * Sets the release date of this anime. Will parse in a String array and set it as a Date object.
     *
     * @param releaseDate is a String Array containing the date information.
     */
    public void setReleaseDate(String[] releaseDate) throws AniException {
        try {
            String dateInString = releaseDate[0] + DASH_DELIMIT + releaseDate[1] + DASH_DELIMIT + releaseDate[2];
            SimpleDateFormat stringToDate = new SimpleDateFormat(DATE_PATTERN_PARSED);
            this.releaseDate = stringToDate.parse(dateInString);
        } catch (java.text.ParseException invalidDateFormat) {
            throw new AniException(SET_RELEASE_DATE_PARSE_ERROR_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return getAnimeName();
    }
}
