package seedu.dietbook.ui;

/**
 * Represents a text user interface that is responsible for printing outputs.
 * A <code>UiOutput</code> object deals with user interaction by showing users the appropriate messages
 * after a valid command is executed or when an error occurs.
 */
public class UiOutput {

    private final UiHelper uiHelper;

    UiOutput() {
        uiHelper = new UiHelper();
    }

    /**
     * Prints the given message to the user.
     *
     * @param message The message to show the user.
     */
    void print(String message) {
        uiHelper.performAssertionsForStringInputs(message, "Message to print");

        System.out.println(uiHelper.getDivider() + UiHelper.LINE_SEPARATOR
                + uiHelper.trimString(message) + UiHelper.LINE_SEPARATOR
                + uiHelper.getDivider());

    }
}