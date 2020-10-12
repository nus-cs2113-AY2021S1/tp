package commands.add;

import access.Access;
import commands.Command;
import manager.card.Card;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a flashcard to the schedule manager. \n"
            + "Parameters: q:QUESTION | a:ANSWER\n"
            + "Example: " + COMMAND_WORD + " q:What is the result of one plus one | a:two\n";

    public static final String QUESTION_ANSWER_PREFIX = " \\| ";
    public static final String QUESTION_PREFIX = "q:";
    public static final String ANSWER_PREFIX = "a:";

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
