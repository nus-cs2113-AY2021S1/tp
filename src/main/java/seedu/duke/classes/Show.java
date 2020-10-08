package seedu.duke.classes;


import java.time.LocalDateTime;

public class Show {
    protected String name;
    protected int numSeasons;
    protected int[] numEpisodesForSeasons;
    protected int rating;
    protected String review;
    protected LocalDateTime showTime;   //The time of the show, maybe include date

    public Show(String name, int numSeasons, int[] numEpisodesForSeasons) {
        this.name = name;
        this.numSeasons = numSeasons;
        this.numEpisodesForSeasons = numEpisodesForSeasons;
        this.rating = -1;
        this.review = "null";
    }


    public String getName() {
        return name;
    }

    public int getNumSeasons() {
        return numSeasons;
    }

    public int[] getNumEpisodesForSeasons() {
        return numEpisodesForSeasons;
    }

    public int getRating() {
        return rating;
    }

    public int getEpisodesForSeason(int season) {
        //TODO : Add the exception for bounds checking
        return numEpisodesForSeasons[season];
    }

    public String getReview() {
        return review;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setNumSeasons(int numSeasons) {
        this.numSeasons = numSeasons;
    }

    public void setNumEpisodesForSeasons(int[] numEpisodesForSeasons) {
        this.numEpisodesForSeasons = numEpisodesForSeasons;
    }
}
