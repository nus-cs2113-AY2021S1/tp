package anichan.human;

import anichan.exception.AniException;

/**
 * Abstract class to represent a Human.
 */
public abstract class Human {
    protected String name;

    /**
     * Creates an instance of a Human object.
     *
     * @param name of the Human
     * @throws AniException if name is empty
     */
    public Human(String name) throws AniException {
        if (!name.isEmpty()) {
            setName(name);
        } else {
            throw new AniException("Is your name empty?");
        }
    }

    /**
     * Returns name of the Human.
     *
     * @return name of Human
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of the Human.
     *
     * @param name of Human
     */
    public void setName(String name) {
        this.name = name;
    }

}
