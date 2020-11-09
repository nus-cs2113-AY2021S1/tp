package command.stuba;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;

import java.util.ArrayList;

public class CheatSheetListStub extends CheatSheetList {
    private ArrayList<CheatSheet> cheatSheetsStub = new ArrayList<>();

    @Override
    public void add(CheatSheet cheatSheet) {
        cheatSheetsStub.add(cheatSheet);
    }

    @Override
    public void clear() {
        cheatSheetsStub.clear();
    }

    @Override
    public void remove(String name) throws CommandException {
        int index = 0;
        for (CheatSheet cs : cheatSheetsStub) {
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
            cheatSheetsStub.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Please enter a valid index");
        }
    }

    @Override
    public int getSize() {
        return cheatSheetsStub.size();
    }
}
