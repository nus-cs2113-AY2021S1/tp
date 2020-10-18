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

    public static final String MESSAGE_INVALID_ACCESS = "Sorry, you are currently at %1$s"
            + ", please go to %2$s level first.";

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

    private String addChapter(Access access, Storage storage, Chapter chapter) {
        Module newModule = access.getModule();
        ChapterList chapters = newModule.getChapters();
        chapters.addChapter(chapter);
        int chapterCount = chapters.getChapterCount();
        StringBuilder result = new StringBuilder();
        result.append(String.format(MESSAGE_SUCCESS, "chapter"));
        result.append(chapter.toString() + "\n");
        result.append(String.format(MESSAGE_COUNT, chapterCount, "chapter"));
        access.setModule(newModule);
        storage.createChapter(chapter.getChapterName(), access.getModuleLevel());
        return result.toString();
    }

    private String addCard(Access access, Storage storage) throws IOException {
        assert access.isChapterLevel() : "Not chapter level";
        CardList cards = access.getChapter().getCards();
        cards.addCard(card);
        int cardCount = cards.getCardCount();
        StringBuilder result = new StringBuilder();
        result.append(String.format(MESSAGE_SUCCESS, "card"));
        result.append(cards.getCard(cardCount - 1).toString() + "\n");
        result.append(String.format(MESSAGE_COUNT, cardCount, "card"));
        storage.saveCards(cards, access.getModule().getModuleName(), access.getChapter().getChapterName());
        return result.toString();
    }

    private String addModule(Access access, Storage storage, Module module) {
        Admin newAdmin = access.getAdmin();
        ModuleList modules = newAdmin.getModules();
        modules.addModule(module);
        int moduleCount = modules.getModuleCount();
        StringBuilder result = new StringBuilder();
        result.append(String.format(MESSAGE_SUCCESS, "module"));
        result.append(module.toString() + "\n");
        result.append(String.format(MESSAGE_COUNT, moduleCount , "module"));
        access.setAdmin(newAdmin);
        storage.createModule(module.getModuleName());
        return result.toString();
    }


}
