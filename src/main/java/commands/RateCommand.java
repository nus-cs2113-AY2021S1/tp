package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import manager.chapter.Chapter;
import storage.Storage;
import ui.Ui;

public class RateCommand extends Command {
    public static final String COMMAND_WORD = "rate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Rates chapter based on a specified index in the list. \n"
            + "Parameters: INDEX\n" + "Example: " + COMMAND_WORD + " 2\n";

    public static final String MESSAGE_SUCCESS = "You have completed rating for this chapter.";
    public static final String MESSAGE_FAIL = "Sorry, rate command format is wrong"
            + ", please read following instruction: \n";
    public static final String MESSAGE_START_RATING = "Please rate this Chapter! \n";
    public static final String MESSAGE_RATING_USAGE = "You have the options of: Easy(E), Medium(M) or Hard(H) \n"
            + "Would your choice be E, M or H?";
    public static final String EASY = "E";
    public static final String MEDIUM = "M";
    public static final String HARD = "H";

    protected int ratingIndex;

    public RateCommand(int ratingIndex) {
        this.ratingIndex = ratingIndex;
    }

    public Chapter getChapter(int ratingIndex, Access access) throws IndexOutOfBoundsException {
        Chapter chapter;
        try {
            chapter = access.getModule().getChapters().getChapter(ratingIndex);
            return chapter;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("The chapter is not found.\n");
        }
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IncorrectAccessLevelException {
        if (!(access.isModuleLevel())) {
            throw new IncorrectAccessLevelException("Rate command can only be called "
                    + "at module level.");
        }
        Chapter toRate = getChapter(ratingIndex, access);
        rateCard(toRate, getChoiceOfDeckRating());
        ui.showToUser(MESSAGE_SUCCESS);
    }

    public String getChoiceOfDeckRating() {
        Ui ratingUi = new Ui();
        ratingUi.showToUser(MESSAGE_START_RATING + MESSAGE_RATING_USAGE);
        String userChoice = ratingUi.readCommand();
        while (!validDeckRating(userChoice)) {
            ratingUi.showToUser(MESSAGE_FAIL + MESSAGE_RATING_USAGE);
            userChoice = ratingUi.readCommand();
        }
        return userChoice.toUpperCase();
    }

    private static boolean validDeckRating(String rating) {
        switch (rating.toUpperCase()) {
        case EASY:
        case MEDIUM:
        case HARD:
            return true;
        default:
            return false;
        }
    }

    public void rateCard(Chapter chapter, String input) {
        switch (input.trim().toLowerCase()) {
        case EASY:
            chapter.setRating(chapter.EASY);
            break;
        case MEDIUM:
            chapter.setRating(chapter.MEDIUM);
            break;
        case HARD:
            chapter.setRating(chapter.HARD);
            break;
        default:
            chapter.setRating(chapter.NO_RATING);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
