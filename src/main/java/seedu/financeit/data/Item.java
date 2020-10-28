package seedu.financeit.data;

/**
 * Item is a class that is element to ItemList.
 */
public abstract class Item {

    protected int index = -1;

    public Item() {
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        assert this.index != -1;
        return this.index;
    }

    public abstract String getName();
}
