package seedu.modtracker;

import java.util.ArrayList;

/**
 * Represents a module list. A <code>ModuleList</code> object corresponds to
 * a list of modules and a Ui object
 */
public class ModuleList {

    public Ui ui = new Ui();
    public static ArrayList<Module> modList = new ArrayList<>();

    /**
     * Creates a module and adds the module to the list of modules if the module
     * does not exist.
     *
     * @param input module code typed in by user
     */
    public void addMod(String input) {
        try {
            String[] modInfo = input.split(" ", 2);
            modInfo[1] = modInfo[1].toUpperCase();

            if (!checkIfModuleValid(modInfo[1])) {
                return;
            }
            if (!checkIfModuleExist(modInfo[1])) {
                Module currentModule = new Module(modInfo[1]);
                modList.add(currentModule);
                ui.printAdd(currentModule);
            } else {
                ui.printExist(modInfo[1]);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please type addmod <module code>" + System.lineSeparator());
        }
    }

    public boolean checkIfModuleValid(String module) {
        String trimmedMod = module.trim();
        if (trimmedMod.contains(" ")) {
            System.out.println("Please type module code without any spacing." + System.lineSeparator());
            return false;
        } else if (trimmedMod.length() < 6 || trimmedMod.length() > 8) {
            System.out.println("Please check module code again. The module code should not have any spacing, and is of 6 - 8 characters." + System.lineSeparator());
            return false;
        } else {
            return true;
        }
    }


    public boolean checkIfTimeValid(double hours) {
        if (hours < 0) {
            System.out.println("Please input a valid number of hours." + System.lineSeparator());
            return false;
        } else {
            return true;
        }
    }

    /**
     * Creates a module and adds the module with expected time to the
     * list of modules if module does not exist.
     * If module already exist, update expected time based on user input
     *
     * @param input module code and expected time typed in by user
     */
    public void addExp(String input) {
        try {
            String[] modInfo = input.split(" ", 3);
            modInfo[1] = modInfo[1].toUpperCase();
            if (!checkIfModuleValid(modInfo[1])) {
                return;
            }
            int expectedTime = Integer.parseInt(modInfo[2]);
            if (!checkIfTimeValid(expectedTime)) {
                return;
            }
            Module currentMod = new Module(modInfo[1], modInfo[2]);
            if (!checkIfModuleExist(modInfo[1])) {
                modList.add(currentMod);
            } else {
                int index = modList.indexOf(currentMod);
                modList.get(index).expected = expectedTime;
            }
            ui.printAdd(currentMod);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please type addexp <module code> <expected workload>" + System.lineSeparator());
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage() + System.lineSeparator());
        }
    }

    /**
     * Deletes the module if module exists.
     *
     * @param input module code typed in by user
     */
    public void deleteMod(String input) {
        try {
            String[] modInfo = input.split(" ", 2);
            modInfo[1] = modInfo[1].toUpperCase();
            if (!checkIfModuleValid(modInfo[1])) {
                return;
            }
            if (checkIfModuleExist(modInfo[1])) {
                Module inputMod = new Module(modInfo[1]);
                modList.remove(inputMod);
                ui.printDelete(modInfo[1]);
            } else {
                ui.printNotExist(modInfo[1]);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please type deletemod <module code>" + System.lineSeparator());
        }
    }

    /**
     * Deletes the expected time of the module if module exists.
     *
     * @param input module code and expected time typed in by user
     */
    public void deleteExp(String input) {
        try {
            String[] modInfo = input.split(" ", 2);
            modInfo[1] = modInfo[1].toUpperCase();
            if (!checkIfModuleValid(modInfo[1])) {
                return;
            }
            if (checkIfModuleExist(modInfo[1])) {
                Module inputMod = new Module(modInfo[1]);
                int index = modList.indexOf(inputMod);
                modList.get(index).expected = -1;
                ui.printDeleteExp(modInfo[1]);
            } else {
                ui.printNotExist(modInfo[1]);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please type deleteexp <module code>" + System.lineSeparator());
        }
    }

    /**
     * Checks if the module exists in the list of modules.
     *
     * @param input module code typed in by user
     * @return the existence of module in the list of modules.
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

    public void addTime(String input) {
        String[] commandInfo = input.split(" ", 4);
        commandInfo[1] = commandInfo[1].toUpperCase();
        Module currentModule = new Module(commandInfo[1]);
        int index = modList.indexOf(currentModule);
        modList.get(index).addActualTime(commandInfo[2], commandInfo[3]);
        System.out.println(commandInfo[2] + " hours are added to " + commandInfo[1] + System.lineSeparator());
    }

    public void minusTime(String input) {
        String[] commandInfo = input.split(" ", 4);
        commandInfo[1] = commandInfo[1].toUpperCase();
        int index = modList.indexOf(commandInfo[1]);
        modList.get(index).minusActualTime(commandInfo[2], commandInfo[3]);
        System.out.println(commandInfo[2] + " hours are removed from " + commandInfo[1] + System.lineSeparator());
    }

    public ArrayList<Module> getData() {
        return modList;
    }
}

