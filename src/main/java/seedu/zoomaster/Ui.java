package seedu.zoomaster;

import org.fusesource.jansi.AnsiConsole;
import seedu.zoomaster.command.ChangeModeCommand;
import seedu.zoomaster.command.ClearCommand;
import seedu.zoomaster.command.ExitCommand;
import seedu.zoomaster.command.bookmark.AddBookmarkCommand;
import seedu.zoomaster.command.bookmark.DeleteBookmarkCommand;
import seedu.zoomaster.command.bookmark.FindBookmarkCommand;
import seedu.zoomaster.command.bookmark.LaunchBookmarkCommand;
import seedu.zoomaster.command.bookmark.ShowBookmarkCommand;
import seedu.zoomaster.command.timetable.AddSlotCommand;
import seedu.zoomaster.command.timetable.DeleteSlotCommand;
import seedu.zoomaster.command.timetable.ShowTimetableCommand;
import seedu.zoomaster.exception.ZoomasterException;

import javax.swing.JSeparator;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.Color.BLACK;
import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.CYAN;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.WHITE;
import static org.fusesource.jansi.Ansi.Color.YELLOW;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * Represents the user interface on the command line and deals with interactions with the user.
 */
public class Ui {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String LINE = "____________________________________________________________" + NEW_LINE;
    private Scanner scanner;

    private String logo2 =
            "                                                                                                 \n"
            + "                                            ███████                                              \n"
            + "                                      ██████████████████                                         \n"
            + "                                    ███████████████████████                                      \n"
            + "                                  ███████████████████████████                                    \n"
            + "                                 ████             █████  █████                                   \n"
            + "                                █████              █     ██████                                  \n"
            + "                                █████              █     ██████                                  \n"
            + "                                █████              █     ██████                                  \n"
            + "                                 █████             ████  █████                                   \n"
            + "                                  ███████████████████████████                                    \n"
            + "                                   █████████████████████████                                     \n"
            + "                                      ███████████████████                                        \n"
            + "                                          ███████████                                            \n"
            + "                                                                         ██                      \n"
            + "█████████   ███████     ████████   ███████ ███████     █████     █████  █████   █████     █ ████ \n"
            + "     ███  ██      ███  ██      ███ ██     ██     ██         ██  ██       ██   ██      ██  ██   ██\n"
            + "   ███   ███       ██ ███       ██ ██     ██     ██  █████████  ███████  ██  ████████████ ██     \n"
            + "███       ███     ███  ██      ███ ██     ██     ██  ██     ██        ██ ██   ██          ██     \n"
            + "██████████   █████       ██████    ██     ██     ██   ████████  ███████   ███  ████████   ██     \n";



    /**
     * Constructs a new Ui instance.
     */
    public Ui() {
        scanner = new Scanner(System.in);
        AnsiConsole.systemInstall();
    }

    /**
     * Returns a string of the user input in the command line.
     *
     * @return the string of user input.
     */
    public String readCommand() {
        if (Parser.getProgramMode() == 0) {
            System.out.print(ansi().fg(GREEN).a("[Main Menu] Input: ").reset());
        } else if (Parser.getProgramMode() == 1) {
            System.out.print(ansi().fg(BLUE).a("[Bookmark mode] Input: ").reset());
        } else if (Parser.getProgramMode() == 2) {
            System.out.print(ansi().fg(YELLOW).a("[Timetable mode] Input: ").reset());
        } else if (Parser.getProgramMode() == 3) {
            System.out.print(ansi().fg(WHITE).a("[Planner mode] Input: ").reset());
        } else {
            System.out.print("[An error has occurred] ");
        }

        return scanner.nextLine().trim();
    }

    public void print(String message) {
        System.out.println(LINE + message + LINE);
    }

    public void printRed(String message) {
        System.out.println(LINE);
        System.out.print(ansi().fg(RED).a(message).reset());
        System.out.println(LINE);
    }

    public void printGreen(String message) {
        System.out.println(LINE);
        System.out.print(ansi().fg(GREEN).a(message).reset());
        System.out.println(LINE);
    }

