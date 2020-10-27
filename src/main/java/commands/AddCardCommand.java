package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import manager.card.Card;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

import static common.Messages.CARD;
import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_INVALID_ACCESS;

public class AddCardCommand extends AddCommand {
    public static final String ACCESS_LEVEL = CHAPTER;
    public static final String CARD_PARAMETERS = " q:QUESTION | a:ANSWER";
    public static final String CARD_MESSAGE_USAGE = COMMAND_WORD + ": Adds a flashcard.\n"
            + "Parameters:" + CARD_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " q:What is the result of one plus one | a:two\n";

    private Card card;

    public AddCardCommand(String question, String answer) {
        this.card = new Card(question, answer);
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IncorrectAccessLevelException, IOException {
        String result = "";

        if (access.isChapterLevel()) {
            result = addCard(access, storage);
        } else {
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS,
                    access.getLevel(), ACCESS_LEVEL));
        }

        ui.showToUser(result);
    }

    private String addCard(Access access, Storage storage) throws IOException {
        assert access.isChapterLevel() : "Not chapter level";
        CardList cards = access.getChapter().getCards();
        cards.addCard(card);
        int cardCount = cards.getCardCount();
        storage.saveCards(cards, access.getModule().getModuleName(), access.getChapter().getChapterName());
        return prepareResult(CARD, card.toString(), cardCount);
    }
}