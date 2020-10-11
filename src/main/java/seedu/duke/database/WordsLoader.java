package seedu.duke.database;

import seedu.duke.words.Words;

import java.util.ArrayList;

public class WordsLoader {

    private static final ArrayList<Words> wordsList = new ArrayList<>();

    public static ArrayList<Words> getWordsList() {
        return wordsList;
    }

    public static int getWordsListSize() {
        return wordsList.size();
    }
}
