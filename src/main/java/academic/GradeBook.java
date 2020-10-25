package academic;

import java.util.ArrayList;

/**
 * Represents a grade book in study-it.
 */
public class GradeBook {
    public static void addGrade(String[] args, ArrayList<Grade> currentGrades) {
        currentGrades.add(new Grade(args[0], Integer.parseInt(args[1]), args[2]));
        if (args.length == 4) {
            if (args[3].equals("true")) {
                Grade.suGrade(currentGrades.get(currentGrades.size() - 1));
            }
        }
    }

    public static void deleteGrade(Integer indexToBeDeleted, ArrayList<Grade> currentGrades) {
        currentGrades.remove(indexToBeDeleted - 1);
    }

    public static String printCap(ArrayList<Grade> currentGrades) {
        double totalGradeScore = 0;
        int totalCredits = 0;
        for (Grade grade : currentGrades) {
            if (!Grade.isGradeSued(grade)) {
                totalCredits += Grade.getModuleCredits(grade);
                totalGradeScore += Grade.convertLetterToCredit(Grade.getModuleGrade(grade))
                        * Grade.getModuleCredits(grade);
            }
        }
        assert totalCredits != 0;
        return "Current CAP is " + totalGradeScore / totalCredits + ".";
    }

    public static void suGradeInGradeBook(Integer indexToBeSued, ArrayList<Grade> currentGrades) {
        Grade.suGrade(currentGrades.get(indexToBeSued - 1));
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
                if (Grade.isGradeSued(grade)) {
                    listToPrint.append(" (This mod is SUed)");
                }
                listIndex++;
                if (currentGrades.size() != listIndex) {
                    listToPrint.append("\n");
                }
            }
        }
        return listToPrint.toString();
    }
}
