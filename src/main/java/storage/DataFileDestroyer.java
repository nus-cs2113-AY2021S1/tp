package storage;

import cheatsheet.CheatSheetList;
import exception.CommandException;
import exception.DirectoryIsEmptyException;
import ui.Printer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to delete stored files as well as empty directories
 * when required.
 */
public class DataFileDestroyer extends DataFile {


    private final Logger destroyLogger = Logger.getLogger("FileDestroyer");

    public DataFileDestroyer(Printer printer, CheatSheetList cheatSheetList) {
        this.printer = printer;
        this.cheatSheetList = cheatSheetList;
    }

    /**
     * Constructor that executes the operation to delete all cheatsheet files.
     */
    @Override
    public void executeFunction() {
        try {
            clearDataDirectory();
        } catch (DirectoryIsEmptyException e) {
            destroyLogger.log(Level.WARNING, "Empty Directory Present");
            printer.print("The following directory is empty: "
                    + System.lineSeparator()
                    + e.getMessage());
        } finally {
            updateDirectory(DATA_DIR.toFile());
        }
    }

    /**
     * Constructor that executes the operation to delete a
     * specific cheatsheet file.
     *
     * @param unwantedFile Name of the cheatsheet to be deleted.
     */
    public void executeFunction(String unwantedFile) {
        try {
            deleteFile(unwantedFile);
        } catch (IOException e) {
            destroyLogger.log(Level.WARNING, "IO File Error");
            printer.print("CheatLogs could not clear a particular file! Is this file deleted?"
                    + System.lineSeparator()
                    + "Here is the location of the file that had issues: "
                    + System.lineSeparator()
                    + e.getMessage());
        } catch (CommandException s) {
            destroyLogger.log(Level.WARNING, "Nonexistent File Error");
            printer.print("This file does not exist:"
                    + unwantedFile
                    + System.lineSeparator()
                    + s.getMessage());
        } finally {
            updateDirectory(DATA_DIR.toFile());
        }
    }

    /**
     * Deletes a specified cheatsheet file from the /data directory.
     *
     * @param unwantedFile      Name of the cheatsheet to be deleted.
     * @throws IOException      Thrown if the path of the unwanted file specified
     *                          is not existent.
     * @throws CommandException Thrown if unwanted file name is not existent.
     */
    private void deleteFile(String unwantedFile) throws IOException, CommandException {
        Path unwantedFilePath;

        String subjectDirectory = cheatSheetList
                .get(unwantedFile)
                .getSubject();
        Path possiblePreloadedPath = Paths.get(USER_DIR,
                DATA,
                PRELOADED,
                subjectDirectory,
                unwantedFile + XML_EXTENSION);

        if (preloadedCheatSheets.contains(possiblePreloadedPath)) {
            unwantedFilePath = possiblePreloadedPath;
            preloadedCheatSheets.remove(possiblePreloadedPath);
        } else {
            unwantedFilePath = Paths.get(USER_DIR,
                    DATA,
                    subjectDirectory,
                    unwantedFile + XML_EXTENSION);
        }
        Files.delete(unwantedFilePath);
    }

    /**
     * Deletes all cheatsheet files from the /data directory in a recursive manner.
     *
     * @throws DirectoryIsEmptyException Thrown if the /data directory is missing or empty.
     */
    private void clearDataDirectory() throws DirectoryIsEmptyException {
        clearDirectory(DATA_DIR);
        
    }

    /**
     * Deletes all cheatsheet files from the given directory.
     *
     * @throws DirectoryIsEmptyException Thrown if the /data directory is missing or empty.
     */
    private void clearDirectory(Path directoryPath) throws DirectoryIsEmptyException {
        String[] dataDirectoryFiles = directoryPath.toFile().list();
        if (dataDirectoryFiles == null) {
            throw new DirectoryIsEmptyException(directoryPath.toString());
        }

        for (String dataDirectoryFile : dataDirectoryFiles) {
            Path filePath = Paths.get(directoryPath.toString(), dataDirectoryFile);

            if (Files.isDirectory(filePath)) {
                clearDirectory(filePath);
                continue;
            }

            try {
                if (!dataDirectoryFile.equals("settings.txt")) {
                    deleteFile(dataDirectoryFile.replace(XML_EXTENSION, EMPTY));
                }
            } catch (IOException e) {
                destroyLogger.log(Level.WARNING, "IO Directory Error");
                printer.print("CheatLogs could not clear a particular file!"
                        + "Here is the location of the file that had issues: "
                        + System.lineSeparator()
                        + e.getMessage());

            } catch (CommandException s) {
                destroyLogger.log(Level.WARNING, "Nonexistent File Error");
                printer.print("This file does not exist: "
                        + dataDirectoryFile
                        .replace(XML_EXTENSION, EMPTY)
                        + System.lineSeparator()
                        + s.getMessage());
            }
        }
        directoryPath.toFile().delete();
    }

}
