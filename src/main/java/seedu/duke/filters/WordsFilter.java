package seedu.duke.filters;

/*
Sample filter commands:
- filter [-continue] by\type  -verb -noun -adjective
- filter [-continue] by\start -a
- filter [-continue] by\include -string -string
 */

import seedu.duke.wordlist.WordList;
import seedu.duke.words.Words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WordsFilter {

    public static ArrayList<Words> filteredWords = new ArrayList<>();

    /**
     * Prints word filtered by type.
     *
     * @param isNewFilter Clears last filter and creates new filter.
     * @param types Types of words need filtering.
     */
    public static void filterByType(boolean isNewFilter, String[] types) {
        if (isNewFilter) {
            ArrayList<Words> words = WordList.wordList;
            filteredWords.clear();
            for (String s : types) {
                String type = s.toLowerCase();
                for (int i = 0; i < WordList.getNumberOfWords(); i++) {
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

        printFilterResult();
    }

    /**
     * Filters words by starting strings and prints them.
     *
     * @param isNewFilter Clears last filter and creates new filter.
     * @param startStrings Array of strings that need filtering.
     */
    public static void filterByStartingString(boolean isNewFilter, String[] startStrings) {
        if (isNewFilter) {
            ArrayList<Words> words = WordList.wordList;
            filteredWords.clear();
            for (String startString : startStrings) {
                String string = startString.toLowerCase();
                for (int i = 0; i < WordList.getNumberOfWords(); i++) {
                    if (words.get(i).getDescription().startsWith(string)) {
                        filteredWords.add(words.get(i));
                    }
                }
            }
        } else {
            ArrayList<Words> wordsToRemove = new ArrayList<>();
            for (Words word : filteredWords) {
                boolean keepsWord = false;
                for (String startString : startStrings) {
                    if (word.getDescription().toLowerCase().startsWith(startString.toLowerCase())) {
                        keepsWord = true;
                        break;
                    }
                }
                if (!keepsWord) {
                    wordsToRemove.add(word);
                }
            }
            for (Words wordToRemove : wordsToRemove) {
                filteredWords.remove(wordToRemove);
            }
        }

        printFilterResult();
    }

    /**
     * Filters words by indicated including strings and prints them.
     *
     * @param isNewFilter Clears last filter and creates new filter.
     * @param includedStrings Array of strings that needs filtering.
     */
    public static void filterByIncludedString(boolean isNewFilter, String[] includedStrings) {
        if (isNewFilter) {
            ArrayList<Words> words = WordList.wordList;
            filteredWords.clear();
            for (String includedString : includedStrings) {
                String string = includedString.toLowerCase();
                for (int i = 0; i < WordList.getNumberOfWords(); i++) {
                    if (words.get(i).getDescription().contains(string)) {
                        filteredWords.add(words.get(i));
                    }
                }
            }
        } else {
            ArrayList<Words> wordsToRemove = new ArrayList<>();
            for (Words word : filteredWords) {
                boolean keepsWord = false;
                for (String includedString : includedStrings) {
                    if (word.getDescription().toLowerCase().contains(includedString.toLowerCase())) {
                        keepsWord = true;
                        break;
                    }
                }
                if (!keepsWord) {
                    wordsToRemove.add(word);
                }
            }
            for (Words wordToRemove : wordsToRemove) {
                filteredWords.remove(wordToRemove);
            }
        }

        printFilterResult();
    }

    private static void printFilterResult() {
        if (filteredWords.size() == 0) {
            System.out.println("Filter returns no result!!");
        } else {
            System.out.println("Words filtered by indicated type are: ");
            for (Words word : filteredWords) {
                System.out.println(word.getDescription() + ": " + word.getDefinition());
            }
        }
    }

}
