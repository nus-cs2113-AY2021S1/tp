package seedu.notus.ui;

import java.text.Normalizer;

//@@author R-Ramana
public class FormatterStub extends Formatter {

    public static String formatString(String message) {
        if (message.equals("Default")) {
            return Formatter.formatString("The following note has been archived: " + message);
        }

        if (message.equals("")) {
            return Formatter.formatString("The index you specified is out of range. "
                    + "Please check and specify a valid index value.");
        }

        if (message.equals("nil")) {
            return Formatter.formatString("This note does not exist in the notebook!");
        }

        if (message.equals("random text")) {
            return Formatter.formatString("The following note has been archived: " + message);
        }

        if (message.equals("The notebook is empty!")) {
            return Formatter.formatString(message);
        }

        if (message.equals("Here are the list of notes:")) {
            return Formatter.formatString(message);
        }

        if (message.equals("Here are the list of pinned notes:")) {
            return Formatter.formatString(message);
        }

        if (message.equals("Here are the list of unpinned notes:")
                || message.equals("Here are the list of archived notes:")) {
            return Formatter.formatString(message);
        }

        if (message.contains("Your tags return no result.")) {
            return Formatter.formatString(message);
        }

        if (message.contains("There are no")) {
            return Formatter.formatString(message);
        }

        if (message.equals("test")) {
            return Formatter.formatString("Here are the matching notes in your list:");
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
