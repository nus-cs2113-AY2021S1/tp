package seedu.modtracker;

import java.util.ArrayList;

public class ModuleList {

    public static ArrayList<Module> modList = new ArrayList<>();

    public static void addMod(String input) {
        Module currentModule = new Module(input.substring(7));
        modList.add(currentModule);
        System.out.println(currentModule + " is added" + System.lineSeparator());
    }

    public static void addExp(String input) {
        String[] modInfo = input.split(" ", 3);
        Module currentMod = new Module(modInfo[1], modInfo[2]);
        modList.add(currentMod);
        System.out.println(currentMod + " is added" + System.lineSeparator());
    }

    public static ArrayList<Module> getData() {
        return modList;
    }
}

