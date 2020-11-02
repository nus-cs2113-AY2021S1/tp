package commands;

import access.Access;
import exception.ExclusionFileException;
import exception.InvalidInputException;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;


public class ExcludeCommand extends Command {
    public static final String EXCLUDE_COMMAND_OPTION_MODULE = "module";
    public static final String EXCLUDE_COMMAND_OPTION_CHAPTER = "chapter";
    String type;
    public static final String COMMAND_WORD = "exclude";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Excludes Modules and Chapters from your schedule.\n"
            + "Parameters: " + EXCLUDE_COMMAND_OPTION_CHAPTER + " or " + EXCLUDE_COMMAND_OPTION_MODULE + "\n"
            + "Example: " + COMMAND_WORD + " " + EXCLUDE_COMMAND_OPTION_MODULE + "\n"
            + "Example: " + COMMAND_WORD + " " + EXCLUDE_COMMAND_OPTION_CHAPTER + "\n";

    public ExcludeCommand(String type) {
        this.type = type;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws InvalidInputException, ExclusionFileException {
        switch (type) {
        case EXCLUDE_COMMAND_OPTION_CHAPTER:
            addChapterToExclusion(storage, ui);
            break;
        case EXCLUDE_COMMAND_OPTION_MODULE:
            addModuleToExclusion(storage, ui);
            break;
        default:
            throw new InvalidInputException("The specified type for the exclude command is invalid.\nThe only valid "
                    + "types are \"" + EXCLUDE_COMMAND_OPTION_CHAPTER + "\" and \"" + EXCLUDE_COMMAND_OPTION_MODULE
                    + "\"."
                    + MESSAGE_USAGE);
        }
    }

    private void addChapterToExclusion(Storage storage, Ui ui) throws ExclusionFileException,
            InvalidInputException {
        String moduleName = ui.getExcludedModuleName(type);
        String chapterName = ui.getExcludedChapterName(moduleName);
        try {
            storage.appendChapterToExclusionFile(moduleName,chapterName);
        } catch (FileNotFoundException e) {
            throw new InvalidInputException("Sorry, the Chapter: " + chapterName + " could not be excluded as it does"
                    + " not exist.");
        }
    }

    private void addModuleToExclusion(Storage storage, Ui ui) throws ExclusionFileException,
            InvalidInputException {
        String moduleName = ui.getExcludedModuleName(type);
        try {
            storage.appendModuleToExclusionFile(moduleName);
        } catch (FileNotFoundException e) {
            throw new InvalidInputException("Sorry, the Module: " + moduleName + " could not be excluded as it "
                    + "does not exist.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
