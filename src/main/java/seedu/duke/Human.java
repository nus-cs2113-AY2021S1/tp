package seedu.duke;

public abstract class Human {
    protected String name;

    public Human(String name) throws DukeException {
        if (!name.isEmpty()) {
            setName(name);
        } else {
            throw new DukeException("Is your name empty?");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
