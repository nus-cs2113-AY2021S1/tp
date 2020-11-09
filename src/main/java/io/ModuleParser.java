//@@author scjx123

package io;

import java.io.IOException;
import java.io.InputStream;
import java.lang.module.ModuleDescriptor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import data.Item;
import data.SingleModule;

import javax.swing.event.ListDataEvent;


public class ModuleParser {
    /**
     * The Module name.
     */
    static String moduleName;
    /**
     * The Module code.
     */
    static String moduleCode;
    /**
     * The Module description.
     */
    static String moduleDescription;
    /**
     * The Module mc.
     */
    static String moduleMC;
    /**
     * The Module SU.
     */
    static String moduleSU;
    /**
     * The Module prerequisite.
     */
    static String modulePrerequisite;
    /**
     * The St.
     */
    static StringTokenizer st;
    /**
     * The Temp string.
     */
    static String tempString;
    private String[] input;

    /**
     * Constructor of ModuleParser.
     */
    public ModuleParser() {

    }

    /**
     * New modules are being created here.
     * Master list of modules are being created here.
     * New modules are being added here to the masterList.
     */
    public ArrayList<Item> load() throws IOException {
        InputStream is = getClass().getResourceAsStream("finalcourselist.txt");
        ArrayList<Item> masterList = new ArrayList<>();
        Scanner s = new Scanner(is);

        while (s.hasNext()) {
            tempString = s.nextLine();
            parseFile(tempString);
            SingleModule m = new SingleModule(moduleCode, moduleName, moduleDescription,
                    moduleMC, modulePrerequisite, moduleSU);
            if (moduleCode != null) {
                if (moduleSU.equals("true")) {
                    m.hasSU = true;
                } else {
                    m.hasSU = false;
                }
                masterList.add(m);
            }
            moduleCode = null;
            moduleSU = "false";
        }
        return masterList;
    }

    private boolean checkValidInput(String input, String useCase) {
        if (useCase.equals("code")) {
            if (input.length() >= 9
                    || input.length() < 5
                    || input.contains(" ")
                    || input.contains(":")) {
                return false;
            }
        }
        if (useCase.equals("mc")) {

            return false;
        }
        if (useCase.equals("name")) {
            if (input.contains(",")
                    || input.contains("\"")
                    || input.contains("/")
                    || input.contains(".")
                    || input.contains("(")
                    || input.contains("?")
                    || input.contains("'")) {
                return true;
            }
        }
        if (!input.contains(".")
                && !input.contains("(")
                && !input.contains("\"")
                && !input.contains("OR")
                && !input.contains("?")) {
            return true;
        } else {
            return false;
        }
    }

    private void parseFile(String tempString) {

        if (tempString != null) {
            input = tempString.split("\t");
        }

        if (input.length >= 1) {
            for (int i = 0; i < input.length; i++) {
                if (input[i].matches("[0-9]+")) {
                    moduleMC = input[i];
                    moduleDescription = input[i - 1];
                    if (i + 3 <= input.length) {
                        modulePrerequisite = input[i + 2];
                    }
                    if (i + 1 != input.length && input[i + 1].equals("TRUE")) {
                        moduleSU = "true";
                    }
                }
            }
        }

        if (checkValidInput(input[0], "code")) {
            moduleCode = input[0];
            if (input.length > 1 && checkValidInput(input[1], "name")) {
                moduleName = input[1];
            }
        }
    }
}
//@@author