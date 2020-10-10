package commands;

import access.Access;
import manager.card.Card;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes flashcard based on a specified index in the list. \n"
            + "Parameters: INDEX\n" + "Example: " + COMMAND_WORD + " 2\n";

    public static final String MESSAGE_SUCCESS = "The following flashcard has been removed:\n";
    public static final String MESSAGE_INVALID_INDEX = "The flashcard is not found, please try again.";

    private final int removeIndex;

    public RemoveCommand(int removeIndex) {
        this.removeIndex = removeIndex;
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        try {
            Card card = cards.getCard(removeIndex);
            ui.showToUser(MESSAGE_SUCCESS + card.toString());
            cards.removeCard(removeIndex);
        } catch (IndexOutOfBoundsException e) {
            ui.showToUser(MESSAGE_INVALID_INDEX);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
