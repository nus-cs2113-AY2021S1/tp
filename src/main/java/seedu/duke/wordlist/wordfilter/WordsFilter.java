package seedu.duke.wordlist.wordfilter;

import seedu.duke.constants.FluffleMessages;
import seedu.duke.wordlist.WordList;
import seedu.duke.words.Words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * A class containing algorithms that execute the filter.
 */
public class WordsFilter {

    private static final ArrayList<Words> WORD_LIST = WordList.getWordList();
    private static final Logger LOGGER = Logger.getLogger("WordsFilter");

    /**
     * Prints word filtered by type.
     *
     * @param isNewFilter Clears last filter and creates new filter.
     * @param types Types of words need filtering.
     */
    public static void filterByType(boolean isNewFilter, String[] types) {
        if (isNewFilter) {
            FilterList.filterWords.clear();
            addTagsToFilteredList(FilterType.WORD_TYPE, types);
        } else {
            FilterList.filterWords = (ArrayList<Words>) FilterList.filterWords.stream()
                    .filter((w) -> Arrays.asList(types).contains(w.getType()))
                    .collect(Collectors.toList());
        }
    }

    /**
     * Filters words by starting strings and prints them.
     *
     * @param isNewFilter Clears last filter and creates new filter.
     * @param startStrings Array of strings that need filtering.
     */
    public static void filterByStartingString(boolean isNewFilter, String[] startStrings) {
        if (isNewFilter) {
            FilterList.filterWords.clear();
            addTagsToFilteredList(FilterType.STARTING_STRING, startStrings);
        } else {
            ArrayList<Words> wordsToRemove = new ArrayList<>();
            generateListOfRemoveWords(FilterType.STARTING_STRING, startStrings, wordsToRemove);
            removeRedundantWords(wordsToRemove);
        }
    }

    /**
     * Filters words by indicated including strings and prints them.
     *
     * @param isNewFilter Clears last filter and creates new filter.
     * @param includedStrings Array of strings that needs filtering.
     */
    public static void filterByIncludedString(boolean isNewFilter, String[] includedStrings) {
        if (isNewFilter) {
            FilterList.filterWords.clear();
            addTagsToFilteredList(FilterType.INCLUDING_STRING, includedStrings);
        } else {
            ArrayList<Words> wordsToRemove = new ArrayList<>();
            generateListOfRemoveWords(FilterType.INCLUDING_STRING, includedStrings, wordsToRemove);
            removeRedundantWords(wordsToRemove);
        }
    }

    private static void generateListOfRemoveWords(FilterType filterType, String[] patterns,
                                                  ArrayList<Words> wordsToRemove) {
        for (Words word : FilterList.filterWords) {
            boolean keepsWord = false;
            for (String pattern : patterns) {
                switch (filterType) {
                case STARTING_STRING:
                    if (word.getDescription().toLowerCase().startsWith(pattern.toLowerCase())) {
                        keepsWord = true;
                        break;
                    }
                    break;
                case INCLUDING_STRING:
                    if (word.getDescription().toLowerCase().contains(pattern.toLowerCase())) {
                        keepsWord = true;
                        break;
                    }
                    break;
                default:
                    LOGGER.log(Level.WARNING, FluffleMessages.INVALID_FILTER_CODE);
                }
            }
            if (!keepsWord) {
                wordsToRemove.add(word);
            }
        }
    }

    private static void addTagsToFilteredList(FilterType filterType, String[] patterns) {
        for (String pattern : patterns) {
            String string = pattern.toLowerCase();
            for (int i = 0; i < WordList.getNumberOfWords(); i++) {
                switch (filterType) {
                case WORD_TYPE:
                    if (WORD_LIST.get(i).getType().equalsIgnoreCase(string)) {
                        FilterList.filterWords.add(WORD_LIST.get(i));
                    }
                    break;
                case STARTING_STRING:
                    if (WORD_LIST.get(i).getDescription().startsWith(string)
                            && !FilterList.filterWords.contains(WORD_LIST.get(i))) {
                        FilterList.filterWords.add(WORD_LIST.get(i));
                    }
                    break;
                case INCLUDING_STRING:
                    if (WORD_LIST.get(i).getDescription().contains(string)
                            && !FilterList.filterWords.contains(WORD_LIST.get(i))) {
                        FilterList.filterWords.add(WORD_LIST.get(i));
                    }
                    break;
                default:
                    LOGGER.log(Level.WARNING, FluffleMessages.INVALID_FILTER_CODE);
                }
            }
        }
    }

    private static void removeRedundantWords(ArrayList<Words> wordsToRemove) {
        for (Words wordToRemove : wordsToRemove) {
            while (FilterList.filterWords.contains(wordToRemove)) {
                FilterList.filterWords.remove(wordToRemove);
            }
        }
    }

}
