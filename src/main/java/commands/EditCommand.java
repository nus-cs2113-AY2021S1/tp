package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;
import manager.card.Card;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit the module name / chapter name / flashcard content. \n"
            + "Parameters: " + COMMAND_WORD + " MODULE_NUMBER MODULE_NAME\n"
            + "            " + COMMAND_WORD + " CHAPTER_NUMBER CHAPTER_NAME\n"
            + "            " + COMMAND_WORD + " FLASHCARD_NUMBER q:QUESTION | a:ANSWER\n"
            + "Example: " + COMMAND_WORD + " 1 CS2113T\n"
            + "         " + COMMAND_WORD + " 2 Chapter 2\n"
            + "         " + COMMAND_WORD + " 3 q:What is the result of one plus one | a:two\n";

    private static final String ADMIN_LEVEL = "admin";
    private static final String MODULE_LEVEL = "module";
    private static final String CHAPTER_LEVEL = "chapter";

    private final int editIndex;
    private final String question;
    private final String answer;
    private final String accessLevel;

    public EditCommand(int editIndex, String question, String answer) {
        this.editIndex = editIndex;
        this.question = question;
        this.answer = answer;
        this.accessLevel = CHAPTER_LEVEL;
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage)
            throws InvalidInputException, IncorrectAccessLevelException {
        if (access.isChapterLevel()) {
            editCard(ui, access);
        } else {
            throw new IncorrectAccessLevelException("Sorry, you are currently at " + access.getLevel()
                    + ", please go to " + accessLevel + " level first.");
        }
    }

    private void editCard(Ui ui, Access access) throws InvalidInputException {
        CardList allCards = access.getChapter().getCards();
        try {
            Card card = allCards.getCard(editIndex);
            ui.showCardUnedited(card);
            if (!(question.isEmpty())) {
                card.setQuestion(question);
            }
            if (!(answer.isEmpty())) {
                card.setAnswer(answer);
            }
            ui.showCardEdited(card);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidInputException("The flashcard number needs to be within the range "
                    + " of the total number of flashcards\n"
                    + MESSAGE_USAGE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
