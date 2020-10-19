package manager.history;

import java.util.ArrayList;

public class HistoryList {
    private final ArrayList<History> histories;

    public HistoryList() {
        histories = new ArrayList<>();
    }

    public HistoryList(ArrayList<History> historyList) {
        this.histories = historyList;
    }

    public void addHistory(History history) {
        histories.add(history);
    }

    public int getHistoryCount() {
        return histories.size();
    }

    public History getHistory(int index) {
        return histories.get(index);
    }
}
