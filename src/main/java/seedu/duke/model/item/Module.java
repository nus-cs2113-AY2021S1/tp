package seedu.duke.model.item;

import seedu.duke.DukeException;
import seedu.duke.common.Messages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// @@author iamchenjiajun

/**
 * Represents a module.
 */
public class Module extends Item {
    public static final Pattern MODULE_CODE_PATTERN = Pattern.compile("(^[A-Z]{2,3}[\\d]{4}[A-Z]?$)");
    public static final Pattern MODULE_SEM_PATTERN = Pattern.compile("(^[\\d]{4}S[12]$)");
    public static final String MODULE_COMPLETED_STRING = "[CM]";
    public static final String MODULE_INCOMPLETE_STRING = "[IC]";
    public static final String MODULE_PRINT_FORMAT = "%s[%s] %s (%d MC) (AY%s)";
    public static final String LAST_VALID_AY = "9900";
    public static final int AY_STRING_LENGTH = 6;
    public static final int YEAR_ONE_START_INDEX = 0;
    public static final int YEAR_ONE_END_INDEX = 2;
    public static final int YEAR_TWO_END_INDEX = 4;
    public static final int ALLOWED_YEAR_DIFFERENCE = 1;
    public static final int MIN_ALLOWED_MCS = 0;
    public static final int MAX_ALLOWED_MCS = 40;

    private final String grade;
    private final double gradePoint;
    private final int mc;
    private final String semester;

    /**
     * Constructor used when adding a new task.
     * By default, the deadline task is not done.
     *
     * @param moduleCode the description of the task
     */
    public Module(String moduleCode, String grade, int mc, String semester, boolean isDone) throws DukeException {
        super(moduleCode);

        this.grade = grade;
        this.mc = mc;
        this.semester = semester;
        gradePoint = getCapFromGrade(grade);
        this.isDone = isDone;

        Matcher matcher = MODULE_CODE_PATTERN.matcher(moduleCode);

        if (!matcher.find() || !checkValidAy(semester)) {
            throw new DukeException(Messages.EXCEPTION_INVALID_FORMAT);
        }

        if (!checkValidMcs(mc)) {
            throw new DukeException(Messages.EXCEPTION_INVALID_MCS);
        }
    }

    @Override
    public String toString() {
        return String.format(MODULE_PRINT_FORMAT, getCompletionString(), getGrade(), getDescription(), getMc(),
                getSemester());
    }

    /**
     * Checks if the number of MCs is in a valid range.
     *
     * @param mc Number of MCs.
     * @return Boolean corresponding to the conditions.
     */
    public static boolean checkValidMcs(int mc) {
        return mc >= MIN_ALLOWED_MCS && mc <= MAX_ALLOWED_MCS;
    }

    /**
     * Checks if the Academic Year is valid.
     *
     * @param ay Academic Year.
     * @return True if the Academic Year is valid.
     */
    public static boolean checkValidAy(String ay) {
        Matcher matcher = MODULE_SEM_PATTERN.matcher(ay);
        if (!matcher.find()) {
            return false;
        }
        if (ay.startsWith(LAST_VALID_AY)) {
            return true;
        }

        assert ay.length() == AY_STRING_LENGTH;
        int start = Integer.parseInt(ay.substring(YEAR_ONE_START_INDEX, YEAR_ONE_END_INDEX));
        int end = Integer.parseInt(ay.substring(YEAR_ONE_END_INDEX, YEAR_TWO_END_INDEX));
        return end - start == ALLOWED_YEAR_DIFFERENCE;
    }

    /**
     * Converts the attributes of the task into a formatted string to be saved into the storage file.
     *
     * @return the formatted string to be saved into the storage file
     */
    @Override
    public String toFile() {
        String isDoneString = (isDone) ? "1" : "0";
        return getDescription() + " | " + getGrade() + " | " + getMc() + " | " + getSemester() + " | " + isDoneString;
    }

    public int getMc() {
        return mc;
    }

    public String getGrade() {
        return grade;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    public String getSemester() {
        return semester;
    }

    public String getCompletionString() {
        return (isDone) ? MODULE_COMPLETED_STRING : MODULE_INCOMPLETE_STRING;
    }

    /**
     * Gets the grade points from a grade.
     *
     * @param grade Grade to get the grade points from.
     * @return Grade points corresponding to given grade.
     * @throws DukeException If grade is invalid.
     */
    private double getCapFromGrade(String grade) throws DukeException {
        switch (grade) {
        case "A+":
            // Fallthrough
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
        case "S":
            // Fallthrough
        case "U":
            // Fallthrough
        case "F":
            return 0.0;
        default:
            throw new DukeException(Messages.EXCEPTION_INVALID_GRADE);
        }
    }
}
