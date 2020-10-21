package commands;

import access.Access;
import exception.ExclusionFileException;
import exception.InvalidInputException;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;


public class ExcludeCommand extends Command {
    String type;
    public static final String COMMAND_WORD = "exclude";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Excludes or Includes Modules and Chapters from your "
            + "schedule.\n"
            + "Parameters: more(excludes from schedule)/less(includes into schedule)\n"
            + "Example: " + COMMAND_WORD + " more\n"
            + "Example: " + COMMAND_WORD + " less\n";


    public ExcludeCommand(String type) {
        this.type = type;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws InvalidInputException, ExclusionFileException {
        switch (type) {
        case "more":
            addingToExclusion(ui, storage);
            break;
        case "less":
            removingFromExclusion(ui, storage);
            break;
        default:
            throw new InvalidInputException("The specified type for the exclude command is invalid.\nThe only valid "
                    + "types are \"more\" and \"less\".");
        }
    }

    private void addChapterToExclusion(Storage storage, Ui ui, String type) throws ExclusionFileException,
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

    private void addModuleToExclusion(Storage storage, Ui ui, String type) throws ExclusionFileException,
            InvalidInputException {
        String moduleName = ui.getExcludedModuleName(type);
        try {
            storage.appendModuleToExclusionFile(moduleName);
        } catch (FileNotFoundException e) {
            throw new InvalidInputException("Sorry, the Module: " + moduleName + " could not be excluded as it "
                    + "does not exist.");
        }
    }

    public void addingToExclusion(Ui ui, Storage storage) throws InvalidInputException, ExclusionFileException {
        String type = ui.chooseModuleOrChapterExclusion("more");
        if (type.equals("module")) {
            addModuleToExclusion(storage, ui, type);
        } else {
            addChapterToExclusion(storage, ui, type);
        }
    }

    private void removeChapterFromExclusion(Storage storage, Ui ui, String type) throws ExclusionFileException,
            InvalidInputException {
        String moduleName = ui.getIncludedModuleName(type);
        String chapterName = ui.getIncludedChapterName(moduleName);
        try {
            storage.removeChapterFromExclusionFile(moduleName,chapterName);
        } catch (FileNotFoundException e) {
            throw new InvalidInputException("Sorry, the Chapter: " + chapterName + " could not be included as it does"
                    + " not exist.");
        }
    }

    private void removeModuleFromExclusion(Storage storage, Ui ui, String type) throws ExclusionFileException,
            InvalidInputException {
        String moduleName = ui.getIncludedModuleName(type);
        try {
            storage.removeModuleFromExclusionFile(moduleName);
        } catch (FileNotFoundException e) {
            throw new InvalidInputException("Sorry, the Module: " + moduleName + "could not be included as it "
                    + "does not exist.");
        }
    }

    public void removingFromExclusion(Ui ui, Storage storage) throws ExclusionFileException, InvalidInputException {
        String type = ui.chooseModuleOrChapterExclusion("less");
        if (type.equals("module")) {
            removeModuleFromExclusion(storage, ui, type);
        } else {
            removeChapterFromExclusion(storage, ui, type);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
