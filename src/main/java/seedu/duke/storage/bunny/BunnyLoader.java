package seedu.duke.storage.bunny;

import seedu.duke.bunny.Bunny;
import seedu.duke.exceptions.SettingObjectWrongFormatException;
import seedu.duke.common.Parsers;
import seedu.duke.storage.FileFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static seedu.duke.constants.FilePaths.DEFAULT_BUNNY_FILE_PATH;
import static seedu.duke.constants.Tags.BUNNY_GENRE_TAG;
import static seedu.duke.constants.Tags.BUNNY_IDEA_TAG;
import static seedu.duke.constants.Tags.NUM_BUNNY_TAG;
import static seedu.duke.storage.FileFunctions.autoCreateNewFile;
import static seedu.duke.storage.FileFunctions.readFileUntilLineContainsString;
import static seedu.duke.common.Parsers.getIntFromString;

public class BunnyLoader {
    private static Logger logger = Logger.getLogger("BunnyLoader");

    public static void loadBunnyFile(ArrayList<Bunny> bunniesList) {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logger.addHandler(ch);

        logger.log(Level.FINEST, "Start loading bunny.txt");
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

        logger.log(Level.FINER, "read in expected number of bunnies");
        try {
            String parsedString;

            fileLine = readFileUntilLineContainsString(NUM_BUNNY_TAG, bunnyFileScanner);
            parsedString = Parsers.parseFileObject(fileLine, NUM_BUNNY_TAG);
            if (!parsedString.isBlank()) {
                numBunnies = getIntFromString(parsedString.trim());
            }
        } catch (SettingObjectWrongFormatException e) {
            logger.log(Level.INFO, "bunny.txt format error detected");
        }

        // load individual bunnies
        while (bunnyFileScanner.hasNext()) {
            try {
                String parsedString;

                logger.log(Level.FINE, "read idea");
                String idea = "";
                fileLine = readFileUntilLineContainsString(BUNNY_IDEA_TAG, bunnyFileScanner);
                parsedString = Parsers.parseFileObject(fileLine, BUNNY_IDEA_TAG);
                if (!parsedString.isBlank()) {
                    idea = parsedString.trim();
                }

                logger.log(Level.FINE, "read genre");
                String genre = "";
                fileLine = readFileUntilLineContainsString(BUNNY_GENRE_TAG, bunnyFileScanner);
                parsedString = Parsers.parseFileObject(fileLine, BUNNY_GENRE_TAG);
                if (!parsedString.isBlank()) {
                    genre = parsedString.trim();
                }

                // add new bunny to list
                Bunny newBunny = new Bunny(idea.trim(), genre.trim());
                bunniesList.add(newBunny);
                numBunniesLoaded++;

            } catch (SettingObjectWrongFormatException e) {
                logger.log(Level.INFO, "bunny.txt format error detected while reading");
            }
        }

    }

}
