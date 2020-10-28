package commands;

import access.Access;
import exception.ExclusionFileException;
import exception.IncorrectAccessLevelException;
import exception.InvalidFileFormatException;
import exception.InvalidInputException;
import exception.DuplicateDataException;
import exception.EmptyFileException;

import storage.Storage;
import ui.Ui;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(Ui ui, Access access, Storage storage)
            throws InvalidInputException, IncorrectAccessLevelException,
            IOException, InvalidFileFormatException, ExclusionFileException, DuplicateDataException, EmptyFileException;

    public abstract boolean isExit();
}
