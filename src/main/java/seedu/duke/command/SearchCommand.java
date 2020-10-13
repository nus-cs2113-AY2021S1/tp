package seedu.duke.command;

public class SearchCommand extends Command {

    private String searchKey;

    public SearchCommand(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchKey() {
        return searchKey;
    }

}
