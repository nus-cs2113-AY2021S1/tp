package seedu.duke.database;

import seedu.duke.constants.FilePaths;
import seedu.duke.constants.FluffleMessages;
import seedu.duke.wordlist.WordList;
import seedu.duke.words.Adjective;
import seedu.duke.words.Noun;
import seedu.duke.words.Verb;
import seedu.duke.words.Words;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordsLoader {
    public static void loadWordList() {
        try {
            File f = new File(FilePaths.WORDS_FILE_PATH);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String word = sc.nextLine();
                String[] wordDetails = word.split(" > ");
                switch (wordDetails[0]) {
                case "n":
                    loadNoun(wordDetails[1], wordDetails[2]);
                    break;
                case "v":
                    loadVerb(wordDetails[1], wordDetails[2]);
                    break;
                case "a":
                    loadAdj(wordDetails[1], wordDetails[2]);
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(FluffleMessages.FILE_NOT_FOUND_MSG);
        }
    }

    public static void loadNoun(String description, String definition) {
        Words toAdd = new Noun(description, definition);
        WordList.wordList.add(toAdd);
    }

    public static void loadVerb(String description, String definition) {
        Words toAdd = new Verb(description, definition);
        WordList.wordList.add(toAdd);
    }

    public static void loadAdj(String description, String definition) {
        Words toAdd = new Adjective(description, definition);
        WordList.wordList.add(toAdd);
    }

    public static int getWordListSize() {
        return WordList.wordList.size();
    }
}
