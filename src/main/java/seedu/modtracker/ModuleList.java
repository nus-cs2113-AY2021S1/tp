package seedu.modtracker;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a module list. A <code>ModuleList</code> object corresponds to
 * a list of modules and a Ui object
 */
public class ModuleList {

    public Ui ui = new Ui();
    public static ArrayList<Module> modList = new ArrayList<>();
    private static final String MODULECODE_LENGTH = Ui.MODULECODE_LENGTH;
    private static final String INVALID_EXP_HOURS = "Please input a number between 1 and 24 for the "
                + "expected workload with a maximum of 1 decimal place.";
    private static final String NEGATIVE_TIME_ERROR = "Please input a positive number for time.";
    private static final String INVALID_TIME_ERROR = "Please input a number between 0 and 99 for time.";
    private static final String ERROR_ADDMOD = "Please type addmod <module code>";
    private static final String ERROR_ADDEXP = "Please type addexp <module code> <expected workload>";
    private static final String ERROR_EXP = " with expected workload being a number between 1 and 24 "
            + "with a maximum of 1 decimal place.";
    private static final String ERROR_DELETEMOD = "Please type deletemod <module code>";
    private static final String ERROR_DELETEEXP = "Please type deleteexp <module code>";
    private static final String NO_WORKLOAD_ERROR = "Cannot minus actual time as there is no actual time inputted.";
    private static final String HOURS_EXCEED_ERROR = "Sorry you are trying to remove too many hours.";
    private static final String HOURS_REMOVAL = " hours have been removed from ";
    private static final String HOUR_REMOVAL = " hour has been removed from ";
    private static final String HOURS_ADD = " hours have been added to ";
    private static final String HOUR_ADD = " hour has been added to ";
    private static final String HOUR_EDIT = " hour is the new actual workload for the module ";
    private static final String HOURS_EDIT = " hours is the new actual workload for the module ";
    private static final String SUMMARY = " hours have been spent on this module in week ";
    private static final String FULL_STOP = ".";
    private static final int MAX_EXP_HOURS = 24;
    private static final int MAX_TIME_HOURS = 99;
    private static final int MIN_MOD_LENGTH = 6;
    private static final int MAX_MOD_LENGTH = 8;
    public static double NO_INPUT = -1.0;


