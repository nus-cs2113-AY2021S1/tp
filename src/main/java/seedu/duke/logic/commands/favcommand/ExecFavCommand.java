package seedu.duke.logic.commands.favcommand;

import seedu.duke.exceptions.CustomException;
import seedu.duke.favorite.Fav;
import seedu.duke.favorite.FavList;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.parser.Parser;

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
