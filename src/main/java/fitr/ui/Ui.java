package fitr.ui;

import java.util.Scanner;

import static fitr.common.Commands.COMMAND_BYE;
import static fitr.common.Commands.COMMAND_DELETE;
import static fitr.common.Commands.COMMAND_EDIT;
import static fitr.common.Commands.COMMAND_EXERCISE;
import static fitr.common.Commands.COMMAND_FOOD;
import static fitr.common.Commands.COMMAND_GOAL;
import static fitr.common.Commands.COMMAND_VIEW;
import static fitr.common.Commands.COMMAND_VIEW_BMI;
import static fitr.common.Commands.COMMAND_VIEW_EXERCISE;
import static fitr.common.Commands.COMMAND_VIEW_FOOD;
import static fitr.common.Commands.COMMAND_VIEW_PROFILE;
import static fitr.common.Commands.COMMAND_VIEW_SUMMARY;

import static fitr.common.Messages.ERROR_INVALID_COMMAND;

import static fitr.common.Messages.FORMAT_BYE;
import static fitr.common.Messages.FORMAT_CLEAR;
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
import static fitr.common.Messages.FORMAT_HELP;
import static fitr.common.Messages.FORMAT_MARK_GOAL_AS_COMPLETE;
import static fitr.common.Messages.FORMAT_RECOMMEND;
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

/**
 * Prints messages.
 */
public class Ui {
    private static final String HELP_SPACER = "%-70s%s%n";
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

        printCustomMessage("-".repeat(54) + "Profile" + "-".repeat(55));
        System.out.printf(HELP_SPACER, FORMAT_VIEW_PROFILE, "View your profile information");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_BMI, "View your BMI");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_NAME, "Edit your profile name");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_AGE, "Edit your profile age");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_GENDER, "Edit your profile gender");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_HEIGHT, "Edit your profile height");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_WEIGHT, "Edit your profile weight");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_FITNESS, "Edit your profile fitness\n");

        printCustomMessage("-".repeat(56) + "Food" + "-".repeat(56));
        System.out.printf(HELP_SPACER, FORMAT_FOOD, "Add a food entry");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_FOOD, "View your food entries");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_FOOD_ON_SPECIFIED_DATE, "View your food entries on a specified date");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_FOOD, "Edit your previous food entry");
        System.out.printf(HELP_SPACER, FORMAT_DELETE_FOOD, "Delete a food entry");
        System.out.printf(HELP_SPACER, FORMAT_CLEAR_FOOD, "Clear all your food entries\n");

        printCustomMessage("-".repeat(54) + "Exercise" + "-".repeat(54));
        System.out.printf(HELP_SPACER, FORMAT_RECOMMEND, "Get a recommended workout");
        System.out.printf(HELP_SPACER, FORMAT_EXERCISE, "Add an exercise entry");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_EXERCISE, "View your exercise entries");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_EXERCISE_ON_SPECIFIED_DATE, "View your exercise entries on a specified date");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_EXERCISE, "Edit your previous exercise entry");
        System.out.printf(HELP_SPACER, FORMAT_DELETE_EXERCISE, "Delete an exercise entry");
        System.out.printf(HELP_SPACER, FORMAT_CLEAR_EXERCISE, "Clear all your exercise entries\n");

        printCustomMessage("-".repeat(56) + "Goal" + "-".repeat(56));
        System.out.printf(HELP_SPACER, FORMAT_FOOD_GOAL, "Add a food goal");
        System.out.printf(HELP_SPACER, FORMAT_SMART_FOOD_GOAL, "Add a smart food goal");
        System.out.printf(HELP_SPACER, FORMAT_EXERCISE_GOAL, "Add an exercise goal");
        System.out.printf(HELP_SPACER, FORMAT_SMART_EXERCISE_GOAL, "Add a smart exercise goal");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_GOAL, "View your goals");
        System.out.printf(HELP_SPACER, FORMAT_EDIT_GOAL, "Edit your previous goal entry");
        System.out.printf(HELP_SPACER, FORMAT_MARK_GOAL_AS_COMPLETE, "Mark your goal entry as complete");
        System.out.printf(HELP_SPACER, FORMAT_DELETE_GOAL, "Delete a goal entry");
        System.out.printf(HELP_SPACER, FORMAT_CLEAR_GOAL, "Clear all your goal entries\n");

        printCustomMessage("-".repeat(55) + "Other" + "-".repeat(56));
        System.out.printf(HELP_SPACER, FORMAT_HELP, "Display available commands");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_SUMMARY, "View calorie summary");
        System.out.printf(HELP_SPACER, FORMAT_VIEW_SUMMARY_ON_SPECIFIED_DATE, "View calorie summary on a specified date");
        System.out.printf(HELP_SPACER, FORMAT_CLEAR, "Clear all food, exercise and goal entries");
        System.out.printf(HELP_SPACER, FORMAT_BYE, "Exit the application");
    }

    public static void printFormatError(String command) {
        switch (command) {
        case COMMAND_FOOD:
            printCustomError("Please input in the correct format!");
            printCustomMessage(FORMAT + FORMAT_FOOD);
            break;
        case COMMAND_EXERCISE:
            printCustomError("Please input in the correct format!");
            printCustomMessage(FORMAT + FORMAT_EXERCISE);
            break;
        case COMMAND_GOAL:
            printCustomError("Please input in the correct format!");
            printCustomMessage(FORMAT + FORMAT_FOOD_GOAL + " or " + FORMAT_EXERCISE_GOAL);
            break;
        case COMMAND_VIEW:
            printCustomError("Please input in the correct format!");
            printCustomMessage(FORMAT + "view <TYPE>");
            System.out.printf(HELP_SPACER, "<TYPE>", "<DESCRIPTION>");
            System.out.printf(HELP_SPACER, COMMAND_VIEW_PROFILE, "View your profile");
            System.out.printf(HELP_SPACER, COMMAND_VIEW_BMI, "View your BMI");
            System.out.printf(HELP_SPACER, COMMAND_VIEW_FOOD, "View food entries");
            System.out.printf(HELP_SPACER, COMMAND_VIEW_EXERCISE, "View exercise entries");
            System.out.printf(HELP_SPACER, COMMAND_VIEW_SUMMARY, "View calorie summary");
            System.out.printf(HELP_SPACER, COMMAND_GOAL, "View your food and exercise goals");
            break;
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
