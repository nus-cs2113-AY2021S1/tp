package seedu.duke.bunnylist;

import seedu.duke.bunny.Bunny;
import seedu.duke.exceptions.BunnyIdeaMissingException;
import seedu.duke.exceptions.CommandMissingArgumentsException;
import seedu.duke.exceptions.MissingParamsException;
import seedu.duke.ui.UI;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.duke.constants.Tags.GENRE_TAG;
import static seedu.duke.constants.Tags.IDEA_TAG;
import static seedu.duke.parsers.Parsers.parseSingleCharacterTaggedParamsFromUserInput;

public class BunnyList {
    public static ArrayList<Bunny> bunniesList = new ArrayList<>();

    public static void addBunny(String userInput) throws CommandMissingArgumentsException, BunnyIdeaMissingException {
        // for returning filter options parsed from the user input
        HashMap<String, String> commandArguments = new HashMap<>();
        String idea;
        String genre = "";

        // parse bunny command into segments
        try {
            parseSingleCharacterTaggedParamsFromUserInput(userInput, commandArguments);
        } catch (MissingParamsException e) {
            throw new CommandMissingArgumentsException();
        }

        // check if there is task type param
        if (commandArguments.containsKey(IDEA_TAG)) {
            idea = commandArguments.get(IDEA_TAG);
        } else {
            throw new BunnyIdeaMissingException();
        }

        if (commandArguments.containsKey(GENRE_TAG)) {
            genre = commandArguments.get(GENRE_TAG);
        }

        // todo: add the character list to bunny in ver 2
        //ArrayList<Character> characters = new ArrayList<>();
        Bunny newBunny = new Bunny(idea.trim(), genre.trim());
        bunniesList.add(newBunny);
        UI.addBunnyMessage(newBunny.getDescription());

    }

    public static void listBunny() {
        UI.listBunnyMessage();
        for (int i = 0; i < bunniesList.size(); i++) {
            System.out.println((i + 1) + ".\n" + bunniesList.get(i).getDescription());
        }
    }

}
