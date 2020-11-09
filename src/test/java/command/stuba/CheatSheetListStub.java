package command.stuba;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;

import java.util.ArrayList;

public class CheatSheetListStub extends CheatSheetList {
    private ArrayList<CheatSheet> cheatSheets = new ArrayList<>();

    @Override
    public void add(CheatSheet cheatSheet) {
        cheatSheets.add(cheatSheet);
    }

    @Override
    public void clear() {
        cheatSheets.clear();
    }

    @Override
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

    @Override
    public void remove(int index) throws CommandException {
        try {
            cheatSheets.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Please enter a valid index");
        }
    }

    public CheatSheet get(int index) throws CommandException {
        CheatSheet cheatSheet;
        try {
            cheatSheet = cheatSheets.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Please enter a valid index");
        }
        return cheatSheet;
    }

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

    @Override
    public int getSize() {
        return cheatSheets.size();
    }
}
