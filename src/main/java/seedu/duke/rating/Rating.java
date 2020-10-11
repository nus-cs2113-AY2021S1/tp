package seedu.duke.rating;

public class Rating {
    private String titleOfRatedBook;
    private int rating;

    public Rating(int rating, String titleOfRatedBook) {
        this.rating = rating;
        this.titleOfRatedBook = titleOfRatedBook;
    }

    public String getTitleOfRatedBook() {
        return titleOfRatedBook;
    }

    public void setTitleOfRatedBook(String titleOfRatedBook) {
        this.titleOfRatedBook = titleOfRatedBook;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return titleOfRatedBook + ": " + rating + " star" + System.lineSeparator();
    }
}
