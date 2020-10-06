package seedu.duke;

import java.util.Arrays;

public class Parser {

    /**
     * Default Constructor for parser
     */
    public Parser() {

    };


    public Command com Parse(String userInput) {

        String[] words = userInput.split(" ");

        if (words.length == 0) {

            System.out.println("Error, no command entered");
            return null;
        }

        String commandWord = words[0];
        String[] argument = Arrays.copyOfRange(words, 1, words.length);

        switch (commandWord) {
        case "add":
            return null;

        case "list":
            return null;

        case "bye":
            return null;

        case "check":
            return null;

        case "repeat":
            return null;

        case "goal":
            return null;

        case "deadline":
            return null;

        default:
            System.out.println("Error! Unrecognised command");
            return null;
        }
    }


}
