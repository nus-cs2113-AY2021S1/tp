package seedu.eduke8.bookmark;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.common.EditableList;
import seedu.eduke8.question.Question;

import java.util.ArrayList;

public class BookmarkList implements DisplayableList, EditableList {

    private ArrayList<Displayable> bookmarks;

    public BookmarkList() {
        bookmarks = new ArrayList<>();
    }

    @Override
    public ArrayList<Displayable> getInnerList() {
        return bookmarks;
    }

    @Override
    public void add(Displayable question) {
        assert question != null;  // Exception in future if user can add question
        bookmarks.add(question);
        ((Question) question).markAsBookmarked();
    }

    @Override
    public Displayable delete(int index) {
        assert (index > 0 && index < bookmarks.size());   // Exception in future if user can delete question
        return bookmarks.remove(index);
    }

    @Override
    public Displayable find(String description) {
        for (Displayable question : bookmarks) {
            if (description.equalsIgnoreCase(question.getDescription())) {
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
