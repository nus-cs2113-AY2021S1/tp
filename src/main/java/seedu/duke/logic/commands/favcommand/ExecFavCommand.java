package seedu.duke.logic.commands.favcommand;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.logic.commands.commons.Command;
import seedu.duke.logic.parser.ExecFavParser;
import seedu.duke.logic.parser.Parser;
import seedu.duke.model.favorite.Fav;
import seedu.duke.model.favorite.FavList;

public class ExecFavCommand extends Command {
    private int index;
    private ExecFavParser parser;
    private Fav fav;

    public ExecFavCommand(String input) throws CustomException {
        this.parser = new ExecFavParser(input);
        super.isValid = false;
    }

    private void getFav() throws CustomException {
        assert ! (parser == null) : "parser not declared";
        index = parser.getIndex();
        fav = FavList.getFav(index);
    }

    @Override
    public void executeCommand() throws CustomException {
        getFav();
        Parser parser = new Parser(fav.getCommand());
        System.out.println(fav.getCommand());
        try {
            boolean done = !parser.extractType();
        } catch (CustomException e) {
            FavList.deleteFav(index + 1);
            throw new CustomException(ExceptionType.LIST_INDEX_CORRUPTED);
        }

    }
}
