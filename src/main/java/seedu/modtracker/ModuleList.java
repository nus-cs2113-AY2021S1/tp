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

    public static void addTime(String input) {
        String[] commandInfo = input.split(" ", 4);
        Module currentModule = new Module(commandInfo[1]);
        int index = modList.indexOf(currentModule);
        System.out.println(index + commandInfo[1]);
        modList.get(index).addActualTime(commandInfo[2], commandInfo[3]);
        System.out.println(commandInfo[2] + " hours are added to" + commandInfo[1] + System.lineSeparator());
    }

    public static void minusTime(String input) {
        String[] commandInfo = input.split(" ", 4);
        int index = modList.indexOf(commandInfo[1]);
        modList.get(index).minusActualTime(commandInfo[2], commandInfo[3]);
        System.out.println(commandInfo[2] + " hours are removed from" + commandInfo[1] + System.lineSeparator());
    }




    public static ArrayList<Module> getData() {
        return modList;
    }
}

