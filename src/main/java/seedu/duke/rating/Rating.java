package seedu.duke.rating;

import seedu.duke.book.Book;

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
}
