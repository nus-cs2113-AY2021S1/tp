package command;

import cheatsheet.CheatSheet;
import command.stuba.CheatSheetListStub;
import command.stuba.DataFileDestroyerStub;
import command.stuba.UiStub;
import exception.CommandException;
import parser.CommandFlag;
import ui.Printer;


import org.junit.jupiter.api.Test;F
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    Printer printer;
    CheatSheetListStub cheatSheetList;
    DataFileDestroyerStub fileDestroyer;
    DeleteCommand command;
    UiStub ui;

    public DeleteCommandTest() {
        printer = new Printer();
        cheatSheetList = new CheatSheetListStub();
        fileDestroyer = new DataFileDestroyerStub();
        ui = new UiStub();

        command = new DeleteCommand(printer, cheatSheetList, fileDestroyer, ui);
    }

    @Test
    public void delete_matchedConfirm_deleted() {
        cheatSheetList.clear();
        cheatSheetList.add(new CheatSheet("A", "1", "2"));
        cheatSheetList.add(new CheatSheet("B", "2", "4"));
        cheatSheetList.add(new CheatSheet("C", "3", "6"));
        cheatSheetList.add(new CheatSheet("D", "4", "8"));
        assertEquals(4, cheatSheetList.getSize());

        ui.clearUserInput();
        ui.pushUserInput("YES");

        LinkedHashMap<CommandFlag, String> flagsToDescriptions = new LinkedHashMap<>();
        flagsToDescriptions.put(CommandFlag.NAME, "A");
        command.setFlagsToDescriptionsMap(flagsToDescriptions);

        try {
            command.execute();
        } catch (CommandException e) {
            fail(e.getMessage());
        }
        assertEquals(3, cheatSheetList.getSize());
    }

    @Test
    public void delete_matchedNotConfirmed_notDeleted(){
    }

    @Test
    public void delete_noneMatchedConfirm_continue() {
    }

    @Test
    public void delete_twoMatchedConfirm_continue() {
    }
}
