import bookmark.BookmarkUi;
import exceptions.InvalidCommandException;
import exceptions.InvalidGradeException;
import exceptions.InvalidMcException;
import flashcard.FlashcardRun;
import academic.GradeBook;
import academic.PersonBook;
import timetable.TimeTableRun;
import bookmark.BookmarkCategory;
import bookmark.commands.BookmarkCommand;
import java.util.ArrayList;


public class Command {

    public static void executeCommand(String command, CommandType commandType,
                                      ArrayList<BookmarkCategory> bookmarkCategories, FlashcardRun flashcardRun,
                                      TimeTableRun timeTableRun, ArrayList<academic.Grade> currentGrades,
                                      ArrayList<academic.Person> listOfPerson) {
        if (commandType == CommandType.EXIT_PROGRAM) {
            Ui.printExit();
        } else if (commandType == CommandType.EXIT_MODE) {
            Ui.exitMode();
            BookmarkParser.resetBookmarkCategory();
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
            ErrorMessage.printUnidentifiableCommand();
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
        }
    }

    public static void executeBookmarkModeCommand(String command, ArrayList<BookmarkCategory> bookmarkCategories) {
        BookmarkUi bookmarkUi = new BookmarkUi();
        BookmarkParser bookmarkParser = new BookmarkParser();
        try {
            BookmarkCommand c = bookmarkParser.evaluateInput(command);
            c.executeCommand(bookmarkUi,bookmarkCategories);
        } catch (InvalidCommandException e) {
            bookmarkUi.showInvalidBookmarkCommand();
        }
    }

    public static void executeTimetableModeCommand(String command, TimeTableRun timeTableRun) {
        timeTableRun.run(command);
    }

    public static void executeAcademicModeCommand(String command, ArrayList<academic.Grade> currentGrades,
                                                  ArrayList<academic.Person> listOfPerson) {
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

            }
        } catch (InvalidCommandException e) {
            ErrorMessage.printUnidentifiableCommand();
        } catch (StringIndexOutOfBoundsException e) {
            ErrorMessage.printUnidentifiableInput();
        } catch (NumberFormatException e) {
            ErrorMessage.printInvalidNumber();
        } catch (InvalidGradeException e) {
            ErrorMessage.printInvalidGrade();
        } catch (InvalidMcException e) {
            ErrorMessage.printInvalidMc();
        }
    }

    public static void executeFlashcardCommand(String command, FlashcardRun flashcardRun) {
        flashcardRun.run(command);
    }
}