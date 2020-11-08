package seedu.duke.storage;

import seedu.duke.wordlist.WordList;
import seedu.duke.words.Adjective;
import seedu.duke.words.Noun;
import seedu.duke.words.Verb;
import seedu.duke.words.Words;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordsSaver {
    private static final String FILE_PATH = "data/words.txt";
    private static final Logger LOGGER = Logger.getLogger("Words saver");

    public static void saveWordsToFile() {
        ArrayList<Words> words = WordList.getWordList();
        String textToSave = "";

        for (Words w : words) {
            if (w instanceof Noun) {
                textToSave = textToSave.concat("noun | " + w.getDescription() + " | "
                        + w.getDefinition() + System.lineSeparator());
            } else if (w instanceof Verb) {
                textToSave = textToSave.concat("verb | " + w.getDescription() + " | "
                        + w.getDefinition() + System.lineSeparator());
            } else if (w instanceof Adjective) {
                textToSave = textToSave.concat("adjective | " + w.getDescription() + " | "
                        + w.getDefinition() + System.lineSeparator());
            } else {
                LOGGER.log(Level.WARNING, "Invalid class instance was passed to saveWordsToFile method!");
                System.out.println("Word type error.");
            }
        }

        try {
            LOGGER.log(Level.INFO, "Saving words to words.txt");
            writeToFile(textToSave);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Program can not write to file");
        }
    }

    private static void writeToFile(String textToWrite) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, false);
        fw.write(textToWrite);
        fw.close();
    }
}
