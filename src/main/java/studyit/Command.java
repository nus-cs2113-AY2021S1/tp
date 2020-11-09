package studyit;

import flashcard.FlashcardRun;
import timetable.TimeTableRun;
import userinterface.ErrorMessage;
import userinterface.HelpMessage;
import userinterface.Ui;
import bookmark.BookmarkRun;
import academic.AcademicRun;


public class Command {

    /**
     * Handles the user input command.
     * It executes the command if it is a general command. Otherwise, it will
     * pass the command to each respective mode to handle under handleNonGeneralCommand()
     *
     * @param command Raw string input of the user command
     * @param commandType Identifies which command type it is (passed in by command parser)
     * @param bookmarkRun Main component for bookmark mode
     * @param flashcardRun Main component for flashcard mode
     * @param timeTableRun Main component for timetable mode
     * @param academicRun Main component for academic mode
     */
    public static void executeCommand(String command, CommandType commandType,
                                      BookmarkRun bookmarkRun, FlashcardRun flashcardRun,
                                      TimeTableRun timeTableRun, AcademicRun academicRun) {
        if (commandType == CommandType.EXIT_PROGRAM) {
            Ui.printExit();
        } else if (commandType == CommandType.EXIT_MODE) {
            Ui.exitMode();
        } else if (commandType == CommandType.LOCATION) {
            Ui.printLocation();
        } else if (commandType == CommandType.CHANGE_MODE) {
            Ui.changeModeCommand(command);
        } else if (commandType == CommandType.HELP) {
            HelpMessage.printHelpMessage();
        } else if (commandType == CommandType.HIGHLIGHT) {
            Ui.printHighlight(bookmarkRun, academicRun);
        } else if (StudyIt.getCurrentMode() != Mode.MENU) {
            // Run the mode specific commands if the input is none of the general command
            handleNonGeneralCommand(command, commandType, bookmarkRun, flashcardRun, timeTableRun,
                    academicRun);
        } else {
            assert commandType == CommandType.UNIDENTIFIABLE : "This command should be unidentifiable";
            ErrorMessage.printUnidentifiableCommand();
        }
    }

    /**
     * Handles the non-general command such as commands under different modes.
     *
     * @param command raw string of the user input
     * @param commandType identifies the command type
     * @param bookmarkRun Main component for bookmark mode
     * @param flashcardRun Main component for flashcard mode
     * @param timeTableRun Main component for timetable mode
     * @param academicRun Main component for academic mode
     */
    public static void handleNonGeneralCommand(String command, CommandType commandType,
                                               BookmarkRun bookmarkRun,
                                               FlashcardRun flashcardRun, TimeTableRun timeTableRun,
                                               AcademicRun academicRun) {
        Mode currentMode = StudyIt.getCurrentMode();
        if (currentMode == Mode.BOOKMARK) {
            executeBookmarkModeCommand(command, bookmarkRun);
        } else if (currentMode == Mode.TIMETABLE) {
            executeTimetableModeCommand(command, timeTableRun);
        } else if (currentMode == Mode.ACADEMIC) {
            executeAcademicModeCommand(command, academicRun);
        } else if (currentMode == Mode.FLASHCARD) {
            executeFlashcardCommand(command, flashcardRun);
        } else {
            assert currentMode == Mode.MENU : "The current mode should be at menu";
            StudyItLog.logger.severe("Mode is not handled properly.");
        }
    }

    public static void executeBookmarkModeCommand(String command, BookmarkRun bookmarkRun) {
        StudyItLog.logger.info("Processing bookmark command: " + command);
        Ui.printDivider();
        bookmarkRun.run(command);
        Ui.printDivider();
    }

    public static void executeTimetableModeCommand(String command, TimeTableRun timeTableRun) {
        StudyItLog.logger.info("Processing timetable command: " + command);
        Ui.printDivider();
        timeTableRun.run(command);
        Ui.printDivider();
    }

    public static void executeAcademicModeCommand(String command, AcademicRun academicRun) {
        StudyItLog.logger.info("Processing academic command: " + command);
        academicRun.run(command);
    }

    public static void executeFlashcardCommand(String command, FlashcardRun flashcardRun) {
        StudyItLog.logger.info("Processing flashcard command: " + command);
        flashcardRun.run(command);
    }
}