package seedu.duke.filters;

/*
Sample filter commands:
- filter [-continue] by\type  -verb -noun -adjective
- filter [-continue] by\start -a
- filter [-continue] by\include -string -string
 */

import seedu.duke.database.WordsLoader;
import seedu.duke.wordlist.WordList;
import seedu.duke.words.Words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WordsFilter {

    public static ArrayList<Words> filteredWords = new ArrayList<>();

    public static void filterByType (boolean isNewFilter, String[] types) {
        if (isNewFilter) {
            int typesCount = types.length;
            ArrayList<Words> words = WordList.wordList;
            filteredWords.clear();
            for (int i = 0; i < typesCount; i++) {
                String type = types[i].toLowerCase();
                for (int j = 0; j < WordsLoader.getWordsListSize(); j++) {
                    if (words.get(i).getType().equalsIgnoreCase(type)) {
                        filteredWords.add(words.get(i));
                    }
                }
            }
        } else {
            filteredWords = (ArrayList<Words>) filteredWords.stream()
                    .filter((w) -> Arrays.asList(types).contains(w.getType()))
                    .collect(Collectors.toList());
        }

        System.out.println("Words filtered by words type are: ");
        for (Words word : filteredWords) {
            System.out.println(word.getDefinition());
        }
    }

    // not ready to use
    public static void filterByStartingString (boolean continuesFilter, String[] startStrings) {
        int stringsCount = startStrings.length;
        ArrayList<Words> words;

        if (!continuesFilter) {
            filteredWords.clear();
            words = WordsLoader.getWordsList();
        } else {
            words = filteredWords;
            filteredWords.clear();
        }

        for (int i = 0; i < stringsCount; i++) {
            String string = startStrings[i].toLowerCase();
            for (int j = 0; j < WordsLoader.getWordsListSize(); j++) {
                if (words.get(i).getDefinition().startsWith(string)) {
                    filteredWords.add(words.get(i));
                }
            }
        }

        System.out.println("Words filtered by starting strings are: ");
        for (Words word : filteredWords) {
            System.out.println(word.getDefinition());
        }
    }

    // not ready to use
    public static void filterByIncludedString(boolean continuesFilter, String[] includedStrings) {
        int stringsCount = includedStrings.length;
        ArrayList<Words> words;

        if (!continuesFilter) {
            filteredWords.clear();
            words = WordsLoader.getWordsList();
        } else {
            words = filteredWords;
            filteredWords.clear();
        }

        for (int i = 0; i < stringsCount; i++) {
            String string = includedStrings[i].toLowerCase();
            for (int j = 0; j < WordsLoader.getWordsListSize(); j++) {
                if (words.get(i).getDefinition().contains(string)) {
                    filteredWords.add(words.get(i));
                }
            }
        }

        System.out.println("Words filtered by containing strings are: ");
        for (Words word : filteredWords) {
            System.out.println(word.getDefinition());
        }
    }

}
