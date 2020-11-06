package seedu.financeit.data;

import seedu.financeit.common.Common;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * List class that stores Item instances as its elements.
 * Extends ParamHandler so it is able to handle params from a CommandPacket.
 */
public abstract class ItemList {
    protected Item currItem;
    protected ArrayList<Item> items = new ArrayList<>();
    protected int indexToModify;
    protected boolean isIndexToModifySet;
    protected String paramTypeFirst;

    public ItemList() {
    }

    public void addItem(Item item) {
        int index = this.getItemsSize();
        item.setIndex(index);
        this.items.add(item);
    }

    public void setIndexToModify(int index, String paramType) throws ParseFailParamException {
        if (index != indexToModify && isIndexToModifySet) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                "Conflicting reference from two separate params!",
                "Please reference to item with one param only.",
                // Add 1 to account for list index offset
                String.format("[%s]: %s", paramTypeFirst, indexToModify + 1),
                String.format("[%s]: %s", paramType, index + 1));
            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG,
                String.format("The first param, [%s] will be used instead.", paramTypeFirst));
            isIndexToModifySet = false;
            throw new ParseFailParamException(paramType);
        }
        isIndexToModifySet = true;
        paramTypeFirst = paramType;
        this.indexToModify = index;
    }

    /**
     * Add the given item and sort the list according to the given comparator.
     *
     * @param item - Item to be added
     * @param comparator - Comparator object to determine sort order
     */
    public void addItemAndSort(Item item, Comparator comparator) {
        this.items.add(item);
        items.sort(comparator);
    }

    public Item getCurrItem() {
        return this.currItem;
    }

    public void setCurrItem(Item item) {
        this.currItem = item;
    }

    public Item popCurrItem() {
        Item tempItem = this.currItem;
        this.currItem = null;
        return tempItem;
    }

    public int getItemsSize() {
        return this.items.size();
    }

    public int findIndexOfItem(Item item) {
        return this.items.indexOf(item);
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public boolean isItemDuplicate(Item item) {
        for (Item i : this.items) {
            if (i.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Provides a reference to the specified item at the zero-based index.
     * Requires index to have been parsed from user-input, else it will
     * attempt to access index -1.
     *
     * @throws IndexOutOfBoundsException If no index has been parsed yet and
     *                                   it attempts to access index -1.
     */
    public Item getItemAtCurrIndex() throws IndexOutOfBoundsException {
        assert this.indexToModify >= 0;
        int index = this.indexToModify;
        this.isIndexToModifySet = false;
        return this.items.get(index);
    }

    //Manually specify index in the code, if necessary
    public Item getItemAtCurrIndex(int index) throws IndexOutOfBoundsException {
        assert index < items.size();
        Item item =  this.items.get(index);
        this.isIndexToModifySet = false;
        return item;
    }

    public void removeAllItems() {
        this.items.clear();
    }

    /**
     * Removes the specified item at the zero-based index. Requires index to
     * have been parsed from user-input, else it will attempt to access index -1.
     *
     * @throws IndexOutOfBoundsException If no index has been parsed yet and
     *                                   it attempts to access index -1.
     */
    public void removeItemAtCurrIndex() throws IndexOutOfBoundsException {
        assert this.indexToModify >= 0;
        this.items.remove(this.indexToModify);
    }

    //Manually specify index in the code, if necessary
    public void removeItemAtCurrIndex(int index) throws IndexOutOfBoundsException {
        assert index < this.items.size();
        this.items.remove(index);
    }

    /**
     * Prints all items that are in the ItemList instance.
     */
    public abstract void printList();
}
