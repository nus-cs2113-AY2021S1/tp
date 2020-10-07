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
    SCENE,
    TOPIC,
    UNRECOGNISED;

    public static CommandChecker commandType;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getUserInput() {
        System.out.print("You: ");
        String inputLine = SCANNER.nextLine();

        while (inputLine.trim().isEmpty()) {
            System.out.println("Oops! Command cannot be empty");
            inputLine = SCANNER.nextLine();
        }
        String[] words = inputLine.split(" ");
        String command = words[0];
        commandType = CommandChecker.extractCommandType(command);

        return inputLine.trim();
    }

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
