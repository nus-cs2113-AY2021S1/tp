package command;

import cheatsheet.CheatSheet;
import command.stuba.CheatSheetListStub;
import command.stuba.DataFileDestroyerStub;
import command.stuba.DataFileReaderStub;
import command.stuba.UiStub;
import exception.CommandException;
import ui.Printer;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClearCommandTest {
    Printer printer;
    CheatSheetListStub cheatSheetList;
    DataFileDestroyerStub fileDestroyer;
    DataFileReaderStub fileReader;
    ClearCommand command;
    UiStub ui;

    public ClearCommandTest() {
        printer = new Printer();
        cheatSheetList = new CheatSheetListStub();
        fileDestroyer = new DataFileDestroyerStub();
        fileReader = new DataFileReaderStub();
        ui = new UiStub();

        command = new ClearCommand(printer, cheatSheetList, fileDestroyer, fileReader, ui);
    }

    @Test
    public void clear_confirmFilledList_empty() {
        cheatSheetList.clear();
        cheatSheetList.add(new CheatSheet("A", "1", "2"));
        cheatSheetList.add(new CheatSheet("B", "2", "4"));
        cheatSheetList.add(new CheatSheet("C", "3", "6"));
        cheatSheetList.add(new CheatSheet("D", "4", "8"));

        assertEquals(4, cheatSheetList.getSize());

        command.execute();
        assertEquals(0, cheatSheetList.getSize());
    }

    @Test
    public void clear_noConfirmFilledList_filled() {

    }

    @Test
    public void clear_confirmEmptyList_empty() {

    }
}
