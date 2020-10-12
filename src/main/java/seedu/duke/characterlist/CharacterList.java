package seedu.duke.characterlist;

import seedu.duke.character.Character;
import seedu.duke.exceptions.CharacterAlreadyExistException;
import seedu.duke.exceptions.CharacterNameMissingException;
import seedu.duke.exceptions.CommandMissingArgumentsException;
import seedu.duke.exceptions.MissingParamsException;
import seedu.duke.ui.UI;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.duke.constants.Tags.NAME_TAG;
import static seedu.duke.parsers.Parsers.parseSingleCharacterTaggedParamsFromUserInput;

public class CharacterList {
    public static ArrayList<Character> characters = new ArrayList<>();

    public static void addCharacter(String userInput)
            throws CommandMissingArgumentsException,
            CharacterNameMissingException,
            CharacterAlreadyExistException {

        // for returning filter options parsed from the user input
        HashMap<String, String> commandArguments = new HashMap<>();
        String name;
        int age = -1;
        ArrayList<String> aliases = new ArrayList<>();
        Character newCharacter;

        // parse character command into segments
        try {
            parseSingleCharacterTaggedParamsFromUserInput(userInput, commandArguments);
        } catch (MissingParamsException e) {
            throw new CommandMissingArgumentsException();
        }

        // check if there is task type param
        if (commandArguments.containsKey(NAME_TAG)) {
            name = commandArguments.get(NAME_TAG);
        } else {
            throw new CharacterNameMissingException();
        }

        // check that character doesn't already exist in the list
        if (characterNameInList(name)) {
            throw new CharacterAlreadyExistException();
        }

        newCharacter = new Character(name);
        characters.add(newCharacter);
        UI.addBunnyMessage(newCharacter.getCharacterName());

    }

    public static void listCharacter() {
        UI.listCharacterMessage();
        for (int i = 0; i < characters.size(); i++) {
            System.out.println((i + 1) + ".\n" + characters.get(i).getCharacterName());
        }
    }

    public static boolean characterNameInList(String name) {
        for (Character character : characters) {
            if (name.equals(character.getCharacterName())) {
                return true;
            }
        }
        return false;
    }
}
