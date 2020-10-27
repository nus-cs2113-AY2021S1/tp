package seedu.duke.logic.commands.favcommand;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.favorite.FavList;
import seedu.duke.logic.commands.commons.Command;

public class DeleteFavCommand extends Command {
    public int index;

    public DeleteFavCommand(String index) throws CustomException {
        if (index.isBlank()) {
            throw new CustomException(ExceptionType.NO_INPUT);
        }
        int indexNum = Integer.parseInt(index.trim());
        this.index = indexNum;
    }

    @Override
    public void executeCommand() throws CustomException {
        int sizeOfArray = FavList.getSize();
        if (index <= 0 || index > sizeOfArray) {
            throw new CustomException(ExceptionType.INVALID_INDEX);
        } else {
            FavList.deleteFav(index);
        }
    }
}
