package commands;

import access.Access;
import manager.card.Card;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MODULE_PARAMETERS = " MODULE_NAME";
    public static final String CHAPTER_PARAMETERS = " CHAPTER_NAME";
    public static final String CARD_PARAMETERS = " q:QUESTION | a:ANSWER";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a module / chapter / flashcard.\n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "           " + CHAPTER_PARAMETERS + "\n"
            + "           " + CARD_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " CS2113T"
            + "         " + COMMAND_WORD + " Chapter 1\n"
            + "         " + COMMAND_WORD + " q:What is the result of one plus one | a:two\n";

    private final Card card;

    public AddCommand(String question, String answer) {
        this.card = new Card(question, answer);
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        if (access.getChapterLevel().equals("")) {
            System.out.println("Sorry, you currently are in the wrong level, please enter chapter level first.");
            return;
        }
        cards.addCard(card);
        int cardCount = cards.getCardCount();
        ui.showCardAdded(cards.getCard(cardCount - 1), cardCount);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
