package academic;

import userinterface.Ui;
import java.util.ArrayList;

/**
 * Text UI for academic mode.
 */
public class AcademicUi extends Ui {
    public static void printStarList(ArrayList<Grade> currentGrades, ArrayList<Person> listOfPerson) {
        int listIndex = 1;
        Boolean listIsEmpty = true;

        System.out.println("Starred grades:");
        for (Grade grade : currentGrades) {
            if (grade.isStar) {
                System.out.println(listIndex + "." + GradeBook.combineGradeDetails(grade));
                listIsEmpty = false;
                listIndex++;
            }
        }
        printEmpty(listIsEmpty);

        // Reset the boolean flag
        listIsEmpty = true;

        System.out.println("\nStarred contacts:");
        listIndex = 1;
        for (Person person : listOfPerson) {
            if (person.isStar) {
                System.out.println(listIndex + "." + PersonBook.combinePersonDetails(person));
                listIsEmpty = false;
                listIndex++;
            }
        }
        printEmpty(listIsEmpty);
        printDivider();
    }

    public static void printEmpty(Boolean isEmpty) {
        if (isEmpty) {
            System.out.println("<empty>");
        }
    }
}
