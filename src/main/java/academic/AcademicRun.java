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
            System.out.println("Problem loading academic storage file");
            StudyItLog.logger.warning("Problem loading academic storage file\n" + e);
        }
        StudyItLog.logger.info("Academic mode initialized");
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
            }
        } catch (InvalidCommandException e) {
            ErrorMessage.printUnidentifiableCommand();
            StudyItLog.logger.warning("Invalid academic command: Command unidentifiable");
        } catch (StringIndexOutOfBoundsException e) {
            ErrorMessage.printUnidentifiableInput();
            StudyItLog.logger.warning("Invalid academic command: String Index out of bounds.");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ErrorMessage.printInvalidNumber();
            StudyItLog.logger.warning("Invalid academic command: Invalid Number");
        } catch (InvalidGradeException e) {
            ErrorMessage.printInvalidGrade();
            StudyItLog.logger.warning("Invalid academic command: Invalid Grade");
        } catch (InvalidMcException e) {
            ErrorMessage.printInvalidMc();
            StudyItLog.logger.warning("Invalid academic command: Invalid MC");
        } catch (InvalidEmailException e) {
            ErrorMessage.printInvalidEmail();
            StudyItLog.logger.warning("Invalid academic command: Invalid Email");
        }

        try {
            AcademicStorage.writeFile(listOfPerson, currentGrades);
        } catch (IOException e) {
            System.out.println("Problem writing to academic storage file");
            StudyItLog.logger.warning("Problem writing to academic storage file\n" + e);
        }
    }
}