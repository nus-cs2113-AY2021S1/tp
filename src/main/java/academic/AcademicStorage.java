package academic;

import exceptions.InvalidEmailException;
import timetable.DateList;
import userinterface.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class AcademicStorage {

    private static final String filePath = "data/academic.txt";

    public static void loadFile(ArrayList<Person> listOfPerson, ArrayList<Grade> listOfGrades) throws IOException {
        try {
            loadText(listOfPerson,listOfGrades);
        } catch (FileNotFoundException | InvalidEmailException e) {
            File f = new File(filePath);
            f.createNewFile();
            System.out.println("New file created for academic helper.");
        }
    }

    private static void loadText(ArrayList<Person> listOfPerson, ArrayList<Grade> listOfGrades)
            throws FileNotFoundException, InvalidEmailException {
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
