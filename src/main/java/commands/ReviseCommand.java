package commands;

import access.Access;
import common.KajiLog;
import manager.card.Card;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import scheduler.Scheduler;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_INVALID_INDEX_RANGE;


/**
 * Starts revision for a particular chapter.
 */
public class ReviseCommand extends Command {
    private static Logger logger = KajiLog.getLogger(ReviseCommand.class.getName());

    public static final String COMMAND_WORD = "revise";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts revision based on a particular chapter. \n"
            + "Parameters: INDEX_OF_CHAPTER\n" + "Example: " + COMMAND_WORD + " 2\n";

    public static final String MESSAGE_SUCCESS = "You have completed revision for <%1$s>.";
    public static final String MESSAGE_NO_CARDS_IN_CHAPTER = "You currently have no cards in <%1$s>.";
    public static final String MESSAGE_CHAPTER_NOT_DUE = "The chapter <%1$s> is not due for revision today.\n";
    public static final String MESSAGE_SHOW_ANSWER_PROMPT = "\n[enter s to show answer]";
    public static final String MESSAGE_SHOW_RATING_PROMPT = "How well did you do for this card?\n"
            + "[enter e(easy), m(medium), h(hard), c(cannot answer)]";
    public static final String MESSAGE_SHOW_REVISE_PROMPT = "Are you sure you want to revise this? (Y/N)";
    public static final String MESSAGE_START_REVISION = "The revision for %s will start now:";
    public static final String MESSAGE_NOT_REVISING = "You have chosen to not revise the chapter <%1s>.";
    public static final String EASY = "e";
    public static final String MEDIUM = "m";
    public static final String HARD = "h";
    public static final String CANNOT_ANSWER = "c";

    private final int reviseIndex;

    public ReviseCommand(int reviseIndex) {
        this.reviseIndex = reviseIndex;
    }

    /**
     * Gets the chapter object based on reviseIndex.
     *
     * @param reviseIndex index of chapter to revise
     * @param access access object of user
     * @return chapter object to revise
     * @throws IndexOutOfBoundsException if index of chapter provided is not found
     */
    private Chapter getChapter(int reviseIndex, Access access) throws IndexOutOfBoundsException {
        Chapter chapter;
        try {
            chapter = access.getModule().getChapters().getChapter(reviseIndex);
            return chapter;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(String.format(MESSAGE_INVALID_INDEX_RANGE, CHAPTER));
        }
    }

    /**
     * Get the list of card objects in the chapter to be revised.
     *
     * @param access access object of the user
     * @param storage to get the cards from storage file
     * @param toRevise chapter object to revise
     * @return list of card objects in the chapter to be revised
     * @throws FileNotFoundException if the is not found
     */
    private ArrayList<Card> getCards(Access access, Storage storage, Chapter toRevise)
            throws FileNotFoundException {
        ArrayList<Card> allCards;
        try {
            allCards = storage.loadCard(access.getModuleLevel(), toRevise.getChapterName());
            toRevise.setCards(allCards);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File is not found.\n");
        }
        return allCards;
    }

    /**
     * Shows the contents of each flashcard during revision.
     *
     * @param count flashcard number
     * @param c card object that is currently being revised
     * @param ui ui object to print contents of the flashcard
     * @param repeatCards list of card objects that user could not answer
     * @param scanner to take in user input
     * @return
     */
    private int reviseCard(int count, Card c, Ui ui, ArrayList<Card> repeatCards, Scanner scanner) {
        ui.showToUser("\nQuestion " + count + ":");
        ui.showCardRevision(c, scanner);
        String input = ui.getInput(MESSAGE_SHOW_RATING_PROMPT, scanner);
        rateCard(ui, repeatCards, c, input, scanner);
        return ++count;
    }

