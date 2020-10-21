package seedu.duke.logic.commands;

import seedu.duke.exceptions.CustomException;

import static seedu.duke.ui.Ui.listFav;

public class ListFavCommand extends Command {
    @Override
    public void executeCommand() throws CustomException {
        listFav();
    }
}
