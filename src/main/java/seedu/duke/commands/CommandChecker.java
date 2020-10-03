package seedu.duke.commands;

import java.util.Scanner;

public enum CommandChecker {
    HELP,
    SETTINGS,
    QNA,
    MCQ,
    FILLBLANK,
    LIST,
    HISTORY,
    STATS,
    REVIEW,
    QUIZ,
    CLEAR,
    EXIT,
    UNRECOGNISED;

    public static CommandChecker commandType;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getUserInput() {
        System.out.print("!man: ");
        String inputLine = SCANNER.nextLine();

        while (inputLine.trim().isEmpty()) {
            System.out.println("Oops! Command cannot be empty");
            inputLine = SCANNER.nextLine();
        }

        commandType = CommandChecker.extractCommandType(inputLine.trim());

        return inputLine.trim();
    }

    public static CommandChecker extractCommandType(String userInput) {
        if (userInput.toLowerCase().trim().startsWith("help")){
            return CommandChecker.HELP;
        } else if (userInput.toLowerCase().trim().startsWith("settings")){
            return CommandChecker.SETTINGS;
        } else if (userInput.toLowerCase().trim().startsWith("qna")){
            return CommandChecker.QNA;
        } else if (userInput.toLowerCase().trim().startsWith("mcq")){
            return CommandChecker.MCQ;
        } else if (userInput.toLowerCase().trim().startsWith("fillblank")){
            return CommandChecker.FILLBLANK;
        } else if (userInput.toLowerCase().trim().startsWith("list")){
            return CommandChecker.LIST;
        } else if (userInput.toLowerCase().trim().startsWith("history")){
            return CommandChecker.HISTORY;
        } else if (userInput.toLowerCase().trim().startsWith("stats")){
            return CommandChecker.STATS;
        } else if (userInput.toLowerCase().trim().startsWith("review")){
            return CommandChecker.REVIEW;
        } else if (userInput.toLowerCase().trim().startsWith("quiz")){
            return CommandChecker.QUIZ;
        } else if (userInput.toLowerCase().trim().startsWith("clear")){
            return CommandChecker.CLEAR;
        } else if (userInput.toLowerCase().trim().startsWith("exit")){
            return CommandChecker.EXIT;
        } else {
            return CommandChecker.UNRECOGNISED;
        }
    }
}
