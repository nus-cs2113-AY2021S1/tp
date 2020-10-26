package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import manager.card.Card;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

import static common.Messages.CARD;
import static common.Messages.MESSAGE_INVALID_ACCESS;

public class AddCardCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a flashcard.\n"
            + "Parameters:" + CARD_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " q:What is the result of one plus one | a:two\n";

    private static final String ACCESS_LEVEL = "chapter";

    private Card card;

    public AddCardCommand(String question, String answer) {
        this.card = new Card(question, answer);
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage)
            throws IncorrectAccessLevelException, IOException {
        if (!access.isChapterLevel()) {
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS,
                    access.getLevel(), ACCESS_LEVEL));
        }

        String result = addCard(access, storage);
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

    @Override
    public boolean isExit() {
        return false;
    }
}
