package seedu.quotesify.lists;

import org.json.simple.JSONArray;
import seedu.quotesify.parser.JsonSerializer;

import java.util.ArrayList;

public abstract class QuotesifyList<T> {
    private ArrayList<T> list;

    public QuotesifyList() {
        list = new ArrayList<>();
    }

    public QuotesifyList(ArrayList<T> list) {
        this.list = list;
    }


    public ArrayList<T> getList() {
        return list;
    }

    public abstract void add(T t);

    public abstract void delete(int index);

    public abstract JSONArray toJsonArray();

    @Override
    public abstract String toString();
}
