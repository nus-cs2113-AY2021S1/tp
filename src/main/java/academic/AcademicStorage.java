package academic;

import exceptions.InvalidEmailException;
import exceptions.RepeatedGradeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *Facilitates the storage of data on to a local storage file.
 */
public class AcademicStorage {
    private static final String filePath = "data/academic.txt";

    /**
     * Determines the existence and validity of the stored file before running the loadText command.
     * @param listOfPerson current list of person.
     * @param listOfGrades current list of grade.
     * @throws IOException when there is failed I/O operations.
     */
    public static void loadFile(ArrayList<Person> listOfPerson, ArrayList<Grade> listOfGrades) throws IOException {
        try {
            loadText(listOfPerson, listOfGrades);
        } catch (FileNotFoundException e) {
            File f = new File(filePath);
            f.createNewFile();
            System.out.println("data/academic.txt is not found, creating a new file now!");
        } catch (StringIndexOutOfBoundsException | NumberFormatException
                | InvalidEmailException | RepeatedGradeException e) {
            listOfGrades.clear();
            listOfPerson.clear();
            File f = new File(filePath);
            f.createNewFile();
            System.out.println("data/academic.txt is corrupted, your data will be reformatted.");
        }
    }

    /**
     * Reads the local storage file and update the array lists containing person and grades accordingly.
     * @param listOfPerson current list of person.
     * @param listOfGrades current list of grade.
     * @throws FileNotFoundException when a local storage file is not found.
     * @throws InvalidEmailException when email accepted is not a valid email
     * @throws RepeatedGradeException when an existing grade is added repeatedly.
     */
    private static void loadText(ArrayList<Person> listOfPerson, ArrayList<Grade> listOfGrades)
            throws FileNotFoundException, InvalidEmailException, RepeatedGradeException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String importedCommand = s.nextLine();
            if (importedCommand.startsWith("[P]")) {
                PersonBook.addPerson(AcademicCommandParser.parseImportedPerson(importedCommand),listOfPerson);
            } else if (importedCommand.startsWith("[G]")) {
                GradeBook.addGrade(AcademicCommandParser.parseImportedGrade(importedCommand),listOfGrades);
            }
        }
    }

    /**
     * Updates the local storage file based on the array lists containing person and grades accordingly.
     * @param listOfPerson current list of person.
     * @param listOfGrades current list of grade.
     * @throws IOException when there is failed I/O operations.
     */
    public static void writeFile(ArrayList<Person> listOfPerson, ArrayList<Grade> listOfGrades)
            throws IOException {

        FileWriter fw = new FileWriter(filePath);

        for (Person person : listOfPerson) {
            if (person != null) {
                fw.write(Person.printIndividualPerson(person) + System.lineSeparator());
            }
        }
        for (Grade grade : listOfGrades) {
            if (grade != null) {
                fw.write(Grade.printIndividualGrade(grade) + System.lineSeparator());
            }
        }
        fw.close();
    }
}
