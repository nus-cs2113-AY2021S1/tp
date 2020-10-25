package userinterface;

import academic.AcademicRun;
import academic.AcademicUi;
import academic.Grade;
import academic.Person;
import bookmark.BookmarkUi;
import bookmark.BookmarkRun;
import exceptions.InvalidModeException;
import studyit.ModeNames;
import studyit.Mode;
import studyit.CommandParser;
import studyit.StudyIt;
import studyit.StudyItLog;

import java.util.ArrayList;
import java.util.Scanner;


public class Ui {
    public static final String LINE_DIVIDER = "=======================================================================";

    public static void printDivider() {
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Receive command input from the user via terminal.
     *
     * @return the command input as a String
     */
    public static String inputCommand() {
        String command;
        Scanner in = new Scanner(System.in);
        assert in != null : "null is passed in";

        command = in.nextLine();
        assert command.length() >= 0 : "The length of command should be at least 0";

        return command;
    }

    /**
     * Prints text with line divider above and below the text.
     *
     * @param text any String type text
     */
    public static void printLine(String text) {
        System.out.println(LINE_DIVIDER + "\n" + text + "\n" + LINE_DIVIDER);
    }

    public static void printExit() {
        System.out.println(LINE_DIVIDER + "\nSee you again soon!!!\n" + LINE_DIVIDER);
    }

    public static void printLocation() {
        printLine("You are currently at " + ModeNames.getCurrentModeName() + "!");
    }

    public static void changeModeCommand(String command) {
        try {
            Mode newMode = CommandParser.getDestinationMode(command);
            StudyIt.changeMode(newMode);
            printLine("Mode changed! You are now at: " + ModeNames.getCurrentModeName());
            printModeIntro(newMode);
        } catch (InvalidModeException e) {
            printLine("Invalid mode name! Please try again.\n"
                    + "You are still at: " + ModeNames.getCurrentModeName());
            StudyItLog.logger.fine("Cannot understand mode chosen.");
        }
    }

    public static void printModeIntro(Mode newMode) {
        // Prints introduction to the mode (if any)
        if (newMode == Mode.BOOKMARK) {
            BookmarkUi.printWelcomeBookmarkMessage();
            //BookmarkUi.showBookmarkCategoryList();
            printDivider();
        }
    }

    public static void exitMode() {
        printDivider();
        System.out.println("Exited " + ModeNames.getCurrentModeName() + "!");
        StudyIt.changeMode(Mode.MENU); //TODO: Check UI
        System.out.println("You are now back at: " + ModeNames.getCurrentModeName());
        printDivider();
    }

    public static void printHighlight(BookmarkRun bookmarkRun, AcademicRun academicRun) {
        System.out.println("Here are your starred items:");
        bookmarkRun.run("list star");
        System.out.println();
        academicRun.run("list star");
    }
}