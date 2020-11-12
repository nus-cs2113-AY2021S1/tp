package zeronote.notebooks;

import java.util.ArrayList;

import zeronote.exceptions.DuplicateFoundException;
import zeronote.exceptions.InvalidPageException;
import zeronote.exceptions.InvalidTagException;

// @@author neilbaner

public class Section {
    private String title;
    private String tag = "";
    private final ArrayList<Page> pageArrayList;
    private static final int notFound = -1;

    public Section(String title) {
        this.title = title;
        pageArrayList = new ArrayList<>();
    }

    // @@author longngng
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // @@author chuckiex3

    /**
     * Add a new page with a given title to this section.
     *
     * @param title the title of the page to be added.
     * @throws DuplicateFoundException when the user inputs a page title that has already been used.
     */
    public void addPage(String title, String content) throws DuplicateFoundException {
        for (Page p : pageArrayList) {
            if (p.getTitle().equals(title)) {
                throw new DuplicateFoundException(title);
            }
        }
        pageArrayList.add(new Page(title, content));
    }

    //@@author neilbaner
    public void addPage(Page p) {
        pageArrayList.add(p);
    }

    //@@author chuckiex3

    /**
     * Find a page with a given title in this section.
     *
     * @param searchKey the title of the page to search for in the section.
     * @return the index of the page with the given title, -1 if not found.
     */
    public int findPage(String searchKey) {
        int index = 0;
        for (Page p : pageArrayList) {
            if (p.getTitle().equals(searchKey)) {
                return index;
            }
            index++;
        }
        return notFound;
    }

    //@@author Lusi711

    /**
     * Deletes a page with a specified index from this section.
     *
     * @param index the index of the page to deleted.
     * @return the page that is deleted.
     * @throws InvalidPageException when the specified page does not exist.
     */
    public Page removePage(int index) throws InvalidPageException {
        try {
            return pageArrayList.remove(index);
        } catch (Exception e) {
            throw new InvalidPageException(Integer.toString(index + 1));
        }
    }

    // @@author neilbaner
    public Page getPageAtIndex(int index) {
        return pageArrayList.get(index);
    }

    // @@author neilbaner
    public ArrayList<Page> getPageArrayList() {
        return pageArrayList;
    }

    //@@author Lusi711

    /**
     * Sets the tag for this page.
     *
     * @param tag the tag for the page.
     * @throws InvalidTagException when the user enters an empty tag.
     */
    public void setTag(String tag) throws InvalidTagException {
        if (!tag.equals("")) {
            this.tag = tag;
        } else {
            throw new InvalidTagException("tag /t" + tag);
        }
    }

    /**
     * Gets the tag for this page.
     *
     * @return the tag of this page.
     */
    public String getTag() {
        return tag;
    }

    // @@author neilbaner
    public String serialize() {
        StringBuilder serialized = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        serialized.append(title);
        serialized.append(lineSeparator);
        serialized.append(pageArrayList.size());
        serialized.append(lineSeparator);
        for (Page p : pageArrayList) {
            serialized.append(p.serialize());
        }
        return serialized.toString();
    }
}
