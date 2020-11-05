package commands;

import access.Access;
import exception.ExclusionFileException;
import exception.InvalidInputException;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;


public class IncludeCommand extends Command {
    public static final String INCLUDE_COMMAND_OPTION_MODULE = "module";
    public static final String INCLUDE_COMMAND_OPTION_CHAPTER = "chapter";
    public static final String INCLUSION_SUCCESS_MESSAGE = "Success! The %s has been included back into your schedule. "
            +  "Check out your updated due dates with the \"due\" command or \"preview\" command now.";
    String type;
    public static final String COMMAND_WORD = "include";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Includes excluded Modules and Chapters back into "
            + "your schedule.\n"
            + "Parameters: " + INCLUDE_COMMAND_OPTION_CHAPTER + " or " + INCLUDE_COMMAND_OPTION_CHAPTER + "\n"
            + "Example: " + COMMAND_WORD + " " + INCLUDE_COMMAND_OPTION_CHAPTER + "\n"
            + "Example: " + COMMAND_WORD + " " + INCLUDE_COMMAND_OPTION_MODULE + "\n";
    public static final String PRINT_FORMAT_MODULE = "Module: %s";
    public static final String PRINT_FORMAT_CHAPTER = "Module: %s; Chapter: %s";

    public IncludeCommand(String type) {
        this.type = type;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws InvalidInputException, ExclusionFileException {
        String result = attemptToInclude(ui, storage);
        ui.showToUser(result);
    }

    private String attemptToInclude(Ui ui, Storage storage) throws ExclusionFileException, InvalidInputException {
        switch (type) {
        case INCLUDE_COMMAND_OPTION_MODULE:
            return removeModuleFromExclusion(storage, ui);
        case INCLUDE_COMMAND_OPTION_CHAPTER:
            return removeChapterFromExclusion(storage, ui);
        default:
            throw new InvalidInputException("The specified type for the include command is invalid.\nThe only valid "
                    + "types are \"" + INCLUDE_COMMAND_OPTION_MODULE + "\" and \"" + INCLUDE_COMMAND_OPTION_CHAPTER
                    + "\"."
                    + MESSAGE_USAGE);
        }
    }

    private String removeChapterFromExclusion(Storage storage, Ui ui) throws ExclusionFileException,
            InvalidInputException {
        String moduleName = ui.getIncludedModuleName(type);
        String chapterName = ui.getIncludedChapterName(moduleName);
        try {
            storage.removeChapterFromExclusionFile(moduleName,chapterName);
            String exclusionTarget = String.format(PRINT_FORMAT_CHAPTER, moduleName, chapterName);
            return String.format(INCLUSION_SUCCESS_MESSAGE, exclusionTarget);
        } catch (FileNotFoundException e) {
            throw new InvalidInputException("Sorry, the Chapter: " + chapterName + " could not be included as it does"
                    + " not exist.");
        }
    }

    private String removeModuleFromExclusion(Storage storage, Ui ui) throws ExclusionFileException,
            InvalidInputException {
        String moduleName = ui.getIncludedModuleName(type);
        try {
            storage.removeModuleFromExclusionFile(moduleName);
            String exclusionTarget = String.format(PRINT_FORMAT_MODULE, moduleName);
            return String.format(INCLUSION_SUCCESS_MESSAGE, exclusionTarget);
        } catch (FileNotFoundException e) {
            throw new InvalidInputException("Sorry, the Module: " + moduleName + "could not be included as it "
                    + "does not exist.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
