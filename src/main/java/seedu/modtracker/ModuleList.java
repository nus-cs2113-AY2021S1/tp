package seedu.modtracker;

import java.util.ArrayList;

/**
 * Represents a module list. A <code>ModuleList</code> object corresponds to
 * a list of modules and a Ui object
 */
public class ModuleList {

    public Ui ui = new Ui();
    public static ArrayList<Module> modList = new ArrayList<>();
    private static final String MODULECODE_SPACING = "Please type module code without any spacing.";
    private static final String MODULECODE_LENGTH = "The module code should have 6 - 8 characters without any spacing.";
    private static final String INVALID_MODULECODE = "Please check module code again.";
    private static final String INVALID_HOURS = "Please input a valid number of hours.";
    private static final String ERROR_ADDMOD = "Please type addmod <module code>";
    private static final String ERROR_ADDEXP = "Please type addexp <module code> <expected workload>";
    private static final String ERROR_DELETEMOD = "Please type deletemod <module code>";
    private static final String ERROR_DELETEEXP = "Please type deleteexp <module code>";
    private static final int MIN_MOD_LENGTH = 6;
    private static final int MAX_MOD_LENGTH = 8;
    public static final int NO_INPUT = -1;

    /**
     * Checks if the module exists in the list of modules.
     *
     * @param input module code typed in by user.
     * @return true if module is found in the list of modules, false otherwise.
     */
    public boolean checkIfModuleExist(String input) {
        Module currentMod = new Module(input);
        for (Module mod: modList) {
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
     * @param toPrint whether the UI should print the output.
     * @return true if module code is valid, false otherwise.
     */
    public boolean checkIfModuleValid(String module, boolean toPrint) {
        String trimmedMod = module.trim();
        if (trimmedMod.contains(" ")) {
            if (toPrint) {
                System.out.println(MODULECODE_SPACING + System.lineSeparator());
            }
            return false;
        } else if (trimmedMod.length() < MIN_MOD_LENGTH || trimmedMod.length() > MAX_MOD_LENGTH) {
            if (toPrint) {
                System.out.println(INVALID_MODULECODE + " " + MODULECODE_LENGTH + System.lineSeparator());
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if the time is valid.
     *
     * @param hours number of hours typed in by user.
     * @param toPrint whether the UI should print the output.
     * @return true if number of hours is valid, false otherwise.
     */
    public boolean checkIfTimeValid(double hours, boolean toPrint) {
        if (hours < 0) {
            if (toPrint) {
                System.out.println(INVALID_HOURS + System.lineSeparator());
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
     * @param input module code typed in by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void addMod(String input, boolean toPrint, Storage storage) {
        try {
            String[] modInfo = input.split(" ", 2);
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
     * @param input module code and expected time typed in by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void addExp(String input, boolean toPrint, Storage storage) {
        try {
            String[] modInfo = input.split(" ", 3);
            String modCode = modInfo[1];
            String expTime = modInfo[2];
            modCode = modCode.toUpperCase();
            if (!checkIfModuleValid(modCode, toPrint)) {
                return;
            }
            int expectedTime = Integer.parseInt(expTime);
            if (!checkIfTimeValid(expectedTime, toPrint)) {
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
                System.out.println("NumberFormatException: " + nfe.getMessage() + System.lineSeparator());
            }
        }
    }

    /**
     * Deletes the module if module exists.
     *
     * @param input module code typed in by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void deleteMod(String input, boolean toPrint, Storage storage) {
        try {
            String[] modInfo = input.split(" ", 2);
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
        } catch (IndexOutOfBoundsException e) {
            if (toPrint) {
                System.out.println(ERROR_DELETEMOD + System.lineSeparator());
            }
        }
    }

    /**
     * Deletes the expected time of the module if module exists.
     *
     * @param input module code and expected time typed in by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void deleteExp(String input, boolean toPrint, Storage storage) {
        try {
            String[] modInfo = input.split(" ", 2);
            String modCode = modInfo[1];
            modCode = modCode.toUpperCase();
            if (!checkIfModuleValid(modCode, toPrint)) {
                return;
            }
            if (checkIfModuleExist(modCode)) {
                Module inputMod = new Module(modCode);
                int index = modList.indexOf(inputMod);
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

    /**
     * Adds time to actual workload to an existing module.
     *
     * @param input module code, added time spent and week input by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void addTime(String input, boolean toPrint, Storage storage) {
        String[] commandInfo = input.split(" ", 4);
        commandInfo[1] = commandInfo[1].toUpperCase();
        Module currentModule = new Module(commandInfo[1]);
        int index = modList.indexOf(currentModule);
        modList.get(index).addActualTime(commandInfo[2], commandInfo[3]);
        if (toPrint) {
            System.out.println(commandInfo[2] + " hours are added to " + commandInfo[1] + System.lineSeparator());
            storage.appendToFile(input);
        }
    }

    /**
     * Minus time from actual workload to an existing module.
     *
     * @param input module code, removed time spent and week input by user.
     * @param toPrint whether the UI should print the output.
     * @param storage storage object where data is stored.
     */
    public void minusTime(String input, boolean toPrint, Storage storage) {
        String[] commandInfo = input.split(" ", 4);
        commandInfo[1] = commandInfo[1].toUpperCase();
        Module currentModule = new Module(commandInfo[1]);
        int index = modList.indexOf(currentModule);
        modList.get(index).minusActualTime(commandInfo[2], commandInfo[3]);
        if (toPrint) {
            System.out.println(commandInfo[2] + " hours are removed from " + commandInfo[1] + System.lineSeparator());
            storage.appendToFile(input);
        }
    }

    public ArrayList<Module> getData() {
        return modList;
    }

}

