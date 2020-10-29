package seedu.eduke8.note;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.common.EditableList;
import seedu.eduke8.exception.Eduke8Exception;

import java.util.ArrayList;
import java.util.logging.Logger;

public class NoteList implements DisplayableList, EditableList {
    private ArrayList<Displayable> notes;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static String INVALID_INT = "Invalid index";

    public NoteList(ArrayList<Displayable> notes) {
        this.notes = notes;
    }

    @Override
    public void add(Displayable note) {
        notes.add(note);
    }

    @Override
    public Displayable delete(int index) {
        assert (index > 0 && index < notes.size());
        return notes.remove(index);
    }

    @Override
    public ArrayList<Displayable> getInnerList() {
        return notes;
    }

    @Override
    public Displayable find(String input) throws Eduke8Exception {
        for (Displayable note : notes) {
            if (input.equalsIgnoreCase(note.getDescription())) {
                return note;
            }
        }
        return null;
    }

    public Note get(int index) {
        return (Note) notes.get(index);
    }

    public int getCount() {
        return notes.size();
    }
}
