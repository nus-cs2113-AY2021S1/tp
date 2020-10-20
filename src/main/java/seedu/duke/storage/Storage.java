package seedu.duke.storage;

import seedu.duke.exception.AniException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.logger.AniLogger.getAniLogger;

public abstract class Storage {
    private static final String NEGATIVE_INTEGER_REGEX = "^[-]\\d+$";
    private static final String POSITIVE_INTEGER_REGEX = "^\\d+$";
    private static final Logger LOGGER = getAniLogger(Storage.class.getName());

    public String readFile(String filePath) throws AniException {
        String fileString = "";
        try {
            fileString = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException exception) {
            LOGGER.log(Level.INFO, "File does not exist at: " + filePath);
            throw new AniException("Does not exist.");
        }

        return fileString;
    }

    public void writeFile(String filePath, String fileString) throws AniException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(fileString);
            fileWriter.close();
            LOGGER.log(Level.INFO, "Wrote to file: " + filePath);
        } catch (IOException exception) {
            LOGGER.log(Level.WARNING, "Failed to write to file at: " + filePath);
            throw new AniException("Failed to write to file.");
        }
    }

    public boolean isPositiveInteger(String integerString) {
        return integerString.matches(POSITIVE_INTEGER_REGEX);
    }

    public boolean isPositiveOrNegativeInteger(String integerString) {
        return integerString.matches(POSITIVE_INTEGER_REGEX) || integerString.matches(NEGATIVE_INTEGER_REGEX);
    }
}
