package seedu.notus.data.notebook;

public class NotebookStub extends Notebook {

    public static String getArchiveNoteTitle(int index) {
        if (index == 1) {
            return "Default";
        }

        if (index == 50) {
            return "";
        }

        return null;
    }

    public static String getArchiveNoteTitle(String title) {
        if (title == "rando") {
            return "nil";
        }

        if (title == "random text") {
            return title;
        }

        return null;
    }

    public static String getUnarchiveNoteTitle(int index) {
        if (index == 1) {
            return "Default";
        }

        if (index == 50) {
            return "";
        }

        return null;
    }

    public static String getUnarchiveNoteTitle(String title) {
        if (title == "rando") {
            return "nil";
        }

        if (title == "random text") {
            return title;
        }

        return null;
    }
}
