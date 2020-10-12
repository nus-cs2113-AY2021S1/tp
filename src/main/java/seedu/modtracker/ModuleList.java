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
     * @param toPrint whether the UI should print the output
     * @param storage storage object where data is stored
     */
    public void addMod(String input, boolean toPrint, Storage storage) {
        String[] modInfo = input.split(" ", 2);
        modInfo[1] = modInfo[1].toUpperCase();

        if (checkModuleIfExist(modInfo[1])) {
            if (toPrint) {
                ui.printExist(modInfo[1]);
            }
            return;
        }

        Module currentModule = new Module(modInfo[1]);
        modList.add(currentModule);
        if (toPrint) {
            ui.printAdd(currentModule);
            storage.appendToFile(input);
        }
    }

    /**
     * Creates a module and adds the module with expected time to the
     * list of modules if module does not exist.
     * If module already exist, update expected time based on user input
     *
     * @param input module code and expected time typed in by user
     * @param toPrint whether the UI should print the output
     * @param storage storage object where data is stored
     */
    public void addExp(String input, boolean toPrint, Storage storage) {
        String[] modInfo = input.split(" ", 3);
        modInfo[1] = modInfo[1].toUpperCase();
        Module currentMod = new Module(modInfo[1], modInfo[2]);
        if (!checkModuleIfExist(modInfo[1])) {
            modList.add(currentMod);
        } else {
            int index = modList.indexOf(currentMod);
            int expectedTime = Integer.parseInt(modInfo[2]);
            modList.get(index).setExpectedWorkload(expectedTime);
        }
        if (toPrint) {
            ui.printAdd(currentMod);
            storage.appendToFile(input);
        }
    }

    /**
     * Deletes the module if module exists.
     *
     * @param input module code typed in by user
     * @param toPrint whether the UI should print the output
     * @param storage storage object where data is stored
     */
    public void deleteMod(String input, boolean toPrint, Storage storage) {
        String[] modInfo = input.split(" ", 2);
        modInfo[1] = modInfo[1].toUpperCase();
        if (checkModuleIfExist(modInfo[1])) {
            Module inputMod = new Module(modInfo[1]);
            modList.remove(inputMod);
            if (toPrint) {
                ui.printDelete(modInfo[1]);
                storage.appendToFile(input);
            }
        } else if (toPrint) {
            ui.printNotExist(modInfo[1]);
        }
    }

    /**
     * Deletes the expected time of the module if module exists.
     *
     * @param input module code and expected time typed in by user
     * @param toPrint whether the UI should print the output
     * @param storage storage object where data is stored
     */
    public void deleteExp(String input, boolean toPrint, Storage storage) {
        String[] modInfo = input.split(" ", 2);
        modInfo[1] = modInfo[1].toUpperCase();
        if (checkModuleIfExist(modInfo[1])) {
            Module inputMod = new Module(modInfo[1]);
            int index = modList.indexOf(inputMod);
            modList.get(index).setExpectedWorkload(-1);
            if (toPrint) {
                ui.printDeleteExp(modInfo[1]);
                storage.appendToFile(input);
            }
        } else if (toPrint) {
            ui.printNotExist(modInfo[1]);
        }
    }

    /**
     * Checks if the module exists in the list of modules.
     *
     * @param input module code typed in by user
     * @return the existence of module in the list of modules.
     */
    public boolean checkModuleIfExist(String input) {
        Module currentMod = new Module(input);
        for (Module mod: modList) {
            if (mod.equals(currentMod)) {
                return true;
            }
        }
        return false;
    }

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

    public void minusTime(String input, boolean toPrint, Storage storage) {
        String[] commandInfo = input.split(" ", 4);
        commandInfo[1] = commandInfo[1].toUpperCase();
        int index = modList.indexOf(commandInfo[1]);
        modList.get(index).minusActualTime(commandInfo[2], commandInfo[3]);
        if (toPrint) {
            System.out.println(commandInfo[2] + " hours are removed from " + commandInfo[1] + System.lineSeparator());
            storage.appendToFile(input);
        }
    }

    public ArrayList<Module> getData() {
        return modList;
    }

    public int getSize() {
        return modList.size();
    }
}

