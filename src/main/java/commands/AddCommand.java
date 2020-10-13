package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import manager.card.Card;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

import java.io.IOException;


public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MODULE_PARAMETERS = " MODULE_NAME";
    public static final String CHAPTER_PARAMETERS = " CHAPTER_NAME";
    public static final String CARD_PARAMETERS = " q:QUESTION | a:ANSWER";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a module / chapter / flashcard.\n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "           " + CHAPTER_PARAMETERS + "\n"
            + "           " + CARD_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " CS2113T\n"
            + "         " + COMMAND_WORD + " Chapter 1\n"
            + "         " + COMMAND_WORD + " q:What is the result of one plus one | a:two\n";

    private final Card card;
    private final String accessLevel;

    public AddCommand(String question, String answer, String accessLevel) {
        this.card = new Card(question, answer);
        this.accessLevel = accessLevel;
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage)
            throws IncorrectAccessLevelException, IOException {
        if (access.isChapterLevel()) {
            addCard(ui, access, storage);
        } else {
            throw new IncorrectAccessLevelException("Sorry, you are currently at " + access.getLevel()
                    + ", please go to " + accessLevel + " level first.\n");
        }
    }

    private void addCard(Ui ui, Access access, Storage storage) throws IOException {
        CardList cards = access.getChapter().getCards();
        cards.addCard(card);
        int cardCount = cards.getCardCount();
        ui.showCardAdded(cards.getCard(cardCount - 1), cardCount);
        storage.saveCards(cards, access.getModule().getModuleName(), access.getChapter().getChapterName());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
