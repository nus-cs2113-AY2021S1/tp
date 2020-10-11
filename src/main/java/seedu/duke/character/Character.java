package seedu.duke.character;

import java.util.ArrayList;

/**
 * Class for character.
 */
public class Character {
    private String characterName;
    private ArrayList<String> aliases;
    private ArrayList<Character> friends;
    private ArrayList<Character> enemies;
    private int age;

    public Character(String characterName) {
        setCharacterName(characterName);
    }

    public Character(String characterName, ArrayList<String> aliases, ArrayList<Character> friends,
                     ArrayList<Character> enemies, int age) {
        setCharacterName(characterName);
        setAliases(aliases);
        setFriends(friends);
        setEnemies(enemies);
        setAge(age);
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setAliases(ArrayList<String> aliases) {
        this.aliases = aliases;
    }

    public void setFriends(ArrayList<Character> friends) {
        this.friends = friends;
    }

    public void setEnemies(ArrayList<Character> enemies) {
        this.enemies = enemies;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCharacterName() {
        return characterName;
    }

    public ArrayList<String> getAliases() {
        return aliases;
    }

    public ArrayList<Character> getFriends() {
        return friends;
    }

    public ArrayList<Character> getEnemies() {
        return enemies;
    }

    public int getAge() {
        return age;
    }
}
