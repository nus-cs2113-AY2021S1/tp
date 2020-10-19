package commands;

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
    private String moduleOrChapter;

    public static final String CHAPTER_PARAMETERS = " CHAPTER_NAME";
    public static final String CHAPTER_MESSAGE_USAGE = COMMAND_WORD + ": Goes to chapter level. \n"
            + "Parameters:" + CHAPTER_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " Chapter 1\n";

    public static final String MODULE_PARAMETERS = " MODULE_NAME";
    public static final String MODULE_MESSAGE_USAGE = COMMAND_WORD + ": Goes to module level. \n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " CS2113T\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes to chapter / module level. \n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "           " + CHAPTER_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " CS2113T\n"
            + "         " + COMMAND_WORD + " Chapter 1\n";

    public GoCommand(String moduleOrChapter) {
        this.moduleOrChapter = moduleOrChapter;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        if (access.isAdminLevel()) {
            goModule(access, storage);
        } else if (access.isModuleLevel()) {
            goChapter(access, storage);
        } else {
            System.out.println("Go command can only be called at admin and module level.");
        }
    }

    private void goChapter(Access access, Storage storage) {
        boolean isLevelExist = false;
        ChapterList chapters = access.getModule().getChapters();
        ArrayList<Chapter> allChapters = chapters.getAllChapters();
        for (Chapter chapter : allChapters) {
            if (moduleOrChapter.equalsIgnoreCase(chapter.getChapterName())) {
                access.setChapterLevel(moduleOrChapter);
                isLevelExist = true;
                try {
                    ArrayList<Card> allCards = storage.loadCard(access.getModuleLevel(), chapter.getChapterName());
                    if (allCards.size() == 0) {
                        System.out.println("This is a new chapter, you can try to add flashcards inside!");
                    }
                    chapter.setCards(allCards);
                    access.setChapter(chapter);
                } catch (FileNotFoundException e) {
                    System.out.println("The chapter file cannot be found.");
                }
                break;
            }
        }
        if (!isLevelExist) {
            System.out.println("Sorry, I cannot find this chapter, please add this chapter first");
        }
    }

    private void goModule(Access access, Storage storage) {
        boolean isLevelExist = false;
        ModuleList modules = access.getAdmin().getModules();
        ArrayList<Module> allModules = modules.getAllModules();
        for (Module module : allModules) {
            if (moduleOrChapter.equalsIgnoreCase(module.getModuleName())) {
                access.setModuleLevel(moduleOrChapter);
                isLevelExist = true;
                try {
                    ArrayList<Chapter> chapters = storage.loadChapter(module.getModuleName());
                    if (chapters.size() == 0) {
                        System.out.println("This is a new module, you can try to add chapters inside!");
                    }
                    module.setChapters(chapters);
                    access.setModule(module);
                } catch (FileNotFoundException e) {
                    System.out.println("The module folder cannot be found.");
                }
                break;
            }
        }
        if (!isLevelExist) {
            System.out.println("Sorry, I cannot find this module, please add this module first");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

