package seedu.duke.database;

import seedu.duke.exceptions.FilePathInvalidException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

import static seedu.duke.constants.ClickerMessages.COULD_NOT_CREATE_DIRECTORY_MSG;
import static seedu.duke.constants.ClickerMessages.DIRECTORY_CREATED_SUCCESSFULLY_MSG;
import static seedu.duke.constants.ClickerMessages.FILE_ALREADY_EXISTS_MSG;
import static seedu.duke.constants.ClickerMessages.FILE_AUTO_CREATED_MSG;
import static seedu.duke.constants.ClickerMessages.FILE_CREATED_PATH_MSG;
import static seedu.duke.constants.ClickerMessages.FILE_NOT_FOUND_MSG;
import static seedu.duke.constants.ClickerMessages.FILE_PATH_TO_DIRECTORY_INVALID_MSG;
import static seedu.duke.constants.ClickerMessages.IO_ERROR_WHEN_MAKING_FILE_MSG;
import static seedu.duke.constants.ClickerMessages.NEW_FILE_CREATED_MSG_FORMAT;

import static seedu.duke.constants.InputMarkers.INPUT_COMMENT_MARKER;
import static seedu.duke.constants.Logos.NEWLINE;
import static seedu.duke.constants.RegexStrings.BLANK_STRING_REGEX;

public class FileFunctions {
    /**
     * Get a file from file path.
     *
     * @param filePath given file path
     * @return file object retrieved from the given file path
     */
    public static File getFileFromFilePath(String filePath) {
        File fileFound = new File(filePath);
        return fileFound;
    }

    /**
     * check if a file exists.
     *
     * @param file a given file to check
     * @throws FileNotFoundException the file to check was not found
     */
    public static void checkFileExists(java.io.File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Writes a double new line to the file to create one blank line of space.
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

    /**
     * Create a new file at the specified file path.
     *
     * @param filePath specified file path
     * @return absolute path of the new path
     */
    public static String autoCreateNewFile(String filePath) {
        File newFile = new File(filePath);
        System.out.println(FILE_AUTO_CREATED_MSG);

        filePath = pathReplaceIllegalCharacters(filePath);

        try {
            checkFileExists(newFile);
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND_MSG);
        }

        // make the directory
        try {
            String directoryPath;
            String txtFileName;

            // identify placements
            int endOfDirectoryName = filePath.lastIndexOf("/");
            int endOfFileName = filePath.indexOf(".txt");

            // check if placement is correct
            if (endOfDirectoryName == -1 || endOfFileName == -1) {
                throw new FilePathInvalidException();
            } else {
                try {
                    directoryPath = filePath.substring(0, endOfDirectoryName);
                    txtFileName = filePath.substring(endOfDirectoryName + 1, endOfFileName).trim();
                } catch (StringIndexOutOfBoundsException exception) {
                    throw new FilePathInvalidException();
                }
            }

            //Creating a File object
            File file = new File(directoryPath);
            //Creating the directory
            boolean isFileCreated = file.mkdir();
            if(isFileCreated) {
                System.out.println(DIRECTORY_CREATED_SUCCESSFULLY_MSG);
                filePath = directoryPath + "/" + txtFileName + ".txt";
            }else{
                System.out.println(COULD_NOT_CREATE_DIRECTORY_MSG);
            }
        } catch (FilePathInvalidException e) {
            System.out.println(FILE_PATH_TO_DIRECTORY_INVALID_MSG);
        }

        newFile = new File(filePath);

        // make the file
        try {
            if (newFile.createNewFile()) {
                System.out.println(FILE_CREATED_PATH_MSG);
            } else {
                System.out.println(FILE_ALREADY_EXISTS_MSG);
            }
        } catch (IOException e) {
            System.out.println(IO_ERROR_WHEN_MAKING_FILE_MSG);
        }

        System.out.println(NEW_FILE_CREATED_MSG_FORMAT);

        return newFile.getAbsolutePath();
    }


    /**
     * Replace '\' with '/' characters in file paths variables.
     *
     * @param path a file path with illegal characters
     * @return return the path without illegal characters
     */
    public static String pathReplaceIllegalCharacters(String path) {
        return path.replace('\\', '/');
    }


}
