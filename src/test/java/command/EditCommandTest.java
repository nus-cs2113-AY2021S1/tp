//package command;
//
//import cheatsheet.CheatSheet;
//import command.stuba.CheatSheetListStub;
//import command.stuba.DataFileDestroyerStub;
//import command.stuba.EditorStub;
//import exception.CommandException;
//import parser.CommandFlag;
//import ui.Printer;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//import java.util.LinkedHashMap;
//
//public class EditCommandTest {
//    Printer printer;
//    CheatSheetListStub cheatSheetList;
//    DataFileDestroyerStub fileDestroyer;
//    EditCommand command;
//    EditorStub editor;
//
//    public EditCommandTest() {
//        printer = new Printer();
//        cheatSheetList = new CheatSheetListStub();
//        fileDestroyer = new DataFileDestroyerStub(cheatSheetList);
//        editor = new EditorStub();
//
//        command = new EditCommand(printer, cheatSheetList, editor);
//    }
//
//    @Test
//    public void edited_matched_edited() {
//        cheatSheetList.clear();
//        cheatSheetList.add(new CheatSheet("A", "1", "2"));
//        cheatSheetList.add(new CheatSheet("B", "2", "4"));
//        cheatSheetList.add(new CheatSheet("C", "3", "6"));
//        cheatSheetList.add(new CheatSheet("D", "4", "8"));
//        assertEquals(4, cheatSheetList.getSize());
//
//        LinkedHashMap<CommandFlag, String> flagsToDescriptions = new LinkedHashMap<>();
//        flagsToDescriptions.put(CommandFlag.NAME, "A");
//        command.setFlagsToDescriptionsMap(flagsToDescriptions);
//
//        try {
//            command.execute();
//        } catch (CommandException e) {
//            fail(e.getMessage());
//        }
//        assertEquals(4, cheatSheetList.getSize());
//    }
//
//    @Test
//    public void edited_outOfBoundIndex_error() {
//        cheatSheetList.clear();
//        cheatSheetList.add(new CheatSheet("A", "1", "2"));
//        cheatSheetList.add(new CheatSheet("B", "2", "4"));
//
//        LinkedHashMap<CommandFlag, String> flagsToDescriptions = new LinkedHashMap<>();
//        flagsToDescriptions.put(CommandFlag.INDEX, "3");
//        command.setFlagsToDescriptionsMap(flagsToDescriptions);
//
//        try {
//            command.execute();
//        } catch (CommandException e) {
//            assertEquals("Please enter a valid index", e.getMessage());
//            return;
//        }
//        fail("Did not throw error when editing out of bounds");
//    }
//
//    @Test
//    public void edited_twoMatchedConfirm_continue() {
//        cheatSheetList.clear();
//        cheatSheetList.add(new CheatSheet("A", "1", "2"));
//        cheatSheetList.add(new CheatSheet("B", "2", "4"));
//        cheatSheetList.add(new CheatSheet("D", "4", "8"));
//
//        LinkedHashMap<CommandFlag, String> flagsToDescriptions = new LinkedHashMap<>();
//        flagsToDescriptions.put(CommandFlag.NAME, "A");
//        flagsToDescriptions.put(CommandFlag.INDEX, "2");
//        command.setFlagsToDescriptionsMap(flagsToDescriptions);
//
//        try {
//            command.execute();
//        } catch (CommandException e) {
//            assertEquals("No cheat sheet matches the name and index entered", e.getMessage());
//        }
//    }
//}
