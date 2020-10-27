package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;
import manager.card.Card;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

import static common.Messages.CARD;
import static common.Messages.MESSAGE_INVALID_INDEX_RANGE;

public class EditCardCommand extends EditCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the flashcard content.\n"
            + "Parameters:" + CARD_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " 3 q:What is the result of one plus one | a:two\n";

    private final int editIndex;
    private String question;
    private String answer;

    public EditCardCommand(int editIndex, String question, String answer) {
        this.editIndex = editIndex;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage)
            throws InvalidInputException, IncorrectAccessLevelException, IOException {
        String result = editCard(access, storage);
        ui.showToUser(result);
    }

    private String editCard(Access access, Storage storage) throws InvalidInputException, IOException {
        assert access.isChapterLevel() : "Not chapter level";
        assert !question.isEmpty() || !answer.isEmpty() : "The content for question and answer are both empty.";
        CardList cards = access.getChapter().getCards();
        try {
            Card card = cards.getCard(editIndex);
            StringBuilder result = new StringBuilder();
            result.append(prepareBeforeEdit(CARD, card.toString()));
            if (!(question.isEmpty())) {
                card.setQuestion(question);
            }
            if (!(answer.isEmpty())) {
                card.setAnswer(answer);
            }
            result.append(prepareAfterEdit(CARD, card.toString()));
            storage.saveCards(cards, access.getModule().getModuleName(), access.getChapter().getChapterName());
            return result.toString();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_INDEX_RANGE, CARD));
        }
    }
}
