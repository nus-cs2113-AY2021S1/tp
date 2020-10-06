package commands;

import manager.card.Card;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import ui.Ui;

import java.util.ArrayList;

/**
 * Starts revision for a particular chapter.
 */
public class ReviseCommand extends Command {
    public static final String COMMAND_WORD = "revise";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts revision based on a particular chapter. \n"
            + "Parameters: CHAPTER_NAME\n" + "Example: " + COMMAND_WORD + " Polymorphism\n";

    public static final String MESSAGE_SUCCESS = "You have completed revision for %1$s.";
    public static final String MESSAGE_NO_CARDS_IN_CHAPTER = "You have currently have no cards in %1$s.";
    public static final String MESSAGE_NO_CARDS_DUE = "You have no cards due for revision in %1$s today.";
    public static final String MESSAGE_SHOW_ANSWER_PROMPT = "\n[enter s to show answer]";

    private final Chapter toRevise;

    public ReviseCommand(Chapter toRevise) {
        this.toRevise = toRevise;
    }

    @Override
    public void execute(CardList cards, Ui ui) {
        ArrayList<Card> allCards = cards.getAllCards();
        int cardCount = cards.getCardCount();
        ui.showRevisionContent(allCards, cardCount, toRevise);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
