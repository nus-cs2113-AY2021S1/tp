package commands;

import access.Access;
import exception.InvalidInputException;
import manager.card.Card;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit the content of a flashcard. \n"
            + "Parameters: edit FLASHCARD_NUMBER q:QUESTION | a:ANSWER\n"
            + "Example: " + COMMAND_WORD + " q:What is the result of one plus one | a:two\n";

    private final int editIndex;
    private String question = "";
    private String answer = "";

    public EditCommand(int editIndex, String question, String answer) {
        this.editIndex = editIndex;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) throws InvalidInputException {
        if (access.getChapterLevel().equals("")) {
            System.out.println("Sorry, you currently are in the wrong level, please enter chapter level first.");
            return;
        }
        try {
            Card card = cards.getCard(editIndex);
            ui.showCardContent(card);
            if (!(question.isEmpty())) {
                card.setQuestion(question);
            }
            if (!(answer.isEmpty())) {
                card.setAnswer(answer);
            }
            ui.showEditedCardContent(card);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidInputException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
