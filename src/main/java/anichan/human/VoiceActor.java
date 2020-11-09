package anichan.human;

import anichan.exception.AniException;

import java.util.ArrayList;

/**
 * Represents a Voice Actor from AniList data.
 */
public class VoiceActor extends Human {
    protected ArrayList<Character> characters = new ArrayList<>();

    /**
     * Creates an instance of Voice Actor.
     *
     * @param name name of the Voice Actor
     * @throws AniException if name is invalid
     */
    public VoiceActor(String name) throws AniException {
        super(name);
    }

    /**
     * Adds Character to Voice actor.
     *
     * @param newCharacter Character who is voiced by this Voice Actor
     */
    public void addCharacter(Character newCharacter) {
        characters.add(newCharacter);
    }

    /**
     * Gets total number of Characters which was voiced by this Voice Actor.
     *
     * @return number of voice actors
     */
    public int getTotalCharacters() {
        return characters.size();
    }

    @Override
    public String toString() {
        return name;
    }
}
