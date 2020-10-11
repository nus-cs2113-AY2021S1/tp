package academic;

/**
 * Represents a grade for a module in study-it.
 */
public class Grade {
    protected String moduleName;
    protected Integer moduleCredits;
    protected String moduleGrade;

    public Grade(String name, Integer credits, String grade){
        this.moduleName = name;
        this.moduleCredits = credits;
        this.moduleGrade = grade;
    }

    public Integer getModuleCredits() {
        return moduleCredits;
    }

    public String getModuleGrade() {
        return moduleGrade;
    }

    public double convertLetterToCredit(){
        switch (this.moduleGrade){
        case "A+":
        case "A":
            return 5.0;
        case "A-":
            return 4.5;
        case "B+":
            return 4.0;
        case "B":
            return 3.5;
        case "B-":
            return 3.0;
        case "C+":
            return 2.5;
        case "C":
            return 2.0;
        case "D+":
            return 1.5;
        case "D":
            return 1.0;
        case "F":
            return 0;
        default:
            return 0;
        }

    }

}
