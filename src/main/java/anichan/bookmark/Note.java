package anichan.bookmark;

import anichan.exception.AniException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Note {

    public ArrayList<String> notes;

    public Note() {
        this.notes = new ArrayList<>();
    }

    public void addNote(String note) {
        this.notes.add(note);
    }

    public String getNote(int noteIndex) {
        return notes.get(noteIndex);
    }

    public int getSize() {
        return notes.size();
    }

    public String removeNote(int noteIndex) {
        String note = notes.get(noteIndex);
        this.notes.remove(noteIndex);
        return note;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < notes.size(); i++) {
            result += notes.get(i) + System.lineSeparator();
        }
        return result;
    }
}
