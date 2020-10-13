package academic;

import java.util.ArrayList;

/**
 * Represents a grade book in study-it.
 */
public class GradeBook {
    public static ArrayList<Grade> currentGrade = new ArrayList<>();//TODO change to local storage

    public static void addGrade(String[] args) {
        GradeBook.currentGrade.add(new Grade(args[0],Integer.parseInt(args[1]),args[2]));
    }

    public static String printCap() {
        double totalGradeScore = 0;
        int totalCredits = 0;
        for (Grade item : GradeBook.currentGrade) {

            totalCredits += Grade.getModuleCredits(item);
            totalGradeScore += Grade.convertLetterToCredit(Grade.getModuleGrade(item)) * Grade.getModuleCredits(item);
        }
        return "Current CAP is " + totalGradeScore / totalCredits + ".";
    }

    public static String printListOfGrades() {
        int listIndex = 0;
        StringBuilder listToPrint = new StringBuilder();
        for (Grade grade : GradeBook.currentGrade) {
            if (grade != null) {
                listToPrint.append(listIndex + 1);
                listToPrint.append(". [" + grade.moduleName + "]");
                listToPrint.append(" [" + grade.moduleCredits + "MC]");
                listToPrint.append(" [" + grade.moduleGrade + "]");
                listIndex++;
                if (GradeBook.currentGrade.size() != listIndex) {
                    listToPrint.append("\n");
                }
            }
        }
        return listToPrint.toString();
    }
}
