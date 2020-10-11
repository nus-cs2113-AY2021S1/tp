package academic;

import java.util.ArrayList;

/**
 * Represents a grade book in study-it.
 */
public class GradeBook {
    protected ArrayList<Grade> currentGrade;

    public void addGrade(Grade grade){
        this.currentGrade.add(grade);
    }

    public static String printCap(GradeBook gradeBook){
        double totalGradeScore = 0;
        int totalCredits = 0;
        for (Grade item : gradeBook.currentGrade) {
            totalCredits+= item.getModuleCredits();
            totalGradeScore += item.convertLetterToCredit()* item.getModuleCredits();
        }
        return "Current CAP is"+ totalGradeScore/totalCredits + ".";
    }
}
