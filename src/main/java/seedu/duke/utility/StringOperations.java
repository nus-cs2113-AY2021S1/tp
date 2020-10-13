package seedu.duke.utility;

import java.util.ArrayList;

public class StringOperations {
    public static ArrayList<String> tokenizeStringArray(String input) {
        ArrayList<String> inputArray = new java.util.ArrayList<>();
        for (String token : input.split(" ")) {
            inputArray.add(token);
        }
        int size = 0;
        try {
            size = inputArray.size();
        } catch (NullPointerException e) {
            Ui.printBadInputException();
        }
        if (size > 0) {
            return inputArray;
        } else {
            return null;
        }
    }

    public static String getFirstWord(String input) {
        int index = input.indexOf(' ');
        if (index == -1) { // Input only contains a single word
            return input;
        } else {
            return input.substring(0, index).trim(); // Extracts first word.
        }
    }

    public static String removeFirstWord(String input) {
        int index = input.indexOf(' ');
        if (index == -1) { // Input only contains a single word
            return "";
        } else {
            return input.substring(index + 1).trim(); // Extracts after space.
        }
    }
}
