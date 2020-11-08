package commands;

import access.Access;
import common.KajiLog;
import manager.card.Card;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

import java.io.IOException;
import java.util.logging.Logger;

import static common.Messages.CARD;
import static common.Messages.MESSAGE_ITEM_EXISTED;

//@@author Jane-Ng
/**
 * Adds a flashcard to a chapter.
 */
public class AddCardCommand extends AddCommand {
    private static Logger logger = KajiLog.getLogger(AddCardCommand.class.getName());

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a flashcard.\n"
            + "Parameters:" + CARD_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " q:What is the result of one plus one | a:two\n";

    private Card card;

    /**
     * Creates an AddCardCommand to add the specified {@code question} and {@code answer}.
     *
     * @param question question to be added to a flashcard
     * @param answer answer to be added to a flashcard
     */
    public AddCardCommand(String question, String answer) {
        this.card = new Card(question, answer);
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IOException {
        String result = addCard(access, storage);
        ui.showToUser(result);
    }

    /**
     * Adds a flashcard.
     *
     * @param access to get the list of cards
     * @param storage to write the flashcard content to the storage file
     * @return result to be displayed
     * @throws IOException if there is an error writing to the storage file
     */
    private String addCard(Access access, Storage storage) throws IOException {
        assert access.isChapterLevel() : "Not chapter level";
        CardList cards = access.getChapter().getCards();
        if (cards.checkCardExistence(card.getQuestion().toLowerCase(), card.getAnswer().toLowerCase())) {
            return String.format(MESSAGE_ITEM_EXISTED, CARD,
                    "[Q] " + card.getQuestion() + " [A] " + card.getAnswer(), CARD);
        }

        logger.info("Adding flashcard: " + card);
        cards.addCard(card);
        int cardCount = cards.getCardCount();
        storage.saveCards(cards, access.getModule().getModuleName(), access.getChapter().getChapterName());
        logger.info("Successfully saved the flashcards.");
        return prepareResult(CARD, card.toString(), cardCount);
    }

}