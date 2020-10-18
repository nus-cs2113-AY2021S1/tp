package seedu.duke.names;

import seedu.duke.exceptions.NameException;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import static seedu.duke.database.NamesDB.loadDB;

public class Names {
    protected static ArrayList<String> names = new ArrayList<>();

    public static void getName() throws NameException {
        loadDB(names);
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(names.size());
        System.out.println(names.get(index));
    }

    public static void filterNames(String match) throws NameException {
        loadDB(names);
        String toMatch = match.toLowerCase().replace("filter names","").trim();
        //System.out.println("Match: " + toMatch);
        System.out.println(names.stream()
                .filter(x -> x.toLowerCase().contains(toMatch))
                .collect(Collectors.toList()));
    }

    public static void listNames() throws NameException {
        loadDB(names);
        System.out.println(names);
    }
}
