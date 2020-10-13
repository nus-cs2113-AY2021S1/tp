package academic;

import java.util.ArrayList;

/**
 * Represents a grade book in study-it.
 */
public class GradeBook {
    public static ArrayList<Grade> currentGrade = new ArrayList<>();

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
}
