package seedu.dietbook.ui;

/**
 * Represents a text user interface that is responsible for printing outputs.
 * A <code>UiOutput</code> objects deals with user interaction by showing users the appropriate messages
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
        String divider =
                "__________________________________________________________________________________________"
                        + "___________________________________________";

        System.out.println(divider + uiHelper.LINE_SEPARATOR
                + uiHelper.trimString(message) + uiHelper.LINE_SEPARATOR
                + divider);

    }
}