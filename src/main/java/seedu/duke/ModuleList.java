package seedu.duke;

import java.util.ArrayList;

public class ModuleList {

    public static ArrayList<Module> modList = new ArrayList<>();

    public static void addMod(String input) {
        Module s = new Module(input.substring(7));
        modList.add(s);
        System.out.println(s + " is added");
    }

    public static void addExp(String input) {
        String[] modInfo = input.split(" ", 3);
        Module s = new Module(modInfo[1], modInfo[2]);
        modList.add(s);
        System.out.println(s + " is added");
    }

    public static ArrayList<Module> getData() {
        return modList;
    }
}

