package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;
import parser.CommandFlag;
import ui.Printer;

/**
 * Command to set a cheat sheet as favourite.
 */
public class FavouriteCommand extends FinderCommand {
    public static final String invoker = "/fav";

    /**
     * Constructor for the FavouriteCommand.
     * Required flag: either NAME or INDEX
     *
     * @param printer        The printer object to handle user interaction
     * @param cheatSheetList The current list of cheat sheets
     */
    public FavouriteCommand(Printer printer, CheatSheetList cheatSheetList) {
        super(printer, cheatSheetList);

        flagsToDescriptions.put(CommandFlag.NAME, null);
        flagsToDescriptions.put(CommandFlag.INDEX, null);
        flagsToDescriptions.put(CommandFlag.DELETE, null);
        alternativeFlags.add(CommandFlag.NAME);
        alternativeFlags.add(CommandFlag.INDEX);
    }

    /**
     * Add/remove a cheat sheet to/from favourites.
     * getCheatSheetFromNameOrIndex() from FinderCommand will retrieve the desired cheat sheet
     * and the isFavourite attribute will be set to true.
     * If the user specified the /d flag, the isFavourite attribute will be set to false.
     *
     * @throws CommandException if the input is not valid
     */
    @Override
    public void execute() throws CommandException {
        try {
            CheatSheet cheatSheetToFavourite = getCheatSheetFromNameOrIndex();
            boolean isAddFav = (flagsToDescriptions.get(CommandFlag.DELETE) == null);

            if (isAddFav == cheatSheetToFavourite.getIsFavourite()) {
                printer.printFavouriteStatusAlreadySet(cheatSheetToFavourite, isAddFav);
                return;
            }
            cheatSheetToFavourite.setFavourite(isAddFav);
            printer.printFavouritedCheatSheetMessage(cheatSheetToFavourite, isAddFav);
        } catch (NullPointerException | IndexOutOfBoundsException i) {
            throw new CommandException("Please enter a valid name or an index");
        } catch (NumberFormatException n) {
            throw new CommandException("Please enter a valid index");
        }
    }
}

