package seedu.duke.names;

import seedu.duke.database.NamesDB;
import seedu.duke.exceptions.NameException;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Names {
    protected static ArrayList<String> nameList = new ArrayList<>();

    /**
     * Returns a random name from the list of names stored in the database.
     **/
    public static void getName() throws NameException {
        NamesDB.loadDB(nameList);
        if (nameList.size() > 0) {
            Random randomGenerator = new Random();
            int index = randomGenerator.nextInt(nameList.size());
            System.out.println(nameList.get(index));
        } else {
            throw new NameException("No stored Names! Try adding some names first!");
        }
    }

    /**
     * Searches for names based on the given input.
     **/
    public static void filterNames(String match) throws NameException {
        NamesDB.loadDB(nameList);
        if (nameList.size() > 0) {
            String toMatch = match.toLowerCase().replace("filter name", "").trim();
            //System.out.println("Match: " + toMatch);
            for (String name : (nameList.stream()
                    .filter(x -> x.toLowerCase().contains(toMatch))
                    .collect(Collectors.toList()))) {
                System.out.println(name);
            }
        } else {
            throw new NameException("No stored Names! Try adding some names first!");
        }
    }

    /**
     * Displays all names stored in the database.
     **/
    public static void listNames() throws NameException {
        NamesDB.loadDB(nameList);
        if (nameList.size() > 0) {
            for (int i = 0; i < nameList.size(); i++) {
                System.out.println(i + 1 + ". " + nameList.get(i));
            }
        } else {
            throw new NameException("No stored Names! Try adding some names first!");
        }
    }

    /**
     * Add name to the list of names.
     **/
    public static void addName(String name) throws NameException {
        NamesDB.loadDB(nameList);
        String nameToAdd = name.replaceAll("(?i)add name", "").trim();
        if (nameToAdd.length() > 0) {
            assert nameToAdd.length() > 0;
            nameList.add(nameToAdd);
            NamesDB.updateDB(nameList);
            System.out.println(nameToAdd + " has been added to the Names list!");
        } else {
            throw new NameException("Please enter a valid name!");
        }
    }

    /**
     * Delete name from the list of names.
     **/
    public static void deleteName(String index) throws NameException {
        NamesDB.loadDB(nameList);
        if (nameList.size() > 0) {
            assert nameList.size() > 0;
            try {
                int indexToDelete = Integer.parseInt(index.replaceAll("(?i)delete name", "").trim()) - 1;
                String nameToDelete = nameList.get(indexToDelete);
                nameList.remove(indexToDelete);
                NamesDB.updateDB(nameList);
                System.out.println(nameToDelete + " has been deleted from the Names list!");
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new NameException("Please enter a valid index!");
            }
        } else {
            throw new NameException("No stored Names! Try adding some names first!");
        }
    }
}
