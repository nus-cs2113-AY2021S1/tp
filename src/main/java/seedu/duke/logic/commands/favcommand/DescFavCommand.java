package seedu.duke.logic.commands.favcommand;

import seedu.duke.exceptions.CustomException;
import seedu.duke.model.favorite.FavList;
import seedu.duke.logic.commands.commons.Command;
import seedu.duke.logic.parser.DescFavParser;
import seedu.duke.ui.Ui;

/**
 * Represents the command for changing the description of a particular favourite in the user's list of favourite
 * commands.
 */
public class DescFavCommand extends Command {

    private String rawMessage;
    private DescFavParser parser;

    public DescFavCommand(String rawMessage) {
        this.rawMessage = rawMessage;
        parser = new DescFavParser(rawMessage);
        isValid = false;
    }

    @Override
    public void executeCommand() throws CustomException {
        parser.parseInput();
        int index = parser.getIndex();
        String description = parser.getDescription();
        String oldDesc = FavList.changeDesc(index, description);
        String command = getCommand(index);
        Ui.printDescChangeMessage(command, oldDesc, description);
    }

    private String getCommand(int index) {
        assert index > 0 && index <= FavList.getList().size() : "Index out of bounds.";
        return FavList.getList().get(index - 1).getCommand();
    }
}
