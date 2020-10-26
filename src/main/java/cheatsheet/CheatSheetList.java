package cheatsheet;

import exception.CommandException;

import java.util.ArrayList;

public class CheatSheetList {
    private ArrayList<CheatSheet> cheatSheets = new ArrayList<>();

    public CheatSheetList() {
    }

    /**
     * Gets the number of cheat sheet currently in the CheatSheetList.
     *
     * @return size of cheatSheets
     */
    public int getSize() {
        return cheatSheets.size();
    }

    /**
     * Gets all the cheat sheets in the cheatSheetList.
     *
     * @return cheatSheets The current list of cheat sheets
     */
    public ArrayList<CheatSheet> getList() {
        return cheatSheets;
    }

    /**
     * Adds a new cheat sheet to the list.
     */
    public void add(CheatSheet cheatSheet) {
        cheatSheets.add(cheatSheet);
    }

    /**
     * Deletes a cheat sheet from the list.
     * Prints an error message if name is not found.
     *
     * @param name The name attribute of the desired cheat sheet
     * @throws CommandException when index < 0 or index >= cheatSheets.size()
     */
    public void remove(String name) throws CommandException {
        int index = 0;
        for (CheatSheet cs : cheatSheets) {
            if (cs.getName().equals(name)) {
                break;
            }
            index++;
        }
        try {
            remove(index);
        } catch (CommandException e) {
            throw new CommandException("Please enter a valid index");
        }
    }

    /**
     * Deletes a cheat sheet from the list.
     * Prints an error message if index is out of bound.
     *
     * @param index The index of the desired cheat sheet
     * @throws CommandException when index < 0 or index >= cheatSheets.size()
     */
    public void remove(int index) throws CommandException {
        try {
            cheatSheets.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Please enter a valid index");
        }
    }

    /**
     * Removes all cheat sheet in the list.
     */
    public void clear() {
        cheatSheets.clear();
    }

    /**
     * Retrieves a cheat sheet from the list using its index.
     *
     * @param index The index of desired cheat sheet
     * @return size of cheatSheets
     * @throws CommandException when index < 0 or index >= cheatSheets.size()
     */
    public CheatSheet get(int index) throws CommandException {
        CheatSheet cheatSheet;
        try {
            cheatSheet = cheatSheets.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Please enter a valid index");
        }
        return cheatSheet;
    }

    /**
     * Retrieves a cheat sheet from the list using its name.
     *
     * @param name The name attribute of desired cheat sheet
     * @return size of cheatSheets
     * @throws CommandException when name cannot be found inside the list
     */
    public CheatSheet get(String name) throws CommandException {
        int index = 0;
        for (CheatSheet cs : cheatSheets) {
            if (cs.getName().equals(name)) {
                break;
            }
            index++;
        }
        CheatSheet cheatSheet;
        try {
            cheatSheet = get(index + 1);
        } catch (CommandException e) {
            throw new CommandException("Please enter a valid name");
        }
        return cheatSheet;
    }

    /**
     * Checks if a cheat sheet exists or not.
     *
     * @param name The name attribute of desired cheat sheet
     * @return true if the cheat sheet exists, false otherwise
     */
    public boolean exists(String name) {
        for (CheatSheet cs : cheatSheets) {
            if (cs.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
