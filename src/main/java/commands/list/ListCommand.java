package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import manager.card.Card;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a list of modules / chapters / flashcards available. \n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) throws IncorrectAccessLevelException {
        if (access.isChapterLevel()) {
            listCards(ui, access);
        } else {
            throw new IncorrectAccessLevelException("List command can only be called at admin, "
                    + "module and chapter level.\n");
        }
    }

    private void listCards(Ui ui, Access access) {
        CardList cards = access.getChapter().getCards();
        ArrayList<Card> allCards = cards.getAllCards();
        int cardCount = cards.getCardCount();
        ui.showCardList(allCards, cardCount);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
