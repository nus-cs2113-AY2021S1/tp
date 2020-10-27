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

public class RemoveCardCommand extends RemoveCommand {
    private static Logger logger = KajiLog.getLogger(RemoveCardCommand.class.getName());

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes chapter based on the index in the list. \n"
            + "Parameters: CHAPTER_INDEX\n" + "Example: " + COMMAND_WORD + " 2\n";

    public static final String MESSAGE_INVALID_INDEX_FLASHCARD = "The flashcard is not found, please try again.";

    private final int removeIndex;

    public RemoveCardCommand(int removeIndex) {
        this.removeIndex = removeIndex;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IOException {
        String result = removeCard(access, storage);
        ui.showToUser(result);
    }

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
            logger.info(MESSAGE_INVALID_INDEX_FLASHCARD);
            return MESSAGE_INVALID_INDEX_FLASHCARD;
        }
    }
}
