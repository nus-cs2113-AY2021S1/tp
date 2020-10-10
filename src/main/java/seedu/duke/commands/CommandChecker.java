package seedu.duke.commands;

import java.util.Scanner;

public enum CommandChecker {
    HELP,
    USERNAME,
    DIVIDER,

    NOUN,
    VERB,
    ADJ,
    LIST_WORDS,
    FILTER_WORDS,

    NAME,
    GEN_NAME,
    LIST_NAMES,
    FILTER_NAMES,

    BUNNY,
    LIST_BUNNY,
    FILTER_BUNNY,

    STATS,
    DELETE,
    EXIT,
    SCENE,
    TOPIC,
    UNRECOGNISED;

    public static CommandChecker commandChecker;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getUserInput() {
        System.out.print("You: ");
        String inputLine = SCANNER.nextLine();

        while (inputLine.trim().isEmpty()) {
            System.out.println("Oops! Command cannot be empty");
            inputLine = SCANNER.nextLine();
        }
<<<<<<< HEAD
        String[] words = inputLine.split(" ");
        String command = words[0];
        commandType = CommandChecker.extractCommandType(command);
=======

        commandChecker = extractCommandType(inputLine.trim());
>>>>>>> df66c260dbe3273b7530aad4a6103ffe3a1370a4

        return inputLine.trim();
    }

<<<<<<< HEAD
    public static CommandChecker extractCommandType(String userCommand) {
        if (userCommand.equalsIgnoreCase("help")) {
            return CommandChecker.HELP;
        } else if (userCommand.equalsIgnoreCase("settings")) {
            return CommandChecker.SETTINGS;
        } else if (userCommand.equalsIgnoreCase("qna")) {
            return CommandChecker.QNA;
        } else if (userCommand.equalsIgnoreCase("mcq")) {
            return CommandChecker.MCQ;
        } else if (userCommand.equalsIgnoreCase("fillblank")) {
            return CommandChecker.FILLBLANK;
        } else if (userCommand.equalsIgnoreCase("list")) {
            return CommandChecker.LIST;
        } else if (userCommand.equalsIgnoreCase("history")) {
            return CommandChecker.HISTORY;
        } else if (userCommand.equalsIgnoreCase("stats")) {
            return CommandChecker.STATS;
        } else if (userCommand.equalsIgnoreCase("review")) {
            return CommandChecker.REVIEW;
        } else if (userCommand.equalsIgnoreCase("quiz")) {
            return CommandChecker.QUIZ;
        } else if (userCommand.equalsIgnoreCase("clear")) {
            return CommandChecker.CLEAR;
        } else if (userCommand.equalsIgnoreCase("exit")) {
=======
    /**
     *  Figure out the command type from userInput.
     *
     * @param userInput string containing user input command
     * @return the type of command detected
     */
    public static CommandChecker extractCommandType(String userInput) {
        if (userInput.toLowerCase().trim().startsWith("help")) {
            return CommandChecker.HELP;
        } else if (userInput.toLowerCase().trim().startsWith("username")) {
            return CommandChecker.USERNAME;
        } else if (userInput.toLowerCase().trim().startsWith("divider")) {
            return CommandChecker.DIVIDER;

        } else if (userInput.toLowerCase().trim().startsWith("noun")) {
            return CommandChecker.NOUN;
        } else if (userInput.toLowerCase().trim().startsWith("verb")) {
            return CommandChecker.VERB;
        } else if (userInput.toLowerCase().trim().startsWith("adj")) {
            return CommandChecker.ADJ;
        } else if (userInput.toLowerCase().trim().startsWith("list words")) {
            return CommandChecker.LIST_WORDS;

        } else if (userInput.toLowerCase().trim().startsWith("bunny")) {
            return CommandChecker.BUNNY;
        } else if (userInput.toLowerCase().trim().startsWith("list bunny")) {
            return CommandChecker.LIST_BUNNY;

        } else if (userInput.toLowerCase().trim().startsWith("exit")) {
>>>>>>> df66c260dbe3273b7530aad4a6103ffe3a1370a4
            return CommandChecker.EXIT;
        } else if (userCommand.equalsIgnoreCase("scene")) {
            return CommandChecker.SCENE;
        } else if (userCommand.equalsIgnoreCase("topic")) {
            return CommandChecker.TOPIC;
        } else {
            return CommandChecker.UNRECOGNISED;
        }
    }

}
