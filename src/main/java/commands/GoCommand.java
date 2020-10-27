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

public abstract class GoCommand extends Command {
    public static final String COMMAND_WORD = "go";

    public static final String CHAPTER_PARAMETERS = " CHAPTER_NAME";
    public static final String MODULE_PARAMETERS = " MODULE_NAME";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes to chapter / module level. \n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "           " + CHAPTER_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " CS2113T\n"
            + "         " + COMMAND_WORD + " Chapter 1\n";

    @Override
    public abstract void execute(Ui ui, Access access, Storage storage) throws IncorrectAccessLevelException;

    @Override
    public boolean isExit() {
        return false;
    }
}

