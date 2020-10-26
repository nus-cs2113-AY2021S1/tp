package academic;

import exceptions.InvalidCommandException;
import exceptions.InvalidEmailException;
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
                PersonBook.addPerson(AcademicCommandParser.getContact(command), listOfPerson);
                Ui.printLine("Adding Contact");
            } else if (commandType == AcademicCommandType.LIST_CONTACT) {
                Ui.printLine(PersonBook.printPersonBook(listOfPerson));
            } else if (commandType == AcademicCommandType.ADD_GRADE) {
                GradeBook.addGrade(AcademicCommandParser.getGrade(command), currentGrades);
                Ui.printLine("Adding Grade");
            } else if (commandType == AcademicCommandType.CHECK_CAP) {
                Ui.printLine(GradeBook.printCap(currentGrades));
            } else if (commandType == AcademicCommandType.LIST_GRADE) {
                Ui.printLine(GradeBook.printListOfGrades(currentGrades));
            } else if (commandType == AcademicCommandType.DELETE_PERSON) {
                PersonBook.deletePerson(AcademicCommandParser.parseDeletePerson(command),listOfPerson);
                Ui.printLine("Deleting contact");
            } else if (commandType == AcademicCommandType.DELETE_GRADE) {
                GradeBook.deleteGrade(AcademicCommandParser.parseDeleteGrade(command),currentGrades);
                Ui.printLine("Deleting grade");
            } else if (commandType == AcademicCommandType.SU_GRADE) {
                GradeBook.suGradeInGradeBook(AcademicCommandParser.parseSuGrade(command), currentGrades);
                Ui.printLine("SU-ing grade");
            } else if (commandType == AcademicCommandType.STAR_GRADE) {
                GradeBook.starGrade(AcademicCommandParser.parseStarGrade(command), currentGrades);
                Ui.printLine("Marking this grade as star");
            } else if (commandType == AcademicCommandType.STAR_CONTACT) {
                PersonBook.starContact(AcademicCommandParser.parseStarContact(command), listOfPerson);
                Ui.printLine("Marking this person as star");
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
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ErrorMessage.printInvalidNumber();
            StudyItLog.logger.info("Invalid Number.");
        } catch (InvalidGradeException e) {
            ErrorMessage.printInvalidGrade();
            StudyItLog.logger.info("Invalid Grade.");
        } catch (InvalidMcException e) {
            ErrorMessage.printInvalidMc();
            StudyItLog.logger.info("Invalid MC.");
        } catch (InvalidEmailException e) {
            ErrorMessage.printInvalidEmail();
            StudyItLog.logger.info("Invalid Email.");
        }

        try {
            AcademicStorage.writeFile(listOfPerson, currentGrades);
        } catch (IOException e) {
            System.out.println("placeholder error message");
        }
    }
}
