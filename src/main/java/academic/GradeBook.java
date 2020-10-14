package academic;

import java.util.ArrayList;

/**
 * Represents a grade book in study-it.
 */
public class GradeBook {
    public static void addGrade(String[] args, ArrayList<Grade> currentGrades) {
        currentGrades.add(new Grade(args[0],Integer.parseInt(args[1]),args[2]));
    }

    public static String printCap(ArrayList<Grade> currentGrades) {
        double totalGradeScore = 0;
        int totalCredits = 0;
        for (Grade item : currentGrades) {

            totalCredits += Grade.getModuleCredits(item);
            totalGradeScore += Grade.convertLetterToCredit(Grade.getModuleGrade(item)) * Grade.getModuleCredits(item);
        }
        return "Current CAP is " + totalGradeScore / totalCredits + ".";
    }

    public static String printListOfGrades(ArrayList<Grade> currentGrades) {
        int listIndex = 0;
        StringBuilder listToPrint = new StringBuilder();
        for (Grade grade : currentGrades) {
            if (grade != null) {
                listToPrint.append(listIndex + 1);
                listToPrint.append(". [" + grade.moduleName + "]");
                listToPrint.append(" [" + grade.moduleCredits + "MC]");
                listToPrint.append(" [" + grade.moduleGrade + "]");
                listIndex++;
                if (currentGrades.size() != listIndex) {
                    listToPrint.append("\n");
                }
            }
        }
        return listToPrint.toString();
    }
}
