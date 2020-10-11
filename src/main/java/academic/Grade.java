package academic;

/**
 * Represents a grade for a module in study-it.
 */
public class Grade {
    protected String moduleName;
    protected Integer moduleCredits;
    protected String moduleGrade;

    public Grade(String name, Integer credits, String grade) {
        this.moduleName = name;
        this.moduleCredits = credits;
        this.moduleGrade = grade;
    }

    public static Integer getModuleCredits(Grade grade) {
        return grade.moduleCredits;
    }

    public static String getModuleGrade(Grade grade) {
        return grade.moduleGrade;
    }

    public static double convertLetterToCredit(String input) {
        switch (input.trim()) {
        case "a+":
        case "a":
            return 5.0;
        case "a-":
            return 4.5;
        case "b+":
            return 4.0;
        case "b":
            return 3.5;
        case "b-":
            return 3.0;
        case "c+":
            return 2.5;
        case "c":
            return 2.0;
        case "d+":
            return 1.5;
        case "d":
            return 1.0;
        case "f":
            return 0;
        default:
            return 0;
        }

    }

}
