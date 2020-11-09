package seedu.modtracker;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a module list. A <code>ModuleList</code> object corresponds to
 * a list of modules and a Ui object
 */
public class ModuleList {

    private static final int MIN_EXP_HOURS = 1;
    private static final int MIN_WEEK = 1;
    private static final int MAX_WEEK = 13;
    public Ui ui = new Ui();
    public static ArrayList<Module> modList = new ArrayList<>();
    private static final String MODULECODE_LENGTH = "The module code should have 6 - 8 characters without any spacing.";
    private static final int MAX_EXP_HOURS = 24;
    private static final int MAX_TIME_HOURS = 99;
    private static final int MIN_MOD_LENGTH = 6;
    private static final int MAX_MOD_LENGTH = 8;
    public static double NO_INPUT = -1.0;
    private static Logger logger = Logger.getLogger(ModuleList.class.getName());


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
     * @param module module code typed in by user.
     * @return true if module code is valid, false otherwise.
     */
    public boolean checkIfModuleValid(String module) {
        String regexType1 = "[A-Z]{2}\\d{4}";               //example: CS2113
        String regexType2 = "[A-Z]{2}\\d{4}[A-Z]";          //example: CS2113T
        String regexType3 = "[A-Z]{3}\\d{4}";               //example: GER1000
        String regexType4 = "[A-Z]{3}\\d{4}[A-Z]";          //example: GES1000T

        Pattern pattern;
        Matcher matcher;
        Pattern secondPattern;
        Matcher secondMatcher;
        boolean matchFound;
        if (module.length() == MIN_MOD_LENGTH) {
            pattern = Pattern.compile(regexType1);
            matcher = pattern.matcher(module);
            matchFound = matcher.find();
            return matchFound;
        } else if (module.length() == (MIN_MOD_LENGTH + 1)) {
            pattern = Pattern.compile(regexType2);
            matcher = pattern.matcher(module);
            secondPattern = Pattern.compile(regexType3);
            secondMatcher = secondPattern.matcher(module);
            matchFound = (matcher.find() || secondMatcher.find());
            return matchFound;
        } else {
            pattern = Pattern.compile(regexType4);
            matcher = pattern.matcher(module);
            matchFound = matcher.find();
            return matchFound;
        }
    }

