package seedu.duke.commands;

import seedu.duke.author.Author;
import seedu.duke.book.Book;
import seedu.duke.book.BookList;
import seedu.duke.lists.ListManager;
import seedu.duke.lists.QuotesifyList;
import seedu.duke.quote.Quote;
import seedu.duke.quote.QuoteList;
import seedu.duke.rating.Rating;
import seedu.duke.rating.RatingList;
import seedu.duke.ui.TextUi;

public class AddCommand extends Command {
    public static final int RATING_ONE = 1;
    public static final int RATING_FIVE = 5;
    private static final String TAG_BOOK = "-b";
    private static final String TAG_QUOTE = "-q";
    private static final String TAG_RATING = "-r";

    private String type;
    private String information;

    public AddCommand(String arguments) {
        String[] details = arguments.split(" ", 2);
        type = details[0];
        information = details[1];
    }

    public void execute(TextUi ui, ListManager listManager) {
        switch (type) {
        case TAG_BOOK:
            BookList books = (BookList) listManager.getList(ListManager.BOOK_LIST);
            addBook(books);
            ui.printSuccessfulAddCommand();
            break;
        case TAG_QUOTE:
            QuoteList quotes = (QuoteList) listManager.getList(ListManager.QUOTE_LIST);
            addQuote(quotes);
            ui.printAllQuotes(quotes);
            break;
        case TAG_RATING:
            RatingList ratings = (RatingList) listManager.getList(ListManager.RATING_LIST);
            BookList existingBooks = (BookList) listManager.getList(ListManager.BOOK_LIST);
            addRating(ratings, existingBooks);
            break;
        default:
        }
    }

    private void addRating(RatingList ratings, BookList existingBooks) {
        String[] ratingDetails = information.split(" ", 2);
        int ratingScore;
        try {
            ratingScore = Integer.parseInt(ratingDetails[0]);
        } catch (NumberFormatException e) {
            System.out.println("Sorry I don't understand you");
            return;
        }
        String titleOfBookToRate = ratingDetails[1].trim();
        if (!(ratingScore >= RATING_ONE && ratingScore <= RATING_FIVE)) {
            System.out.println("That score is out of our range my friend");
            return;
        }

        boolean doesExist = false;
        for (int i = 0; i < existingBooks.getList().size(); i++) {
            if (existingBooks.getList().get(i).getTitle().equals(titleOfBookToRate)) {
                doesExist = true;
                break;
            }
        }

        if (doesExist) {
            ratings.add(new Rating(ratingScore, titleOfBookToRate));
            System.out.println("You have just rated " + titleOfBookToRate + " "
                    + ratingScore + " star!");
        } else {
            System.out.println("I can't find this book to rate!");
        }
    }

    private void addBook(BookList books) {
        String[] titleAndAuthor = information.split("/by", 2);
        Author author = new Author(titleAndAuthor[1].trim());
        books.add(new Book(author, titleAndAuthor[0].trim()));
    }

    private void addQuote(QuoteList quotes) {
        if (information.contains("/from") && information.contains("/by")) {
            String[] quoteAndInformation = information.split("/from", 2);
            String[] referenceAndAuthor = quoteAndInformation[1].split("/by", 2);
            Author author = new Author(referenceAndAuthor[1].trim());
            quotes.add(new Quote(quoteAndInformation[0].trim(), referenceAndAuthor[0].trim(), author));
        } else if (information.contains("/from")) {
            String[] quoteAndReference = information.split("/from", 2);
            quotes.add(new Quote(quoteAndReference[0].trim(), quoteAndReference[1].trim()));
        } else if (information.contains("/by")) {
            String[] quoteAndAuthor = information.split("/by", 2);
            quotes.add(new Quote(quoteAndAuthor[0].trim(), quoteAndAuthor[1].trim()));
        } else {
            quotes.add(new Quote(information.trim()));
        }

    }

    public boolean isExit() {
        return false;
    }
}
