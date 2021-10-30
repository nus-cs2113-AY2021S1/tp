package academic;

import exceptions.EmptyInputException;
import exceptions.RepeatedGradeException;

import java.util.ArrayList;

/**
 * Represents a grade book in study-it.
 */
public class GradeBook {

    /**
     * Adds a grade to an array list of grades.
     * @param args parameters of the grade.
     * @param currentGrades array list of grade.
     * @throws RepeatedGradeException when grade added is already present in list of grade.
     */
    public static void addGrade(String[] args, ArrayList<Grade> currentGrades)
            throws RepeatedGradeException, EmptyInputException {

        for (Grade grade : currentGrades) {

            if (args[0].equals("")) {
                throw new EmptyInputException();
            } else if (args[0].equals(grade.moduleName)) {
                throw new RepeatedGradeException();
            }
        }
        currentGrades.add(new Grade(args[0], Integer.parseInt(args[1]), args[2].toUpperCase()));
        if (args.length == 5) {
            if (args[3].equals("true")) {
                Grade.suGrade(currentGrades.get(currentGrades.size() - 1));
            }
            if (args[4].equals("true")) {
                Grade.changeStarGrade(currentGrades.get(currentGrades.size() - 1));
            }
        }
    }

    /**
     * Delete a grade from an array list of grades.
     * @param indexToBeDeleted index of the grade to be deleted.
     * @param currentGrades array list of grade.
     */
    public static void deleteGrade(Integer indexToBeDeleted, ArrayList<Grade> currentGrades) {
        currentGrades.remove(indexToBeDeleted - 1);
    }

    /**
     * Calculate current CAP and return it as a string.
     * @param currentGrades array list of grade.
     * @return string with current CAP.
     */
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
        if (totalCredits != 0) {
            return "Current CAP is " + totalGradeScore / totalCredits + ".";
        } else {
            return "Current CAP is not available!";
        }
    }

    /**
     * SU a grade.
     * @param indexToBeSued index of the grade to be SUed.
     * @param currentGrades array list of grade.
     */
    public static void suGradeInGradeBook(Integer indexToBeSued, ArrayList<Grade> currentGrades) {
        Grade.suGrade(currentGrades.get(indexToBeSued - 1));
    }

    public static void starGrade(Integer indexToBeStar, ArrayList<Grade> currentGrades) {
        if (indexToBeStar > 0 && indexToBeStar <= currentGrades.size()) {
            Grade.changeStarGrade(currentGrades.get(indexToBeStar - 1));
        } else {
            throw new NumberFormatException();
        }
    }

    public static String printListOfGrades(ArrayList<Grade> currentGrades) {
        int listIndex = 0;
        StringBuilder listToPrint = new StringBuilder();
        if (currentGrades.size() == 0) {
            listToPrint.append("You have not added any grades!");
        }
        for (Grade grade : currentGrades) {
            if (grade != null) {
                listToPrint.append(listIndex + 1 + ".");
                listToPrint.append(combineGradeDetails(grade));
                listIndex++;
                if (currentGrades.size() != listIndex) {
                    listToPrint.append("\n");
                }
            }
        }
        return listToPrint.toString();
    }

    public static String combineGradeDetails(Grade grade) {
        StringBuilder gradeDetail = new StringBuilder();

        gradeDetail.append("[" + grade.moduleName + "]");
        gradeDetail.append(" [" + grade.moduleCredits + "MC]");
        gradeDetail.append(" [" + grade.moduleGrade + "]");
        if (Grade.isGradeSued(grade)) {
            gradeDetail.append(" (This mod is SU-ed)");
        }
        if (Grade.isGradeStar(grade)) {
            gradeDetail.append(" (*)");
        }
        return gradeDetail.toString();
    }
}