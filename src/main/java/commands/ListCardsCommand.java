package commands;

import access.Access;
import manager.card.Card;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

import static common.Messages.CARD;

/**
 * Lists all the flashcards in a chapter.
 */
public class ListCardsCommand extends ListCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of flashcards available. \n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        String result = listCards(access);
        ui.showToUser(result);
    }

    /**
     * Lists all of the flashcards.
     *
     * @param access to get the list of cards
     * @return result to be displayed
     */
    private String listCards(Access access) {
        assert access.isChapterLevel() : "Not chapter level";
        CardList cards = access.getChapter().getCards();
        ArrayList<Card> allCards = cards.getAllCards();
        int cardCount = cards.getCardCount();

        if (cardCount == 0) {
            return String.format(MESSAGE_DOES_NOT_EXIST, CARD);
        }

        StringBuilder result = new StringBuilder();
        result.append(String.format(MESSAGE_EXIST, CARD));
        for (Card c : allCards) {
            result.append("\n").append(allCards.indexOf(c) + 1).append(".").append(c);
        }
        return result.toString();
    }
}
