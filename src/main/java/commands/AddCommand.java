package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import manager.admin.Admin;
import manager.admin.ModuleList;
import manager.card.Card;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

import static common.Messages.CARD;
import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_INVALID_ACCESS;
import static common.Messages.MODULE;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String CHAPTER_PARAMETERS = " CHAPTER_NAME";
    public static final String CHAPTER_MESSAGE_USAGE = COMMAND_WORD + ": Adds a chapter to the module. \n"
            + "Parameters:" + CHAPTER_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " Chapter 1\n";

    public static final String MODULE_PARAMETERS = " MODULE_NAME";
    public static final String MODULE_MESSAGE_USAGE = COMMAND_WORD + ": Adds a new module. \n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " CS2113T\n";

    public static final String CARD_PARAMETERS = " q:QUESTION | a:ANSWER";
    public static final String CARD_MESSAGE_USAGE = COMMAND_WORD + ": Adds a flashcard.\n"
            + "Parameters:" + CARD_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " q:What is the result of one plus one | a:two\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module / chapter / flashcard.\n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "           " + CHAPTER_PARAMETERS + "\n"
            + "           " + CARD_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " CS2113T\n"
            + "         " + COMMAND_WORD + " Chapter 1\n"
            + "         " + COMMAND_WORD + " q:What is the result of one plus one | a:two\n";

    public static final String MESSAGE_SUCCESS = "Got it. I've added this %1$s:\n";
    public static final String MESSAGE_COUNT = "Now you have %1$d %2$s(s) in the list.";

    private String moduleOrChapter;
    private Card card;
    private String accessLevel;

    public AddCommand(String moduleOrChapter) {
        this.moduleOrChapter = moduleOrChapter;
    }

    public AddCommand(String question, String answer, String accessLevel) {
        this.card = new Card(question, answer);
        this.accessLevel = accessLevel;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage)
            throws IncorrectAccessLevelException, IOException {
        if (access.isChapterLevel()) {
            String result = addCard(access, storage);
            ui.showToUser(result);
        } else if (access.isAdminLevel()) {
            Module module = new Module(moduleOrChapter);
            String result = addModule(access, storage, module);
            ui.showToUser(result);
        } else if (access.isModuleLevel()) {
            Chapter chapter = new Chapter(moduleOrChapter, Chapter.rateChapter(), storage, access);
            String result = addChapter(access, storage, chapter);
            ui.showToUser(result);
        } else {
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS
                    , access.getLevel(), accessLevel));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String addChapter(Access access, Storage storage, Chapter chapter) throws IOException {
        Module newModule = access.getModule();
        ChapterList chapters = newModule.getChapters();
        chapters.addChapter(chapter);
        int chapterCount = chapters.getChapterCount();
        access.setModule(newModule);
        StringBuilder result = new StringBuilder();
        result.append(storage.createChapter(chapter.getChapterName(), access.getModuleLevel()));
        result.append(String.format(MESSAGE_SUCCESS, CHAPTER) + chapter.toString() + "\n"
                + String.format(MESSAGE_COUNT, chapterCount, CHAPTER));
        return result.toString();
    }

    private String addCard(Access access, Storage storage) throws IOException {
        assert access.isChapterLevel() : "Not chapter level";
        CardList cards = access.getChapter().getCards();
        cards.addCard(card);
        int cardCount = cards.getCardCount();
        storage.saveCards(cards, access.getModule().getModuleName(), access.getChapter().getChapterName());
        String result = String.format(MESSAGE_SUCCESS, CARD) + cards.getCard(cardCount - 1).toString()
                + "\n" + String.format(MESSAGE_COUNT, cardCount, CARD);
        return result;
    }

    private String addModule(Access access, Storage storage, Module module) {
        Admin newAdmin = access.getAdmin();
        ModuleList modules = newAdmin.getModules();
        modules.addModule(module);
        int moduleCount = modules.getModuleCount();
        access.setAdmin(newAdmin);
        StringBuilder result = new StringBuilder();
        result.append(storage.createModule(module.getModuleName()));
        result.append(String.format(MESSAGE_SUCCESS, MODULE) + module.toString() + "\n" +
                String.format(MESSAGE_COUNT, moduleCount, MODULE));
        return result.toString();
    }


}
