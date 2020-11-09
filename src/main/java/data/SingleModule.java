package data;

import constants.Constants;
import data.jobs.Task;

import java.util.ArrayList;


/**
 * The type Single module.
 */
public class SingleModule extends Item {

    /**
     * The Module code.
     */
    public String moduleCode;
    /**
     * The Module mc.
     */
    public String moduleMC;
    /**
     * The Module mc.
     */
    public String moduleSU;
    /**
     * The Module name.
     */
    public String moduleName;
    /**
     * The Module description.
     */
    public String moduleDescription;
    /**
     * The Module prerequisite.
     */
    public String modulePrerequisite;

    public ArrayList<Item> taskList;

    public boolean isTaken = false;

    public boolean isCompleted = false;

    public boolean hasSU = false;

    public String grade;

    /**
     * Instantiates a new Single module.
     *
     * @param code        module code
     * @param name        module name
     * @param description module description
     * @param mc          number of mc
     * @param prereq      module prerequisite
     */
    public SingleModule(String code, String name, String description, String mc, String prereq, String su) {
        super(code); //moduleDescription is handle here.
        this.moduleCode = replaceBlanks(code);
        this.moduleDescription = replaceBlanks(description);
        this.moduleName = replaceBlanks(name);
        this.moduleMC = replaceBlanks(mc);
        this.modulePrerequisite = replaceBlanks(prereq);
        this.moduleSU = replaceBlanks(su);
        taskList = new ArrayList<>();
    }

    public String replaceBlanks(String input) {
        if (input != null) {
            return input.replace(Constants.TAB, Constants.SPACE).replace(Constants.NEWLINE, Constants.SPACE)
                    .replace(Constants.RETURN, Constants.SPACE).replace("  ", Constants.SPACE).trim();
        } else {
            return null;
        }
    }

    @Override
    public String getName() {
        return (isCompleted ? Constants.COMPLETED_LABEL : Constants.ZERO_LENGTH_STRING) + moduleCode;
    }


    @Override
    public String toString() {
        return getName() + Constants.SPACE + moduleName + Constants.SPACE
                + moduleMC + (isTaken ? "MC Taken" : "MC") + (isSelected ? " Selected" : "");
    }

    public boolean complete() {
        if (isCompleted || !isTaken) {
            return false;
        }
        for (String grade : Constants.VALID_GRADES) {
            if (grade.equals(this.grade.toUpperCase())) {
                isCompleted = true;
                isTaken = false;
                break;
            }
        }
        return isCompleted;
    }

    @Override
    public String getDetails() {
        StringBuilder builder = new StringBuilder(toString());
        builder.append(Constants.WIN_NEWLINE).append(removeBlanks(moduleDescription.trim()))
                .append(Constants.WIN_NEWLINE).append("Tasks: ").append(Constants.WIN_NEWLINE);
        if (taskList != null && taskList.size() > 0) {
            for (Item item : taskList) {
                builder.append((item).getDetails()).append(Constants.WIN_NEWLINE);
            }
        } else {
            builder.append(Constants.NOT_FOUND);
        }
        return builder.toString();
    }

    private String removeBlanks(String input) {
        input = input.replace(Constants.TAB, Constants.SPACE).replace(Constants.WIN_NEWLINE, Constants.SPACE)
                .replace(Constants.NEWLINE, Constants.SPACE);
        return input.replace(Constants.SPACE.repeat(2), Constants.SPACE);
    }

    /**
     * Gets module mc.
     *
     * @return the module mc
     */
    public String getModuleMC() {
        return moduleMC;
    }


    /**
     * Gets module mc.
     *
     * @return the module mc
     */
    public ArrayList<Item> getTaskList() {
        return taskList;
    }

    public boolean isGraded() {
        if (grade == null) {
            return false;
        } else {
            for (String g : Constants.VALID_GRADES) {
                if (g.equals(grade)) {
                    return true;
                }
            }
        }
        return false;
    }

}
