package commands;

import access.Access;
import exception.ExclusionFileException;
import exception.InvalidFileFormatException;
import exception.InvalidInputException;
import exception.StorageDataException;

import storage.Storage;
import ui.Ui;

import java.io.IOException;

/**
 * Represents a command that has the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param ui ui which the command uses to print messages
     * @param access access which the command uses to get the modules, chapters or cards
     * @param storage storage which the command uses to load or write data to storage files
     * @throws InvalidInputException if the user input is not valid
     * @throws IOException if there is an error writing to files
     * @throws InvalidFileFormatException if the format in the storage file is invalid
     * @throws ExclusionFileException if there is an error with the exclusion file
     * @throws StorageDataException if there is an error with any of the storage folders or files
     */
    public abstract void execute(Ui ui, Access access, Storage storage)
            throws InvalidInputException, IOException, InvalidFileFormatException,
            ExclusionFileException, StorageDataException;

    /**
     * Checks to see if the program should terminate.
     *
     * @return true to terminate the program
     */
    public abstract boolean isExit();
}
