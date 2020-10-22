package seedu.eduke8.note;

import seedu.eduke8.common.Displayable;

import java.util.ArrayList;
import java.util.logging.Logger;

public class NoteList {
    private ArrayList<Displayable> noteList;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public NoteList(ArrayList<Displayable> noteList) {
        this.noteList = noteList;
    }

    public void add(Displayable note) {
        noteList.add(note);
    }

    public void delete(int index) {
        assert (index > 0 && index < noteList.size());
        noteList.remove(index);
    }

    public int getCount() {
        return noteList.size();
    }
}
