package anichan.human;

import anichan.exception.AniException;

import java.util.ArrayList;

/**
 * Represents a Character from AniList data.
 */
public class Character extends Human {
    protected ArrayList<VoiceActor> voiceActors = new ArrayList<>();

    /**
     * Creates an instance of Character.
     *
     * @param name name of the Character
     * @throws AniException if name is invalid
     */
    public Character(String name) throws AniException {
        super(name);
    }

    /**
     * Adds voice actor to Character.
     *
     * @param newVoiceActor voice actor who voiced this character
     */
    public void addVoiceActor(VoiceActor newVoiceActor) {
        voiceActors.add(newVoiceActor);
    }

    /**
     * Gets total number of voice actors who played as this Character.
     *
     * @return number of voice actors
     */
    public int getTotalVoiceActors() {
        return voiceActors.size();
    }

    @Override
    public String toString() {
        return name;
    }
}
