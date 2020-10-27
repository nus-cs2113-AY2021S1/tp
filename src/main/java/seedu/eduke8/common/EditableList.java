package seedu.eduke8.common;

public interface EditableList {

    void add(Displayable displayable);

    Displayable delete(int index);
}
