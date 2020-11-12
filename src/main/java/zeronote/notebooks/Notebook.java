package zeronote.notebooks;

import java.util.ArrayList;

import zeronote.exceptions.DuplicateFoundException;
import zeronote.exceptions.InvalidTagException;

/**
 * Class to represent a notebook that stores an array of sections.
 */
public class Notebook {
    private String title;
    private String tag = "";
    private final ArrayList<Section> sectionArrayList;
    private static final int notFound = -1;

    public Notebook(String title) {
        this.title = title;
        sectionArrayList = new ArrayList<>();
    }

    //@@author Lusi711

    /**
     * Sets the tag of this notebook.
     *
     * @param tag the tag of the notebook.
     * @throws InvalidTagException when the user inputs an empty tag.
     */
    public void setTag(String tag) throws InvalidTagException {
        if (!tag.equals("")) {
            this.tag = tag;
        } else {
            throw new InvalidTagException("tag /t" + tag);
        }
    }

    /**
     * Gets the tag of this notebook.
     *
     * @return the tag of the notebook.
     */
    public String getTag() {
        return tag;
    }

    // @@author longngng

    /**
     * Get the title of this notebook.
     *
     * @return the title of the notebook.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of this notebook.
     *
     * @param title the desired new title of the notebook.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    //@@author neilbaner
    public ArrayList<Section> getSectionArrayList() {
        return sectionArrayList;
    }

    /**
     * Get the section at a particular index in the ArrayList. Use findSection() to get an index from a given title.
     *
     * @param index the index of the section to be found.
     */
    public Section getSectionAtIndex(int index) {
        return sectionArrayList.get(index);
    }

    // @@author chuckiex3

    /**
     * Add a new section with a given title to this notebook.
     *
     * @param title the title of the section to be added.
     * @throws DuplicateFoundException when the user inputs a section title that has already been used.
     */
    public void addSection(String title) throws DuplicateFoundException {
        for (Section s : sectionArrayList) {
            if (s.getTitle().equals(title)) {
                throw new DuplicateFoundException(title);
            }
        }
        sectionArrayList.add(new Section(title));
    }

    public void addSection(Section s) {
        sectionArrayList.add(s);
    }

    // @@author chuckiex3

    /**
     * Find a section with a given title in this notebook.
     *
     * @param searchKey the title of the section to search for in the notebook.
     * @return the index of the section with the given title, -1 if not found.
     */
    public int findSection(String searchKey) {
        int index = 0;
        for (Section s : sectionArrayList) {
            if (s.getTitle().equals(searchKey)) {
                return index;
            }
            index++;
        }
        return notFound;
    }

    // @@author Lusi711

    /**
     * Remove a section from the notebook.
     *
     * @param index the index of the section to remove.
     */
    public Section removeSection(int index) {
        return sectionArrayList.remove(index);
    }

    // @@author neilbaner
    public String serialize() {
        StringBuilder serialized = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        serialized.append(title);
        serialized.append(lineSeparator);
        serialized.append(sectionArrayList.size());
        serialized.append(lineSeparator);
        for (Section s : sectionArrayList) {
            serialized.append(s.serialize());
        }
        return serialized.toString();
    }
}
