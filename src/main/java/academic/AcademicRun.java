package academic;

import exceptions.InvalidCommandException;
import exceptions.InvalidGradeException;
import exceptions.InvalidMcException;
import studyit.StudyItLog;
import userinterface.ErrorMessage;
import userinterface.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class AcademicRun {
    private ArrayList<Grade> currentGrades;
    private ArrayList<Person> listOfPerson;

    public AcademicRun() {
        currentGrades = new ArrayList<>();
        listOfPerson = new ArrayList<>();

        try {
            AcademicStorage.loadFile(listOfPerson, currentGrades);
        } catch (IOException e) {
            System.out.println("placeholder error message");
        }
    }

    public void run(String command) {
        try {
            AcademicCommandType commandType = AcademicCommandParser.getAcademicCommandType(command);

            if (commandType == AcademicCommandType.ADD_CONTACT) {
                Ui.printLine("Adding Contact");
                PersonBook.addPerson(AcademicCommandParser.getContact(command), listOfPerson);

            } else if (commandType == AcademicCommandType.CHECK_CONTACT) {
                Ui.printLine(PersonBook.printPersonBook(listOfPerson));

            } else if (commandType == AcademicCommandType.ADD_GRADE) {
                Ui.printLine("Adding Grade");
                GradeBook.addGrade(AcademicCommandParser.getGrade(command), currentGrades);

            } else if (commandType == AcademicCommandType.CHECK_GRADE) {
                Ui.printLine(GradeBook.printCap(currentGrades));

            } else if (commandType == AcademicCommandType.LIST_GRADE) {
                Ui.printLine(GradeBook.printListOfGrades(currentGrades));

            } else if (commandType == AcademicCommandType.DELETE_PERSON) {
                Ui.printLine("Deleting contact");
                PersonBook.deletePerson(AcademicCommandParser.parseDeletePerson(command),listOfPerson);

            } else if (commandType == AcademicCommandType.DELETE_GRADE) {
                Ui.printLine("Deleting grade");
                GradeBook.deleteGrade(AcademicCommandParser.parseDeleteGrade(command),currentGrades);

            } else if (commandType == AcademicCommandType.SU_GRADE) {
                Ui.printLine("SU-ing grade");
                GradeBook.suGradeInGradeBook(AcademicCommandParser.parseSuGrade(command), currentGrades);

            } else if (commandType == AcademicCommandType.STAR_GRADE) {
                Ui.printLine("Marking this grade as star");
                GradeBook.starGrade(AcademicCommandParser.parseStarGrade(command), currentGrades);
            } else if (commandType == AcademicCommandType.STAR_CONTACT) {
                Ui.printLine("Marking this person as star");
                PersonBook.starContact(AcademicCommandParser.parseStarContact(command), listOfPerson);
            } else if (commandType == AcademicCommandType.LIST_STAR) {
                AcademicUi.printStarList(currentGrades, listOfPerson);
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
}
