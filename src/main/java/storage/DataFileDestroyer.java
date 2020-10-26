package storage;

import ui.Printer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataFileDestroyer extends DataFile {
    public DataFileDestroyer(Printer printer) {
        this.printer = printer;
    }

    /**
     * Constructor that executes the operation to delete all cheatsheet files.
     */
    @Override
    public void executeFunction() {
        try {
            clearDirectory();
        } catch (IOException e) {
            printer.print(e.getMessage());
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
            printer.print(e.getMessage());
        }
    }

    /**
     * Deletes a specified cheatsheet file from the /data directory.
     *
     * @param unwantedFile Name of the cheatsheet to be deleted.
     * @throws IOException Thrown if the path of the unwanted file specified
     *                     is not existent.
     */
    protected void deleteFile(String unwantedFile) throws IOException {
        Path unwantedFilePath = Paths.get(USER_DIR, DATA, unwantedFile + XML_EXTENSION);
        Files.delete(unwantedFilePath);
    }

    /**
     * Deletes all cheatsheet files from the /data directory.
     *
     * @throws IOException Thrown if the /data directory is missing or empty.
     */
    protected void clearDirectory() throws IOException {
        String[] dataDirectoryFiles = DATA_DIR.toFile().list();
        if (dataDirectoryFiles == null) {
            throw new IOException();
        }
        for (String dataDirectoryFile : dataDirectoryFiles) {
            Path filePath = Paths.get(USER_DIR, DATA, dataDirectoryFile);
            if (Files.isDirectory(filePath)) {
                continue;
            }
            deleteFile(dataDirectoryFile.replace(XML_EXTENSION, EMPTY));
        }
    }

}
