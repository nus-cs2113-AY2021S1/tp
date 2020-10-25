package academic;

import userinterface.Ui;
import java.util.ArrayList;

public class AcademicUi extends Ui {
    public static void printStarList(ArrayList<Grade> currentGrades, ArrayList<Person> listOfPerson) {
        int listIndex = 1;

        System.out.println("Starred grades:");
        for (Grade grade : currentGrades) {
            if (grade.isStar) {
                System.out.println(listIndex + "." + GradeBook.combineGradeDetails(grade));
                listIndex++;
            }
        }

        System.out.println("\nStarred contacts:");
        listIndex = 1;
        for (Person person : listOfPerson) {
            if (person.isStar) {
                System.out.println(listIndex + "." + PersonBook.combinePersonDetails(person));
                listIndex++;
            }
        }
    }
}
