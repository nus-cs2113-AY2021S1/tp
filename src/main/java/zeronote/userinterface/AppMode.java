package zeronote.userinterface;

//@@author longngng

public enum AppMode {
    TIMETABLE,
    NOTEBOOK_SHELF,
    NOTEBOOK_BOOK,
    NOTEBOOK_SECTION,
    NOTEBOOK_PAGE;

    @Override
    public String toString() {
        switch (this) {
        case TIMETABLE:
            return "timetable";
        case NOTEBOOK_BOOK:
            return "notebook book";
        case NOTEBOOK_SECTION:
            return "notebook section";
        case NOTEBOOK_PAGE:
            return "notebook page";
        case NOTEBOOK_SHELF:
            //Fallthrough
        default:
            return "notebook";
        }
    }
}
