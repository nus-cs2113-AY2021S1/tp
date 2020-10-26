package seedu.eduke8.common;

public interface EditableList {

    public void add(Displayable displayable);

    public Displayable delete(int index);
}
