package seedu.duke.database;

import seedu.duke.constants.FilePaths;
import seedu.duke.constants.FluffleMessages;
import seedu.duke.wordlist.WordList;

import java.io.FileWriter;
import java.io.IOException;

public class WordsSaver {
    public static void saveWordList() {
        try {
            FileWriter fw = new FileWriter(FilePaths.WORDS_FILE_PATH);
            // Write each word into each line in the file
            for (int i = 0; i < WordList.wordList.size(); i++) {
                fw.write(WordList.wordList.get(i).toString());
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(FluffleMessages.FILE_SAVE_ERROR_MSG);
        }
    }
}
