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
    public static final String EXCLUSION_SUCCESS_MESSAGE = "Success! The %s has been excluded from your schedule. "
            + "Check out your updated due dates with the \"due\" command or \"preview\" command now.";
    String type;
    public static final String COMMAND_WORD = "exclude";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Excludes Modules and Chapters from your schedule.\n"
            + "Parameters: " + EXCLUDE_COMMAND_OPTION_CHAPTER + " or " + EXCLUDE_COMMAND_OPTION_MODULE + "\n"
            + "Example: " + COMMAND_WORD + " " + EXCLUDE_COMMAND_OPTION_MODULE + "\n"
            + "Example: " + COMMAND_WORD + " " + EXCLUDE_COMMAND_OPTION_CHAPTER + "\n";
    public static final String PRINT_FORMAT_MODULE = "Module: %s";
    public static final String PRINT_FORMAT_CHAPTER = "Module: %s; Chapter: %s";

    public ExcludeCommand(String type) {
        this.type = type;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws InvalidInputException, ExclusionFileException {
        String result = attemptToExclude(ui, storage);
        ui.showToUser(result);
    }

    private String attemptToExclude(Ui ui, Storage storage) throws ExclusionFileException,
            InvalidInputException {
        switch (type) {
        case EXCLUDE_COMMAND_OPTION_CHAPTER:
            return addChapterToExclusion(storage, ui);
        case EXCLUDE_COMMAND_OPTION_MODULE:
            return addModuleToExclusion(storage, ui);
        default:
            throw new InvalidInputException("The specified type for the exclude command is invalid.\nThe only valid "
                    + "types are \"" + EXCLUDE_COMMAND_OPTION_CHAPTER + "\" and \"" + EXCLUDE_COMMAND_OPTION_MODULE
                    + "\"."
                    + MESSAGE_USAGE);
        }
    }

    private String addChapterToExclusion(Storage storage, Ui ui) throws ExclusionFileException,
            InvalidInputException {
        String moduleName = ui.getExcludedModuleName(type);
        String chapterName = ui.getExcludedChapterName(moduleName);
        try {
            storage.appendChapterToExclusionFile(moduleName,chapterName);
            String exclusionTarget = String.format(PRINT_FORMAT_CHAPTER, moduleName, chapterName);
            return String.format(EXCLUSION_SUCCESS_MESSAGE, exclusionTarget);
        } catch (FileNotFoundException e) {
            throw new InvalidInputException("Sorry, the Chapter: " + chapterName + " could not be excluded as it does"
                    + " not exist.");
        }
    }

    private String addModuleToExclusion(Storage storage, Ui ui) throws ExclusionFileException,
            InvalidInputException {
        String moduleName = ui.getExcludedModuleName(type);
        try {
            storage.appendModuleToExclusionFile(moduleName);
            String exclusionTarget = String.format(PRINT_FORMAT_MODULE, moduleName);
            return String.format(EXCLUSION_SUCCESS_MESSAGE, exclusionTarget);
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
