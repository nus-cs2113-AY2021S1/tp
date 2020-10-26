package commands;

import exception.IncorrectAccessLevelException;
import manager.admin.ModuleList;
import manager.card.Card;
import access.Access;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GoCommand extends Command {
    public static final String COMMAND_WORD = "go";

    public static final String CHAPTER_PARAMETERS = " CHAPTER_NAME";
    public static final String MODULE_PARAMETERS = " MODULE_NAME";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes to chapter / module level. \n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "           " + CHAPTER_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " CS2113T\n"
            + "         " + COMMAND_WORD + " Chapter 1\n";

    private String moduleOrChapter;

    public GoCommand(String moduleOrChapter) {
        this.moduleOrChapter = moduleOrChapter;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IncorrectAccessLevelException {
        if (access.isAdminLevel()) {
            String result = goModule(access, storage, ui);
            if (result.equals("")) {
                return;
            }
            ui.showToUser(result);
        } else if (access.isModuleLevel()) {
            String result = goChapter(access, storage);
            if (result.equals("")) {
                return;
            }
            ui.showToUser(result);
        } else {
            throw new IncorrectAccessLevelException("Go command can only be called "
                    + "at admin and module level.");
        }
    }

    private String goChapter(Access access, Storage storage) throws IncorrectAccessLevelException {
        boolean isLevelExist = false;
        String result = "";
        ChapterList chapters = access.getModule().getChapters();
        ArrayList<Chapter> allChapters = chapters.getAllChapters();
        for (Chapter chapter : allChapters) {
            if (moduleOrChapter.equalsIgnoreCase(chapter.getChapterName())) {
                access.setChapterLevel(moduleOrChapter);
                isLevelExist = true;
                try {
                    ArrayList<Card> allCards = storage.loadCard(access.getModuleLevel(), chapter.getChapterName());
                    if (allCards.size() == 0) {
                        result = "This is a new chapter, you can try to add flashcards inside!";
                    }
                    chapter.setCards(allCards);
                    access.setChapter(chapter);
                } catch (FileNotFoundException e) {
                    result = "The chapter file cannot be found.";
                }
                break;
            }
        }
        if (!isLevelExist) {
            result = "Sorry, I cannot find this chapter, please add this chapter first";
        }
        return result;
    }

    private String goModule(Access access, Storage storage, Ui ui) throws IncorrectAccessLevelException {
        boolean isLevelExist = false;
        String result = "";
        ModuleList modules = access.getAdmin().getModules();
        ArrayList<Module> allModules = modules.getAllModules();
        for (Module module : allModules) {
            if (moduleOrChapter.equalsIgnoreCase(module.getModuleName())) {
                access.setModuleLevel(moduleOrChapter);
                isLevelExist = true;
                try {
                    ArrayList<Chapter> chapters = storage.loadChapter(module.getModuleName(), ui);
                    if (chapters.size() == 0) {
                        result = "This is a new module, you can try to add chapters inside!";
                    }
                    module.setChapters(chapters);
                    access.setModule(module);
                } catch (FileNotFoundException e) {
                    result = "The module folder cannot be found.";
                }
                break;
            }
        }
        if (!isLevelExist) {
            result = "Sorry, I cannot find this module, please add this module first";
        }
        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

