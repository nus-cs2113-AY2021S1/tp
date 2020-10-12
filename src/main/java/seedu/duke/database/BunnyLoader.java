package seedu.duke.database;

import seedu.duke.bunny.Bunny;
import seedu.duke.exceptions.SettingObjectWrongFormatException;
import seedu.duke.parsers.Parsers;
import seedu.duke.ui.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.constants.ClickerMessages.ERROR_READING_FILE_ON_LINE_MSG_FORMAT;
import static seedu.duke.constants.FilePaths.DEFAULT_BUNNY_FILE_PATH;
import static seedu.duke.constants.FilePaths.DEFAULT_USER_SETTINGS_FILE_PATH;
import static seedu.duke.constants.Tags.BUNNY_GENRE_TAG;
import static seedu.duke.constants.Tags.BUNNY_IDEA_TAG;
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
            autoCreateNewFile(DEFAULT_BUNNY_FILE_PATH);
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
            try {
                String parsedString;

                // read idea
                String idea = "";
                fileLine = readFileUntilLineContainsString(BUNNY_IDEA_TAG, bunnyFileScanner);
                parsedString = Parsers.parseFileObject(fileLine, BUNNY_IDEA_TAG);
                if (!parsedString.isBlank()) {
                    idea = parsedString.trim();
                }

                // read genre
                String genre = "";
                fileLine = readFileUntilLineContainsString(BUNNY_GENRE_TAG, bunnyFileScanner);
                parsedString = Parsers.parseFileObject(fileLine, BUNNY_GENRE_TAG);
                if (!parsedString.isBlank()) {
                    genre = parsedString.trim();
                }

                // todo: implement characters collection in version 2
                // read characters (names pipe separated, find the character in the character list)
                /*
                String charactersString = "";
                fileLine = readFileUntilLineContainsString(BUNNY_IDEA_TAG, bunnyFileScanner);
                parsedString = Parsers.parseFileObject(fileLine, BUNNY_IDEA_TAG);
                if (!parsedString.isBlank()) {
                    // parse the charactersString into list of names
                    // for each name retrieve the corresponding character (loaded from the character list)
                }
                 */

                // add new bunny to list
                Bunny newBunny = new Bunny(idea.trim(), genre.trim());
                bunniesList.add(newBunny);
                numBunniesLoaded++;

            } catch (SettingObjectWrongFormatException e) {
                //System.out.printf(ERROR_READING_FILE_ON_LINE_MSG_FORMAT, fileLine);
            }
        }

        UI.numBunnyLoaded(numBunnies, numBunniesLoaded);
    }

}