    /**
     * Checks if the module exists in the list of modules.
     *
     * @param input module code typed in by user.
     * @return true if module is found in the list of modules, false otherwise.
     */
    public boolean checkIfModuleExist(String input) {
        Module currentMod = new Module(input);
        for (Module mod : modList) {
            if (mod.equals(currentMod)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the module is valid.
     *
     * @param module  module code typed in by user.
     * @param toPrint whether the UI should print the output.
     * @return true if module code is valid, false otherwise.
     */
    public boolean checkIfModuleValid(String module, boolean toPrint) {
        String regexType1 = "[A-Z]{2}\\d{4}";               //example: CS2113
        String regexType2 = "[A-Z]{2}\\d{4}[A-Z]";          //example: CS2113T
        String regexType3 = "[A-Z]{3}\\d{4}";               //example: GER1000
        String regexType4 = "[A-Z]{3}\\d{4}[A-Z]";          //example: GES1000T

        Pattern pattern;
        Matcher matcher;
        Pattern secondPattern;
        Matcher secondMatcher;
        boolean matchFound;
        String trimmedMod = module.trim();
        if (trimmedMod.contains(" ")) {
            ui.printInvalidModuleSpacing(toPrint);
        } else if (trimmedMod.length() < MIN_MOD_LENGTH || trimmedMod.length() > MAX_MOD_LENGTH) {
            ui.printInvalidModuleLength(toPrint);
        } else if (trimmedMod.length() == MIN_MOD_LENGTH) {
            pattern = Pattern.compile(regexType1);
            matcher = pattern.matcher(trimmedMod);
            matchFound = matcher.find();
            if (matchFound) {
                return true;
            } else {
                ui.printInvalidModuleType(toPrint);
            }
        } else if (trimmedMod.length() == (MIN_MOD_LENGTH + 1)) {
            pattern = Pattern.compile(regexType2);
            matcher = pattern.matcher(trimmedMod);
            secondPattern = Pattern.compile(regexType3);
            secondMatcher = secondPattern.matcher(trimmedMod);
            matchFound = (matcher.find() || secondMatcher.find());
            if (matchFound) {
                return true;
            } else {
                ui.printInvalidModuleType(toPrint);
            }
        } else {
            pattern = Pattern.compile(regexType4);
            matcher = pattern.matcher(trimmedMod);
            matchFound = matcher.find();
            if (matchFound) {
                return true;
            } else {
                ui.printInvalidModuleType(toPrint);
            }
        }
        return false;
    }

    /**
     * Checks if the expected workload has less than or equal to 1 decimal place.
     *
     * @param exp   expected workload typed in by user.
     * @param toPrint whether the UI should print the output.
     * @return true if expected workload is valid, false otherwise.
     */
    public boolean checkIfExpStringValid(String exp, boolean toPrint) {
        if (!exp.contains(".")) {
            return true;
        }
        String[] arrayOfExp = exp.split("\\.", 2);
        if (arrayOfExp[1].length() > 1) {
            if (toPrint) {
                System.out.println(ERROR_ADDEXP + ERROR_EXP + System.lineSeparator());
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if the time is valid.
     *
     * @param hours   number of hours typed in by user.
     * @param toPrint whether the UI should print the output.
     * @return true if number of hours is valid, false otherwise.
     */
    public boolean checkIfExpTimeValid(double hours, boolean toPrint) {
        if (hours < 1 || hours > MAX_EXP_HOURS) {
            if (toPrint) {
                System.out.println(INVALID_EXP_HOURS + System.lineSeparator());
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if the time is valid when using add time, minus time and edit time functions.
     *
     * @param hours   number of hours typed in by user.
     * @param toPrint whether the UI should print the output.
     * @return true if number of hours is valid, false otherwise.
     */
    public boolean checkIfTimeValid(double hours, boolean toPrint) {
        if (hours < 0) {
            if (toPrint) {
                System.out.println(NEGATIVE_TIME_ERROR + System.lineSeparator());
            }
            return false;
        } else if (hours > MAX_TIME_HOURS) {
            if (toPrint) {
                System.out.println(INVALID_TIME_ERROR + System.lineSeparator());
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * Creates a module and adds the module to the list of modules if the module
     * does not exist.
     *
     * @param input   module code typed in by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void addMod(String input, boolean toPrint, Storage storage) {
        try {
            String[] modInfo = input.trim().split(" ", 2);
            String modCode = modInfo[1];
            modCode = modCode.toUpperCase();

            if (!checkIfModuleValid(modCode, toPrint)) {
                return;
            }
            assert modCode.length() >= MIN_MOD_LENGTH : MODULECODE_LENGTH;
            assert modCode.length() <= MAX_MOD_LENGTH : MODULECODE_LENGTH;
            if (checkIfModuleExist(modCode)) {
                if (toPrint) {
                    ui.printExist(modCode);
                }
            } else {
                Module currentModule = new Module(modCode);
                modList.add(currentModule);
                if (toPrint) {
                    ui.printAdd(currentModule);
                    storage.appendToFile(input);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            if (toPrint) {
                System.out.println(ERROR_ADDMOD + System.lineSeparator());
            }
        }
    }

    /**
     * Creates a module and adds the module with expected time to the
     * list of modules if module does not exist.
     * If module already exist, update expected time based on user input.
     *
     * @param input   module code and expected time typed in by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void addExp(String input, boolean toPrint, Storage storage) {
        try {
            String[] modInfo = input.trim().split(" ", 3);
            String modCode = modInfo[1];
            String expTime = modInfo[2];
            modCode = modCode.toUpperCase();
            if (!checkIfModuleValid(modCode, toPrint)) {
                return;
            }
            if (!checkIfExpStringValid(expTime, toPrint)) {
                return;
            }
            double expectedTime = Double.parseDouble(expTime);
            expectedTime = Math.round(expectedTime * 10) / 10.0;
            if (!checkIfExpTimeValid(expectedTime, toPrint)) {
                return;
            }
            assert expectedTime >= 0 : "The expected time should be positive";
            Module currentMod = new Module(modCode, expTime);
            if (!checkIfModuleExist(modCode)) {
                modList.add(currentMod);
            } else {
                int index = modList.indexOf(currentMod);
                modList.get(index).setExpectedWorkload(expectedTime);
            }
            if (toPrint) {
                ui.printAdd(currentMod);
                storage.appendToFile(input);
            }
        } catch (IndexOutOfBoundsException e) {
            if (toPrint) {
                System.out.println(ERROR_ADDEXP + System.lineSeparator());
            }
        } catch (NumberFormatException nfe) {
            if (toPrint) {
                System.out.println(ERROR_ADDEXP + ERROR_EXP + System.lineSeparator());
            }
        }
    }

    /**
     * Deletes the module if module exists.
     *
     * @param input   module code typed in by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void deleteMod(String input, boolean toPrint, Storage storage) {
        try {
            String[] modInfo = input.trim().split(" ", 2);
            String modCode = modInfo[1];
            modCode = modCode.toUpperCase();
            if (!checkIfModuleValid(modCode, toPrint)) {
                return;
            }
            if (checkIfModuleExist(modCode)) {
                Module inputMod = new Module(modCode);
                modList.remove(inputMod);
                if (toPrint) {
                    ui.printDelete(modCode);
                    storage.appendToFile(input);
                }
            } else {
                if (toPrint) {
                    ui.printNotExist(modCode);
                }
            }
            TaskList taskList = new TaskList();
            ArrayList<Task> tasks = taskList.getTaskData();
            int i;
            for (i = 1; i <= tasks.size(); i++) {
                String mod = tasks.get(i - 1).getModCode().trim();
                if (mod.equals(modCode)) {
                    tasks.remove(i - 1);
                    i--;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            if (toPrint) {
                System.out.println(ERROR_DELETEMOD + System.lineSeparator());
            }
        }
    }

    /**
     * Deletes the expected time of the module if module exists.
     *
     * @param input   module code and expected time typed in by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void deleteExp(String input, boolean toPrint, Storage storage) {
        try {
            String[] modInfo = input.trim().split(" ", 2);
            String modCode = modInfo[1];
            modCode = modCode.toUpperCase();
            if (!checkIfModuleValid(modCode, toPrint)) {
                return;
            }
            if (checkIfModuleExist(modCode)) {
                Module inputMod = new Module(modCode);
                int index = modList.indexOf(inputMod);
                if (modList.get(index).getExpectedWorkload() == NO_INPUT) {
                    if (toPrint) {
                        System.out.println("There is no input in the expected workload." + System.lineSeparator());
                    }
                    return;
                }
                modList.get(index).setExpectedWorkload(NO_INPUT);
                if (toPrint) {
                    ui.printDeleteExp(modCode);
                    storage.appendToFile(input);
                }
            } else {
                if (toPrint) {
                    ui.printNotExist(modCode);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            if (toPrint) {
                System.out.println(ERROR_DELETEEXP + System.lineSeparator());
            }
        }
    }

    public void deleteTime(String input, boolean toPrint, Storage storage) {
        String[] commandInfo = input.trim().split(" ", 3);
        String modCode;
        modCode = commandInfo[1].toUpperCase();
        if (!checkIfModuleValid(modCode, toPrint)) {
            return;
        }
        assert modCode.length() >= MIN_MOD_LENGTH : MODULECODE_LENGTH;
        assert modCode.length() <= MAX_MOD_LENGTH : MODULECODE_LENGTH;
        if (!checkIfModuleExist(modCode)) {
            if (toPrint) {
                ui.printNotExist(modCode);
            }
        } else {
            Module currentModule = new Module(modCode);
            int index = modList.indexOf(currentModule);
            int week = Integer.parseInt(commandInfo[2]);
            if (week < 1 || week > 13) {
                System.out.println("The week number should be between 1 and 13." + System.lineSeparator());
                return;
            }
            if (modList.get(index).getActualTimeInSpecificWeek(commandInfo[2]) == -1.0) {
                if (toPrint) {
                    System.out.println("There is no input in the actual time." + System.lineSeparator());
                }
                return;
            }
            modList.get(index).deleteActualTime(week);
            if (toPrint) {
                System.out.println("Actual time of " + modCode + " of week " + commandInfo[2] + " is removed.");
                System.out.println();
                storage.appendToFile(input);
            }
        }
    }

    /**
     * Adds time to actual workload to an existing module.
     *
     * @param input   module code, added time spent and week input by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void addTime(String input, boolean toPrint, Storage storage) {
        String[] commandInfo = input.trim().split(" ", 4);
        String modCode;
        double hours;
        modCode = commandInfo[1].toUpperCase();
        hours = Double.parseDouble(commandInfo[2]);
        if (!checkIfModuleValid(modCode, toPrint)) {
            return;
        }
        assert modCode.length() >= MIN_MOD_LENGTH : MODULECODE_LENGTH;
        assert modCode.length() <= MAX_MOD_LENGTH : MODULECODE_LENGTH;

        if (!checkIfTimeValid(hours, toPrint)) {
            return;
        }

        if (!checkIfModuleExist(modCode)) {
            if (toPrint) {
                ui.printNotExist(modCode);
            }
        } else {
            Module currentModule = new Module(modCode);
            int index = modList.indexOf(currentModule);
            modList.get(index).addActualTime(commandInfo[2], commandInfo[3]);
            if (toPrint) {
                if (hours > 1) {
                    System.out.println(commandInfo[2] + HOURS_ADD + modCode + FULL_STOP);
                    System.out.println(modList.get(index).getActualTimeInSpecificWeek(commandInfo[3])
                            + SUMMARY + commandInfo[3] + FULL_STOP + System.lineSeparator());
                } else {
                    System.out.println(commandInfo[2] + HOUR_ADD + modCode + FULL_STOP);
                    System.out.println(modList.get(index).getActualTimeInSpecificWeek(commandInfo[3])
                            + SUMMARY + commandInfo[3] + FULL_STOP + System.lineSeparator());
                }
                storage.appendToFile(input);
            }
        }
    }


    /**
     * Minus time from actual workload to an existing module.
     *
     * @param input   module code, removed time spent and week input by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void minusTime(String input, boolean toPrint, Storage storage) {
        String modCode;
        double hours;
        String[] commandInfo = input.trim().split(" ", 4);
        modCode = commandInfo[1].toUpperCase();
        hours = Double.parseDouble(commandInfo[2]);

        if (!checkIfModuleValid(modCode, toPrint)) {
            return;
        }
        assert modCode.length() >= MIN_MOD_LENGTH : MODULECODE_LENGTH;
        assert modCode.length() <= MAX_MOD_LENGTH : MODULECODE_LENGTH;

        if (!checkIfTimeValid(hours, toPrint)) {
            return;
        }

        if (!checkIfModuleExist(modCode)) {
            if (toPrint) {
                ui.printNotExist(modCode);
            }
        } else {
            Module currentModule = new Module(modCode);
            int index = modList.indexOf(currentModule);
            int week = Integer.parseInt(commandInfo[3]);
            if (modList.get(index).doesActualTimeExist(week)) {
                if (!modList.get(index).doesHoursExceedTotal(hours, week)) {
                    modList.get(index).minusActualTime(commandInfo[2], commandInfo[3]);
                    if (toPrint) {
                        if (hours > 1) {
                            System.out.println(commandInfo[2] + HOURS_REMOVAL
                                    + modCode + FULL_STOP);
                            System.out.println(modList.get(index).getActualTimeInSpecificWeek(commandInfo[3])
                                    + SUMMARY + commandInfo[3] + FULL_STOP + System.lineSeparator());

                        } else {
                            System.out.println(commandInfo[2] + HOUR_REMOVAL
                                    + modCode + FULL_STOP);
                            System.out.println(modList.get(index).getActualTimeInSpecificWeek(commandInfo[3])
                                    + SUMMARY + commandInfo[3] + FULL_STOP + System.lineSeparator());
                        }
                        storage.appendToFile(input);
                    }
                } else {
                    if (toPrint) {
                        System.out.println(HOURS_EXCEED_ERROR
                                + System.lineSeparator());
                    }
                }
            } else {
                if (toPrint) {
                    System.out.println(NO_WORKLOAD_ERROR
                            + System.lineSeparator());
                }
            }
        }
    }

    /**
     * Edits time to actual workload to an existing module.
     *
     * @param input   module code, edited time spent and week input by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void editTime(String input, boolean toPrint, Storage storage) {
        String[] commandInfo = input.trim().split(" ", 4);
        String modCode;
        double hours;
        modCode = commandInfo[1].toUpperCase();
        hours = Double.parseDouble(commandInfo[2]);
        if (!checkIfModuleValid(modCode, toPrint)) {
            return;
        }
        assert modCode.length() >= MIN_MOD_LENGTH : MODULECODE_LENGTH;
        assert modCode.length() <= MAX_MOD_LENGTH : MODULECODE_LENGTH;

        if (!checkIfTimeValid(hours, toPrint)) {
            return;
        }

        if (!checkIfModuleExist(modCode)) {
            if (toPrint) {
                ui.printNotExist(modCode);
            }
        } else {
            Module currentModule = new Module(modCode);
            int index = modList.indexOf(currentModule);
            modList.get(index).editsActualTime(commandInfo[2], commandInfo[3]);
            if (toPrint) {
                if (hours > 1) {
                    System.out.println(commandInfo[2] + HOURS_EDIT + modCode + FULL_STOP);
                    System.out.println(modList.get(index).getActualTimeInSpecificWeek(commandInfo[3])
                            + SUMMARY + commandInfo[3] + FULL_STOP + System.lineSeparator());
                } else {
                    System.out.println(commandInfo[2] + HOUR_EDIT + modCode + FULL_STOP);
                    System.out.println(modList.get(index).getActualTimeInSpecificWeek(commandInfo[3])
                            + SUMMARY + commandInfo[3] + FULL_STOP + System.lineSeparator());
                }
                storage.appendToFile(input);
            }
        }
    }

    public ArrayList<Module> getData() {
        return modList;
    }

    /**
     * Gets all the module codes of modules taken by the user.
     *
     * @return a list containing all the modules codes.
     */
    public ArrayList<String> getModuleCodes() {
        ArrayList<String> output = new ArrayList<>();
        for (Module m : modList) {
            output.add(m.getModuleCode());
        }
        return output;
    }

    public void clear() {
        modList = new ArrayList<>();
    }
}