    public void printYellow(String message) {
        System.out.println(LINE);
        System.out.print(ansi().fg(YELLOW).a(message).reset());
        System.out.println(LINE);
    }

    public void clearScreen() {
        System.out.print("\033[2J");
    }

    /**
     * Prints a message after starting the program.
     */
    public void showWelcomeScreen() {
        System.out.println(LINE);

        System.out.println("\t\t\t\t  ++++{  WELCOME TO  }++++");
        System.out.println(ansi().bg(WHITE));
        System.out.println(ansi().fg(CYAN).a(logo2).reset());

        System.out.println(ansi().bg(BLACK));
        System.out.println(LINE);
    }

    /**
     * Prints a message before exiting the program.
     */
    public void showExitScreen() {
        String message = "Bye. Hope to see you again soon!" + NEW_LINE;
        print(message);
        AnsiConsole.systemUninstall();
    }

    /**
     * Prints the error message if data file is not found.
     */
    public void showLoadingError() {
        String message = "Data file not found" + NEW_LINE;
        printRed(message);
    }

    /**
     * This method detects the type of ZoomasterException error and prints the corresponding error message.
     *
     * @param e The ZoomasterException error.
     */
    public void showErrorMessage(ZoomasterException e) {
        switch (e.getError()) {
        case UNKNOWN_INPUT:
            print("Unknown input" + NEW_LINE);
            printHelpMessage();
            break;
        case INVALID_MODE:
            printUnknownModeMessage();
            break;
        case WRITE_FILE_ERROR:
            printErrorWritingToFile();
            break;
        case ERROR_LOADING_FILE:
            printErrorLoadingFile();
            break;
        case INVALID_ADD_BOOKMARK_INPUT:
            printInvalidAddBookmarkInputMessage();
            break;
        case EMPTY_DESCRIPTION:
            printEmptyBookmarkDescriptionMessage();
            break;
        case EMPTY_COMMAND:
            printEmptyCommandMessage(e.getInfo());
            break;
        case NON_INTEGER_INPUT:
            printUseIntegerAsInput();
            break;
        case BOOKMARK_NUMBER_OUT_OF_BOUNDS:
            printUseValidBookmarkNumberMessage(e.getInfo());
            break;
        case INVALID_URL:
            printInvalidUrl();
            break;
        case ERROR_LAUNCHING_URL:
            printErrorLaunchUrlMessage();
            break;
        case INVALID_COMMAND_FORMAT:
            printRed("invalid command format" + NEW_LINE);
            break;
        case INVALID_MODULE:
            printRed("module does not exist" + NEW_LINE);
            break;
        case INVALID_SLOT_NUMBER:
            printInvalidSlotNumber(e.getInfo());
            break;
        case INVALID_TIME_FORMAT:
            printInvalidTimeFormat();
            break;
        case INVALID_SLOT_INPUT:
            printInvalidSlotInput();
            break;
        case INVALID_TIMETABLE_DAY:
            printUnknownDayMessage();
            break;
        case EMPTY_TIMETABLE:
            printEmptyTimetableMessage();
            break;
        case CONNECTION_ERROR:
            printConnectionErrorMessage(e.getInfo());
            break;
        case JSON_PARSE_ERROR:
            printJsonParseErrorMessage(e.getInfo());
            break;
        default:
            // unable to get dukeExceptionType
            break;
        }
    }

    private void printInvalidSlotNumber(String index) {
        printRed("Invalid slot number. Please enter a valid index number between 1 and " + index + NEW_LINE
                + "Enter command: \"show <module>\" to view slot index" + NEW_LINE);
    }

    private void printJsonParseErrorMessage(String weblink) {
        printRed("Unable to parse modules from " + weblink + NEW_LINE
                + "The app will not check for valid modules" + NEW_LINE);
    }

    private void printConnectionErrorMessage(String weblink) {
        printRed("Unable to connect to " + weblink + NEW_LINE
                + "Please check your internet connection" + NEW_LINE
                + "The app will not check for valid modules" + NEW_LINE);
    }

