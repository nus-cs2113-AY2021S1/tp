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

    public AddCommand() {
    }

    public AddCommand(String moduleOrChapter, String accessLevel) {
        this.moduleOrChapter = moduleOrChapter;
        this.accessLevel = accessLevel;
    }

    public AddCommand(String question, String answer, String accessLevel) {
        this.card = new Card(question, answer);
        this.accessLevel = accessLevel;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IncorrectAccessLevelException, IOException {
        if (!access.isAdminLevel() && !access.isModuleLevel() && !access.isChapterLevel()) {
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS,
                    access.getLevel(), accessLevel));
        }

        String result = "";

        if (access.isChapterLevel()) {
            result = addCard(access, storage);
        } else if (access.isAdminLevel()) {
            Module module = new Module(moduleOrChapter);
            result = addModule(access, storage, module);
        } else if (access.isModuleLevel()) {
            Chapter chapter = new Chapter(moduleOrChapter, rateChapter(), storage, access);
            result = addChapter(access, storage, chapter);
        } else {
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS,
                    access.getLevel(), accessLevel));
        }

        ui.showToUser(result);
    }

    private String addChapter(Access access, Storage storage, Chapter chapter) throws IOException {
        Module newModule = access.getModule();
        ChapterList chapters = newModule.getChapters();
        chapters.addChapter(chapter);
        int chapterCount = chapters.getChapterCount();
        access.setModule(newModule);
        storage.createChapter(chapter.getChapterName(), access.getModuleLevel());
        return prepareResult(CHAPTER, chapter.toString(), chapterCount);
    }

    private String addCard(Access access, Storage storage) throws IOException {
        assert access.isChapterLevel() : "Not chapter level";
        CardList cards = access.getChapter().getCards();
        cards.addCard(card);
        int cardCount = cards.getCardCount();
        storage.saveCards(cards, access.getModule().getModuleName(), access.getChapter().getChapterName());
        return prepareResult(CARD, card.toString(), cardCount);
    }

    private String addModule(Access access, Storage storage, Module module) {
        Admin newAdmin = access.getAdmin();
        ModuleList modules = newAdmin.getModules();
        modules.addModule(module);
        int moduleCount = modules.getModuleCount();
        access.setAdmin(newAdmin);
        storage.createModule(module.getModuleName());
        return prepareResult(MODULE, module.toString(), moduleCount);
    }

    protected String prepareResult(String type, String content, int count) {
        StringBuilder result = new StringBuilder();
        result.append(String.format(MESSAGE_SUCCESS, type));
        result.append(content).append("\n");
        result.append(String.format(MESSAGE_COUNT, count, type));
        return result.toString();
    }

    public String rateChapter() {
        if (Ui.chooseToRateNewDeck()) {
            return Ui.getChoiceOfNewDeckRating();
        } else {
            return "N";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
