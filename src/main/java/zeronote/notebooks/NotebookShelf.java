package zeronote.notebooks;

import zeronote.exceptions.DuplicateFoundException;

import java.util.ArrayList;

// @@author neilbaner

/**
 * Class to represent a bookshelf that stores Notebooks.
 */
public class NotebookShelf {
    private final ArrayList<Notebook> notebooksArrayList;
    private static final int notFound = -1;

    public NotebookShelf() {
        notebooksArrayList = new ArrayList<>();
    }

    //@@author longngng
    public ArrayList<Notebook> getNotebooksArrayList() {
        return notebooksArrayList;
    }

    //@@author neilbaner
    /**
     * Find the notebook on the shelf with the given title.
     *
     * @param title the title of the notebook the user wants to find.
     * @return the index of the notebook the user wants to find.
     */
    public int findNotebook(String title) {
        int index = 0;
        for (Notebook n : notebooksArrayList) {
            if (n.getTitle().equals(title)) {
                return index;
            }
            index++;
        }
        return notFound;
    }

    /**
     * Get the notebook at a particular index in the ArrayList. Use findNotebook() to get an index from a given title.
     *
     * @param index the index of the notebook to be found.
     */
    public Notebook getNotebookAtIndex(int index) {
        return notebooksArrayList.get(index);
    }

    //@@author chuckiex3
    /**
     * Add a notebook with a given title to the shelf.
     *
     * @param title the title of the notebook to be added
     * @throws DuplicateFoundException when the notebook title input by the user has already been used.
     */
    public void addNotebook(String title) throws DuplicateFoundException {
        for (Notebook n : notebooksArrayList) {
            if (n.getTitle().equals(title)) {
                throw new DuplicateFoundException(title);
            }
        }
        notebooksArrayList.add(new Notebook(title));
    }

    public void addNotebook(Notebook n) {
        notebooksArrayList.add(n);
    }

    //@@author Lusi711
    /**
     * Removes a notebook with the specified index from the shelf.
     *
     * @param index the index of the notebook to be removed.
     * @return the notebook that is removed.
     */
    public Notebook removeNotebook(int index) {
        return notebooksArrayList.remove(index);
    }

    //@@author neilbaner
    public String serialize() {
        StringBuilder serialized = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        serialized.append(notebooksArrayList.size());
        serialized.append(lineSeparator);
        for (Notebook n : notebooksArrayList) {
            serialized.append(n.serialize());
        }
        return serialized.toString();
    }
}
