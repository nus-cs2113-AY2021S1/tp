package seedu.quotesify.commands.delete;

import seedu.quotesify.book.Book;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.rating.RatingParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;
import seedu.quotesify.ui.UiMessage;

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
        System.out.println(UiMessage.DIVIDER_LINE);
        boolean hasMissingInput = RatingParser.checkUserInput(information);
        if (hasMissingInput) {
            System.out.println(UiMessage.DIVIDER_LINE);
            return;
        }

        String bookIndex = information.trim();
        Book bookToDeleteRating = RatingParser.checkBookExists(bookIndex);

        if (bookToDeleteRating == null) {
            System.out.println(UiMessage.DIVIDER_LINE);
            return;
        }

        bookToDeleteRating.setRating(RatingParser.UNRATED);
        String title = bookToDeleteRating.getTitle();
        String author = bookToDeleteRating.getAuthor().getName();

        for (Rating rating : ratings.getList()) {
            if (rating.getTitle().equals(title) && rating.getAuthor().equals(author)) {
                ratings.delete(ratings.getList().indexOf(rating));
                ui.printDeleteRating(title, author);
                return;
            }
        }
        System.out.println(ERROR_RATING_DO_NOT_EXIST);
        System.out.println(UiMessage.DIVIDER_LINE);
    }
}