    /**
     * Executes the ReviseCommand by calling the relevant methods.
     *
     * @param ui ui which the command uses to print messages
     * @param access access which the command uses to get the modules, chapters or cards
     * @param storage storage which the command uses to load or write data to storage files
     * @throws IOException if there is error in loading or writing data to storage files
     */
    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IOException {
        Chapter toRevise = getChapter(reviseIndex, access);
        // re-initialize scanner for testing purposes
        Scanner scanner = new Scanner(System.in);
        if (!Scheduler.isDeadlineDue(toRevise.getDueBy())) {
            if (promptNotDue(ui, toRevise, scanner)) {
                ui.showToUser(String.format(MESSAGE_NOT_REVISING, toRevise));
                return;
            }
        }

        ArrayList<Card> allCards = getCards(access, storage, toRevise);
        ArrayList<Card> repeatCards = new ArrayList<>();
        int cardCount = allCards.size();
        ui.showToUser("\nCard count: " + cardCount);
        if (cardCount == 0) {
            ui.showToUser(String.format(MESSAGE_NO_CARDS_IN_CHAPTER, toRevise));
            return;
        }

        ui.showToUser(String.format(MESSAGE_START_REVISION, toRevise));

        int count = 1;

        for (Card c : allCards) {
            count = reviseCard(count, c, ui, repeatCards, scanner);
        }
        repeatRevision(ui, repeatCards, count, scanner);
        ui.showToUser(String.format(MESSAGE_SUCCESS, toRevise));
        logger.info("Revision has completed for chapter: " + toRevise);
        toRevise.setDueBy(Scheduler.computeChapterDeadline(toRevise.getCards()), storage, access);
        CardList newCards = new CardList(allCards);
        storage.saveCards(newCards, access.getModuleLevel(), toRevise.getChapterName());
        HistoryCommand.addHistory(access, storage, reviseIndex);
    }

    /**
     * Prompts user to confirm whether he wants to start revision on a chapter that is not due.
     *
     * @param ui ui object to print the prompt
     * @param toRevise chapter object to be revised
     * @param scanner scanner object to take in user input
     * @return boolean value of whether user wants to revise the chapter
     */
    private boolean promptNotDue(Ui ui, Chapter toRevise, Scanner scanner) {
        StringBuilder prompt = new StringBuilder();
        prompt.append(String.format(MESSAGE_CHAPTER_NOT_DUE, toRevise));
        prompt.append(MESSAGE_SHOW_REVISE_PROMPT);
        String input = ui.getInput(prompt.toString(), scanner).trim().toUpperCase();
        boolean isInvalid = true;
        boolean notRevising = false;
        while (isInvalid) {
            switch (input) {
            case "Y":
                isInvalid = false;
                notRevising = false;
                break;
            case "N":
                isInvalid = false;
                notRevising = true;
                break;
            default:
                input = ui.getInput("You have entered an invalid input, please try again.", scanner)
                        .trim().toUpperCase();
            }
        }
        return notRevising;
    }

    /**
     * Gets user rating for a flashcard.
     *
     * @param ui ui object to get user's rating if it is invalid
     * @param repeatCards list of card objects that user could not answer
     * @param c card object that is currently being revised
     * @param input user's rating for the flashcard
     * @param scanner scanner object to take in user's input if it is invalid
     * @return list of card objects which user could not answer after rating
     */
    private ArrayList<Card> rateCard(Ui ui, ArrayList<Card> repeatCards,
                                           Card c, String input, Scanner scanner) {
        boolean isInvalid = true;
        while (isInvalid) {
            switch (input.trim().toLowerCase()) {
            case EASY:
                c.setPreviousInterval(Scheduler.computeEasyInterval(c.getPreviousInterval()));
                c.setRating(Card.EASY);
                isInvalid = false;
                break;
            case MEDIUM:
                c.setPreviousInterval(Scheduler.computeMediumInterval(c.getPreviousInterval()));
                c.setRating(Card.MEDIUM);
                isInvalid = false;
                break;
            case HARD:
                c.setPreviousInterval(Scheduler.computeHardInterval(c.getPreviousInterval()));
                c.setRating(Card.HARD);
                isInvalid = false;
                break;
            case CANNOT_ANSWER:
                repeatCards.add(c);
                c.setRating(Card.CANNOT_ANSWER);
                isInvalid = false;
                break;
            default:
                input = ui.getInput("You have entered an invalid input, please try again.", scanner);
            }
        }
        return repeatCards;
    }

    /**
     * Repeats revision for cards which user could not answer.
     *
     * @param ui ui object to pass to revise card for printing of contents of flashcard
     * @param cards list of card objects to be revised again
     * @param count current flashcard count
     * @param scanner scanner object to take in user input
     */
    private void repeatRevision(Ui ui, ArrayList<Card> cards, int count, Scanner scanner) {
        while (cards.size() != 0) {
            ArrayList<Card> repeatCards = new ArrayList<>();
            for (Card c : cards) {
                count = reviseCard(count, c, ui, repeatCards, scanner);
            }
            cards = new ArrayList<>(repeatCards);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
