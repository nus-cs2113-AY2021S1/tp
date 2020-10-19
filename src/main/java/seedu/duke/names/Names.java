package seedu.duke.names;

import seedu.duke.database.NamesDB;
import seedu.duke.exceptions.NameException;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Names {
    protected static ArrayList<String> names = new ArrayList<>();

    /**
     * Returns a random name from the list of names stored in the database.
     **/
    public static void getName() throws NameException {
        NamesDB.loadDB(names);
        if (names.size() > 0) {
            Random randomGenerator = new Random();
            int index = randomGenerator.nextInt(names.size());
            System.out.println(names.get(index));
        } else {
            System.out.println("No stored Names! Try adding some names first!");
        }
    }

    /**
     * Searches for names based on the given input.
     **/
    public static void filterNames(String match) throws NameException {
        NamesDB.loadDB(names);
        if (names.size() > 0) {
            String toMatch = match.toLowerCase().replace("filter name", "").trim();
            //System.out.println("Match: " + toMatch);
            for (String name : (names.stream()
                    .filter(x -> x.toLowerCase().contains(toMatch))
                    .collect(Collectors.toList()))) {
                System.out.println(name);
            }
        } else {
            System.out.println("No stored Names! Try adding some names first!");
        }
    }

    /**
     * Displays all names stored in the database.
     **/
    public static void listNames() throws NameException {
        NamesDB.loadDB(names);
        if (names.size() > 0) {
            for (int i = 0; i < names.size(); i++) {
                System.out.println(i + 1 + ". " + names.get(i));
            }
        } else {
            System.out.println("No stored Names! Try adding some names first!");
        }
    }

    /**
     * Add name to the list of names.
     **/
    public static void addName(String name) throws NameException {
        NamesDB.loadDB(names);
        String nameToAdd = name.replaceAll("(?i)add name", "").trim();
        names.add(nameToAdd);
        NamesDB.updateDB(names);
        System.out.println(nameToAdd + " has been added to the Names list!");
    }

    /**
     * Delete name from the list of names.
     **/
    public static void deleteName(String index) throws NameException {
        NamesDB.loadDB(names);
        if (names.size() > 0) {
            try {
                int indexToDelete = Integer.parseInt(index.replaceAll("(?i)delete name", "").trim()) - 1;
                String nameToDelete = names.get(indexToDelete);
                names.remove(indexToDelete);
                NamesDB.updateDB(names);
                System.out.println(nameToDelete + " has been deleted from the Names list!");
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Please enter a valid index!");
            }
        } else {
            System.out.println("No stored Names! Try adding some names first!");
        }
    }
}
