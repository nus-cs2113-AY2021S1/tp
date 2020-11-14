package seedu.quotesify.lists;

import org.json.simple.JSONArray;

import java.util.ArrayList;

/**
 * Represents all lists.
 *
 * @param <T> Quotesify model objects.
 */
public abstract class QuotesifyList<T> {
    private ArrayList<T> list;

    /**
     * Default constructor for quotesify list.
     */
    public QuotesifyList() {
        list = new ArrayList<>();
    }

    /**
     * Constructor for quotesify list with an existing list.
     *
     * @param list An array list of model objects.
     */
    public QuotesifyList(ArrayList<T> list) {
        this.list = list;
    }

    /**
     * Returns an array list of model objects.
     *
     * @return An array list of model objects.
     */
    public ArrayList<T> getList() {
        return list;
    }

    /**
     * Adds a model object into the list.
     *
     * @param t Model object.
     */
    public abstract void add(T t);

    /**
     * Removes a model object from the list based on index.
     *
     * @param index Item index in the list.
     */
    public abstract void delete(int index);

    /**
     * Returns a JSONArray of the list.
     *
     * @return JSONArray object.
     */
    public abstract JSONArray toJsonArray();

    /**
     * Returns details of a list.
     *
     * @return List details.
     */
    @Override
    public abstract String toString();
}
