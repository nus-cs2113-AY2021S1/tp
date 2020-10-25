package seedu.duke.logic.commands.favcommand;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.favorite.Fav;
import seedu.duke.favorite.FavList;
import seedu.duke.logic.commands.Command;

public class DeleteFavCommand extends Command {
    public int index;

    public DeleteFavCommand(String index) throws CustomException {
        if (index.isBlank()) {
            throw new CustomException(ExceptionType.EMPTY_INDEX);
        }
        int indexNum = Integer.parseInt(index.trim());
        this.index = indexNum;
    }

    @Override
    public void executeCommand() throws CustomException {
        int sizeOfArray = FavList.getSize();
        if (index <= 0 || index > sizeOfArray) {
            throw new CustomException(ExceptionType.INDEX_NOT_IN_RANGE);
        } else {
            FavList.deleteFav(index);
        }
    }
}
