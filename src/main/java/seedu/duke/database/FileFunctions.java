package seedu.duke.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

import static seedu.duke.constants.InputMarkers.INPUT_COMMENT_MARKER;
import static seedu.duke.constants.Logos.NEWLINE;
import static seedu.duke.constants.RegexStrings.BLANK_STRING_REGEX;

public class fileFunctions {
    /**
     * Get a file from file path
     *
     * @param filePath given file path
     * @return file object retrieved from the given file path
     */
    public static File getFileFromFilePath(String filePath) {
        File fileFound = new File(filePath);
        return fileFound;
    }

    /**
     * check if a file exists
     *
     * @param file a given file to check
     * @throws FileNotFoundException the file to check was not found
     */
    public static void checkFileExists(java.io.File file) throws FileNotFoundException {
        if (!file.exists()){
            throw new FileNotFoundException();
        }
    }

    /**
     * Writes a double new line to the file to create one blank line of space
     *
     * @param filePath given file path
     * @throws IOException unable to write to file
     */
    public static void writeDoubleNewlineToFile(String filePath) throws IOException {
        appendsStringToFile(NEWLINE, filePath);
        appendsStringToFile(NEWLINE, filePath);
    }

    /**
     * Appends the string to the given file specified by filePath.
     *
     * @param textToAppend string to be appended to the file
     * @param filePath filepath to the file
     * @throws IOException unable to write to file
     */
    public static void appendsStringToFile(String textToAppend, String filePath) throws IOException {
        java.io.FileWriter fw = new java.io.FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Continue reading through a file until a specific string is found.
     *
     * @param stringSearched indicator string
     * @param fileScanner    scanner for scanning a file
     * @return return the line the string is on
     */
    public static String readFileUntilLineContainsString(String stringSearched, Scanner fileScanner) {
        String fileInput = "";
        // read each setting and return the variables accordingly
        while (fileScanner.hasNext()) {
            fileInput = getFileNextLine(fileScanner);
            if (fileInput.contains(stringSearched)) {
                break;
            }
        }

        return fileInput;
    }

    /**
     * Read non-blank lines of the file.
     *
     * @param fileScanner scanner to read through lines in the file
     * @return return a non blank line read from the file
     */
    public static String getFileNextLine(Scanner fileScanner) {
        String fileInput;
        do {
            fileInput = fileScanner.nextLine();
        } while (fileInput.matches(BLANK_STRING_REGEX)
                || fileInput.startsWith(INPUT_COMMENT_MARKER));
        return fileInput;
    }
}
