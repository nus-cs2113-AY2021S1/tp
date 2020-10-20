package seedu.duke.utility;

import java.util.ArrayList;


public class StringOperations {

    /**
     * Tokenize the user input into an argument array.
     *
     * @param input              User input.
     * @return ArrayList of tokenized user input.
     * @throws NullPointerException if input is empty or invalid.
     */
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

    /**
     * Extracts the first word from user input.
     *
     * @param input User input.
     * @return String containing the first word.
     */
    public static String getFirstWord(String input) {
        int index = input.indexOf(' ');
        if (index == -1) { // Input only contains a single word
            return input;
        } else {
            return input.substring(0, index).trim(); // Extracts first word.
        }
    }

    /**
     * Removes the first word from user input.
     *
     * @param input User input.
     * @return string without first word or empty string if user input contains one word.
     */
    public static String removeFirstWord(String input) {
        int index = input.indexOf(' ');
        if (index == -1) { // Input only contains a single word
            return "";
        } else {
            return input.substring(index + 1).trim(); // Extracts after space.
        }
    }


}