    /**
     * Checks if the expected workload has less than or equal to 1 decimal place.
     *
     * @param exp     expected workload typed in by user.
     * @param toPrint whether the UI should print the output.
     * @return true if expected workload is valid, false otherwise.
     */
    public boolean checkIfExpStringValid(String exp, boolean toPrint) {
        if (!exp.contains(".")) {
            return true;
        }
        String[] arrayOfExp = exp.split("\\.", 2);
        if (arrayOfExp[1].length() > 1) {
            ui.printInvalidExpString(toPrint);
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
        if (hours < MIN_EXP_HOURS || hours > MAX_EXP_HOURS) {
            ui.printInvalidExpTime(toPrint);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if the week is valid.
     *
     * @param week    number of weeks typed in by user.
     * @param toPrint whether the UI should print the output.
     * @return true if week number is valid, false otherwise.
     */
    public boolean checkIfWeekValid(String week, boolean toPrint) {


        if (week.length() == 2) {
            char firstCharacter = week.charAt(0);
            boolean firstIntegerCheck = Character.isDigit(firstCharacter);
            char secondCharacter = week.charAt(1);
            boolean secondIntegerCheck = Character.isDigit(secondCharacter);

            if (!firstIntegerCheck) {
                ui.printWeekAlphabetError(toPrint);
                return false;
            } else if (!secondIntegerCheck) {
                ui.printWeekAlphabetError(toPrint);
                return false;
            }

            int weekNumber = Integer.parseInt(week);
            if (weekNumber < MIN_WEEK || weekNumber > MAX_WEEK) {
                ui.printWeekError(toPrint);
                return false;
            } else {
                return true;
            }
        } else if (week.length() == 1) {
            char firstCharacter = week.charAt(0);
            boolean firstIntegerCheck = Character.isDigit(firstCharacter);
            if (firstIntegerCheck) {
                int weekNumber = Integer.parseInt(week);
                if (weekNumber < MIN_WEEK || weekNumber > MAX_WEEK) {
                    ui.printWeekError(toPrint);
                    return false;
                } else {
                    return true;
                }
            } else {
                ui.printWeekAlphabetError(toPrint);
                return false;
            }

        } else {
            ui.printWeekError(toPrint);
            return false;
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
                ui.printNegativeTimeError();
            }
            return false;
        } else if (hours > MAX_TIME_HOURS) {
            if (toPrint) {
                ui.printTimeOutOfRangeError();
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
            modCode = modCode.trim();
            modCode = modCode.toUpperCase();

            if (!checkIfModuleValid(modCode)) {
                ui.printAddModError(toPrint);
                ui.printInvalidModuleType(toPrint);
                return;
            }
            assert modCode.length() >= MIN_MOD_LENGTH : MODULECODE_LENGTH;
            assert modCode.length() <= MAX_MOD_LENGTH : MODULECODE_LENGTH;
            if (checkIfModuleExist(modCode)) {
                ui.printExist(modCode, toPrint);
            } else {
                Module currentModule = new Module(modCode);
                modList.add(currentModule);
                ui.printAdd(currentModule, toPrint);
                if (toPrint) {
                    storage.appendToFile(input);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            ui.printAddModError(toPrint);
            ui.printEmptyline(toPrint);
        }
    }

    /**
     * Creates a module and adds the module with expected workload to the
     * list of modules if module does not exist.
     * If module already exist, update expected workload based on user input.
     *
     * @param input   module code and expected workload typed in by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void addExp(String input, boolean toPrint, Storage storage) {
        try {
            logger.setLevel(Level.INFO);
            String[] modInfo = input.trim().split(" ", 3);
            String modCode = modInfo[1];
            String expTime = modInfo[2];
            modCode = modCode.toUpperCase();
            if (!checkIfModuleValid(modCode)) {
                ui.printAddExpError(toPrint);
                ui.printInvalidModuleType(toPrint);
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
                ui.printAdd(currentMod, toPrint);
            } else {
                int index = modList.indexOf(currentMod);
                double initialExp = modList.get(index).getExpectedWorkload();
                if (initialExp == expectedTime) {
                    ui.printExpAlreadyUpdated(expectedTime, toPrint);
                    return;
                } else if (initialExp == NO_INPUT) {
                    modList.get(index).setExpectedWorkload(expectedTime);
                    ui.printAdd(currentMod, toPrint);
                } else {
                    modList.get(index).setExpectedWorkload(expectedTime);
                    ui.printExpUpdated(modCode, expectedTime, toPrint);
                }
            }
            if (toPrint) {
                storage.appendToFile(input);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.printAddExpError(toPrint);
            ui.printEmptyline(toPrint);
        } catch (NumberFormatException nfe) {
            logger.log(Level.INFO, "Invalid number format");
            ui.printAddExpNfe(toPrint);
        }
    }

    /**
     * Deletes the module from list of modules.
     *
     * @param input   module code typed in by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void deleteMod(String input, boolean toPrint, Storage storage) {
        try {
            String[] modInfo = input.trim().split(" ", 2);
            String modCode = modInfo[1];
            modCode = modCode.trim();
            modCode = modCode.toUpperCase();
            if (!checkIfModuleValid(modCode)) {
                ui.printDeleteModError(toPrint);
                ui.printInvalidModuleType(toPrint);
                return;
            }
            if (checkIfModuleExist(modCode)) {
                Module inputMod = new Module(modCode);
                modList.remove(inputMod);
                ui.printDelete(modCode, toPrint);
                if (toPrint) {
                    storage.appendToFile(input);
                }
            } else {
                ui.printNotExist(modCode, toPrint);
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
            ui.printDeleteModError(toPrint);
            ui.printEmptyline(toPrint);
        }
    }

    /**
     * Deletes the expected workload of the module.
     *
     * @param input   module code typed in by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void deleteExp(String input, boolean toPrint, Storage storage) {
        try {
            String[] modInfo = input.trim().split(" ", 2);
            String modCode = modInfo[1];
            modCode = modCode.trim();
            modCode = modCode.toUpperCase();
            if (!checkIfModuleValid(modCode)) {
                ui.printDeleteExpError(toPrint);
                ui.printInvalidModuleType(toPrint);
                return;
            }
            if (checkIfModuleExist(modCode)) {
                Module inputMod = new Module(modCode);
                int index = modList.indexOf(inputMod);
                if (modList.get(index).getExpectedWorkload() == NO_INPUT) {
                    ui.printEmptyExp(toPrint);
                    return;
                }
                modList.get(index).setExpectedWorkload(NO_INPUT);
                ui.printDeleteExp(modCode, toPrint);
                if (toPrint) {
                    storage.appendToFile(input);
                }
            } else {
                ui.printNotExist(modCode, toPrint);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.printDeleteExpError(toPrint);
            ui.printEmptyline(toPrint);
        }
    }

    /**
     * Deletes the actual time of the module of the particular week.
     *
     * @param input   module code and week number typed in by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void deleteTime(String input, boolean toPrint, Storage storage) {
        String[] commandInfo = input.trim().split(" ", 3);
        String modCode;
        modCode = commandInfo[1].toUpperCase();
        if (!checkIfModuleValid(modCode)) {
            ui.printDeleteTimeError(toPrint);
            ui.printInvalidModuleType(toPrint);
            return;
        }
        assert modCode.length() >= MIN_MOD_LENGTH : MODULECODE_LENGTH;
        assert modCode.length() <= MAX_MOD_LENGTH : MODULECODE_LENGTH;
        if (!checkIfModuleExist(modCode)) {
            ui.printDeleteTimeNotExist(toPrint);
            ui.printNotExist(modCode, toPrint);
        } else {
            Module currentModule = new Module(modCode);
            int index = modList.indexOf(currentModule);
            int week = Integer.parseInt(commandInfo[2]);
            if (week < MIN_WEEK || week > MAX_WEEK) {
                ui.printWeekError(toPrint);
                return;
            }
            if (modList.get(index).getActualTimeInSpecificWeek(commandInfo[2]) == NO_INPUT) {
                ui.printEmptyActual(toPrint);
                return;
            }
            modList.get(index).deleteActualTime(week);
            ui.removeActualTime(modCode, commandInfo[2], toPrint);
            if (toPrint) {
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
        String week;
        double hours;
        modCode = commandInfo[1].toUpperCase();
        week = commandInfo[3];
        double initialHours = Double.parseDouble(commandInfo[2]);
        hours = Math.round(initialHours * 10) / 10; // this rounds the hours to the nearest 1dp.

        if (!checkIfModuleValid(modCode)) {
            ui.printInvalidModule(toPrint);
            return;
        }
        assert modCode.length() >= MIN_MOD_LENGTH : MODULECODE_LENGTH;
        assert modCode.length() <= MAX_MOD_LENGTH : MODULECODE_LENGTH;


        if (!checkIfTimeValid(hours, toPrint)) {
            return;
        } else if (!checkIfWeekValid(week, toPrint)) {
            return;
        }

        Module currentModule = new Module(modCode);
        int index = modList.indexOf(currentModule);
        int weekNumber = Integer.parseInt(commandInfo[3]);
        if (!checkIfModuleExist(modCode)) {
            ui.printNotExist(modCode, toPrint);
        } else if (modList.get(index).doesHoursExceed99(hours, weekNumber)) {
            ui.printHoursExceed();
            return;
        } else {
            modList.get(index).addActualTime(commandInfo[2], commandInfo[3]);
            if (toPrint) {
                ui.printHoursAdded(hours, modCode);
                ui.printHoursSummary(modList.get(index).getActualTimeInSpecificWeek(commandInfo[3]), week);
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
        String[] commandInfo = input.trim().split(" ", 4);
        String modCode;
        String weekNumber;
        double hours;
        modCode = commandInfo[1].toUpperCase();
        weekNumber = commandInfo[3];
        double initialHours = Double.parseDouble(commandInfo[2]);
        hours = (Math.round(initialHours * 10)) / 10; // this rounds the hours to the nearest 1dp.

        if (!checkIfModuleValid(modCode)) {
            ui.printInvalidModule(toPrint);
            return;
        }
        assert modCode.length() >= MIN_MOD_LENGTH : MODULECODE_LENGTH;
        assert modCode.length() <= MAX_MOD_LENGTH : MODULECODE_LENGTH;

        if (!checkIfTimeValid(hours, toPrint)) {
            return;
        } else if (!checkIfWeekValid(weekNumber, toPrint)) {
            return;
        }

        if (!checkIfModuleExist(modCode)) {
            ui.printNotExist(modCode, toPrint);
        } else {
            Module currentModule = new Module(modCode);
            int index = modList.indexOf(currentModule);
            int week = Integer.parseInt(commandInfo[3]);
            if (modList.get(index).doesActualTimeExist(week)) {
                if (!modList.get(index).doesHoursExceedTotal(hours, week)) {
                    modList.get(index).minusActualTime(commandInfo[2], commandInfo[3]);
                    if (toPrint) {
                        ui.printHoursMinus(hours, modCode);
                        ui.printHoursSummary(
                                modList.get(index).getActualTimeInSpecificWeek(commandInfo[3]), weekNumber);
                        storage.appendToFile(input);
                    }
                } else {
                    if (toPrint) {
                        ui.printHoursMinusExceed();
                    }
                }
            } else {
                if (toPrint) {
                    ui.printWorkloadError();
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
        String week;
        double hours;
        double initialHours = Double.parseDouble(commandInfo[2]);
        hours = Math.round(initialHours * 10.0) / 10.0; // this rounds the hours to the nearest 1dp.
        modCode = commandInfo[1].toUpperCase();
        week = commandInfo[3];


        if (!checkIfModuleValid(modCode)) {
            ui.printInvalidModule(toPrint);
            return;
        }
        assert modCode.length() >= MIN_MOD_LENGTH : MODULECODE_LENGTH;
        assert modCode.length() <= MAX_MOD_LENGTH : MODULECODE_LENGTH;

        if (!checkIfTimeValid(hours, toPrint)) {
            return;
        } else if (!checkIfWeekValid(week, toPrint)) {
            return;
        }

        if (!checkIfModuleExist(modCode)) {
            ui.printNotExist(modCode, toPrint);
        } else {
            Module currentModule = new Module(modCode);
            int index = modList.indexOf(currentModule);
            modList.get(index).editsActualTime(commandInfo[2], commandInfo[3]);
            if (toPrint) {
                ui.printHoursEditted(hours, modCode);
                ui.printHoursSummary(hours, week);
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

