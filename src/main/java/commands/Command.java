package commands;

import access.Access;
import exception.*;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(Ui ui, Access access, Storage storage)
            throws InvalidInputException, IncorrectAccessLevelException,
            IOException, InvalidFileFormatException, ExclusionFileException, DuplicateDataException, EmptyFileException;

    public abstract boolean isExit();
}
