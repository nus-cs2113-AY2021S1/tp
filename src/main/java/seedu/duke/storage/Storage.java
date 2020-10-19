package seedu.duke.storage;

import seedu.duke.exception.AniException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class Storage {
    private static final String NEGATIVE_INTEGER_REGEX = "^[-]\\d+$";
    private static final String POSITIVE_INTEGER_REGEX = "^\\d+$";

    public String readFile(String filePath) throws AniException {
        StringBuilder sbFileString = new StringBuilder();
        try {
            File fileToRead = new File(filePath);
            Scanner fileReader = new Scanner(fileToRead);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                sbFileString.append(line);
                sbFileString.append(System.lineSeparator());
            }
        } catch (FileNotFoundException exception) {
            throw new AniException("Does not exist.");
        }

        return sbFileString.toString();
    }

    public void writeFile(String filePath, String fileString) throws AniException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(fileString);
            fileWriter.close();
        } catch (IOException exception) {
            throw new AniException("Failed to write to the file.");
        }
    }

    public boolean isPositiveInteger(String integerString) {
        return integerString.matches(POSITIVE_INTEGER_REGEX);
    }

    public boolean isPositiveOrNegativeInteger(String integerString) {
        return integerString.matches(POSITIVE_INTEGER_REGEX) || integerString.matches(NEGATIVE_INTEGER_REGEX);
    }
}
