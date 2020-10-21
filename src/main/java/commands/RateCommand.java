package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import manager.chapter.Chapter;
import storage.Storage;
import ui.Ui;

public class RateCommand extends Command {
    public static final String COMMAND_WORD = "rate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Rate chapter based on a specified index in the list. \n"
            + "Parameters: INDEX\n" + "Example: " + COMMAND_WORD + " 2\n";

    public static final String MESSAGE_SUCCESS = "You have completed rating for this chapter.";
    public static final String MESSAGE_FAIL = "Sorry, rate command format is wrong"
            + ", please read following instruction: \n";
    public static final String MESSAGE_START_RATING = "Please rate this Chapter! \n"
            + "You have the options of: Easy(E), Medium(M) or Hard(H) \n" + "Would your choice be E, M or H?";
    public static final String EASY = "e";
    public static final String MEDIUM = "m";
    public static final String HARD = "h";

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
        System.out.println(MESSAGE_SUCCESS);
    }

    public String getChoiceOfDeckRating() {
        System.out.println(MESSAGE_START_RATING);
        Ui ratingUi = new Ui();
        String userChoice = ratingUi.readCommand();
        while (!validDeckRating(userChoice)) {
            userChoice = ratingUi.readCommand();
        }
        return userChoice.toUpperCase();
    }

    private static boolean validDeckRating(String rating) {
        switch (rating.toUpperCase()) {
        case "E":
        case "M":
        case "H":
            return true;
        default:
            System.out.println(MESSAGE_FAIL);
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
