package seedu.duke.logic.commands.favcommand;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.favorite.FavList;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.parser.DescFavParser;
import seedu.duke.ui.Ui;

public class DescFavCommand extends Command {

    private String rawMessage;
    private DescFavParser parser;

    public DescFavCommand(String rawMessage) {
        this.rawMessage = rawMessage;
        parser = new DescFavParser(rawMessage);
    }

    @Override
    public void executeCommand() throws CustomException {
        parser.parseInput();
        int index = parser.getIndex();
        String description = parser.getDescription();
        String oldDesc = FavList.changeDesc(index, description);
        String command = FavList.getList().get(index-1).getCommand();
        Ui.printDescChangeMessage(command, oldDesc, description);
    }
}
