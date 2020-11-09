package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;
import parser.CommandFlag;
import ui.Printer;

/**
 * Base class for commands that searches through cheatSheetList for matching content.
 */
public abstract class FinderCommand extends Command {

    /**
     * Constructor of FinderCommand.
     *
     * @param printer The printer object handles the user interaction.
     * @param cheatSheetList The cheatSheetList object holds the current list of cheatsheets
     */
    public FinderCommand(Printer printer, CheatSheetList cheatSheetList) {
        super(printer);
        this.cheatSheetList = cheatSheetList;
    }

    /**
     * Searches through cheatSheetList for cheatsheets with name or index that matched what the user inputs.
     *
     * @return Cheatsheet that matched what the user inputs.
     * @throws CommandException Thrown if no matching cheatsheet is found
     */
    protected CheatSheet getCheatSheetFromNameOrIndex() throws CommandException {
        CheatSheet desiredCheatSheet = null;
        String name = flagsToDescriptions.get(CommandFlag.NAME);
        String index = flagsToDescriptions.get(CommandFlag.INDEX);
        if (name != null && index == null) {
            desiredCheatSheet = cheatSheetList.get(name.trim());
        } else if (index != null && name == null) {
            desiredCheatSheet = cheatSheetList.get(Integer.parseInt(index));
        } else if (index != null) {
            if (cheatSheetList.get(name).equals(cheatSheetList.get(Integer.parseInt(index)))) {
                desiredCheatSheet = cheatSheetList.get(name.trim());
            }
        }

        if (desiredCheatSheet == null) {
            if (name != null && index == null) {
                throw new CommandException("Please enter a valid name");
            } else if (name == null && index != null) {
                throw new CommandException("Please enter a valid index");
            } else if (name != null) {
                throw new CommandException("No cheat sheet matches the name and index entered");
            }
        }
        return desiredCheatSheet;
    }

    /**
     * Checks if a cheatsheet matches with the name, subject or keyword the user inputs.
     *
     * @param cs The target cheatsheet
     * @param name The name of cheatsheet entered by user
     * @param subject The subject of cheatsheet entered by user
     * @param keyword The keyword of cheatsheet entered by user
     * @return A boolean on whether the cheatsheet matched what the user inputs
     * @throws CommandException Thrown if name, subject and keyword are null
     */
    public boolean checkCheatSheetMatchesWithFields(CheatSheet cs, String name, String subject,
                                                    String keyword) throws CommandException {
        if (subject != null && keyword == null && name == null) {
            return cs.getSubject().contains(subject);
        } else if (subject == null && keyword != null && name == null) {
            return cs.getDetails().contains(keyword);
        } else if (subject != null && keyword != null && name == null) {
            return cs.getSubject().contains(subject) && cs.getDetails().contains(keyword);
        } else if (subject == null && keyword == null && name != null) {
            return cs.getName().contains(name);
        } else if (subject != null && keyword == null) {
            return cs.getSubject().contains(subject) && cs.getName().contains(name);
        } else if (subject == null && keyword != null) {
            return cs.getDetails().contains(keyword) && cs.getName().contains(name);
        } else if (subject != null) {
            return cs.getSubject().contains(subject) && cs.getDetails().contains(keyword)
                && cs.getName().contains(name);
        } else {
            throw new CommandException("Please enter at least an argument");
        }
    }
}

