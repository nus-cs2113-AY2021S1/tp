package studyit;

import academic.AcademicStorage;
import academic.Grade;
import academic.Person;
import timetable.TimeTableRun;
import flashcard.FlashcardRun;
import bookmark.BookmarkCategory;
import bookmark.NusCategory;
import bookmark.ZoomCategory;
import userinterface.MainMenu;
import userinterface.Ui;

import java.io.IOException;
import java.util.ArrayList;


public class StudyIt {
    public static ArrayList<BookmarkCategory> bookmarkCategories = new ArrayList<>();
    private static Mode currentMode = Mode.MENU;

    public static void changeMode(Mode destinationMode) {
        currentMode = destinationMode;
    }

    public static Mode getCurrentMode() {
        return currentMode;
    }

    public static TimeTableRun timeTableRun = new TimeTableRun();
    public static FlashcardRun flashcardRun = new FlashcardRun();
    public static ArrayList<Grade> currentGrades = new ArrayList<>();//TODO change to local storage
    public static ArrayList<Person> listOfPerson = new ArrayList<>(); //TODO change to local storage


    public StudyIt() {
        bookmarkCategories.add(new NusCategory());
        bookmarkCategories.add(new ZoomCategory());
        StudyItLog.setUpLogger();
    }

    public static void main(String[] args) {

        try { //TODO Yuanbing please help me shift this
            AcademicStorage.loadFile(listOfPerson, currentGrades);
        } catch (IOException e) {
            System.out.println("placeholder error message");
        }

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
            Command.executeCommand(command, commandType, bookmarkCategories, flashcardRun,
                    timeTableRun, currentGrades, listOfPerson);

        } while (commandType != CommandType.EXIT_PROGRAM);

        StudyItLog.logger.info("End of program.");
    }
}