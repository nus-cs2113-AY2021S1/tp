package seedu.duke.history;

import java.util.ArrayList;

public class HistoryList {
    private int countObjects;
    private ArrayList<History> histories;

    public HistoryList() {
        countObjects = 0;
        this.histories = new ArrayList<>();
    }

    public void add(History h) {
        countObjects++;
        this.histories.add(h);
    }

    public History get(int i) {
        return this.histories.get(i);
    }

    public void remove(int i) {
        histories.remove(i);
    }

    public int getCountObjects() {
        return countObjects;
    }
}
