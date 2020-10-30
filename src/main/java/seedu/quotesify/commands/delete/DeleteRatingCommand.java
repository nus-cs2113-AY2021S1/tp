package seedu.quotesify.commands.delete;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.commands.Command;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.rating.RatingParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class DeleteRatingCommand extends DeleteCommand {

    public DeleteRatingCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TextUi ui, Storage storage) {
        RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        deleteRating(ratings, ui);
    }

    private void deleteRating(RatingList ratings, TextUi ui) {

        boolean hasMissingInput = RatingParser.checkUserInput(information);
        if (hasMissingInput) {
            return;
        }

        String[] titleAndAuthor;
        String title;
        String author;
        try {
            titleAndAuthor = information.split(Command.FLAG_AUTHOR, 2);
            title = titleAndAuthor[0].trim();
            author = titleAndAuthor[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(RatingParser.ERROR_INVALID_FORMAT_RATING);
            return;
        }

        BookList books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        for (Book book : books.getList()) {
            if (book.getTitle().equals(title) && book.getAuthor().getName().equals(author)) {
                book.setRating(RatingParser.UNRATED);
                break;
            }
        }

        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(title)
                    && rating.getAuthorOfRatedBook().equals(author)) {
                ratings.delete(ratings.getList().indexOf(rating));
                ui.printDeleteRating(title, author);
                return;
            }
        }

        System.out.println(ERROR_RATING_NOT_FOUND);
    }
}
