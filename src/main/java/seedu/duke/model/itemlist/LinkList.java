

package seedu.duke.model.itemlist;

import seedu.duke.DukeException;
import seedu.duke.common.Messages;
import seedu.duke.model.item.Link;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

// @@author Cao-Zeyu

public class LinkList extends ItemList<Link> {
    //private ArrayList<Link> links;

    /**
     * Constructs an empty link list.
     */
    public LinkList() {
        items = new ArrayList<>();
    }

    /**
     * Constructs a link list with the given links.
     *
     * @param items An ArrayList of {@code Link}.
     */
    public LinkList(ArrayList<Link> items) {
        this.items = items;
    }

    /**
     * Retrieves the list of links.
     *
     * @return An ArrayList of {@code Link}.
     */
    public ArrayList<Link> getLinks() {
        return items;
    }

    /**
     * Adds a link to the link list.
     *
     * @param link the link to be added in to the list.
     */
    public void addLink(Link link) throws DukeException {
        checkLinkAlreadyExists(link);
        items.add(link);
        Ui.dukePrint(Messages.MESSAGE_ADD_LINK + link.toString() + Messages.MESSAGE_STATUS_FIRST
                + items.size() + Messages.MESSAGE_LINK_STATUS_LAST);
    }

    /**
     * Retrieves the size of the link list.
     *
     * @return the size of the link list
     */
    public int size() {
        return items.size();
    }

    /**
     * Retrieves the i-th link in the link list.
     *
     * @param i the index of the desired link.
     * @return the i-th link in the link list.
     */
    public Link get(int i) {
        return items.get(i);
    }

    /**
     * Lists all the links in the link list.
     */
    public void listLink() {
        String message = "";
        if (items.size() == 0) {
            Ui.dukePrint(Messages.MESSAGE_EMPTY_LINK_LIST);
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            message = message + "\n     " + (i + 1) + "." + items.get(i).toString();
        }
        Ui.dukePrint(Messages.MESSAGE_LINK_LIST + message);
    }

    /**
     * Deletes a link from the list, identified by the index of the link in the list.
     *
     * @param index the index of the link in the list.
     */
    public void deleteLink(int index) {
        if (index > items.size() || index < 1) {
            Ui.dukePrint(Messages.WARNING_NO_LINK);
        } else {
            Link linkRemoved = items.get(index - 1);
            Ui.dukePrint(Messages.MESSAGE_DELETE_LINK + linkRemoved.toString() + Messages.MESSAGE_STATUS_FIRST
                    + (items.size() - 1) + Messages.MESSAGE_LINK_STATUS_LAST);
            items.remove(index - 1);
        }
    }

    private void checkLinkAlreadyExists(Link link) throws DukeException {
        int count = (int) items.stream()
                .filter(existingLink -> existingLink.getModule().equals(link.getModule()))
                .filter(existingLink -> existingLink.getType().equals(link.getType()))
                .count();
        if (count != 0) {
            throw new DukeException("~Error~ Link of this lesson already exists!");
        }
    }
}
