package seedu.eduke8.bookmark;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;

import java.util.ArrayList;

public class BookmarkList implements DisplayableList {

    private ArrayList<Displayable> bookmarks = new ArrayList<>();

    @Override
    public ArrayList<Displayable> getInnerList() {
        return bookmarks;
    }


    public void add(Displayable question) {
        assert question != null;  // Exception in future if user can add question
        bookmarks.add(question);
    }


    public void delete(int index) {
        assert (index > 0 && index < bookmarks.size());   // Exception in future if user can delete question
        bookmarks.remove(index);
    }

    @Override
    public Displayable find(String description) {
        for (Displayable question : bookmarks) {
            if (description.equals(question.getDescription())) {
                return question;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return bookmarks.size();
    }
}
