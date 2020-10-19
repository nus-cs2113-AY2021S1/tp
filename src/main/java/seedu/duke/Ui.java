package seedu.duke;

import seedu.duke.command.ChangeModeCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.bookmark.AddBookmarkCommand;
import seedu.duke.command.bookmark.DeleteBookmarkCommand;
import seedu.duke.command.bookmark.FindBookmarkCommand;
import seedu.duke.command.bookmark.LaunchBookmarkCommand;
import seedu.duke.command.bookmark.ShowBookmarkCommand;
import seedu.duke.command.timetable.AddSlotCommand;
import seedu.duke.command.timetable.DeleteSlotCommand;
import seedu.duke.command.timetable.ShowTimetableCommand;
import seedu.duke.exception.DukeException;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.Slot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.WHITE;
import static org.fusesource.jansi.Ansi.Color.BLACK;

import static org.fusesource.jansi.Ansi.Color.CYAN;
import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.YELLOW;





/**
 * Represents the user interface on the command line and deals with interactions with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________\n";
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

    /**
     * Prints a message after starting the program.
     */
    public void showWelcomeScreen() {
        System.out.println(ansi().eraseScreen());
        System.out.println(LINE);
        System.out.println("HELLO FROM:");

        System.out.println(ansi().bg(WHITE));
        System.out.println(ansi().fg(CYAN).a(logo2).reset());

        System.out.println(ansi().bg(BLACK));
        System.out.println(LINE);
    }

    /**
     * Prints a message before exiting the program.
     */
    public void showExitScreen() {
        String message = "Bye. Hope to see you again soon!\n";
        print(message);
        AnsiConsole.systemUninstall();
    }

    /**
     * Prints the error message if data file is not found.
     */
    public void showLoadingError() {
        String message = "Data file not found\n";
        printRed(message);
    }

    /**
     * This method detects the type of dukeException error and prints the corresponding error message.
     *
     * @param dukeException The dukeException error.
     */
    public void showErrorMessage(DukeException dukeException) {
        switch (dukeException.getError()) {
        case UNKNOWN_INPUT:
            printUnknownInputMessage();
            break;
        case INVALID_MODE:
            printUnknownModeMessage();
            break;
        case WRITE_FILE_ERROR:
            printErrorWritingToFile();
            break;
        case INVALID_ADD_BOOKMARK_INPUT:
            printInvalidAddBookmarkInputMessage();
            break;
        case EMPTY_DESCRIPTION:
            printEmptyBookmarkDescriptionMessage();
            break;
        case EMPTY_COMMAND:
            printEmptyCommandMessage(dukeException.getInfo());
            break;
        case NON_INTEGER_INPUT:
            printUseIntegerAsInput();
            break;
        case BOOKMARK_NUMBER_OUT_OF_BOUNDS:
            printUseValidBookmarkNumberMessage(dukeException.getInfo());
            break;
        case INVALID_URL:
            printInvalidUrl();
            break;
        case ERROR_LAUNCHING_URL:
            printErrorLaunchUrlMessage();
            break;
        case INVALID_COMMAND_FORMAT:
            print("invalid command format\n");
            break;
        case INVALID_MODULE:
            print("module does not exist\n");
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
        default:
            // unable to get dukeExceptionType
            break;
        }
    }

    private void printErrorWritingToFile() {
        printRed("Error writing to file.\n");
    }

    private void printUseIntegerAsInput() {
        printRed("Command requires an integer input\n");
    }


    private void printUseValidBookmarkNumberMessage(String info) {
        printRed("Please enter a valid index number between 1 and " + info + "\n");
    }

    private void printErrorLaunchUrlMessage() {
        printRed("Error launching url\n");
    }

    private void printInvalidUrl() {
        printRed("Invalid URL" + "\n" + "URL must start with either 'www.'"
                + " or 'https://' and have no spaces\n");
    }

    private void printInvalidAddBookmarkInputMessage() {
        printRed("Invalid bookmark input\n"
                + "Format: add {module(optional)} {description} {URL}\n");
    }

    private void printEmptyBookmarkDescriptionMessage() {
        printRed("Bookmark description required!\n");
    }

    private void printEmptyCommandMessage(String info) {
        printRed("Please enter " + info + " with input!\n");
    }

    private void printUnknownInputMessage() {
        if (Parser.programMode == 0) {
            print("Unknown input\n" + "Available inputs in Main menu are\n"
                    + "1) mode {bookmark/timetable}\n"
                    + "2) exit\n");
        } else if (Parser.programMode == 1) {
            print("Unknown input\n" + "Available inputs in Bookmark mode are\n"
                    + "1) " + AddBookmarkCommand.ADD_KW + "\n"
                    + "2) " + DeleteBookmarkCommand.DEL_KW + "\n"
                    + "3) " + ShowBookmarkCommand.LIST_KW + "\n"
                    + "4) " + FindBookmarkCommand.FIND_KW + "\n"
                    + "5) " + LaunchBookmarkCommand.LAUNCH_KW + "\n"
                    + "6) " + ChangeModeCommand.MODE_KW + " timetable\n"
                    + "7) " + ExitCommand.BYE_KW + "\n");
        } else if (Parser.programMode == 2) {
            print("Unknown input\n" + "Available inputs in Timetable mode are\n"
                    + "1) " + AddSlotCommand.ADD_KW + "\n"
                    + "2) " + DeleteSlotCommand.DEL_KW + "\n"
                    + "3) " + ShowTimetableCommand.SHOW_KW + "\n"
                    + "4) " + ChangeModeCommand.MODE_KW + " bookmark\n"
                    + "5) " + ExitCommand.BYE_KW + "\n");
        }
    }

    private void printUnknownModeMessage() {
        printRed("Unknown mode input\n" + "Valid modes: bookmark, timetable\n");
    }

    private void printUnknownDayMessage() {
        printRed("Unknown day input\n"
              + "Valid days: monday, tuesday, wednesday, thursday, friday, saturday, sunday\n");
    }

    private void printEmptyTimetableMessage() {
        printRed("Timetable is empty\n");
    }

    private void printInvalidSlotInput() {
        printRed("Invalid slot input\n");
    }

    private void printInvalidTimeFormat() {
        printRed("Invalid time format\n");
    }

}
