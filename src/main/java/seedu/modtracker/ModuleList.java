package seedu.modtracker;

import java.util.ArrayList;

public class ModuleList {

    public static ArrayList<Module> modList = new ArrayList<>();

    public void addMod(String input) {
        Module currentModule = new Module(input.substring(7));
        modList.add(currentModule);
        System.out.println(currentModule + " is added" + System.lineSeparator());
    }

    public void addExp(String input) {
        String[] modInfo = input.split(" ", 3);
        Module currentMod = new Module(modInfo[1], modInfo[2]);
        modList.add(currentMod);
        System.out.println(currentMod + " is added" + System.lineSeparator());
    }

    public ArrayList<Module> getData() {
        return modList;
    }
}

