package fitr.ui;

import java.util.Scanner;

import static fitr.common.Commands.COMMAND_BYE;
import static fitr.common.Commands.COMMAND_CLEAR;
import static fitr.common.Commands.COMMAND_COMPLETE;
import static fitr.common.Commands.COMMAND_DELETE;
import static fitr.common.Commands.COMMAND_EDIT;
import static fitr.common.Commands.COMMAND_EXERCISE;
import static fitr.common.Commands.COMMAND_FOOD;
import static fitr.common.Commands.COMMAND_GOAL;
import static fitr.common.Commands.COMMAND_HELP;
import static fitr.common.Commands.COMMAND_RECOMMEND;
import static fitr.common.Commands.COMMAND_VIEW;
import static fitr.common.Commands.COMMAND_VIEW_BMI;
import static fitr.common.Commands.COMMAND_VIEW_PROFILE;
import static fitr.common.Commands.COMMAND_VIEW_SUMMARY;

import static fitr.common.Commands.WORD_AEROBIC;
import static fitr.common.Commands.WORD_LOWERBODY;
import static fitr.common.Commands.WORD_STRETCH;
import static fitr.common.Commands.WORD_UPPERBODY;
import static fitr.common.Messages.ERROR_FORMAT_MESSAGE;
import static fitr.common.Messages.ERROR_INVALID_COMMAND;

import static fitr.common.Messages.FORMAT_CLEAR_EXERCISE;
import static fitr.common.Messages.FORMAT_CLEAR_FOOD;
import static fitr.common.Messages.FORMAT_CLEAR_GOAL;
import static fitr.common.Messages.FORMAT_DELETE_EXERCISE;
import static fitr.common.Messages.FORMAT_DELETE_FOOD;
import static fitr.common.Messages.FORMAT_DELETE_GOAL;
import static fitr.common.Messages.FORMAT_EDIT_AGE;
import static fitr.common.Messages.FORMAT_EDIT_EXERCISE;
import static fitr.common.Messages.FORMAT_EDIT_FITNESS;
import static fitr.common.Messages.FORMAT_EDIT_FOOD;
import static fitr.common.Messages.FORMAT_EDIT_GENDER;
import static fitr.common.Messages.FORMAT_EDIT_GOAL;
import static fitr.common.Messages.FORMAT_EDIT_HEIGHT;
import static fitr.common.Messages.FORMAT_EDIT_NAME;
import static fitr.common.Messages.FORMAT_EDIT_WEIGHT;
import static fitr.common.Messages.FORMAT_EXERCISE;
import static fitr.common.Messages.FORMAT_EXERCISE_GOAL;
import static fitr.common.Messages.FORMAT_FOOD;
import static fitr.common.Messages.FORMAT_FOOD_GOAL;
import static fitr.common.Messages.FORMAT_MARK_GOAL_AS_COMPLETE;
import static fitr.common.Messages.FORMAT_SMART_EXERCISE_GOAL;
import static fitr.common.Messages.FORMAT_SMART_FOOD_GOAL;
import static fitr.common.Messages.FORMAT_VIEW_BMI;
import static fitr.common.Messages.FORMAT_VIEW_EXERCISE;
import static fitr.common.Messages.FORMAT_VIEW_EXERCISE_ON_SPECIFIED_DATE;
import static fitr.common.Messages.FORMAT_VIEW_FOOD;
import static fitr.common.Messages.FORMAT_VIEW_FOOD_ON_SPECIFIED_DATE;
import static fitr.common.Messages.FORMAT_VIEW_GOAL;
import static fitr.common.Messages.FORMAT_VIEW_PROFILE;
import static fitr.common.Messages.FORMAT_VIEW_SUMMARY;
import static fitr.common.Messages.FORMAT_VIEW_SUMMARY_ON_SPECIFIED_DATE;
import static fitr.common.Messages.MESSAGE_BYE;
import static fitr.common.Messages.MESSAGE_GREET;
import static fitr.common.Messages.MESSAGE_SUGGEST_QUESTION;
import static fitr.common.Messages.PHRASE_EXTRA_PARAMETERS;

/**
 * Prints messages.
 */
public class Ui {
    private static final String HELP_SPACER = "%-90s%s%n";
    private static final String VIEW_SPACER = "%-30s%s%n";
    private static final String GOAL_SPACER = "%-45s%s%n";
    private static final String DELETE_SPACER = "%-60s%s%n";
    private static final String GREEN_COLOUR = "\033[0;32m";
    private static final String RED_COLOUR = "\033[0;31m";
    private static final String RESET_COLOUR = "\033[0m";
    public static final String YELLOW_COLOUR = "\033[0;93m";
    private static final String FORMAT = GREEN_COLOUR + "Format: " + RESET_COLOUR;

    public static String read() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void printCustomMessage(String message) {
        System.out.println(message);
    }

