package seedu.duke;

import java.util.ArrayList;

public class ModuleList {

    public static ArrayList<Module> modList = new ArrayList<>();

    public static ArrayList<Module> add(String input) {
        if (input.startsWith("addmod")) {
            Module s = new Module(input.substring(7));
            modList.add(s);
            System.out.println(s + " is added");
        } else if (input.startsWith("addexp")) {
            Module s = new Module(input.substring(7, 13), input.substring(14));
            modList.add(s);
            System.out.println(s + " is added");
        }
        return modList;
    }
}

