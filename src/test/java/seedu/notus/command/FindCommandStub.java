package seedu.notus.command;

//@@author R-Ramana

public class FindCommandStub extends FindCommand {

    /**
     * Constructs a FindCommand to find Notes in the Notebook given the keyword.
     *
     * @param keywords to look for in the Notebook.
     */
    public FindCommandStub(String keywords) {
        super(keywords);
    }

    public static String execute(String keywords) {
        if (keywords.equals("test")) {
            return keywords;
        }
        return null;
    }

}