    //Prints message in red
    public static void printCustomError(String errorMessage) {
        System.out.println(RED_COLOUR + errorMessage + RESET_COLOUR);
    }

    //Prints message in yellow
    public static void printMessageInYellow(String message) {
        System.out.println(YELLOW_COLOUR + message + RESET_COLOUR);
    }

    public static void printGreetingMessage() {
        printCustomMessage(MESSAGE_GREET);
    }

    public static void printSuggestQuestion() {
        printCustomMessage(MESSAGE_SUGGEST_QUESTION);
    }

    public static void printExitMessage() {
        printCustomMessage(MESSAGE_BYE);
    }


    public static void printHelpMessage() {
        printCustomMessage("These are commands Fitr understands:");
        printCustomMessage("Do note that:");
        printCustomMessage(" * On the left shows the command format while the right shows its usage");
        printCustomMessage(" * Words formatted as UPPER_CASE are to be supplied by you");
        printCustomMessage(" * Items in square brackets are [optional]");
        printCustomMessage(" * Items in brackets are (additional information)\n");

        printCustomMessage("-".repeat(64) + "Profile" + "-".repeat(65));
        System.out.printf(HELP_SPACER, FORMAT_VIEW_PROFILE, "View your profile information");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_BMI, "View your BMI");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_NAME, "Edit your profile name");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_AGE, "Edit your profile age");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_GENDER, "Edit your profile gender");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_HEIGHT, "Edit your profile height");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_WEIGHT, "Edit your profile weight");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_FITNESS, "Edit your profile fitness\n");

        printCustomMessage("-".repeat(66) + "Food" + "-".repeat(66));
        System.out.printf(HELP_SPACER, FORMAT_FOOD, "Add a food entry");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_FOOD, "View your food entries");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_FOOD_ON_SPECIFIED_DATE,
                "View your food entries on a specified date");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_FOOD, "Edit your previous food entry");
        System.out.printf(HELP_SPACER, FORMAT_DELETE_FOOD, "Delete a food entry");
        System.out.printf(HELP_SPACER, FORMAT_CLEAR_FOOD, "Clear all your food entries\n");

        printCustomMessage("-".repeat(64) + "Exercise" + "-".repeat(64));
        System.out.printf(HELP_SPACER, COMMAND_RECOMMEND, "Get a recommended workout");
        System.out.printf(HELP_SPACER, COMMAND_RECOMMEND + " " + WORD_AEROBIC, "Get a recommended aerobic workout");
        System.out.printf(HELP_SPACER, COMMAND_RECOMMEND + " " + WORD_UPPERBODY, "Get a recommended upperbody workout");
        System.out.printf(HELP_SPACER, COMMAND_RECOMMEND + " " + WORD_LOWERBODY, "Get a recommended lowerbody workout");
        System.out.printf(HELP_SPACER, COMMAND_RECOMMEND + " " + WORD_STRETCH, "Get a recommended stretch workout");
        System.out.printf(HELP_SPACER, FORMAT_EXERCISE, "Add an exercise entry");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_EXERCISE, "View your exercise entries");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_EXERCISE_ON_SPECIFIED_DATE,
                "View your exercise entries on a specified date");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_EXERCISE, "Edit your previous exercise entry");
        System.out.printf(HELP_SPACER, FORMAT_DELETE_EXERCISE, "Delete an exercise entry");
        System.out.printf(HELP_SPACER, FORMAT_CLEAR_EXERCISE, "Clear all your exercise entries\n");

        printCustomMessage("-".repeat(66) + "Goal" + "-".repeat(66));
        System.out.printf(HELP_SPACER, FORMAT_FOOD_GOAL, "Add a food goal");
        System.out.printf(HELP_SPACER, FORMAT_SMART_FOOD_GOAL, "Add a smart food goal");
        System.out.printf(HELP_SPACER, FORMAT_EXERCISE_GOAL, "Add an exercise goal");
        System.out.printf(HELP_SPACER, FORMAT_SMART_EXERCISE_GOAL, "Add a smart exercise goal");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_GOAL, "View your goals");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_GOAL, "Edit your previous goal entry");
        System.out.printf(HELP_SPACER, FORMAT_MARK_GOAL_AS_COMPLETE, "Mark your goal entry as complete");
        System.out.printf(HELP_SPACER, FORMAT_DELETE_GOAL, "Delete a goal entry");
        System.out.printf(HELP_SPACER, FORMAT_CLEAR_GOAL, "Clear all your goal entries\n");

        printCustomMessage("-".repeat(65) + "Other" + "-".repeat(66));
        System.out.printf(HELP_SPACER, COMMAND_HELP, "Display available commands");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_SUMMARY, "View calorie summary");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_SUMMARY_ON_SPECIFIED_DATE,
                "View calorie summary on a specified date");
        System.out.printf(HELP_SPACER, COMMAND_CLEAR, "Clear all food, exercise and goal entries");
        System.out.printf(HELP_SPACER, COMMAND_BYE, "Exit the application");
    }

    public static void printFormatError(String command) {
        switch (command) {
        case COMMAND_FOOD:
            printCustomError(ERROR_FORMAT_MESSAGE);
            printCustomMessage(FORMAT + FORMAT_FOOD);
            break;
        case COMMAND_EXERCISE:
            printCustomError(ERROR_FORMAT_MESSAGE);
            printCustomMessage(FORMAT + FORMAT_EXERCISE);
            break;
        case COMMAND_GOAL:
            printCustomError(ERROR_FORMAT_MESSAGE);
            System.out.printf(GOAL_SPACER, FORMAT_FOOD_GOAL, "Add a food goal");
            System.out.printf(GOAL_SPACER, FORMAT_SMART_FOOD_GOAL, "Add a smart food goal");
            System.out.printf(GOAL_SPACER, FORMAT_EXERCISE_GOAL, "Add an exercise goal");
            System.out.printf(GOAL_SPACER, FORMAT_SMART_EXERCISE_GOAL, "Add a smart exercise goal");
            break;
        case COMMAND_VIEW:
            printCustomError(ERROR_FORMAT_MESSAGE);
            printCustomMessage(FORMAT + "view <TYPE>");
            System.out.printf(VIEW_SPACER, "<TYPE>", "<DESCRIPTION>");
            System.out.printf(VIEW_SPACER, COMMAND_VIEW_PROFILE, "View your profile");
            System.out.printf(VIEW_SPACER, COMMAND_VIEW_BMI, "View your BMI");
            System.out.printf(VIEW_SPACER, COMMAND_FOOD, "View food entries");
            System.out.printf(VIEW_SPACER, COMMAND_EXERCISE, "View exercise entries");
            System.out.printf(VIEW_SPACER, COMMAND_VIEW_SUMMARY, "View calorie summary");
            System.out.printf(VIEW_SPACER, COMMAND_GOAL, "View your food and exercise goals");
            break;
        case PHRASE_EXTRA_PARAMETERS:
            Ui.printCustomError("Please remove the extra parameters!");
            break;
        case COMMAND_DELETE:
            printCustomError(ERROR_FORMAT_MESSAGE);
            System.out.printf(DELETE_SPACER, "<FORMAT>", "<USAGE>");
            System.out.printf(DELETE_SPACER, FORMAT_DELETE_FOOD, "Delete a food entry");
            System.out.printf(DELETE_SPACER, FORMAT_DELETE_EXERCISE, "Delete an exercise entry");
            System.out.printf(DELETE_SPACER, FORMAT_DELETE_GOAL, "Delete a goal entry");
            break;
        case COMMAND_EDIT:
            printCustomError(ERROR_FORMAT_MESSAGE);
            System.out.printf(HELP_SPACER, "<FORMAT>", "<USAGE>");
            System.out.printf(HELP_SPACER, FORMAT_EDIT_NAME, "Edit your profile name");
            System.out.printf(HELP_SPACER, FORMAT_EDIT_AGE, "Edit your profile age");
            System.out.printf(HELP_SPACER, FORMAT_EDIT_GENDER, "Edit your profile gender");
            System.out.printf(HELP_SPACER, FORMAT_EDIT_HEIGHT, "Edit your profile height");
            System.out.printf(HELP_SPACER, FORMAT_EDIT_WEIGHT, "Edit your profile weight");
            System.out.printf(HELP_SPACER, FORMAT_EDIT_FITNESS, "Edit your profile fitness");
            System.out.printf(HELP_SPACER, FORMAT_EDIT_FOOD, "Edit your previous food entry");
            System.out.printf(HELP_SPACER, FORMAT_EDIT_EXERCISE, "Edit your previous exercise entry");
            System.out.printf(HELP_SPACER, FORMAT_EDIT_GOAL, "Edit your previous goal entry");
            break;
        case "Smart food goal":
            printCustomError(ERROR_FORMAT_MESSAGE);
            printCustomMessage(FORMAT + FORMAT_SMART_FOOD_GOAL);
            break;
        case "Smart exercise goal":
            printCustomError(ERROR_FORMAT_MESSAGE);
            printCustomMessage(FORMAT + FORMAT_SMART_EXERCISE_GOAL);
            break;
        case COMMAND_COMPLETE:
            printCustomError(ERROR_FORMAT_MESSAGE);
            printCustomMessage(FORMAT + FORMAT_MARK_GOAL_AS_COMPLETE);
        default:
            printInvalidCommandError();
            break;
        }
    }

    public static void printInvalidCommandError() {
        printCustomError(ERROR_INVALID_COMMAND);
        printCustomMessage("Use 'help' to see the list of commands Fitr understands.");
    }
}
