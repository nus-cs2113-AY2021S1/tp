package seedu.ui;

public class TaskDisplay {
    private DisplayMode displayMode = DisplayMode.ALL;
    private SortingKey currSort = SortingKey.NONE;


    public void setDisplayMode(DisplayMode displayMode) {
        displayMode = displayMode;
    }

    public void setCurrSort(SortingKey currSort) {
        currSort = currSort;
    }
}
