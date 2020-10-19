package seedu.duke.storage;

import seedu.duke.exception.AniException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class Storage {

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
}
