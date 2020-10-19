package studyit;

import academic.Grade;
import academic.Person;
import log.StudyItLog;
import timetable.TimeTableRun;
import flashcard.FlashcardRun;
<<<<<<< HEAD:src/main/java/StudyIt.java
=======
import bookmark.BookmarkCategory;
import bookmark.NusCategory;
import bookmark.ZoomCategory;
import userinterface.MainMenu;
import userinterface.Ui;

>>>>>>> master:src/main/java/studyit/StudyIt.java
import java.util.ArrayList;
import bookmark.BookmarkRun;

public class StudyIt {
    private static Mode currentMode = Mode.MENU;

    public static void changeMode(Mode destinationMode) {
        currentMode = destinationMode;
    }

    public static Mode getCurrentMode() {
        return currentMode;
    }

    public static BookmarkRun bookmarkRun = new BookmarkRun();
    public static TimeTableRun timeTableRun = new TimeTableRun();
    public static FlashcardRun flashcardRun = new FlashcardRun();
    public static ArrayList<Grade> currentGrades = new ArrayList<>();//TODO change to local storage
    public static ArrayList<Person> listOfPerson = new ArrayList<>(); //TODO change to local storage


    public StudyIt() {
        StudyItLog.setUpLogger();
    }

    public static void main(String[] args) {
        //assert false : "dummy assertion";
        MainMenu.printWelcome();
        new StudyIt().run();
        StudyItLog.logger.info("Starting process");

    }

    public void run() {
        CommandType commandType;
        StudyItLog.logger.info("Executing program");
        // Repeatedly receive & process user command until "exit" is given
        do {
            // Collect user's command & identify the type
            String command = Ui.inputCommand();
            commandType = CommandParser.getCommandType(command);
            Command.executeCommand(command, commandType, bookmarkRun, flashcardRun,
                    timeTableRun, currentGrades, listOfPerson);

        } while (commandType != CommandType.EXIT_PROGRAM);

        StudyItLog.logger.info("End of program.");
    }
}