package seedu.duke.filters;

import seedu.duke.constants.FilterMessages;
import seedu.duke.parsers.Parsers;
import seedu.duke.words.Words;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.constants.FilterMessages.NO_FILTER_RESULT;

public class FilterList {

    public static Scanner SCANNER = new Scanner(System.in);

    public static void printFilterList(int printLimit) {
        ArrayList<Words> filteredWords = new ArrayList<>(WordsFilter.filteredWords);
        if (filteredWords.size() == 0) {
            System.out.println(NO_FILTER_RESULT);
            return;
        }

        if (filteredWords.size() <= printLimit) {
            System.out.printf(FilterMessages.FILTER_MESSAGE, filteredWords.size());
            for (Words word : filteredWords) {
                System.out.println(word.getDescription() + ": " + word.getDefinition());
            }
            return;
        }

        if (printLimit != -1) {
            System.out.printf(FilterMessages.LONG_FILTER_LIST_MESSAGE, printLimit);
        } else {
            System.out.println(FilterMessages.PRINT_LIMIT_NOT_FOUND);
            System.out.printf(FilterMessages.FILTER_MESSAGE, filteredWords.size());
            for (Words word : filteredWords) {
                System.out.println(word.getDescription() + ": " + word.getDefinition());
            }
            return;
        }

        String userInput = Parsers.getUserInput(SCANNER);
        if (userInput.trim().equalsIgnoreCase("y")
                || userInput.trim().equalsIgnoreCase("yes")) {
            System.out.printf(FilterMessages.FILTER_MESSAGE, filteredWords.size());
            for (Words word : filteredWords) {
                System.out.println(word.getDescription() + ": " + word.getDefinition());
            }
        } else if (userInput.trim().equalsIgnoreCase("n")
                || userInput.trim().equalsIgnoreCase("no")) {
            System.out.printf(FilterMessages.FILTER_MESSAGE_LIMIT, printLimit, filteredWords.size());
            for (int i = 0; i < printLimit; i++) {
                Words word = filteredWords.get(i);
                System.out.println(word.getDescription() + ": " + word.getDefinition());
            }
        } else {
            System.out.println(FilterMessages.INVALID_LIST_FILTER_ANSWER);
        }
    }
}
