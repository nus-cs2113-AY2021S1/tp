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
    public static final String CARD_MESSAGE_USAGE = COMMAND_WORD + ": Add a flashcard.\n"
            + "Parameters:" + CARD_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " q:What is the result of one plus one | a:two\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a module / chapter / flashcard.\n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "           " + CHAPTER_PARAMETERS + "\n"
            + "           " + CARD_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " CS2113T\n"
            + "         " + COMMAND_WORD + " Chapter 1\n"
            + "         " + COMMAND_WORD + " q:What is the result of one plus one | a:two\n";

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
    public void execute(CardList cards, Ui ui, Access access, Storage storage)
            throws IncorrectAccessLevelException, IOException {
        if (access.isChapterLevel()) {
            addCard(ui, access, storage);
        } else if (access.isAdminLevel()) {
            Module module = new Module(moduleOrChapter);
            addModule(ui, access, storage, module);
        } else if (access.isModuleLevel()) {
            Chapter chapter = new Chapter(moduleOrChapter, Chapter.rateChapter());
            addChapter(ui, access, storage, chapter);
        } else {
            throw new IncorrectAccessLevelException("Sorry, you are currently at " + access.getLevel()
                    + ", please go to " + accessLevel + " level first.\n");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private void addChapter(Ui ui, Access access, Storage storage, Chapter chapter) {
        Module newModule = access.getModule();
        ChapterList chapters = newModule.getChapters();
        chapters.addChapter(chapter);
        int chapterCount = chapters.getChapterCount();
        ui.showChapterAdded(chapter, chapterCount);
        access.setModule(newModule);
        storage.createChapter(chapter.getChapterName(), access.getModuleLevel());
    }

    private void addCard(Ui ui, Access access, Storage storage) throws IOException {
        CardList cards = access.getChapter().getCards();
        cards.addCard(card);
        int cardCount = cards.getCardCount();
        ui.showCardAdded(cards.getCard(cardCount - 1), cardCount);
        storage.saveCards(cards, access.getModule().getModuleName(), access.getChapter().getChapterName());
    }

    private void addModule(Ui ui, Access access, Storage storage, Module module) {
        Admin newAdmin = access.getAdmin();
        ModuleList modules = newAdmin.getModules();
        modules.addModule(module);
        int moduleCount = modules.getModuleCount();
        ui.showModuleAdded(module, moduleCount);
        access.setAdmin(newAdmin);
        storage.createModule(module.getModuleName());
    }


}
