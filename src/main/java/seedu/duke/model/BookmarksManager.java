package seedu.duke.model;

import seedu.duke.exception.DukeException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BookmarksManager {

    private Bookmarks bookmarks;

    public BookmarksManager() {
        load();
    }

    public Bookmarks getBookmarks() {
        return bookmarks;
    }

    public void addToBookmarks(String symbol) throws DukeException {
        bookmarks.addToBookmarks(symbol);
        save();
    }

    public void removeBookmark(String symbol) throws DukeException {
        bookmarks.removeBookmark(symbol);
        save();
    }

    private void load() {
        try {
            FileInputStream fis = new FileInputStream("data/bookmarks.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            bookmarks = (Bookmarks) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            bookmarks = new Bookmarks();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidClassException e) {
            bookmarks = new Bookmarks();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try {
            FileOutputStream fos = new FileOutputStream("data/bookmarks.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bookmarks);
            oos.close();
            fos.close();
            System.out.println("Serialization Done!!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
