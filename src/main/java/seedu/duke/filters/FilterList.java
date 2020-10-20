package seedu.duke.filters;

import seedu.duke.constants.FilterMessages;
import seedu.duke.parsers.Parsers;
import seedu.duke.words.Words;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.constants.FilterMessages.INVALID_LIST_FILTER_ANSWER;
import static seedu.duke.constants.FilterMessages.LONG_FILTER_LIST_MESSAGE;

public class FilterList {

    public static Scanner SCANNER = new Scanner(System.in);

    public static void printFilterList() {
        ArrayList<Words> filteredWords = new ArrayList<>(WordsFilter.filteredWords);
        if (filteredWords.size() == 0) {
            System.out.println(FilterMessages.NO_FILTER_RESULT);
            return;
        }

        if (filteredWords.size() <= 10) {
            System.out.println(FilterMessages.FILTER_MESSAGE);
            for (Words word : filteredWords) {
                System.out.println(word.getDescription() + ": " + word.getDefinition());
            }
            return;
        }

        System.out.println(LONG_FILTER_LIST_MESSAGE);
        String userInput = Parsers.getUserInput(SCANNER);
        if (userInput.trim().equalsIgnoreCase("y") ||
                userInput.trim().equalsIgnoreCase("yes")) {
            System.out.println(FilterMessages.FILTER_MESSAGE);
            for (Words word : filteredWords) {
                System.out.println(word.getDescription() + ": " + word.getDefinition());
            }
        } else if (userInput.trim().equalsIgnoreCase("n") ||
                userInput.trim().equalsIgnoreCase("no")) {
            System.out.println(FilterMessages.FILTER_MESSAGE);
            for (int i = 0; i < 20; i++) {
                Words word = filteredWords.get(i);
                System.out.println(word.getDescription() + ": " + word.getDefinition());
            }
        } else {
            System.out.println(INVALID_LIST_FILTER_ANSWER);
        }
    }
}
