package seedu.quotesify.commands.edit;

import seedu.quotesify.commands.Command;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.rating.RatingParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class EditRatingCommand extends EditCommand {

    public EditRatingCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TextUi ui, Storage storage) {
        RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        editRating(ratings, ui);
    }

    private void editRating(RatingList ratings, TextUi ui) {

        boolean hasMissingInput = RatingParser.checkUserInput(information);
        if (hasMissingInput) {
            return;
        }

        String[] ratingDetails;
        String title;
        String author;
        try {
            ratingDetails = information.split(" ", 2);
            String[] titleAndAuthor = ratingDetails[1].split(Command.FLAG_AUTHOR, 2);
            title = titleAndAuthor[0].trim();
            author = titleAndAuthor[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(RatingParser.ERROR_INVALID_FORMAT_RATING);
            return;
        }

        int ratingScore = RatingParser.checkValidityOfRatingScore(ratingDetails[0]);
        Rating existingRating = checkIfRatingExists(ratings, title, author);
        boolean isValid = ((ratingScore != RatingParser.INVALID_RATING) && (existingRating != null));
        if (isValid) {
            existingRating.setRating(ratingScore);
            existingRating.getRatedBook().setRating(ratingScore);
            ui.printEditRating(ratingScore, title, author);
        }
    }

    private Rating checkIfRatingExists(RatingList ratings, String title, String author) {
        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(title)
                    && rating.getAuthorOfRatedBook().equals(author)) {
                return rating;
            }
        }

        System.out.println(ERROR_RATING_NOT_FOUND);
        return null;
    }
}
