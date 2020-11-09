package command;

import cheatsheet.CheatSheet;
import command.stuba.CheatSheetListStub;
import command.stuba.DataFileDestroyerStub;
import command.stuba.DataFileReaderStub;
import command.stuba.UiStub;
import ui.Printer;

import org.junit.jupiter.api.Test;

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
        fileDestroyer = new DataFileDestroyerStub(cheatSheetList);
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

        ui.clearUserInput();
        ui.pushUserInput("YES");
        command.execute();
        assertEquals(0, cheatSheetList.getSize());
    }

    @Test
    public void clear_noConfirmFilledList_filled() {
        cheatSheetList.clear();
        cheatSheetList.add(new CheatSheet("Abn", "Java", "While"));
        cheatSheetList.add(new CheatSheet("er", "C", "If else"));
        assertEquals(2, cheatSheetList.getSize());

        ui.clearUserInput();
        ui.pushUserInput("No");
        command.execute();
        assertEquals(2, cheatSheetList.getSize());
    }

    @Test
    public void clear_confirmEmptyList_empty() {
        cheatSheetList.clear();
        assertEquals(0, cheatSheetList.getSize());

        ui.clearUserInput();
        ui.pushUserInput("Yes");
        command.execute();
        assertEquals(0, cheatSheetList.getSize());
    }
}
