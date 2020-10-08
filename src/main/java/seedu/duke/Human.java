package seedu.duke;

public abstract class Human {
    protected String name;

    public Human(String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
