package seedu.duke.apps.academicplanner.commons;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import static seedu.duke.apps.academicplanner.commons.SharedUtils.getEntryToBeEdited;
import seedu.duke.apps.academicplanner.exceptions.AcademicException;
import seedu.duke.apps.capcalculator.commons.CalculatorUtils;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;

//@@author harryleecp
/**
 * Class representing edit module utilities from the edit module command.
 */
public class EditUtils {

    private final ArrayList<PartialModule> modulesList;
    private final HashMap<String, ArrayList<Integer>> modulesAddedMap;
    private final ModuleValidator modChecker;
    private final CalculatorUtils calculatorUtils;
    private final Person currentPerson;

    /**
     * Default constructor for EditUtils.
     *
     * @param allModules all modules offered by NUS
     * @param currentPerson current User
     */
    public EditUtils(ModuleLoader allModules, Person currentPerson) {
        this.modulesList = currentPerson.getModulesList();
        this.modulesAddedMap = currentPerson.getModulesAddedMap();
        this.modChecker = new ModuleValidator(allModules, currentPerson);
        this.calculatorUtils = new CalculatorUtils(currentPerson);
        this.currentPerson = currentPerson;
    }

    private static final int FROM_EDIT = 2;
    private static final String PROMPT_NEW_SEMESTER_VALUE = "Enter the new semester value: ";
    private static final String PROMPT_NEW_GRADE = "Enter the new grade: ";
    private static final String ERROR_INVALID_SEMESTER_INDEX = "INVALID SEMESTER INDEX";
    private static final String ERROR_INVALID_GRADE = "INVALID GRADE VALUE";
    private static final String VALID_GRADES = "Valid grades are:\n"
            + "\tLetter Grades: A+, A, A-, B+, B, B-, C+, C, D+, D, F\n"
            + "\tSpecial Grades: CS, CU, S, U, W, IC, IP, AUD, WU, EXE\n"
            + "\tIf you have yet to have a grade for the module: NT";
    private static final String VALID_SEMESTERS = "Valid semesters are integers from 1 to 10, inclusive";

    /**
     * Changes the current grade of module to the input of the user.
     * PartialModule must exist in user's list and hashmap
     *
     * @param in scanner
     * @param moduleCode code of module to edit the grade
     * @throws AcademicException invalid grade
     */
    public void editModuleGrade(Scanner in, String moduleCode) throws AcademicException {
        int indexToUpdate = getEntryToBeEdited(in, moduleCode, currentPerson, FROM_EDIT);

        System.out.println(PROMPT_NEW_GRADE);
        System.out.println(VALID_GRADES);
        String gradeValue = in.nextLine().trim().toUpperCase();

        if (!modChecker.isValidGrade(gradeValue)) {
            throw new AcademicException(ERROR_INVALID_GRADE);
        }

        ArrayList<Integer> moduleIndexList = modulesAddedMap.get(moduleCode);
        updateModuleGrade(gradeValue, moduleIndexList.get(indexToUpdate));
        System.out.println("Grade for " + moduleCode + " successfully updated!");
    }

    /**
     * Updates user's module with new grade and updates user's Cap.
     *
     * @param indexToUpdate Index of Module List that needs to be updated
     * @param gradeValue grade to edit to
     */
    public void updateModuleGrade(String gradeValue, int indexToUpdate) {
        PartialModule module = modulesList.get(indexToUpdate);
        updateCurrentModuleGrade(gradeValue, module);
    }

    /**
     * Updates module to reflect the new grade.
     *
     * @param gradeValue new grade value to reflect
     * @param module module to edit
     */
    private void updateCurrentModuleGrade(String gradeValue, PartialModule module) {
        double oldCap = module.getCap();
        module.setGrade(gradeValue);
        double newCap = module.getCap();
        calculatorUtils.updateCap(FROM_EDIT, module, oldCap, newCap);
    }

    /**
     * Edits module semester taken when module is in user's list.
     * PartialModule must exist in user's module list and hashmap.
     *
     * @param in scanner
     * @param moduleCode module to edit
     * @throws AcademicException invalid semester index
     */
    public void editModuleSemester(Scanner in, String moduleCode) throws AcademicException {
        int indexToUpdate = getEntryToBeEdited(in, moduleCode, currentPerson, FROM_EDIT);

        System.out.println(PROMPT_NEW_SEMESTER_VALUE);
        System.out.println(VALID_SEMESTERS);
        String newValue = in.nextLine().trim();

        if (!ModuleValidator.isValidSemester(parseInt(newValue))) {
            throw new AcademicException(ERROR_INVALID_SEMESTER_INDEX);
        }

        ArrayList<Integer> moduleIndexList = modulesAddedMap.get(moduleCode);
        updateModuleSemester(newValue, moduleIndexList.get(indexToUpdate));
        System.out.println("Semester for " + moduleCode + " successfully updated!");
    }

    /**
     * Finds the module and updates the semester taken.
     *
     * @param newValue new semester index
     * @param indexToEdit Index of Module List that needs to be updated
     */
    public void updateModuleSemester(String newValue, int indexToEdit) {
        PartialModule item = modulesList.get(indexToEdit);
        item.setSemesterIndex(parseInt(newValue));
        assert item.getSemesterIndex() == parseInt(newValue);
    }


}
