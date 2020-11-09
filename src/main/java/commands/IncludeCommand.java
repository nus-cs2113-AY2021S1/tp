package commands;

import access.Access;
import exception.ExclusionFileException;
import exception.InvalidInputException;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;


/**
 * Removes Chapters from the Exclusion List stored in the Exclusion File so that these Chapters will once again be
 * scheduled by KAJI.
 */
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

    /**
     * Constructor for the IncludeCommand class. Assigns the command argument that the "include" command was called with
     * as the type of the command.
     * @param type Command argument that the command was called with.
     */
    public IncludeCommand(String type) {
        this.type = type;
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
                    + "\".\n"
                    + MESSAGE_USAGE);
        }
    }

    private String removeChapterFromExclusion(Storage storage, Ui ui) throws ExclusionFileException,
            InvalidInputException {
        String moduleName = ui.getIncludedModuleName(type);
        String chapterName = ui.getIncludedChapterName(moduleName);
        try {
            boolean result = storage.removeChapterFromExclusionFile(moduleName,chapterName);
            if (!result) {
                throw new InvalidInputException("Sorry, the Chapter: " + chapterName + " could not "
                        + "be included as it does not exist.");
            }
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
            throw new InvalidInputException("Sorry, the Module: " + moduleName + " could not be included as it "
                    + "does not exist.");
        }
    }

    /**
     * Executes the "include" command.
     * @param ui ui which the command uses to print messages
     * @param access access which the command uses to get the modules, chapters or cards
     * @param storage storage which the command uses to load or write data to storage files
     * @throws InvalidInputException if the user specifies a command argument other than chapter and module, or if an
     *     non-existent target Module or ChapterName is given.
     * @throws ExclusionFileException if there are errors with the Exclusion File
     */
    @Override
    public void execute(Ui ui, Access access, Storage storage) throws InvalidInputException, ExclusionFileException {
        String result = attemptToInclude(ui, storage);
        ui.showToUser(result);
    }

    /**
     * Used to determine if this Command is the "exit" command.
     * @return false as this is not the "exit" command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
