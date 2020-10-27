package seedu.duke.spellchecker;

import seedu.duke.database.DictionaryLoader;

import java.util.ArrayList;

public class Dictionary {

    private ArrayList<String> wordList = new ArrayList<>();

    //Get the list of words
    public ArrayList<String> getWordList() {
        return wordList;
    }

    //Set the list of words
    public void setWordList(ArrayList<String> wordList) {
        this.wordList = wordList;
    }

    //create an object of Dictionary
    private static Dictionary instance = new Dictionary();

    //private constructor so that this class cannot be instantiated
    private Dictionary() {
        this.wordList = DictionaryLoader.getWordList();
    }

    //Get the only object available
    public static Dictionary getInstance() {
        return instance;
    }
}
