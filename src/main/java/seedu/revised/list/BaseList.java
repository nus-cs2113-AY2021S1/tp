package seedu.revised.list;

import java.util.List;

public abstract class BaseList<T> {
    protected List<T> list;

    public BaseList(List<T> list) {
        this.list = list;
    }

    /**
     * Adds an item into the list.
     *
     * @param item The item to add into the list
     */
    public void add(T item) {
        list.add(item);
    }

    /**
     * Gets the list.
     *
     * @return the list of items
     */
    public List<T> getList() {
        return list;
    }
}
