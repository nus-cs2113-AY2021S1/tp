package seedu.duke.logic.commands.favcommand;

import seedu.duke.exceptions.CustomException;
import seedu.duke.logic.commands.commons.Command;
import seedu.duke.logic.parser.Parser;
import seedu.duke.model.favorite.Fav;
import seedu.duke.model.favorite.FavList;

public class ExecFavCommand extends Command {
    private int index;

    public ExecFavCommand(String input) {
        this.index = Integer.parseInt(input) - 1;
    }

    @Override
    public void executeCommand() throws CustomException {
        Fav f = FavList.getFav(index);
        Parser parser = new Parser(f.getCommand());
        System.out.println(f.getCommand());
        boolean done = !parser.extractType();
    }
}
