package seedu.duke;

import java.util.ArrayList;

public class VoiceActor extends Human {
    protected ArrayList<Character> characters = new ArrayList<>();

    public VoiceActor(String name) {
        super(name);
    }

    public void addCharacter(Character newCharacter) {
        characters.add(newCharacter);
    }

    public void printCharacters() {
        System.out.println("Characters voiced by " + name + " are:");
        for (int i = 0; i < characters.size(); i++) {
            System.out.println(i + 1 + ". " + characters.get(i));
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
