package seedu.duke.database;

import seedu.duke.wordlist.WordList;
import seedu.duke.words.Adjective;
import seedu.duke.words.Noun;
import seedu.duke.words.Verb;
import seedu.duke.words.Words;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordsLoader {

    private static final ArrayList<Words> wordsList = WordList.getWordList();
    private static final String FILE_PATH = "data/words.txt";
    private static final Logger LOGGER = Logger.getLogger("Words loader");

    public static void loadWordsFile() {
        File directory = new File("data");
        File f = new File(FILE_PATH);

        if (directory.mkdir()) {
            LOGGER.log(Level.INFO, "Directory is being created.");
        }

        try {
            Scanner s = new Scanner(f);
            try {
                readDataFromFile(s);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error reading file due to unexpected format. Please manually check your file.");
                LOGGER.log(Level.WARNING, "Program cannot continue reading data from file.");
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.INFO, "File is created the first time.");
        }
    }

    private static void readDataFromFile(Scanner s) throws IndexOutOfBoundsException {
        while (s.hasNext()) {
            String[] readings = s.nextLine().split("\\|");

            for (int i = 0; i < readings.length; i++) {
                readings[i] = readings[i].trim();
            }

            switch (readings[0]) {
                case "verb":
                    try {
                        wordsList.add(new Verb(readings[1], readings[2]));
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("No arguments about readings[1] or readings[2] is provided");
                    }
                    break;
                case "noun":
                    try {
                        wordsList.add(new Noun(readings[1], readings[2]));
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("No arguments about readings[1] or readings[2] is provided");
                    }
                    break;
                case "adjective":
                    try {
                        wordsList.add(new Adjective(readings[1], readings[2]));
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("No arguments about readings[1] or readings[2] is provided");
                    }
                    break;
                default:
                    throw new IndexOutOfBoundsException();
            }
        }
    }
}