    private void printErrorLoadingFile() {
        printRed("Error loading file." + NEW_LINE);
    }

    private void printErrorWritingToFile() {
        printRed("Error writing to file." + NEW_LINE);
    }

    private void printUseIntegerAsInput() {
        printRed("Command requires an integer input" + NEW_LINE);
    }


    private void printUseValidBookmarkNumberMessage(String info) {
        printRed("Please enter a valid index number between 1 and " + info + NEW_LINE);
    }

    private void printErrorLaunchUrlMessage() {
        printRed("Error launching url" + NEW_LINE);
    }

    private void printInvalidUrl() {
        printRed("Invalid URL" + NEW_LINE + "URL must start with either 'www.'"
                + " or 'https://' and have no spaces" + NEW_LINE);
    }

    private void printInvalidAddBookmarkInputMessage() {
        printRed("Invalid bookmark input" + NEW_LINE
                + "Format: add {module(optional)} {description} {URL}" + NEW_LINE);
    }

    private void printEmptyBookmarkDescriptionMessage() {
        printRed("Bookmark description required!" + NEW_LINE);
    }

    private void printEmptyCommandMessage(String info) {
        printRed("Please enter " + info + " with input!" + NEW_LINE);
    }

    public void printHelpMessage() {
        assert (Parser.programMode >= 0) && (Parser.programMode <= 2) : "only modes of Zoomaster are 0, 1, 2";
        if (Parser.programMode == 0) {
            printYellow("Available inputs in Main menu are" + NEW_LINE
                    + "1) mode {bookmark/timetable}" + NEW_LINE
                    + "2) " + ClearCommand.CLEAR_KW + NEW_LINE
                    + "3) exit" + NEW_LINE);
        } else if (Parser.programMode == 1) {
            printYellow("Available inputs in Bookmark mode are" + NEW_LINE
                    + "1) " + AddBookmarkCommand.ADD_KW + NEW_LINE
                    + "2) " + DeleteBookmarkCommand.DEL_KW + NEW_LINE
                    + "3) " + ShowBookmarkCommand.LIST_KW + NEW_LINE
                    + "4) " + FindBookmarkCommand.FIND_KW + NEW_LINE
                    + "5) " + LaunchBookmarkCommand.LAUNCH_KW + NEW_LINE
                    + "6) " + ClearCommand.CLEAR_KW + NEW_LINE
                    + "7) " + ChangeModeCommand.MODE_KW + " timetable" + NEW_LINE
                    + "8) " + ExitCommand.BYE_KW + NEW_LINE);
        } else if (Parser.programMode == 2) {
            printYellow("Available inputs in Timetable mode are" + NEW_LINE
                    + "1) " + AddSlotCommand.ADD_KW + NEW_LINE
                    + "2) " + DeleteSlotCommand.DEL_KW + NEW_LINE
                    + "3) " + ShowTimetableCommand.SHOW_KW + NEW_LINE
                    + "4) " + ChangeModeCommand.MODE_KW + " bookmark" + NEW_LINE
                    + "5) " + ClearCommand.CLEAR_KW + NEW_LINE
                    + "6) " + ExitCommand.BYE_KW + NEW_LINE);
        }
    }

    private void printUnknownModeMessage() {
        printRed("Unknown mode input" + NEW_LINE + "Valid modes: bookmark, timetable" + NEW_LINE);
    }

    private void printUnknownDayMessage() {
        printRed("Unknown day input" + NEW_LINE
              + "Valid days: monday, tuesday, wednesday, thursday, friday, saturday, sunday" + NEW_LINE);
    }

    private void printEmptyTimetableMessage() {
        printRed("Timetable is empty" + NEW_LINE);
    }

    private void printInvalidSlotInput() {
        printRed("Invalid slot input" + NEW_LINE);
    }

    private void printInvalidTimeFormat() {
        printRed("Invalid time format" + NEW_LINE);
    }
}
