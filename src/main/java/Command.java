import academic.GradeBook;
import academic.PersonBook;
import exceptions.InvalidCommandException;
import exceptions.InvalidGradeException;
import exceptions.InvalidMcException;
import exceptions.InvalidModeException;
import timetable.TimeTableRun;

public class Command {
    public static void executeCommand(String command, CommandType commandType, TimeTableRun timeTableRun) {
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
            handleNonGeneralCommand(command, commandType, timeTableRun);
        } else {
            ErrorMessage.printUnidentifiableCommand();
        }
    }

    public static void handleNonGeneralCommand(String command, CommandType commandType, TimeTableRun timeTableRun) {
        Mode currentMode = StudyIt.getCurrentMode();
        if (currentMode == Mode.BOOKMARK) {
            executeBookmarkModeCommand();
        } else if (currentMode == Mode.TIMETABLE) {
            executeTimetableModeCommand(command, timeTableRun);
        } else if (currentMode == Mode.ACADEMIC) {
            executeAcademicModeCommand(command);
        } else if (currentMode == Mode.FLASHCARD) {
            executeFlashcardCommand();
        }
    }

    public static void executeBookmarkModeCommand() {

    }

    public static void executeTimetableModeCommand(String command, TimeTableRun timeTableRun) {
        timeTableRun.run(command);
    }

    public static void executeAcademicModeCommand(String command) {
        try {
            AcademicCommandType commandType = AcademicCommandParser.getAcademicCommandType(command);

            if (commandType == AcademicCommandType.ADD_CONTACT) {
                Ui.printLine("Adding Contact"); //TODO: Remove placeholder line.
                PersonBook.addPerson(AcademicCommandParser.getContact(command));

            } else if (commandType == AcademicCommandType.CHECK_CONTACT) {
                Ui.printLine("Checking Contact"); //TODO: Remove placeholder line.
                Ui.printLine(PersonBook.printPersonBook());

            } else if (commandType == AcademicCommandType.ADD_GRADE) {
                Ui.printLine("Adding Grade"); //TODO: Remove placeholder line.
                GradeBook.addGrade(AcademicCommandParser.getGrade(command));

            } else if (commandType == AcademicCommandType.CHECK_GRADE) {
                Ui.printLine("Checking Grade"); //TODO: Remove placeholder line.
                Ui.printLine(GradeBook.printCap());

            } else if (commandType == AcademicCommandType.LIST_GRADE) {
                Ui.printLine("Listing Grade"); //TODO: Remove placeholder line.
                Ui.printLine(GradeBook.printListOfGrades());

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

    public static void executeFlashcardCommand() {

    }
}