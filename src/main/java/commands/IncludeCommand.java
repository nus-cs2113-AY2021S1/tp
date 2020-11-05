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
    String type;
    public static final String COMMAND_WORD = "include";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Includes excluded Modules and Chapters back into "
            + "your schedule.\n"
            + "Parameters: " + INCLUDE_COMMAND_OPTION_CHAPTER + " or " + INCLUDE_COMMAND_OPTION_CHAPTER + "\n"
            + "Example: " + COMMAND_WORD + " " + INCLUDE_COMMAND_OPTION_CHAPTER + "\n"
            + "Example: " + COMMAND_WORD + " " + INCLUDE_COMMAND_OPTION_MODULE + "\n";

    public IncludeCommand(String type) {
        this.type = type;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws InvalidInputException, ExclusionFileException {
        switch (type) {
        case INCLUDE_COMMAND_OPTION_MODULE:
            removeModuleFromExclusion(storage, ui);
            break;
        case INCLUDE_COMMAND_OPTION_CHAPTER:
            removeChapterFromExclusion(storage, ui);
            break;
        default:
            throw new InvalidInputException("The specified type for the include command is invalid.\nThe only valid "
                    + "types are \"" + INCLUDE_COMMAND_OPTION_MODULE + "\" and \"" + INCLUDE_COMMAND_OPTION_CHAPTER
                    + "\"."
                    + MESSAGE_USAGE);
        }
    }

    private void removeChapterFromExclusion(Storage storage, Ui ui) throws ExclusionFileException,
            InvalidInputException {
        String moduleName = ui.getIncludedModuleName(type);
        String chapterName = ui.getIncludedChapterName(moduleName);
        try {
            storage.removeChapterFromExclusionFile(moduleName,chapterName);
            ui.printInclusionSuccess(type, moduleName,chapterName);
        } catch (FileNotFoundException e) {
            throw new InvalidInputException("Sorry, the Chapter: " + chapterName + " could not be included as it does"
                    + " not exist.");
        }
    }

    private void removeModuleFromExclusion(Storage storage, Ui ui) throws ExclusionFileException,
            InvalidInputException {
        String moduleName = ui.getIncludedModuleName(type);
        try {
            storage.removeModuleFromExclusionFile(moduleName);
            ui.printInclusionSuccess(type, moduleName, "");
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
