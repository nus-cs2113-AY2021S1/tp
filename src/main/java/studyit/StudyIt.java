package studyit;

import academic.AcademicRun;
import timetable.TimeTableRun;
import flashcard.FlashcardRun;
import userinterface.MainMenu;
import userinterface.Ui;
import bookmark.BookmarkRun;



public class StudyIt {
    private static Mode currentMode = Mode.MENU;
    private BookmarkRun bookmarkRun;
    private TimeTableRun timeTableRun;
    private FlashcardRun flashcardRun;
    private AcademicRun academicRun;

    public static void changeMode(Mode destinationMode) {
        currentMode = destinationMode;
    }

    public static Mode getCurrentMode() {
        return currentMode;
    }

    public StudyIt() {
        StudyItLog.setUpLogger();
        StudyItLog.logger.info("Initializing program");
        bookmarkRun = new BookmarkRun();
        timeTableRun = new TimeTableRun();
        flashcardRun = new FlashcardRun();
        academicRun = new AcademicRun();
        StudyItLog.logger.info("Program initialized");
    }

    public static void main(String[] args) {
        MainMenu.printWelcome();
        new StudyIt().run();
    }

    public void run() {
        CommandType commandType;
        StudyItLog.logger.info("Executing program");
        // Repeatedly receive & process user command until "exit" is given
        do {
            // Collect user's command & identify the type
            String command = Ui.inputCommand();
            commandType = CommandParser.getCommandType(command);

            StudyItLog.logger.info("Command received: " + command);
            StudyItLog.logger.info("Command type identified: " + commandType);

            Command.executeCommand(command, commandType, bookmarkRun, flashcardRun,
                    timeTableRun, academicRun);
        } while (commandType != CommandType.EXIT_PROGRAM);

        StudyItLog.logger.info("End of program.");
    }
}