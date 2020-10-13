package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;
import manager.admin.ModuleList;
import manager.card.Card;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MODULE_PARAMETERS = " MODULE_NUMBER MODULE_NAME";
    public static final String CHAPTER_PARAMETERS = " CHAPTER_NUMBER CHAPTER_NAME";
    public static final String CARD_PARAMETERS = " FLASHCARD_NUMBER q:QUESTION | a:ANSWER";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit the module name / chapter name / flashcard content.\n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "           " + CHAPTER_PARAMETERS + "\n"
            + "           " + CARD_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " 1 CS2113T\n"
            + "         " + COMMAND_WORD + " 2 Chapter 2\n"
            + "         " + COMMAND_WORD + " 3 q:What is the result of one plus one | a:two\n";

    private static final String MODULE = "module";
    private static final String CHAPTER = "chapter";
    private static final String CARD = "card";

    private final int editIndex;
    private String moduleOrChapter;
    private String question;
    private String answer;
    private final String accessLevel;

    public EditCommand(int editIndex, String moduleOrChapter, String accessLevel) {
        this.editIndex = editIndex;
        this.moduleOrChapter = moduleOrChapter;
        this.accessLevel = accessLevel;
    }

    public EditCommand(int editIndex, String question, String answer, String accessLevel) {
        this.editIndex = editIndex;
        this.question = question;
        this.answer = answer;
        this.accessLevel = accessLevel;
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage)
            throws InvalidInputException, IncorrectAccessLevelException, IOException {
        if (access.isChapterLevel()) {
            editCard(ui, access, storage);
        } else if (access.isAdminLevel()) {
            editModule(ui, access, storage);
        } else if (access.isModuleLevel()) {
            editChapter(ui, access, storage);
        } else {
            throw new IncorrectAccessLevelException("Sorry, you are currently at " + access.getLevel()
                    + ", please go to " + accessLevel + " level first.\n");
        }
    }

    private void editCard(Ui ui, Access access, Storage storage) throws InvalidInputException, IOException {
        CardList cards = access.getChapter().getCards();
        try {
            Card card = cards.getCard(editIndex);
            ui.showUnedited(CARD, card.toString());
            if (!(question.isEmpty())) {
                card.setQuestion(question);
            }
            if (!(answer.isEmpty())) {
                card.setAnswer(answer);
            }
            ui.showEdited(CARD, card.toString());
            storage.saveCards(cards, access.getModule().getModuleName(), access.getChapter().getChapterName());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidInputException("The flashcard number needs to be within the range "
                    + "of the total number of flashcards\n");
        }
    }

    private void editModule(Ui ui, Access access, Storage storage) throws InvalidInputException {
        ModuleList modules = access.getAdmin().getModules();
        try {
            Module module = modules.getModule(editIndex);
            ui.showUnedited(MODULE, module.toString());
            module.setModuleName(moduleOrChapter);
            ui.showEdited(MODULE, module.toString());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidInputException("The module number needs to be within the range "
                    + "of the total number of modules\n");
        }
    }

    private void editChapter(Ui ui, Access access, Storage storage) throws InvalidInputException {
        ChapterList chapters = access.getModule().getChapters();
        try {
            Chapter chapter = chapters.getChapter(editIndex);
            ui.showUnedited(CHAPTER, chapter.toString());
            chapter.setChapterName(moduleOrChapter);
            ui.showEdited(CHAPTER, chapter.toString());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidInputException("The chapter number needs to be within the range "
                    + "of the total number of chapters\n");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
