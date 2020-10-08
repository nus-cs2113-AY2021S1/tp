package commands;

import manager.card.Card;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import scheduler.Scheduler;
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
    public static final String MESSAGE_NO_CARDS_IN_CHAPTER = "You currently have no cards in %1$s.";
    public static final String MESSAGE_NO_CARDS_DUE = "You have no cards due for revision in %1$s today.";
    public static final String MESSAGE_SHOW_ANSWER_PROMPT = "\n[enter s to show answer]";
    public static final String MESSAGE_SHOW_RATING_PROMPT = "How well did you do for this card?\n"
            + "[enter e(easy), m(medium), h(hard), c(cannot answer)]";
    public static final String EASY = "e";
    public static final String MEDIUM = "m";
    public static final String HARD = "h";
    public static final String CANNOT_ANSWER = "c";

    private final Chapter toRevise;

    public ReviseCommand(String toRevise) {
        this.toRevise = new Chapter(toRevise);
    }

    @Override
    public void execute(CardList cards, Ui ui) {
        ArrayList<Card> allCards = cards.getAllCards();
        ArrayList<Card> repeatCards = new ArrayList<>();
        int cardCount = cards.getCardCount();
        if (cardCount == 0) {
            ui.showToUser(String.format(MESSAGE_NO_CARDS_IN_CHAPTER, toRevise));
            return;
        }
        ui.showToUser("The revision for " + toRevise + " will start now:");
        int count = 1;
        for (Card c : allCards) {
            if (Scheduler.isDeadlineDue(c.getDueBy())) {
                ui.showToUser("\nQuestion " + count + ":");
                ui.showCard(c);
                String input = ui.getRating();
                repeatCards = rateCard(ui, repeatCards, c, input);
                count++;
            }
        }
        if (count == 1) {
            ui.showToUser(String.format(MESSAGE_NO_CARDS_DUE, toRevise));
            return;
        }

        repeatRevision(ui, repeatCards, count);
        ui.showToUser(String.format(MESSAGE_SUCCESS, toRevise));
    }

    public static ArrayList<Card> rateCard(Ui ui, ArrayList<Card> repeatCards, Card c, String input) {
        boolean isInvalid = true;
        while (isInvalid) {
            switch (input.toLowerCase()) {
            case EASY:
                c.setDueBy(Scheduler.computeEasyDeadline(c.getPreviousInterval()));
                isInvalid = false;
                break;
            case MEDIUM:
                c.setDueBy(Scheduler.computeMediumDeadline(c.getPreviousInterval()));
                isInvalid = false;
                break;
            case HARD:
                c.setDueBy(Scheduler.computeHardDeadline(c.getPreviousInterval()));
                isInvalid = false;
                break;
            case CANNOT_ANSWER:
                repeatCards.add(c);
                isInvalid = false;
                break;
            default:
                ui.showToUser("You have entered an invalid input, please try again.");
                input = ui.getRating();
            }
        }
        return repeatCards;
    }

    private void repeatRevision(Ui ui, ArrayList<Card> cards, int count) {
        while (cards.size() != 0) {
            System.out.println(cards.size());
            ArrayList<Card> repeatCards = new ArrayList<>();
            for (Card c : cards) {
                ui.showToUser("\nQuestion " + count + ":");
                ui.showCard(c);
                String input = ui.getRating();
                repeatCards = rateCard(ui, repeatCards, c, input);
                count++;
            }
            cards = new ArrayList<>(repeatCards);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
