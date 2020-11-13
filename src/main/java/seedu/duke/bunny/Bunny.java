package seedu.duke.bunny;

import java.util.ArrayList;

/**
 * Class for plot bunny.
 */
public class Bunny {
    /** A brief description of the idea. */
    private String idea;
    /** The genre of the idea. */
    private String genre;
    /** The Characters used in the idea. */
    private ArrayList<Character> characters;

    public Bunny(String idea) {
        setIdea(idea);
        setGenre("none");
    }

    public Bunny(String idea, String genre) {
        setIdea(idea);
        setGenre(genre);
    }

    public Bunny(String idea, String genre, ArrayList<Character> characters) {
        setIdea(idea);
        setGenre(genre);
        setCharacters(characters);
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    // todo: add character list in version 2
    public String getDescription() {
        String description = "  idea: " + idea + "\n";
        description = description + "  genre: " + genre + "\n";

        return description;
    }
}
