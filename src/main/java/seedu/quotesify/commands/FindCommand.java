package seedu.quotesify.commands;

import seedu.quotesify.lists.ListManager;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class FindCommand extends Command {

    private String type;
    private String information;

    public FindCommand(String arguments) {
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    public void execute(TextUi ui) {
        switch (type) {
        case TAG_RATING:
            RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
            findRating(ratings, ui);
            break;
        default:
            ui.printListOfFindCommands();
            break;
        }
        Storage.save();
    }

    private void findRating(RatingList ratings, TextUi ui) {
        String ratingToFind = information.trim();
        if (ratingToFind.isEmpty()) {
            System.out.println(ERROR_RATING_MISSING_BOOK_TITLE);
            return;
        }
        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(ratingToFind)) {
                ui.printFoundRating(rating, ratingToFind);
                return;
            }
        }
        System.out.println(ERROR_RATING_NOT_FOUND);
    }

    public boolean isExit() {
        return false;
    }
}
