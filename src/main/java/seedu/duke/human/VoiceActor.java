package seedu.duke.human;

import seedu.duke.exception.AniException;

import java.util.ArrayList;

public class VoiceActor extends Human {
    protected ArrayList<Character> characters = new ArrayList<>();

    public VoiceActor(String name) throws AniException {
        super(name);
    }

    public void addCharacter(Character newCharacter) {
        characters.add(newCharacter);
    }


    public int getTotalCharacters() {
        return characters.size();
    }

    @Override
    public String toString() {
        return name;
    }
}
