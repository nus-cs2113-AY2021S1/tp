package studyit;

import academic.Grade;
import academic.GradeBook;
import academic.Person;
import academic.PersonBook;
import academic.AcademicCommandType;
import academic.AcademicStorage;
import academic.AcademicCommandParser;
import bookmark.BookmarkParser;
import bookmark.BookmarkUi;
import exceptions.InvalidCommandException;
import exceptions.InvalidGradeException;
import exceptions.InvalidMcException;
import flashcard.FlashcardRun;
import timetable.TimeTableRun;
import bookmark.BookmarkCategory;
import bookmark.commands.BookmarkCommand;
import userinterface.ErrorMessage;
import userinterface.HelpMessage;
import userinterface.Ui;

import java.io.IOException;
import java.util.ArrayList;


public class Command {
    public static int chosenCategory = 0;

    public static void executeCommand(String command, CommandType commandType,
                                      ArrayList<BookmarkCategory> bookmarkCategories, FlashcardRun flashcardRun,
                                      TimeTableRun timeTableRun, ArrayList<academic.Grade> currentGrades,
                                      ArrayList<academic.Person> listOfPerson) {
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
        } else if (StudyIt.getCurrentMode() != Mode.MENU) {
            // Run the mode specific commands if the input is none of the general command
            handleNonGeneralCommand(command, commandType, bookmarkCategories, flashcardRun, timeTableRun,
                    currentGrades, listOfPerson);
        } else {
            assert commandType == CommandType.UNIDENTIFIABLE : "This command should be unidentifiable";
            ErrorMessage.printUnidentifiableCommand();
            StudyItLog.logger.info("Cannot understand command input.");
        }
    }

    public static void handleNonGeneralCommand(String command, CommandType commandType,
                                               ArrayList<BookmarkCategory> bookmarkCategories,
                                               FlashcardRun flashcardRun, TimeTableRun timeTableRun,
                                               ArrayList<academic.Grade> currentGrades,
                                               ArrayList<academic.Person> listOfPerson) {
        Mode currentMode = StudyIt.getCurrentMode();
        if (currentMode == Mode.BOOKMARK) {
            executeBookmarkModeCommand(command, bookmarkCategories);
        } else if (currentMode == Mode.TIMETABLE) {
            executeTimetableModeCommand(command, timeTableRun);
        } else if (currentMode == Mode.ACADEMIC) {
            executeAcademicModeCommand(command, currentGrades, listOfPerson);
        } else if (currentMode == Mode.FLASHCARD) {
            executeFlashcardCommand(command, flashcardRun);
        } else {
            assert currentMode == Mode.MENU : "The current mode should be at menu";
            StudyItLog.logger.severe("Mode is not handled properly.");
        }
    }

    public static void executeBookmarkModeCommand(String command, ArrayList<BookmarkCategory> bookmarkCategories) {
        StudyItLog.logger.info("Processing bookmark mode.");
        BookmarkUi bookmarkUi = new BookmarkUi();
        BookmarkParser bookmarkParser = new BookmarkParser();
        try {
            BookmarkCommand c = bookmarkParser.evaluateInput(command,chosenCategory);
            c.executeCommand(bookmarkUi,bookmarkCategories);
            chosenCategory = c.getCategoryNumber();
        } catch (InvalidCommandException e) {
            bookmarkUi.showInvalidBookmarkCommand();
            StudyItLog.logger.info("Cannot understand bookmark command");
        }
    }

    public static void executeTimetableModeCommand(String command, TimeTableRun timeTableRun) {
        StudyItLog.logger.info("Processing timetable mode.");
        timeTableRun.run(command);
    }

    public static void executeAcademicModeCommand(String command, ArrayList<academic.Grade> currentGrades,
                                                  ArrayList<academic.Person> listOfPerson) {
        StudyItLog.logger.info("Processing academic mode.");


        try {
            AcademicCommandType commandType = AcademicCommandParser.getAcademicCommandType(command);

            if (commandType == AcademicCommandType.ADD_CONTACT) {
                Ui.printLine("Adding Contact"); //TODO: Remove placeholder line.
                PersonBook.addPerson(AcademicCommandParser.getContact(command), listOfPerson);

            } else if (commandType == AcademicCommandType.CHECK_CONTACT) {
                Ui.printLine("Checking Contact"); //TODO: Remove placeholder line.
                Ui.printLine(PersonBook.printPersonBook(listOfPerson));

            } else if (commandType == AcademicCommandType.ADD_GRADE) {
                Ui.printLine("Adding Grade"); //TODO: Remove placeholder line.
                GradeBook.addGrade(AcademicCommandParser.getGrade(command), currentGrades);

            } else if (commandType == AcademicCommandType.CHECK_GRADE) {
                Ui.printLine("Checking Grade"); //TODO: Remove placeholder line.
                Ui.printLine(GradeBook.printCap(currentGrades));

            } else if (commandType == AcademicCommandType.LIST_GRADE) {
                Ui.printLine("Listing Grade"); //TODO: Remove placeholder line.
                Ui.printLine(GradeBook.printListOfGrades(currentGrades));

            } else {
                StudyItLog.logger.severe("Invalid command type, check studyit.Command Parser");
            }
        } catch (InvalidCommandException e) {
            ErrorMessage.printUnidentifiableCommand();
            StudyItLog.logger.info("Invalid academic command.");
        } catch (StringIndexOutOfBoundsException e) {
            ErrorMessage.printUnidentifiableInput();
            StudyItLog.logger.info("Invalid academic command. Sting Index out of bounds.");
        } catch (NumberFormatException e) {
            ErrorMessage.printInvalidNumber();
            StudyItLog.logger.info("Invalid Number.");
        } catch (InvalidGradeException e) {
            ErrorMessage.printInvalidGrade();
            StudyItLog.logger.info("Invalid Grade.");
        } catch (InvalidMcException e) {
            ErrorMessage.printInvalidMc();
            StudyItLog.logger.info("Invalid MC.");
        }

        try {
            AcademicStorage.writeFile(listOfPerson, currentGrades);
        } catch (IOException e) {
            System.out.println("placeholder error message");
        }

    }

    public static void executeFlashcardCommand(String command, FlashcardRun flashcardRun) {
        StudyItLog.logger.info("Processing flashcard mode.");
        flashcardRun.run(command);
    }
}