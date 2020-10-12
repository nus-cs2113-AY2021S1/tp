package seedu.duke.database;

import seedu.duke.bunny.Bunny;
import seedu.duke.exceptions.SettingObjectWrongFormatException;
import seedu.duke.parsers.Parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.constants.ClickerMessages.ERROR_READING_FILE_ON_LINE_MSG_FORMAT;
import static seedu.duke.constants.FilePaths.DEFAULT_BUNNY_FILE_PATH;
import static seedu.duke.constants.FilePaths.DEFAULT_USER_SETTINGS_FILE_PATH;
import static seedu.duke.constants.Tags.NUM_BUNNY_TAG;
import static seedu.duke.database.FileFunctions.autoCreateNewFile;
import static seedu.duke.database.FileFunctions.readFileUntilLineContainsString;
import static seedu.duke.parsers.Parsers.getIntFromString;

public class BunnyLoader {
    public static void loadBunnyFile(ArrayList<Bunny> bunniesList) {

        try {
            File bunnyFile = FileFunctions.getFileFromFilePath(DEFAULT_BUNNY_FILE_PATH);
            FileFunctions.checkFileExists(bunnyFile);
            readBunnyFile(bunnyFile, bunniesList);
        } catch (FileNotFoundException e) {
            //System.out.println(SETTINGS_FILE_NOT_FOUND_MSG);
            autoCreateNewFile(DEFAULT_USER_SETTINGS_FILE_PATH);
            //autoCreateNewFile(TEST_FILE_PATH);
        }
    }

    private static void readBunnyFile(File bunnyFile, ArrayList<Bunny> bunniesList) throws FileNotFoundException {
        Scanner bunnyFileScanner = new Scanner(bunnyFile);
        String fileLine = "";
        int numBunnies = 0;
        int numBunniesLoaded = 0;

        // read in expected number of bunnies
        try {
            String parsedString;

            fileLine = readFileUntilLineContainsString(NUM_BUNNY_TAG, bunnyFileScanner);
            parsedString = Parsers.parseFileObject(fileLine, NUM_BUNNY_TAG);
            if (!parsedString.isBlank()) {
                numBunnies = getIntFromString(parsedString.trim());
            }
        } catch (SettingObjectWrongFormatException e) {
            System.out.printf(ERROR_READING_FILE_ON_LINE_MSG_FORMAT, fileLine);
        }

        // load bunnies
        while (bunnyFileScanner.hasNext()) {
            boolean hasGenre = false;
            boolean hasCharacters = false;

            try {
                String parsedString;

                fileLine = readFileUntilLineContainsString(NUM_BUNNY_TAG, bunnyFileScanner);
                parsedString = Parsers.parseFileObject(fileLine, NUM_BUNNY_TAG);
                if (!parsedString.isBlank()) {
                    numBunnies = getIntFromString(parsedString.trim());
                }
            } catch (SettingObjectWrongFormatException e) {
                System.out.printf(ERROR_READING_FILE_ON_LINE_MSG_FORMAT, fileLine);
            }

            // read idea
            // read genre
            // read characters (pipe separated)
            // add new bunny to list
        }
    }

}
