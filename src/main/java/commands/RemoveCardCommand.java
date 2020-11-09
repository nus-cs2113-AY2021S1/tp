package commands;

import access.Access;
import common.KajiLog;
import manager.card.Card;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import static common.Messages.CARD;
import static common.Messages.MESSAGE_INVALID_INDEX_RANGE;

//@@author neojiaern
/**
 * Removes a flashcard from the chapter.
 */
public class RemoveCardCommand extends RemoveCommand {
    private static Logger logger = KajiLog.getLogger(RemoveCardCommand.class.getName());

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes card based on the index in the list. \n"
            + "Parameters: " + CARD_PARAMETER + "\n" + "Example: " + COMMAND_WORD + " 2\n";

    private final int removeIndex;

    public RemoveCardCommand(int removeIndex) {
        this.removeIndex = removeIndex;
    }

    /**
     * Executes the RemoveCardCommand by calling the relevant methods.
     *
     * @param ui ui which the command uses to print messages
     * @param access access which the command uses to get the cards
     * @param storage storage which the command uses to load or write data to storage files
     * @throws IOException if there is error in loading or writing data to storage files
     */
    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IOException {
        String result = removeCard(access, storage);
        ui.showToUser(result);
    }

    /**
     * Removes a flashcard from the chapter.
     *
     * @param access to get the list of card objects
     * @param storage to remove card from storage
     * @return result of removing the card
     * @throws IOException if there is an error removing the card
     */
    private String removeCard(Access access, Storage storage) throws IOException {
        assert access.isChapterLevel() : "Not chapter level";
        try {
            CardList cards = access.getChapter().getCards();
            ArrayList<Card> allCards = cards.getAllCards();
            Card card = allCards.get(removeIndex);
            logger.info("Deleting flashcard: " + card.toString());
            cards.removeCard(removeIndex);
            storage.saveCards(cards, access.getModule().getModuleName(), access.getChapter().getChapterName());
            logger.info("Flashcard successfully deleted.");
            return prepareResult(CARD, card.toString(), cards.getCardCount());
        } catch (IndexOutOfBoundsException e) {
            String result = String.format(MESSAGE_INVALID_INDEX_RANGE, CARD);
            logger.info(result);
            return result;
        }
    }
}
