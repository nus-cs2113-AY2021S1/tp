package seedu.duke.ui;

public class FormatterStub extends Formatter {

    public static String encloseRow(String message) {
        if (message.equals("Default")) {
            return System.lineSeparator()
                    + "|| The following note has been archived: "
                    + message
                    + "                                                  ||"
                    + System.lineSeparator();
        }

        if (message.equals("")) {
            return System.lineSeparator()
                    + "|| The index you specified is out of range. Please check and specify a valid index value."
                    + "         ||"
                    + System.lineSeparator();
        }

        if (message.equals("nil")) {
            return System.lineSeparator()
                    + "|| This note does not exist in the notebook!"
                    + "                                                      ||"
                    + System.lineSeparator();
        }

        if (message.equals("random text")) {
            return System.lineSeparator()
                    + "|| The following note has been archived: "
                    + message
                    + "                                              ||"
                    + System.lineSeparator();
        }

        if (message.equals("The notebook is empty!")) {
            return System.lineSeparator()
                    + "|| "
                    + message
                    + "                                                                         ||"
                    + System.lineSeparator();
        }

        if (message.equals("Here are the list of notes:")) {
            return System.lineSeparator()
                    + "|| "
                    + message
                    + "                                                                    ||"
                    + System.lineSeparator();
        }

        if (message.equals("Here are the list of pinned notes:")) {
            return System.lineSeparator()
                    + "|| "
                    + message
                    + "                                                             ||"
                    + System.lineSeparator();
        }

        if (message.equals("Here are the list of unpinned notes:")
                || message.equals("Here are the list of archived notes:")) {
            return System.lineSeparator()
                    + "|| "
                    + message
                    + "                                                           ||"
                    + System.lineSeparator();
        }

        return "";
    }

    public static String encloseRow(String message, boolean flag) {
        if (message.equals("Default")) {
            return System.lineSeparator()
                    + "|| The following note has been unarchived: "
                    + message
                    + "                                                ||"
                    + System.lineSeparator();
        }

        if (message.equals("random text")) {
            return System.lineSeparator()
                    + "|| The following note has been unarchived: "
                    + message
                    + "                                            ||"
                    + System.lineSeparator();
        }

        return "";
    }
}
